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

import java.io.ByteArrayOutputStream

object ServerExt {
  val pauseTime: Long = 200
  val platform: String = Os.kind match {
    case Os.Kind.Win => "win"
    case Os.Kind.Linux => "linux"
    case Os.Kind.Mac => "mac"
    case _ => "unsupported"
  }
  var prefix: Predef.String = ""

  def readInput(): String = try {
    val prefixSize = prefix.length

    val baos = new ByteArrayOutputStream
    var b = System.in.read()
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
            b = System.in.read()
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
      b = System.in.read
    }
    return new Predef.String(baos.toByteArray, "UTF-8")
  } catch {
    case _: Throwable => throw new InterruptedException
  }

  def writeOutput(s: String): Unit = this.synchronized {
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
}
