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

@datatype trait Request {
  def id: ISZ[String]
}

@datatype class Terminate extends Request {
  @strictpure def id: ISZ[String] = ISZ()
}

@datatype trait Response {
  def id: ISZ[String]
}

@datatype class Cancel(val id: ISZ[String]) extends Request

@datatype class Timing(val id: ISZ[String], desc: String, timeInMs: Z) extends Response

@datatype class Report(val id: ISZ[String], val message: Message) extends Response


object Version {

  @datatype class Request extends org.sireum.server.protocol.Request {
    @strictpure def id: ISZ[String] = ISZ()
  }

  @datatype class Response(val version: String) extends org.sireum.server.protocol.Response {
    @strictpure def id: ISZ[String] = ISZ()
  }

}


object Slang {

  @datatype class CheckScript(val isBackground: B, val id: ISZ[String], val uriOpt: Option[String], val content: String) extends Request

}


object Logika {

  object Verify {

    @datatype class Start(val id: ISZ[String], val currentTimeMillis: Z) extends Response

    @datatype class End(val isBackground: B,
                        val id: ISZ[String],
                        val wasCancelled: B,
                        val isIllFormed: B,
                        val hasLogika: B,
                        val totalTimeMillis: Z,
                        val numOfSmt2Calls: Z,
                        val smt2TimeMillis: Z,
                        val numOfErrors: Z,
                        val numOfWarnings: Z) extends Response

    @datatype class Config(val config: logika.Config) extends Request {
      @strictpure def id: ISZ[String] = ISZ()
    }

    @datatype class State(val id: ISZ[String], val posOpt: Option[Position], val state: logika.State) extends Response

    @datatype class Halted(val id: ISZ[String], val posOpt: Option[Position], val state: logika.State) extends Response

    @datatype class Smt2Query(val id: ISZ[String], val pos: Position, val timeInMs: Z, val result: logika.Smt2Query.Result) extends Response

    val defaultConfig: logika.Config = logika.Config(
      smt2Configs = ISZ(),
      timeoutInMs = 2000,
      defaultLoopBound = 3,
      loopBounds = HashMap.empty,
      unroll = F,
      charBitWidth = 32,
      intBitWidth = 0,
      logPc = F,
      logRawPc = F,
      logVc = F,
      logVcDirOpt = None(),
      dontSplitPfq = F,
      splitAll = F,
      splitContract = F,
      splitIf = F,
      splitMatch = F,
      simplifiedQuery = F,
    )

  }

}