/*
 Copyright (c) 2020, Robby, Kansas State University
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
import org.sireum.server.service.{Service, LogikaService}

import java.io.ByteArrayOutputStream

object ServerExt {
  def readInput(): String = {
    val baos = new ByteArrayOutputStream()
    def read(): Predef.String = {
      var b = System.in.read
      while (b >= 0) {
        if (b == '\n') {
          return new Predef.String(baos.toByteArray, "UTF-8")
        } else {
          baos.write(b)
        }
        b = System.in.read
      }
      return new Predef.String(baos.toByteArray, "UTF-8")
    }
    val r = read()
    assert(r.startsWith(Server.prefix.value), s"Input should start with '${Server.prefix}'")
    return r.substring(Server.prefix.value.length)
  }
  def writeOutput(s: String): Unit = this.synchronized {
    System.out.print(Server.prefix.value)
    System.out.println(s)
    System.out.flush()
  }
  def version: String = $internal.Macro.version
  def logikaService(numOfThreads: Z): Service = {
    LogikaService.defaultConfig = LogikaService.defaultConfig(smt2Configs = ISZ(
      logika.Cvc4Config(LogikaService.cvc4Exe), logika.Z3Config(LogikaService.z3Exe)
    ))
    new service.LogikaService(numOfThreads)
  }
}
