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
import org.sireum.server.service.{Service, AnalysisService}

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

  def cvcExe(sireumHome: Os.Path): Os.Path = sireumHome / "bin" / platform / (if (Os.isWin) "cvc.exe" else "cvc")

  def z3Exe(sireumHome: Os.Path): Os.Path = sireumHome / "bin" / platform / "z3" / "bin" / (if (Os.isWin) "z3.exe" else "z3")

  def analysisService(sireumHome: Os.Path, numOfThreads: Z): Service = {
    AnalysisService.setConfig(AnalysisService._hint, AnalysisService._smt2query,
      AnalysisService.defaultConfig(smt2Configs = ISZ(
        logika.CvcConfig(cvcExe(sireumHome).string, ISZ("--full-saturate-quant"), ISZ(), 1000000),
        logika.Z3Config(z3Exe(sireumHome).string, ISZ(), ISZ()))))
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
