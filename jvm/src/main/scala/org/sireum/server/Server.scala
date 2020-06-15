// #Sireum
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
import org.sireum.server.protocol.CustomMessagePack

object Server {

  def run(): Unit = {
    while (request()) {}
  }

  def request(): B = {
    val req: protocol.Request = getRequest() match {
      case Some(r) => r
      case _ => return T
    }
    req match {
      case _: protocol.Terminate => return F
      case _: protocol.Version.Request => handleVersion()
      case req: protocol.Cancel => halt("TODO")
      case req: protocol.Slang.Check.Script.Start => halt("TODO")
      case req: protocol.Logika.Verify.Config => halt("TODO")
    }
    return T
  }

  def handleVersion(): Unit = {
    val resp = protocol.Version.Response(Ext.version)
    Ext.writeOutput(protocol.CustomMessagePack.fromResponse(resp))
  }

  def getRequest(): Option[protocol.Request] = {
    CustomMessagePack.toRequest(Ext.readInput()) match {
      case Either.Left(r) => return Some(r)
      case Either.Right(err) =>
        reportError(err)
        return None()
    }
  }

  def reportError(msg: String): Unit = {
    val resp = protocol.Report(message.Message(message.Level.Error, None(), "Server", s"Unrecognized request: $msg"))
    Ext.writeOutput(protocol.CustomMessagePack.fromResponse(resp))
  }

  @ext("ServerExt") object Ext {
    def readInput(): String = $
    def writeOutput(s: String): Unit = $
    def version: String = $
  }
}
