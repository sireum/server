// #Sireum
/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

  val apeMessage: String = "Please configure Linux binfmt, see: https://sireum.org/getting-started/#using-notes"

  def checkLinuxAPE(sireumHome: Os.Path): B = {
    return Os.isLinux ___>:
      Os.proc(ISZ((sireumHome / "bin" / "z3" / "bin" / "z3.com").string, "-h")).run().ok
  }

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
    Server.Ext.init(isMsgPack, server.serverAPI)
    if (!checkLinuxAPE(sireumHome)) {
      server.serverAPI.sendRespond(
        org.sireum.server.protocol.Report(ISZ(), message.Message(message.Level.Error, None(), "Sireum", apeMessage))
      )
    }
    return server.run()
  }

  @ext("ServerExt") object Ext {
    def init(isMsgPack: B, serverAPI: ServerAPI): Unit = $

    def destroy(): Unit = $

    def readInput(): String = $

    def writeResponse(r: org.sireum.server.protocol.Response): Unit = $

    def pause(): Unit = $

    def analysisService(sireumHome: Os.Path, numOfThreads: Z): Service = $

    def totalMemory: Z = $

    def freeMemory: Z = $

    def gc(): Unit = $

    def timeStamp(isRequest: B): String = $

    def log(file: Os.Path, content: String): Unit = $

    def emptyCache(uriOpt: Option[String]): logika.Logika.Cache = $
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

  def sendRespond(resp: org.sireum.server.protocol.Response): Unit

  def totalMemory: Z = {
    return Server.Ext.totalMemory
  }

  def freeMemory: Z = {
    return Server.Ext.freeMemory
  }

  def reportStatus(): Unit = {
    sendRespond(org.sireum.server.protocol.Status.Response(totalMemory, freeMemory))
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
  def sendRespond(resp: org.sireum.server.protocol.Response): Unit = {
    Server.Ext.writeResponse(resp)
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
  def sendRespond(resp: org.sireum.server.protocol.Response): Unit = {
    Server.Ext.writeResponse(resp)
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
    serverAPI.log(F, s"Server v$version")
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
    Server.Ext.destroy()
    serverAPI.log(F, s"Shutdown ...")
    return 0
  }

  def serveLoop(): Unit = {
    while (serve()) {}
  }

  def serve(): B = {
    val req: org.sireum.server.protocol.Request = retrieveRequest() match {
      case Some(r) => r
      case _ => return T
    }
    req match {
      case _: org.sireum.server.protocol.Terminate => return F
      case _: org.sireum.server.protocol.Version.Request => handleVersion()
      case _: org.sireum.server.protocol.Status.Request => handleStatus()
      case _: org.sireum.server.protocol.Cancel =>
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
          serverAPI.sendRespond(org.sireum.server.protocol.Report(req.id, message.Message(message.Level.InternalError, None(),
            "server", s"Unimplemented request handler for: $req")))
        }
    }
    return T
  }

  def handleVersion(): Unit = {
    serverAPI.sendRespond(org.sireum.server.protocol.Version.Response(version))
  }

  def handleStatus(): Unit = {
    serverAPI.sendRespond(org.sireum.server.protocol.Status.Response(Server.Ext.totalMemory, Server.Ext.freeMemory))
    Server.Ext.gc()
  }

  def retrieveRequest(): Option[org.sireum.server.protocol.Request] = {
    val input = Server.Ext.readInput()
    if (input.size == 0) {
      return None()
    }
    var shouldLog: B = F
    //println(s"'$input'")
    val r: Option[org.sireum.server.protocol.Request] = if (isMsgPack) {
      CustomMessagePack.toRequest(input) match {
        case Either.Left(req) =>
          req match {
            case _: org.sireum.server.protocol.Status.Request =>
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
            case _: org.sireum.server.protocol.Status.Request =>
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
    serverAPI.sendRespond(org.sireum.server.protocol.Report(id, message.Message(message.Level.Error, None(), "Server", s"$msg: '$input'")))
  }
}
