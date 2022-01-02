// #Sireum
/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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

  val maxLogFileSize: Z = 1024 * 1024
  val maxLogLineSize: Z = 256 - Server.Ext.timeStamp(T).size - Os.lineSep.size

  def run(version: String, isMsgPack: B, numOfLogikaWorkers: Z, cacheInput: B, cacheType: B, log: B, verbose: B,
          javaHome: Os.Path, scalaHome: Os.Path, sireumHome: Os.Path, defaultVersions: ISZ[(String, String)]): Z = {
    val server = Server(
      version,
      isMsgPack,
      cacheInput,
      cacheType,
      log,
      verbose,
      MSZ(
        Ext.analysisService(sireumHome, numOfLogikaWorkers),
        SlangService()
      ),
      javaHome,
      scalaHome,
      sireumHome,
      defaultVersions
    )
    return server.run()
  }

  @ext("ServerExt") object Ext {
    def readInput(): String = $

    def writeOutput(s: String): Unit = $

    def pause(): Unit = $

    def analysisService(sireumHome: Os.Path, numOfThreads: Z): Service = $

    def totalMemory: Z = $

    def freeMemory: Z = $

    def gc(): Unit = $

    def timeStamp(isRequest: B): String = $

    def log(file: Os.Path, content: String): Unit = $
  }
}

@datatype trait ServerAPI {
  @memoize def logFile: Os.Path = {
    return sireumHome / ".server.log"
  }

  def cacheInput: B

  def cacheType: B

  def isLogEnabled: B

  def isVerbose: B

  def sireumHome: Os.Path

  def scalaHome: Os.Path

  def javaHome: Os.Path

  def defaultVersions: ISZ[(String, String)]

  def sendRespond(resp: protocol.Response): Unit

  def totalMemory: Z = {
    return Server.Ext.totalMemory
  }

  def freeMemory: Z = {
    return Server.Ext.freeMemory
  }

  def reportStatus(): Unit = {
    sendRespond(protocol.Status.Response(totalMemory, freeMemory))
  }

  def log(isRequest: B, text: String): Unit = {
    if (!isLogEnabled) {
      return
    }
    if (logFile.size > Server.maxLogFileSize) {
      Server.Ext.log(logFile, "")
    }
    val textOps = ops.StringOps(text)
    val content: String = if (text.size > Server.maxLogLineSize) textOps.substring(0, Server.maxLogLineSize) else text
    Server.Ext.log(logFile, s"${Server.Ext.timeStamp(isRequest)}$content${Os.lineSep}")
  }
}

@datatype class JsonServerAPI(val cacheInput: B,
                              val cacheType: B,
                              val isLogEnabled: B,
                              val isVerbose: B,
                              val javaHome: Os.Path,
                              val scalaHome: Os.Path,
                              val sireumHome: Os.Path,
                              val defaultVersions: ISZ[(String, String)]) extends ServerAPI {
  def sendRespond(resp: protocol.Response): Unit = {
    val respString = protocol.JSON.fromResponse(resp, T)
    resp match {
      case _: protocol.Status.Response =>
      case _ => log(F, respString)
    }
    Server.Ext.writeOutput(respString)
  }
}

@datatype class MsgPackServerAPI(val cacheInput: B,
                                 val cacheType: B,
                                 val isLogEnabled: B,
                                 val isVerbose: B,
                                 val javaHome: Os.Path,
                                 val scalaHome: Os.Path,
                                 val sireumHome: Os.Path,
                                 val defaultVersions: ISZ[(String, String)]) extends ServerAPI {
  def sendRespond(resp: protocol.Response): Unit = {
    val respString = protocol.CustomMessagePack.fromResponse(resp)
    resp match {
      case _: protocol.Status.Response =>
      case _ => log(F, respString)
    }
    Server.Ext.writeOutput(respString)
  }
}

@record class Server(val version: String,
                     val isMsgPack: B,
                     val cacheInput: B,
                     val cacheType: B,
                     val log: B,
                     val verbose: B,
                     val services: MSZ[Service],
                     val javaHome: Os.Path,
                     val scalaHome: Os.Path,
                     val sireumHome: Os.Path,
                     val defaultVersions: ISZ[(String, String)]) {
  val serverAPI: ServerAPI =
    if (isMsgPack) MsgPackServerAPI(cacheInput, cacheType, log, verbose, javaHome, scalaHome, sireumHome, defaultVersions)
    else JsonServerAPI(cacheInput, cacheType, log, verbose, javaHome, scalaHome, sireumHome, defaultVersions)

  def run(): Z = {
    serverAPI.logFile.writeOver("")
    serverAPI.log(F, s"Initializing runtime library ...")
    val (_, _) = lang.FrontEnd.checkedLibraryReporter
    for (i <- services.indices) {
      serverAPI.log(F, s"Initializing service: ${services(i).id} ...")
      services(i).init(serverAPI)
    }
    serverAPI.log(F, s"Serving ...")
    extension.Cancel.cancellable(serveLoop _)
    for (i <- services.indices) {
      serverAPI.log(F, s"Finalizing service: ${services(i).id} ...")
      services(i).finalise()
    }
    serverAPI.log(F, s"Shutdown ...")
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
      case _: protocol.Status.Request => handleStatus()
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

  def handleStatus(): Unit = {
    serverAPI.sendRespond(protocol.Status.Response(Server.Ext.totalMemory, Server.Ext.freeMemory))
    Server.Ext.gc()
  }

  def retrieveRequest(): Option[protocol.Request] = {
    val input = Server.Ext.readInput()
    var shouldLog: B = F
    //println(s"'$input'")
    val r: Option[protocol.Request] = if (isMsgPack) {
      CustomMessagePack.toRequest(input) match {
        case Either.Left(req) =>
          req match {
            case _: protocol.Status.Request =>
            case _ => shouldLog = T
          }
          Some(req)
        case Either.Right(err) =>
          shouldLog = T
          reportError(ISZ(), err, input)
          None()
      }
    } else {
      JSON.toRequest(input) match {
        case Either.Left(req) =>
          req match {
            case _: protocol.Status.Request =>
            case _ => shouldLog = T
          }
          Some(req)
        case Either.Right(err) =>
          shouldLog = T
          reportError(ISZ(), err.message, input)
          None()
      }
    }
    if (shouldLog) {
      serverAPI.log(T, input)
    }
    return r
  }

  def reportError(id: ISZ[String], msg: String, input: String): Unit = {
    serverAPI.sendRespond(protocol.Report(id, message.Message(message.Level.Error, None(), "Server", s"$msg: '$input'")))
  }
}
