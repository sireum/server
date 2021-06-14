// #Sireum
/*
 Copyright (c) 2017-2021, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sireum.server

import org.sireum._
import org.sireum.server.protocol.{CustomMessagePack, JSON}
import org.sireum.server.service.{Service, SlangService}

object Server {

  def run(version: String, isMsgPack: B, numOfLogikaWorkers: Z): Z = {
    val server = Server(
      version,
      isMsgPack,
      MSZ(
        Ext.logikaService(numOfLogikaWorkers),
        SlangService()
      )
    )
    return server.run()
  }

  @ext("ServerExt") object Ext {
    def readInput(): String = $
    def writeOutput(s: String): Unit = $
    def pause(): Unit = $
    def logikaService(numOfThreads: Z): Service = $
  }
}

@datatype trait ServerAPI {
  def sendRespond(resp: protocol.Response): Unit
}

@datatype class JsonServerAPI extends ServerAPI {
  def sendRespond(resp: protocol.Response): Unit = {
    Server.Ext.writeOutput(protocol.JSON.fromResponse(resp, T))
  }
}

@datatype class MsgPackServerAPI extends ServerAPI {
  def sendRespond(resp: protocol.Response): Unit = {
    Server.Ext.writeOutput(protocol.CustomMessagePack.fromResponse(resp))
  }
}

@datatype class Server(version: String, isMsgPack: B, services: MSZ[Service]) {
  val serverAPI: ServerAPI = if (isMsgPack) MsgPackServerAPI() else JsonServerAPI()
  def run(): Z = {
    for (i <- services.indices) {
      services(i).init(serverAPI)
    }
    extension.Cancel.cancellable(serveLoop _)
    for (i <- services.indices) {
      services(i).finalise()
    }
    return 0
  }

  def serveLoop(): Unit = {
    while (serve()) {}
  }

  def serve(): B = {
    val req: protocol.Request = retrieveRequest() match {
      case Some(r) => r
      case _ => return T
    }
    req match {
      case _: protocol.Terminate => return F
      case _: protocol.Version.Request => handleVersion()
      case _: protocol.Cancel =>
        var found = F
        val maxTries = 3
        var tries = 0
        while (!found && tries < maxTries) {
          for (service <- services if !found && service.canHandle(req)) {
            found = T
            service.handle(serverAPI, req)
          }
          if (!found && tries < maxTries) {
            tries = tries + 1
            if (tries < maxTries) {
              Server.Ext.pause()
            }
          }
        }
      case _ =>
        var found = F
        for (service <- services if !found && service.canHandle(req)) {
          found = T
          service.handle(serverAPI, req)
        }
        if (!found) {
          serverAPI.sendRespond(protocol.Report(req.id, message.Message(message.Level.InternalError, None(),
            "server", s"Unimplemented request handler for: $req")))
        }
    }
    return T
  }

  def handleVersion(): Unit = {
    serverAPI.sendRespond(protocol.Version.Response(version))
  }

  def retrieveRequest(): Option[protocol.Request] = {
    val input = Server.Ext.readInput()
    //println(s"'$input'")
    if (isMsgPack) {
      CustomMessagePack.toRequest(input) match {
        case Either.Left(r) => return Some(r)
        case Either.Right(err) =>
          reportError(ISZ(), err, input)
          return None()
      }
    } else {
      JSON.toRequest(input) match {
        case Either.Left(r) => return Some(r)
        case Either.Right(err) =>
          reportError(ISZ(), err.message, input)
          return None()
      }
    }
  }

  def reportError(id: ISZ[String], msg: String, input: String): Unit = {
    serverAPI.sendRespond(protocol.Report(id, message.Message(message.Level.Error, None(), "Server", s"$msg: '$input'")))
  }
}
