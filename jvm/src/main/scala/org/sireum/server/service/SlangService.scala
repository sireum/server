// #Sireum
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
package org.sireum.server.service

import org.sireum._
import org.sireum.message.{Message, Reporter}

@record class SlangService extends Service {

  val id: String = "slang"

  def init(serverAPI: server.ServerAPI): Unit = {
  }

  def canHandle(request: server.protocol.Request): B = {
    request match {
      case request: server.protocol.Slang.Rewrite.Request if request.kind != server.protocol.Slang.Rewrite.Kind.RenumberProofSteps => return T
      case _ => return F
    }
  }

  def handle(serverAPI: server.ServerAPI, request: server.protocol.Request): Unit = {
    val req = request.asInstanceOf[server.protocol.Slang.Rewrite.Request]
    req.kind match {
      case server.protocol.Slang.Rewrite.Kind.InsertConstructorVals =>
        val reporter = Reporter.create
        org.sireum.lang.FrontEnd.rewrite(org.sireum.lang.FrontEnd.Rewrite.InsertConstructorVals,
          req.isScript, req.fileUriOpt, req.text, reporter) match {
          case org.sireum.Some((newText, n)) =>
            if (n != 0) {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.Info, None(), "Slang Rewrite",
                  s"Successfully inserted $n constructor parameter val modifier(s)"), Some(newText), n))
            } else {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.Info, None(), "Slang Rewrite",
                  "All constructor parameters have already been annotated with val or var"), None(), n))
            }
          case _ =>
            if (reporter.hasError) {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.Error, None(), "Slang Rewrite",
                  "Cannot insert constructor parameter val modifiers for an ill-formed program"), None(), 0))
            } else {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.InternalError, None(), "Slang Rewrite",
                  "An error occurred when inserting constructor parameter val modifiers"), None(), 0))
            }
        }
      case server.protocol.Slang.Rewrite.Kind.ReplaceEnumSymbols =>
        val reporter = Reporter.create
        org.sireum.lang.FrontEnd.rewrite(org.sireum.lang.FrontEnd.Rewrite.ReplaceEnumSymbols,
          req.isScript, req.fileUriOpt, req.text, reporter) match {
          case org.sireum.Some((newText, n)) =>
            if (n != 0) {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.Info, None(), "Slang Rewrite",
                  s"Successfully replaced $n enum element symbol(s) with strings"), Some(newText), n))
            } else {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.Info, None(), "Slang Rewrite",
                  "All enum elements have already in string form"), None(), n))
            }
          case _ =>
            if (reporter.hasError) {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.Error, None(), "Slang Rewrite",
                  "Cannot replace enum element symbols for an ill-formed program"), None(), 0))
            } else {
              serverAPI.sendResponse(server.protocol.Slang.Rewrite.Response(
                req.id, req.kind, Message(message.Level.InternalError, None(), "Slang Rewrite",
                  "An error occurred when replacing enum element symbols"), None(), 0))
            }
        }
      case server.protocol.Slang.Rewrite.Kind.RenumberProofSteps => halt("Infeasible")
    }
  }

  def finalise(): Unit = {
  }
}
