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

package org.sireum.server.protocol

import org.sireum._
import org.sireum.message.Position
import org.sireum.message.Message

@datatype trait Request

@datatype class Terminate extends Request

@datatype trait Response

@datatype class Report(val message: Message) extends Response

@datatype trait RequestId extends Request {
  def id: String
}

@datatype class Cancel(val id: String) extends RequestId

@datatype trait ResponseId extends Response {
  def id: String
}

@datatype class ReportId(val id: String, val message: Message) extends ResponseId


object Version {

  @datatype class Request extends org.sireum.server.protocol.Request

  @datatype class Response(val version: String) extends org.sireum.server.protocol.Response

}


object Slang {

  object Check {

    object Script {

      @datatype class Start(val id: String, val uriOpt: Option[String], val content: String) extends RequestId

    }

    @datatype class End(val id: String) extends ResponseId

  }

}

object Logika {

  object Verify {

    @datatype class Config(val config: logika.Config) extends org.sireum.server.protocol.Request

    @datatype class State(val id: String, val posOpt: Option[Position], val state: logika.State) extends ResponseId

    @datatype class Halted(val id: String, val posOpt: Option[Position], val state: logika.State) extends ResponseId

    @datatype class Smt2QueryResult(val id: String, val pos: Position, val result: logika.Smt2Query.Result) extends ResponseId

    val defaultConfig: logika.Config = logika.Config(
      smt2Configs = ISZ(logika.Z3Config("z3", 2000)),
      defaultLoopBound = 3,
      loopBounds = HashMap.empty,
      unroll = F,
      charBitWidth = 32,
      intBitWidth = 0,
      logPc = F,
      logRawPc = F,
      logVc = F,
      logVcDirOpt = None(),
      splitAll = F,
      splitContract = F,
      splitIf = F,
      splitMatch = F,
      simplifiedQuery = F,
    )

  }

}