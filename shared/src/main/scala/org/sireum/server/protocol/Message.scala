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

package org.sireum.server.protocol

import org.sireum._
import org.sireum.message.Position
import org.sireum.message.Message

@datatype trait Request {
  def id: ISZ[String]
}

@datatype class Terminate extends Request {
  @strictpure override def id: ISZ[String] = ISZ()
}

@datatype trait Response {
  @pure def id: ISZ[String]
  @pure def posOpt: Option[Position]
}

@datatype class Cancel(val id: ISZ[String]) extends Request

@datatype class Timing(val id: ISZ[String], val desc: String, val timeInMs: Z) extends Response {
  @strictpure override def posOpt: Option[Position] = None()
}

@datatype class SocketPort(val id: ISZ[String], val port: Z) extends Response {
  @strictpure override def posOpt: Option[Position] = None()
}

@datatype class Report(val id: ISZ[String], val message: Message) extends Response {
  @strictpure override def posOpt: Option[Position] = message.posOpt
}


object Version {

  @datatype class Request extends org.sireum.server.protocol.Request {
    @strictpure override def id: ISZ[String] = ISZ()
  }

  @datatype class Response(val version: String) extends org.sireum.server.protocol.Response {
    @strictpure override def id: ISZ[String] = ISZ()
    @strictpure override def posOpt: Option[Position] = None()
  }

}


object Status {

  @datatype class Request extends org.sireum.server.protocol.Request {
    @strictpure override def id: ISZ[String] = ISZ()
  }

  @datatype class Response(val totalMemory: Z, val freeMemory: Z) extends org.sireum.server.protocol.Response {
    @strictpure override def id: ISZ[String] = ISZ()
    @strictpure override def posOpt: Option[Position] = None()
  }

}

object SysMLv2 {

  object Check {

    @datatype class Files(val isBackground: B,
                          val logikaEnabled: B,
                          val id: ISZ[String],
                          val rootDir: String,
                          val files: HashSMap[String, String],
                          val vfiles: ISZ[String],
                          val line: Z) extends Request

  }

}

object Slang {

  @datatype trait Check extends Request {
    @pure def isBackground: B
    @pure def rootDirOpt: Option[String]
    @pure def line: Z
  }

  object Check {

    @datatype class Script(val isBackground: B,
                           val logikaEnabled: B,
                           val id: ISZ[String],
                           val rootDirOpt: Option[String],
                           val uriOpt: Option[String],
                           val content: String,
                           val line: Z,
                           val rewriteKindOpt: Option[Rewrite.Kind.Type],
                           val returnAST: B) extends Check


    @datatype class Project(val isBackground: B,
                            val id: ISZ[String],
                            val rootDir: String,
                            val files: HashSMap[String, String],
                            val vfiles: ISZ[String],
                            val line: Z,
                            val rewriteKind: Rewrite.Kind.Type,
                            val rewriteUriOpt: Option[String],
                            val returnAST: B) extends Check {
      @strictpure def rootDirOpt: Option[String] = Some(rootDir)
    }

  }

  object Rewrite {

    @enum object Kind {
      "InsertConstructorVals"
      "RenumberProofSteps"
      "ReplaceEnumSymbols"
      "ExpandInduct"
    }

    @datatype class Request(val id: ISZ[String],
                            val kind: Kind.Type,
                            val isScript: B,
                            val fileUriOpt: Option[String],
                            val text: String) extends org.sireum.server.protocol.Request

    @datatype class Response(val id: ISZ[String],
                             val kind: Kind.Type,
                             val message: Message,
                             val newTextOpt: Option[String],
                             val numOfRewrites: Z) extends org.sireum.server.protocol.Response {
      @pure override def posOpt: Option[Position] = {
        return message.posOpt
      }
    }

  }
}

object Analysis {

  @datatype class Start(val id: ISZ[String], val currentTimeMillis: Z) extends Response {
    @strictpure override def posOpt: Option[Position] = None()
  }

  @datatype class Coverage(val id: ISZ[String], val setCache: B, val cached: U64, val pos: Position) extends Response {
    @pure override def posOpt: Option[Position] = {
      return Some(pos)
    }
  }

  @datatype class ResolvedAst(val id: ISZ[String], val path: String) extends Response {
    @pure override def posOpt: Option[Position] = {
      return None()
    }
  }

  @datatype class End(val isBackground: B,
                      val id: ISZ[String],
                      val wasCancelled: B,
                      val isIllFormed: B,
                      val hasLogika: B,
                      val totalTimeMillis: Z,
                      val numOfVCs: Z,
                      val numOfSats: Z,
                      val vcTimeMillis: Z,
                      val satTimeMillis: Z,
                      val numOfInternalErrors: Z,
                      val numOfErrors: Z,
                      val numOfWarnings: Z) extends Response {
    @strictpure override def posOpt: Option[Position] = None()
  }

  object Cache {

    @enum object Kind {
      "All"
      "Files"
      "SMT2"
      "Transitions"
      "Persistent"
    }

    @datatype class Clear(val kind: Cache.Kind.Type) extends Request {
      @strictpure override def id: ISZ[String] = ISZ()
    }

    @datatype class Cleared(val msg: String) extends Response {
      @strictpure override def id: ISZ[String] = ISZ()

      @strictpure override def posOpt: Option[Position] = None()
    }
  }

}


object Logika {

  object Verify {

    @datatype class Config(val infoFlow: B, val config: logika.Config) extends Request {
      @strictpure def id: ISZ[String] = ISZ()
    }

    @datatype class State(val id: ISZ[String], val posOpt: Option[Position], val terminated: B, val labels: ISZ[String], val claims: String) extends Response

    @datatype class Smt2Query(val id: ISZ[String], val pos: Position, val isSat: B, val timeInMs: Z, val title: String,
                              val kind: logika.Smt2Query.Result.Kind.Type, val solverName: String, val query: String,
                              val info: String, val output: String) extends Response {
      @pure override def posOpt: Option[Position] = {
        return Some(pos)
      }
    }

    @datatype class Info(val id: ISZ[String], val pos: Position, val kind: Info.Kind.Type, val message: String) extends Response {
      @pure override def posOpt: Option[Position] = {
        return Some(pos)
      }
    }

    object Info {
      @enum object Kind {
        "Verified"
        "Error"
      }
    }

    val defaultConfig: logika.Config = logika.Config(
      smt2Configs = ISZ(),
      parCores = 1,
      sat = F,
      rlimit = 1000000,
      timeoutInMs = 2000,
      charBitWidth = 32,
      intBitWidth = 0,
      useReal = F,
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
      checkInfeasiblePatternMatch = T,
      fpRoundingMode = "RNE",
      smt2Caching = F,
      smt2Seq = F,
      branchPar = logika.Config.BranchPar.All,
      atLinesFresh = F,
      interp = F,
      loopBound = 3,
      callBound = 3,
      interpContracts = F,
      elideEncoding = F,
      rawInscription = F,
      strictPureMode = logika.Config.StrictPureMode.Default,
      transitionCache = F,
      patternExhaustive = T,
      pureFun = F,
      detailedInfo = F,
      satTimeout = F,
      isAuto = T,
      background = logika.Config.BackgroundMode.Type,
      atRewrite = T,
      searchPc = F,
      rwTrace = T,
      rwMax = 100,
      rwPar = T,
      rwEvalTrace = T,
      branchParPredNum = 3,
      branchParPredComp = 16
    )

  }

}