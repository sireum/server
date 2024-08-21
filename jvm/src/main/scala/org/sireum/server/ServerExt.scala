/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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
import org.sireum.logika.{Smt2, Smt2Config, Smt2Invoke}
import org.sireum.server.service.{AnalysisService, Service}

import java.io.{BufferedWriter, ByteArrayOutputStream, InputStream, OutputStreamWriter}
import java.net.{InetAddress, ServerSocket, Socket}
import java.util.concurrent.LinkedBlockingQueue

object ServerExt {
  val pauseTime: Long = 200
  val platform: String = Os.kind match {
    case Os.Kind.Win => "win"
    case Os.Kind.Linux => "linux"
    case Os.Kind.Mac => "mac"
    case _ => "unsupported"
  }
  var prefix: Predef.String = ""
  var serverSocket: ServerSocket = null
  var socket: Socket = null
  var ir: InputStream = null
  var ow: BufferedWriter = null
  val queue: LinkedBlockingQueue[server.protocol.Response] = new LinkedBlockingQueue
  var thread: Thread = null

  def init(isMsgPack: B, serverAPI: ServerAPI): Unit = {
    if (serverSocket != null) {
      return
    }
    serverSocket = new ServerSocket(0, 0, InetAddress.getLoopbackAddress)
    val serverTxt = serverAPI.sireumHome / ".server.txt"
    val current = Class.forName("java.lang.ProcessHandle").getMethod("current").invoke(null)
    val pid = Class.forName("java.lang.ProcessHandle").getMethod("pid").invoke(current)
    serverTxt.writeOver(s"$pid:${serverSocket.getLocalPort}")
    serverTxt.removeOnExit()
    socket = serverSocket.accept()
    ir = socket.getInputStream
    ow = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream, "UTF-8"))
    thread = new Thread {
      override def run(): Unit = {
        while (true) {
          val r = queue.take()
          if (isMsgPack) {
            val respString = protocol.CustomMessagePack.fromResponse(r)
            r match {
              case _: protocol.Status.Response =>
              case _ => serverAPI.log(F, respString)
            }
          } else {
            val respString = protocol.JSON.fromResponse(r, T)
            r match {
              case _: protocol.Status.Response =>
              case _ => serverAPI.log(F, respString)
            }
            writeDirectOutput(respString.value)
          }
        }
      }
    }
    thread.setDaemon(T)
    thread.start()
  }

  def destroy(): Unit = {
    queue.clear()
    if (socket != null) try socket.close() catch { case _: Throwable => }
    if (serverSocket != null) try serverSocket.close() catch { case _: Throwable => }
    if (ir != null) try ir.close() catch { case _: Throwable => }
    if (ow != null) try ow.close() catch { case _: Throwable => }
    ow = null
    ir = null
    serverSocket = null
  }

  def readInput(): String = try {
    val in = if (serverSocket != null && !serverSocket.isClosed && ir != null) ir else return ""
    val prefixSize = prefix.length
    val baos = new ByteArrayOutputStream
    var b = in.read()
    var i = 0
    while (b >= 0) {
      if (b == '\n') {
        return new Predef.String(baos.toByteArray, "UTF-8")
      } else if (i < prefixSize) {
        if (prefix.charAt(i) == b) {
          baos.write(b)
        } else {
          while (b >= 0 && b != '\n') {
            baos.write(b)
            b = in.read()
          }
          return new Predef.String(baos.toByteArray, "UTF-8")
        }
      } else {
        if (i == prefixSize) {
          baos.reset()
        }
        baos.write(b)
      }
      i = i + 1
      b = in.read
    }
    return new Predef.String(baos.toByteArray, "UTF-8")
  } catch {
    case _: Throwable => throw new InterruptedException
  }

  def writeResponse(r: server.protocol.Response): Unit = {
    queue.add(r)
  }

  def writeDirectOutput(s: Predef.String): Unit = {
    if (ow != null) {
      if (prefix.nonEmpty) ow.write(prefix)
      ow.write(s)
      ow.write('\n')
      ow.flush()
      return
    }
    if (prefix.nonEmpty) System.out.print(prefix)
    System.out.println(s)
    System.out.flush()
  }

  def pause(): Unit = Thread.sleep(pauseTime)

  def analysisService(sireumHome: Os.Path, numOfThreads: Z): Service = {
    val smt2Configs =
      if(Smt2Invoke.isSupportedPlatform)
        Smt2.parseConfigs(Smt2Invoke.nameExePathMap(sireumHome), F, Smt2.defaultValidOpts).left ++
          Smt2.parseConfigs(Smt2Invoke.nameExePathMap(sireumHome), T, Smt2.defaultSatOpts).left
      else
        ISZ[Smt2Config]()
    AnalysisService._defaultConfig = AnalysisService.defaultConfig(smt2Configs = smt2Configs)
    AnalysisService.setConfig(AnalysisService._infoFlow, AnalysisService._defaultConfig)
    new service.AnalysisService(numOfThreads)
  }

  def totalMemory: Z = _root_.java.lang.Runtime.getRuntime.totalMemory

  def freeMemory: Z = _root_.java.lang.Runtime.getRuntime.freeMemory

  def gc(): Unit = System.gc()

  def timeStamp(isRequest: B): String =
    _root_.java.time.format.DateTimeFormatter.ofPattern(s"HHmmss ${if (isRequest) "<"else ">"} ").format(_root_.java.time.LocalDateTime.now)

  def log(file: Os.Path, content: String): Unit = synchronized {
    file.writeAppend(content)
  }

  def emptyCache(uriOpt: Option[String]): logika.Logika.Cache = AnalysisService.createCache(uriOpt)
}
