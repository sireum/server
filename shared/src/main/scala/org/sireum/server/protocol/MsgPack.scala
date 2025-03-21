// #Sireum
// @formatter:off

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

// This file is auto-generated from Message.scala, Config.scala, Smt2Query.scala, Typed.scala

package org.sireum.server.protocol

import org.sireum._
import org.sireum.server.protocol.Request
import org.sireum.server.protocol.Terminate
import org.sireum.server.protocol.Response
import org.sireum.server.protocol.Cancel
import org.sireum.server.protocol.Timing
import org.sireum.server.protocol.SocketPort
import org.sireum.server.protocol.Report

object MsgPack {

  object Constants {

    val Terminate: Z = -32

    val Cancel: Z = -31

    val Timing: Z = -30

    val SocketPort: Z = -29

    val Report: Z = -28

    val VersionRequest: Z = -27

    val VersionResponse: Z = -26

    val StatusRequest: Z = -25

    val StatusResponse: Z = -24

    val SysMLv2CheckFiles: Z = -23

    val SlangCheckScript: Z = -22

    val SlangCheckProject: Z = -21

    val SlangRewriteRequest: Z = -20

    val SlangRewriteResponse: Z = -19

    val AnalysisStart: Z = -18

    val AnalysisCoverage: Z = -17

    val AnalysisResolvedAst: Z = -16

    val AnalysisEnd: Z = -15

    val AnalysisCacheClear: Z = -14

    val AnalysisCacheCleared: Z = -13

    val LogikaVerifyConfig: Z = -12

    val LogikaVerifyState: Z = -11

    val LogikaVerifySmt2Query: Z = -10

    val LogikaVerifyInfo: Z = -9

    val orgsireumlogikaConfig: Z = -8

    val orgsireumlogikaSmt2Config: Z = -7

    val orgsireumlogikaLoopId: Z = -6

    val _logikaSmt2QueryResult: Z = -5

    val _langastTypedName: Z = -4

    val _langastTypedTuple: Z = -3

    val _langastTypedFun: Z = -2

    val _langastTypedTypeVar: Z = -1

    val _langastTypedPackage: Z = 0

    val _langastTypedObject: Z = 1

    val _langastTypedEnum: Z = 2

    val _langastTypedMethod: Z = 3

    val _langastTypedMethods: Z = 4

    val _langastTypedFact: Z = 5

    val _langastTypedTheorem: Z = 6

    val _langastTypedInv: Z = 7

  }

  object Writer {

    @record class Default(val writer: MessagePack.Writer.Impl) extends Writer

  }

  @msig trait Writer {

    def writer: MessagePack.Writer

    def writeRequest(o: Request): Unit = {
      o match {
        case o: Terminate => writeTerminate(o)
        case o: Cancel => writeCancel(o)
        case o: Version.Request => writeVersionRequest(o)
        case o: Status.Request => writeStatusRequest(o)
        case o: SysMLv2.Check.Files => writeSysMLv2CheckFiles(o)
        case o: Slang.Check.Script => writeSlangCheckScript(o)
        case o: Slang.Check.Project => writeSlangCheckProject(o)
        case o: Slang.Rewrite.Request => writeSlangRewriteRequest(o)
        case o: Analysis.Cache.Clear => writeAnalysisCacheClear(o)
        case o: Logika.Verify.Config => writeLogikaVerifyConfig(o)
      }
    }

    def writeTerminate(o: Terminate): Unit = {
      writer.writeZ(Constants.Terminate)
    }

    def writeResponse(o: Response): Unit = {
      o match {
        case o: Timing => writeTiming(o)
        case o: SocketPort => writeSocketPort(o)
        case o: Report => writeReport(o)
        case o: Version.Response => writeVersionResponse(o)
        case o: Status.Response => writeStatusResponse(o)
        case o: Slang.Rewrite.Response => writeSlangRewriteResponse(o)
        case o: Analysis.Start => writeAnalysisStart(o)
        case o: Analysis.Coverage => writeAnalysisCoverage(o)
        case o: Analysis.ResolvedAst => writeAnalysisResolvedAst(o)
        case o: Analysis.End => writeAnalysisEnd(o)
        case o: Analysis.Cache.Cleared => writeAnalysisCacheCleared(o)
        case o: Logika.Verify.State => writeLogikaVerifyState(o)
        case o: Logika.Verify.Smt2Query => writeLogikaVerifySmt2Query(o)
        case o: Logika.Verify.Info => writeLogikaVerifyInfo(o)
      }
    }

    def writeCancel(o: Cancel): Unit = {
      writer.writeZ(Constants.Cancel)
      writer.writeISZ(o.id, writer.writeString _)
    }

    def writeTiming(o: Timing): Unit = {
      writer.writeZ(Constants.Timing)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeString(o.desc)
      writer.writeZ(o.timeInMs)
    }

    def writeSocketPort(o: SocketPort): Unit = {
      writer.writeZ(Constants.SocketPort)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeZ(o.port)
    }

    def writeReport(o: Report): Unit = {
      writer.writeZ(Constants.Report)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeMessage(o.message)
    }

    def writeVersionRequest(o: Version.Request): Unit = {
      writer.writeZ(Constants.VersionRequest)
    }

    def writeVersionResponse(o: Version.Response): Unit = {
      writer.writeZ(Constants.VersionResponse)
      writer.writeString(o.version)
    }

    def writeStatusRequest(o: Status.Request): Unit = {
      writer.writeZ(Constants.StatusRequest)
    }

    def writeStatusResponse(o: Status.Response): Unit = {
      writer.writeZ(Constants.StatusResponse)
      writer.writeZ(o.totalMemory)
      writer.writeZ(o.freeMemory)
    }

    def writeSysMLv2CheckFiles(o: SysMLv2.Check.Files): Unit = {
      writer.writeZ(Constants.SysMLv2CheckFiles)
      writer.writeB(o.isBackground)
      writer.writeB(o.logikaEnabled)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeString(o.rootDir)
      writer.writeHashSMap(o.files, writer.writeString _, writer.writeString _)
      writer.writeISZ(o.vfiles, writer.writeString _)
      writer.writeZ(o.line)
    }

    def writeSlangCheck(o: Slang.Check): Unit = {
      o match {
        case o: Slang.Check.Script => writeSlangCheckScript(o)
        case o: Slang.Check.Project => writeSlangCheckProject(o)
      }
    }

    def writeSlangCheckScript(o: Slang.Check.Script): Unit = {
      writer.writeZ(Constants.SlangCheckScript)
      writer.writeB(o.isBackground)
      writer.writeB(o.logikaEnabled)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeOption(o.rootDirOpt, writer.writeString _)
      writer.writeOption(o.uriOpt, writer.writeString _)
      writer.writeString(o.content)
      writer.writeZ(o.line)
      writer.writeOption(o.rewriteKindOpt, writeSlangRewriteKindType _)
      writer.writeB(o.returnAST)
    }

    def writeSlangCheckProject(o: Slang.Check.Project): Unit = {
      writer.writeZ(Constants.SlangCheckProject)
      writer.writeB(o.isBackground)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeString(o.rootDir)
      writer.writeHashSMap(o.files, writer.writeString _, writer.writeString _)
      writer.writeISZ(o.vfiles, writer.writeString _)
      writer.writeZ(o.line)
      writeSlangRewriteKindType(o.rewriteKind)
      writer.writeOption(o.rewriteUriOpt, writer.writeString _)
      writer.writeB(o.returnAST)
    }

    def writeSlangRewriteKindType(o: Slang.Rewrite.Kind.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def writeSlangRewriteRequest(o: Slang.Rewrite.Request): Unit = {
      writer.writeZ(Constants.SlangRewriteRequest)
      writer.writeISZ(o.id, writer.writeString _)
      writeSlangRewriteKindType(o.kind)
      writer.writeB(o.isScript)
      writer.writeOption(o.fileUriOpt, writer.writeString _)
      writer.writeString(o.text)
    }

    def writeSlangRewriteResponse(o: Slang.Rewrite.Response): Unit = {
      writer.writeZ(Constants.SlangRewriteResponse)
      writer.writeISZ(o.id, writer.writeString _)
      writeSlangRewriteKindType(o.kind)
      writer.writeMessage(o.message)
      writer.writeOption(o.newTextOpt, writer.writeString _)
      writer.writeZ(o.numOfRewrites)
    }

    def writeAnalysisStart(o: Analysis.Start): Unit = {
      writer.writeZ(Constants.AnalysisStart)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeZ(o.currentTimeMillis)
    }

    def writeAnalysisCoverage(o: Analysis.Coverage): Unit = {
      writer.writeZ(Constants.AnalysisCoverage)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeB(o.setCache)
      writer.writeU64(o.cached)
      writer.writePosition(o.pos)
    }

    def writeAnalysisResolvedAst(o: Analysis.ResolvedAst): Unit = {
      writer.writeZ(Constants.AnalysisResolvedAst)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeString(o.path)
    }

    def writeAnalysisEnd(o: Analysis.End): Unit = {
      writer.writeZ(Constants.AnalysisEnd)
      writer.writeB(o.isBackground)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeB(o.wasCancelled)
      writer.writeB(o.isIllFormed)
      writer.writeB(o.hasLogika)
      writer.writeZ(o.totalTimeMillis)
      writer.writeZ(o.numOfVCs)
      writer.writeZ(o.numOfSats)
      writer.writeZ(o.vcTimeMillis)
      writer.writeZ(o.satTimeMillis)
      writer.writeZ(o.numOfInternalErrors)
      writer.writeZ(o.numOfErrors)
      writer.writeZ(o.numOfWarnings)
    }

    def writeAnalysisCacheKindType(o: Analysis.Cache.Kind.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def writeAnalysisCacheClear(o: Analysis.Cache.Clear): Unit = {
      writer.writeZ(Constants.AnalysisCacheClear)
      writeAnalysisCacheKindType(o.kind)
    }

    def writeAnalysisCacheCleared(o: Analysis.Cache.Cleared): Unit = {
      writer.writeZ(Constants.AnalysisCacheCleared)
      writer.writeString(o.msg)
    }

    def writeLogikaVerifyConfig(o: Logika.Verify.Config): Unit = {
      writer.writeZ(Constants.LogikaVerifyConfig)
      writer.writeB(o.infoFlow)
      writeorgsireumlogikaConfig(o.config)
    }

    def writeLogikaVerifyState(o: Logika.Verify.State): Unit = {
      writer.writeZ(Constants.LogikaVerifyState)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeOption(o.posOpt, writer.writePosition _)
      writer.writeB(o.terminated)
      writer.writeISZ(o.labels, writer.writeString _)
      writer.writeString(o.claims)
    }

    def writeLogikaVerifySmt2Query(o: Logika.Verify.Smt2Query): Unit = {
      writer.writeZ(Constants.LogikaVerifySmt2Query)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writePosition(o.pos)
      writer.writeB(o.isSat)
      writer.writeZ(o.timeInMs)
      writer.writeString(o.title)
      write_logikaSmt2QueryResultKindType(o.kind)
      writer.writeString(o.solverName)
      writer.writeString(o.query)
      writer.writeString(o.info)
      writer.writeString(o.output)
    }

    def writeLogikaVerifyInfo(o: Logika.Verify.Info): Unit = {
      writer.writeZ(Constants.LogikaVerifyInfo)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writePosition(o.pos)
      writeLogikaVerifyInfoKindType(o.kind)
      writer.writeString(o.message)
    }

    def writeLogikaVerifyInfoKindType(o: Logika.Verify.Info.Kind.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def writeorgsireumlogikaConfig(o: org.sireum.logika.Config): Unit = {
      writer.writeZ(Constants.orgsireumlogikaConfig)
      writer.writeISZ(o.smt2Configs, writeorgsireumlogikaSmt2Config _)
      writer.writeZ(o.parCores)
      writer.writeB(o.sat)
      writer.writeZ(o.rlimit)
      writer.writeZ(o.timeoutInMs)
      writer.writeZ(o.charBitWidth)
      writer.writeZ(o.intBitWidth)
      writer.writeB(o.useReal)
      writer.writeB(o.logPc)
      writer.writeB(o.logRawPc)
      writer.writeB(o.logVc)
      writer.writeOption(o.logVcDirOpt, writer.writeString _)
      writer.writeB(o.dontSplitPfq)
      writer.writeB(o.splitAll)
      writer.writeB(o.splitIf)
      writer.writeB(o.splitMatch)
      writer.writeB(o.splitContract)
      writer.writeB(o.simplifiedQuery)
      writer.writeB(o.checkInfeasiblePatternMatch)
      writer.writeString(o.fpRoundingMode)
      writer.writeB(o.smt2Caching)
      writer.writeB(o.smt2Seq)
      write_logikaConfigBranchParType(o.branchPar)
      writer.writeB(o.atLinesFresh)
      writer.writeB(o.interp)
      writer.writeZ(o.loopBound)
      writer.writeZ(o.callBound)
      writer.writeB(o.interpContracts)
      writer.writeB(o.elideEncoding)
      writer.writeB(o.rawInscription)
      write_logikaConfigStrictPureModeType(o.strictPureMode)
      writer.writeB(o.transitionCache)
      writer.writeB(o.patternExhaustive)
      writer.writeB(o.pureFun)
      writer.writeB(o.detailedInfo)
      writer.writeB(o.satTimeout)
      writer.writeB(o.isAuto)
      write_logikaConfigBackgroundModeType(o.background)
      writer.writeB(o.atRewrite)
      writer.writeB(o.searchPc)
      writer.writeB(o.rwTrace)
      writer.writeZ(o.rwMax)
      writer.writeB(o.rwPar)
      writer.writeB(o.rwEvalTrace)
      writer.writeZ(o.branchParPredNum)
      writer.writeZ(o.branchParPredComp)
    }

    def write_logikaConfigBranchParType(o: org.sireum.logika.Config.BranchPar.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def write_logikaConfigStrictPureModeType(o: org.sireum.logika.Config.StrictPureMode.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def write_logikaConfigBackgroundModeType(o: org.sireum.logika.Config.BackgroundMode.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def writeorgsireumlogikaSmt2Config(o: org.sireum.logika.Smt2Config): Unit = {
      writer.writeZ(Constants.orgsireumlogikaSmt2Config)
      writer.writeB(o.isSat)
      writer.writeString(o.name)
      writer.writeString(o.exe)
      writer.writeISZ(o.opts, writer.writeString _)
    }

    def writeorgsireumlogikaLoopId(o: org.sireum.logika.LoopId): Unit = {
      writer.writeZ(Constants.orgsireumlogikaLoopId)
      writer.writeISZ(o.ids, writer.writeString _)
    }

    def write_logikaSmt2QueryResultKindType(o: org.sireum.logika.Smt2Query.Result.Kind.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def write_logikaSmt2QueryResult(o: org.sireum.logika.Smt2Query.Result): Unit = {
      writer.writeZ(Constants._logikaSmt2QueryResult)
      write_logikaSmt2QueryResultKindType(o.kind)
      writer.writeString(o.solverName)
      writer.writeString(o.query)
      writer.writeString(o.info)
      writer.writeString(o.output)
      writer.writeZ(o.timeMillis)
      writer.writeZ(o.totalTimeMillis)
      writer.writeB(o.cached)
    }

    def write_langastPurityType(o: org.sireum.lang.ast.Purity.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def write_langastMethodModeType(o: org.sireum.lang.ast.MethodMode.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def write_langastTyped(o: org.sireum.lang.ast.Typed): Unit = {
      o match {
        case o: org.sireum.lang.ast.Typed.Name => write_langastTypedName(o)
        case o: org.sireum.lang.ast.Typed.Tuple => write_langastTypedTuple(o)
        case o: org.sireum.lang.ast.Typed.Fun => write_langastTypedFun(o)
        case o: org.sireum.lang.ast.Typed.TypeVar => write_langastTypedTypeVar(o)
        case o: org.sireum.lang.ast.Typed.Package => write_langastTypedPackage(o)
        case o: org.sireum.lang.ast.Typed.Object => write_langastTypedObject(o)
        case o: org.sireum.lang.ast.Typed.Enum => write_langastTypedEnum(o)
        case o: org.sireum.lang.ast.Typed.Method => write_langastTypedMethod(o)
        case o: org.sireum.lang.ast.Typed.Methods => write_langastTypedMethods(o)
        case o: org.sireum.lang.ast.Typed.Fact => write_langastTypedFact(o)
        case o: org.sireum.lang.ast.Typed.Theorem => write_langastTypedTheorem(o)
        case o: org.sireum.lang.ast.Typed.Inv => write_langastTypedInv(o)
      }
    }

    def write_langastTypedVarKindType(o: org.sireum.lang.ast.Typed.VarKind.Type): Unit = {
      writer.writeZ(o.ordinal)
    }

    def write_langastTypedName(o: org.sireum.lang.ast.Typed.Name): Unit = {
      writer.writeZ(Constants._langastTypedName)
      writer.writeISZ(o.ids, writer.writeString _)
      writer.writeISZ(o.args, write_langastTyped _)
    }

    def write_langastTypedTuple(o: org.sireum.lang.ast.Typed.Tuple): Unit = {
      writer.writeZ(Constants._langastTypedTuple)
      writer.writeISZ(o.args, write_langastTyped _)
    }

    def write_langastTypedFun(o: org.sireum.lang.ast.Typed.Fun): Unit = {
      writer.writeZ(Constants._langastTypedFun)
      write_langastPurityType(o.purity)
      writer.writeB(o.isByName)
      writer.writeISZ(o.args, write_langastTyped _)
      write_langastTyped(o.ret)
    }

    def write_langastTypedTypeVar(o: org.sireum.lang.ast.Typed.TypeVar): Unit = {
      writer.writeZ(Constants._langastTypedTypeVar)
      writer.writeString(o.id)
      write_langastTypedVarKindType(o.kind)
    }

    def write_langastTypedPackage(o: org.sireum.lang.ast.Typed.Package): Unit = {
      writer.writeZ(Constants._langastTypedPackage)
      writer.writeISZ(o.name, writer.writeString _)
    }

    def write_langastTypedObject(o: org.sireum.lang.ast.Typed.Object): Unit = {
      writer.writeZ(Constants._langastTypedObject)
      writer.writeISZ(o.owner, writer.writeString _)
      writer.writeString(o.id)
    }

    def write_langastTypedEnum(o: org.sireum.lang.ast.Typed.Enum): Unit = {
      writer.writeZ(Constants._langastTypedEnum)
      writer.writeISZ(o.name, writer.writeString _)
    }

    def write_langastTypedMethod(o: org.sireum.lang.ast.Typed.Method): Unit = {
      writer.writeZ(Constants._langastTypedMethod)
      writer.writeB(o.isInObject)
      write_langastMethodModeType(o.mode)
      writer.writeISZ(o.typeParams, writer.writeString _)
      writer.writeISZ(o.owner, writer.writeString _)
      writer.writeString(o.name)
      writer.writeISZ(o.paramNames, writer.writeString _)
      write_langastTypedFun(o.tpe)
    }

    def write_langastTypedMethods(o: org.sireum.lang.ast.Typed.Methods): Unit = {
      writer.writeZ(Constants._langastTypedMethods)
      writer.writeISZ(o.methods, write_langastTypedMethod _)
    }

    def write_langastTypedFact(o: org.sireum.lang.ast.Typed.Fact): Unit = {
      writer.writeZ(Constants._langastTypedFact)
      writer.writeISZ(o.owner, writer.writeString _)
      writer.writeString(o.id)
    }

    def write_langastTypedTheorem(o: org.sireum.lang.ast.Typed.Theorem): Unit = {
      writer.writeZ(Constants._langastTypedTheorem)
      writer.writeISZ(o.owner, writer.writeString _)
      writer.writeString(o.id)
    }

    def write_langastTypedInv(o: org.sireum.lang.ast.Typed.Inv): Unit = {
      writer.writeZ(Constants._langastTypedInv)
      writer.writeB(o.isInObject)
      writer.writeISZ(o.owner, writer.writeString _)
      writer.writeString(o.id)
    }

    def result: ISZ[U8] = {
      return writer.result
    }

  }

  object Reader {

    @record class Default(val reader: MessagePack.Reader.Impl) extends Reader {
      def errorOpt: Option[MessagePack.ErrorMsg] = {
        return reader.errorOpt
      }
    }

  }

  @msig trait Reader {

    def reader: MessagePack.Reader

    def readRequest(): Request = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants.Terminate => val r = readTerminateT(T); return r
        case Constants.Cancel => val r = readCancelT(T); return r
        case Constants.VersionRequest => val r = readVersionRequestT(T); return r
        case Constants.StatusRequest => val r = readStatusRequestT(T); return r
        case Constants.SysMLv2CheckFiles => val r = readSysMLv2CheckFilesT(T); return r
        case Constants.SlangCheckScript => val r = readSlangCheckScriptT(T); return r
        case Constants.SlangCheckProject => val r = readSlangCheckProjectT(T); return r
        case Constants.SlangRewriteRequest => val r = readSlangRewriteRequestT(T); return r
        case Constants.AnalysisCacheClear => val r = readAnalysisCacheClearT(T); return r
        case Constants.LogikaVerifyConfig => val r = readLogikaVerifyConfigT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of Request.")
          val r = readLogikaVerifyConfigT(T)
          return r
      }
    }

    def readTerminate(): Terminate = {
      val r = readTerminateT(F)
      return r
    }

    def readTerminateT(typeParsed: B): Terminate = {
      if (!typeParsed) {
        reader.expectZ(Constants.Terminate)
      }
      return Terminate()
    }

    def readResponse(): Response = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants.Timing => val r = readTimingT(T); return r
        case Constants.SocketPort => val r = readSocketPortT(T); return r
        case Constants.Report => val r = readReportT(T); return r
        case Constants.VersionResponse => val r = readVersionResponseT(T); return r
        case Constants.StatusResponse => val r = readStatusResponseT(T); return r
        case Constants.SlangRewriteResponse => val r = readSlangRewriteResponseT(T); return r
        case Constants.AnalysisStart => val r = readAnalysisStartT(T); return r
        case Constants.AnalysisCoverage => val r = readAnalysisCoverageT(T); return r
        case Constants.AnalysisResolvedAst => val r = readAnalysisResolvedAstT(T); return r
        case Constants.AnalysisEnd => val r = readAnalysisEndT(T); return r
        case Constants.AnalysisCacheCleared => val r = readAnalysisCacheClearedT(T); return r
        case Constants.LogikaVerifyState => val r = readLogikaVerifyStateT(T); return r
        case Constants.LogikaVerifySmt2Query => val r = readLogikaVerifySmt2QueryT(T); return r
        case Constants.LogikaVerifyInfo => val r = readLogikaVerifyInfoT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of Response.")
          val r = readLogikaVerifyInfoT(T)
          return r
      }
    }

    def readCancel(): Cancel = {
      val r = readCancelT(F)
      return r
    }

    def readCancelT(typeParsed: B): Cancel = {
      if (!typeParsed) {
        reader.expectZ(Constants.Cancel)
      }
      val id = reader.readISZ(reader.readString _)
      return Cancel(id)
    }

    def readTiming(): Timing = {
      val r = readTimingT(F)
      return r
    }

    def readTimingT(typeParsed: B): Timing = {
      if (!typeParsed) {
        reader.expectZ(Constants.Timing)
      }
      val id = reader.readISZ(reader.readString _)
      val desc = reader.readString()
      val timeInMs = reader.readZ()
      return Timing(id, desc, timeInMs)
    }

    def readSocketPort(): SocketPort = {
      val r = readSocketPortT(F)
      return r
    }

    def readSocketPortT(typeParsed: B): SocketPort = {
      if (!typeParsed) {
        reader.expectZ(Constants.SocketPort)
      }
      val id = reader.readISZ(reader.readString _)
      val port = reader.readZ()
      return SocketPort(id, port)
    }

    def readReport(): Report = {
      val r = readReportT(F)
      return r
    }

    def readReportT(typeParsed: B): Report = {
      if (!typeParsed) {
        reader.expectZ(Constants.Report)
      }
      val id = reader.readISZ(reader.readString _)
      val message = reader.readMessage()
      return Report(id, message)
    }

    def readVersionRequest(): Version.Request = {
      val r = readVersionRequestT(F)
      return r
    }

    def readVersionRequestT(typeParsed: B): Version.Request = {
      if (!typeParsed) {
        reader.expectZ(Constants.VersionRequest)
      }
      return Version.Request()
    }

    def readVersionResponse(): Version.Response = {
      val r = readVersionResponseT(F)
      return r
    }

    def readVersionResponseT(typeParsed: B): Version.Response = {
      if (!typeParsed) {
        reader.expectZ(Constants.VersionResponse)
      }
      val version = reader.readString()
      return Version.Response(version)
    }

    def readStatusRequest(): Status.Request = {
      val r = readStatusRequestT(F)
      return r
    }

    def readStatusRequestT(typeParsed: B): Status.Request = {
      if (!typeParsed) {
        reader.expectZ(Constants.StatusRequest)
      }
      return Status.Request()
    }

    def readStatusResponse(): Status.Response = {
      val r = readStatusResponseT(F)
      return r
    }

    def readStatusResponseT(typeParsed: B): Status.Response = {
      if (!typeParsed) {
        reader.expectZ(Constants.StatusResponse)
      }
      val totalMemory = reader.readZ()
      val freeMemory = reader.readZ()
      return Status.Response(totalMemory, freeMemory)
    }

    def readSysMLv2CheckFiles(): SysMLv2.Check.Files = {
      val r = readSysMLv2CheckFilesT(F)
      return r
    }

    def readSysMLv2CheckFilesT(typeParsed: B): SysMLv2.Check.Files = {
      if (!typeParsed) {
        reader.expectZ(Constants.SysMLv2CheckFiles)
      }
      val isBackground = reader.readB()
      val logikaEnabled = reader.readB()
      val id = reader.readISZ(reader.readString _)
      val rootDir = reader.readString()
      val files = reader.readHashSMap(reader.readString _, reader.readString _)
      val vfiles = reader.readISZ(reader.readString _)
      val line = reader.readZ()
      return SysMLv2.Check.Files(isBackground, logikaEnabled, id, rootDir, files, vfiles, line)
    }

    def readSlangCheck(): Slang.Check = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants.SlangCheckScript => val r = readSlangCheckScriptT(T); return r
        case Constants.SlangCheckProject => val r = readSlangCheckProjectT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of Slang.Check.")
          val r = readSlangCheckProjectT(T)
          return r
      }
    }

    def readSlangCheckScript(): Slang.Check.Script = {
      val r = readSlangCheckScriptT(F)
      return r
    }

    def readSlangCheckScriptT(typeParsed: B): Slang.Check.Script = {
      if (!typeParsed) {
        reader.expectZ(Constants.SlangCheckScript)
      }
      val isBackground = reader.readB()
      val logikaEnabled = reader.readB()
      val id = reader.readISZ(reader.readString _)
      val rootDirOpt = reader.readOption(reader.readString _)
      val uriOpt = reader.readOption(reader.readString _)
      val content = reader.readString()
      val line = reader.readZ()
      val rewriteKindOpt = reader.readOption(readSlangRewriteKindType _)
      val returnAST = reader.readB()
      return Slang.Check.Script(isBackground, logikaEnabled, id, rootDirOpt, uriOpt, content, line, rewriteKindOpt, returnAST)
    }

    def readSlangCheckProject(): Slang.Check.Project = {
      val r = readSlangCheckProjectT(F)
      return r
    }

    def readSlangCheckProjectT(typeParsed: B): Slang.Check.Project = {
      if (!typeParsed) {
        reader.expectZ(Constants.SlangCheckProject)
      }
      val isBackground = reader.readB()
      val id = reader.readISZ(reader.readString _)
      val rootDir = reader.readString()
      val files = reader.readHashSMap(reader.readString _, reader.readString _)
      val vfiles = reader.readISZ(reader.readString _)
      val line = reader.readZ()
      val rewriteKind = readSlangRewriteKindType()
      val rewriteUriOpt = reader.readOption(reader.readString _)
      val returnAST = reader.readB()
      return Slang.Check.Project(isBackground, id, rootDir, files, vfiles, line, rewriteKind, rewriteUriOpt, returnAST)
    }

    def readSlangRewriteKindType(): Slang.Rewrite.Kind.Type = {
      val r = reader.readZ()
      return Slang.Rewrite.Kind.byOrdinal(r).get
    }

    def readSlangRewriteRequest(): Slang.Rewrite.Request = {
      val r = readSlangRewriteRequestT(F)
      return r
    }

    def readSlangRewriteRequestT(typeParsed: B): Slang.Rewrite.Request = {
      if (!typeParsed) {
        reader.expectZ(Constants.SlangRewriteRequest)
      }
      val id = reader.readISZ(reader.readString _)
      val kind = readSlangRewriteKindType()
      val isScript = reader.readB()
      val fileUriOpt = reader.readOption(reader.readString _)
      val text = reader.readString()
      return Slang.Rewrite.Request(id, kind, isScript, fileUriOpt, text)
    }

    def readSlangRewriteResponse(): Slang.Rewrite.Response = {
      val r = readSlangRewriteResponseT(F)
      return r
    }

    def readSlangRewriteResponseT(typeParsed: B): Slang.Rewrite.Response = {
      if (!typeParsed) {
        reader.expectZ(Constants.SlangRewriteResponse)
      }
      val id = reader.readISZ(reader.readString _)
      val kind = readSlangRewriteKindType()
      val message = reader.readMessage()
      val newTextOpt = reader.readOption(reader.readString _)
      val numOfRewrites = reader.readZ()
      return Slang.Rewrite.Response(id, kind, message, newTextOpt, numOfRewrites)
    }

    def readAnalysisStart(): Analysis.Start = {
      val r = readAnalysisStartT(F)
      return r
    }

    def readAnalysisStartT(typeParsed: B): Analysis.Start = {
      if (!typeParsed) {
        reader.expectZ(Constants.AnalysisStart)
      }
      val id = reader.readISZ(reader.readString _)
      val currentTimeMillis = reader.readZ()
      return Analysis.Start(id, currentTimeMillis)
    }

    def readAnalysisCoverage(): Analysis.Coverage = {
      val r = readAnalysisCoverageT(F)
      return r
    }

    def readAnalysisCoverageT(typeParsed: B): Analysis.Coverage = {
      if (!typeParsed) {
        reader.expectZ(Constants.AnalysisCoverage)
      }
      val id = reader.readISZ(reader.readString _)
      val setCache = reader.readB()
      val cached = reader.readU64()
      val pos = reader.readPosition()
      return Analysis.Coverage(id, setCache, cached, pos)
    }

    def readAnalysisResolvedAst(): Analysis.ResolvedAst = {
      val r = readAnalysisResolvedAstT(F)
      return r
    }

    def readAnalysisResolvedAstT(typeParsed: B): Analysis.ResolvedAst = {
      if (!typeParsed) {
        reader.expectZ(Constants.AnalysisResolvedAst)
      }
      val id = reader.readISZ(reader.readString _)
      val path = reader.readString()
      return Analysis.ResolvedAst(id, path)
    }

    def readAnalysisEnd(): Analysis.End = {
      val r = readAnalysisEndT(F)
      return r
    }

    def readAnalysisEndT(typeParsed: B): Analysis.End = {
      if (!typeParsed) {
        reader.expectZ(Constants.AnalysisEnd)
      }
      val isBackground = reader.readB()
      val id = reader.readISZ(reader.readString _)
      val wasCancelled = reader.readB()
      val isIllFormed = reader.readB()
      val hasLogika = reader.readB()
      val totalTimeMillis = reader.readZ()
      val numOfVCs = reader.readZ()
      val numOfSats = reader.readZ()
      val vcTimeMillis = reader.readZ()
      val satTimeMillis = reader.readZ()
      val numOfInternalErrors = reader.readZ()
      val numOfErrors = reader.readZ()
      val numOfWarnings = reader.readZ()
      return Analysis.End(isBackground, id, wasCancelled, isIllFormed, hasLogika, totalTimeMillis, numOfVCs, numOfSats, vcTimeMillis, satTimeMillis, numOfInternalErrors, numOfErrors, numOfWarnings)
    }

    def readAnalysisCacheKindType(): Analysis.Cache.Kind.Type = {
      val r = reader.readZ()
      return Analysis.Cache.Kind.byOrdinal(r).get
    }

    def readAnalysisCacheClear(): Analysis.Cache.Clear = {
      val r = readAnalysisCacheClearT(F)
      return r
    }

    def readAnalysisCacheClearT(typeParsed: B): Analysis.Cache.Clear = {
      if (!typeParsed) {
        reader.expectZ(Constants.AnalysisCacheClear)
      }
      val kind = readAnalysisCacheKindType()
      return Analysis.Cache.Clear(kind)
    }

    def readAnalysisCacheCleared(): Analysis.Cache.Cleared = {
      val r = readAnalysisCacheClearedT(F)
      return r
    }

    def readAnalysisCacheClearedT(typeParsed: B): Analysis.Cache.Cleared = {
      if (!typeParsed) {
        reader.expectZ(Constants.AnalysisCacheCleared)
      }
      val msg = reader.readString()
      return Analysis.Cache.Cleared(msg)
    }

    def readLogikaVerifyConfig(): Logika.Verify.Config = {
      val r = readLogikaVerifyConfigT(F)
      return r
    }

    def readLogikaVerifyConfigT(typeParsed: B): Logika.Verify.Config = {
      if (!typeParsed) {
        reader.expectZ(Constants.LogikaVerifyConfig)
      }
      val infoFlow = reader.readB()
      val config = readorgsireumlogikaConfig()
      return Logika.Verify.Config(infoFlow, config)
    }

    def readLogikaVerifyState(): Logika.Verify.State = {
      val r = readLogikaVerifyStateT(F)
      return r
    }

    def readLogikaVerifyStateT(typeParsed: B): Logika.Verify.State = {
      if (!typeParsed) {
        reader.expectZ(Constants.LogikaVerifyState)
      }
      val id = reader.readISZ(reader.readString _)
      val posOpt = reader.readOption(reader.readPosition _)
      val terminated = reader.readB()
      val labels = reader.readISZ(reader.readString _)
      val claims = reader.readString()
      return Logika.Verify.State(id, posOpt, terminated, labels, claims)
    }

    def readLogikaVerifySmt2Query(): Logika.Verify.Smt2Query = {
      val r = readLogikaVerifySmt2QueryT(F)
      return r
    }

    def readLogikaVerifySmt2QueryT(typeParsed: B): Logika.Verify.Smt2Query = {
      if (!typeParsed) {
        reader.expectZ(Constants.LogikaVerifySmt2Query)
      }
      val id = reader.readISZ(reader.readString _)
      val pos = reader.readPosition()
      val isSat = reader.readB()
      val timeInMs = reader.readZ()
      val title = reader.readString()
      val kind = read_logikaSmt2QueryResultKindType()
      val solverName = reader.readString()
      val query = reader.readString()
      val info = reader.readString()
      val output = reader.readString()
      return Logika.Verify.Smt2Query(id, pos, isSat, timeInMs, title, kind, solverName, query, info, output)
    }

    def readLogikaVerifyInfo(): Logika.Verify.Info = {
      val r = readLogikaVerifyInfoT(F)
      return r
    }

    def readLogikaVerifyInfoT(typeParsed: B): Logika.Verify.Info = {
      if (!typeParsed) {
        reader.expectZ(Constants.LogikaVerifyInfo)
      }
      val id = reader.readISZ(reader.readString _)
      val pos = reader.readPosition()
      val kind = readLogikaVerifyInfoKindType()
      val message = reader.readString()
      return Logika.Verify.Info(id, pos, kind, message)
    }

    def readLogikaVerifyInfoKindType(): Logika.Verify.Info.Kind.Type = {
      val r = reader.readZ()
      return Logika.Verify.Info.Kind.byOrdinal(r).get
    }

    def readorgsireumlogikaConfig(): org.sireum.logika.Config = {
      val r = readorgsireumlogikaConfigT(F)
      return r
    }

    def readorgsireumlogikaConfigT(typeParsed: B): org.sireum.logika.Config = {
      if (!typeParsed) {
        reader.expectZ(Constants.orgsireumlogikaConfig)
      }
      val smt2Configs = reader.readISZ(readorgsireumlogikaSmt2Config _)
      val parCores = reader.readZ()
      val sat = reader.readB()
      val rlimit = reader.readZ()
      val timeoutInMs = reader.readZ()
      val charBitWidth = reader.readZ()
      val intBitWidth = reader.readZ()
      val useReal = reader.readB()
      val logPc = reader.readB()
      val logRawPc = reader.readB()
      val logVc = reader.readB()
      val logVcDirOpt = reader.readOption(reader.readString _)
      val dontSplitPfq = reader.readB()
      val splitAll = reader.readB()
      val splitIf = reader.readB()
      val splitMatch = reader.readB()
      val splitContract = reader.readB()
      val simplifiedQuery = reader.readB()
      val checkInfeasiblePatternMatch = reader.readB()
      val fpRoundingMode = reader.readString()
      val smt2Caching = reader.readB()
      val smt2Seq = reader.readB()
      val branchPar = read_logikaConfigBranchParType()
      val atLinesFresh = reader.readB()
      val interp = reader.readB()
      val loopBound = reader.readZ()
      val callBound = reader.readZ()
      val interpContracts = reader.readB()
      val elideEncoding = reader.readB()
      val rawInscription = reader.readB()
      val strictPureMode = read_logikaConfigStrictPureModeType()
      val transitionCache = reader.readB()
      val patternExhaustive = reader.readB()
      val pureFun = reader.readB()
      val detailedInfo = reader.readB()
      val satTimeout = reader.readB()
      val isAuto = reader.readB()
      val background = read_logikaConfigBackgroundModeType()
      val atRewrite = reader.readB()
      val searchPc = reader.readB()
      val rwTrace = reader.readB()
      val rwMax = reader.readZ()
      val rwPar = reader.readB()
      val rwEvalTrace = reader.readB()
      val branchParPredNum = reader.readZ()
      val branchParPredComp = reader.readZ()
      return org.sireum.logika.Config(smt2Configs, parCores, sat, rlimit, timeoutInMs, charBitWidth, intBitWidth, useReal, logPc, logRawPc, logVc, logVcDirOpt, dontSplitPfq, splitAll, splitIf, splitMatch, splitContract, simplifiedQuery, checkInfeasiblePatternMatch, fpRoundingMode, smt2Caching, smt2Seq, branchPar, atLinesFresh, interp, loopBound, callBound, interpContracts, elideEncoding, rawInscription, strictPureMode, transitionCache, patternExhaustive, pureFun, detailedInfo, satTimeout, isAuto, background, atRewrite, searchPc, rwTrace, rwMax, rwPar, rwEvalTrace, branchParPredNum, branchParPredComp)
    }

    def read_logikaConfigBranchParType(): org.sireum.logika.Config.BranchPar.Type = {
      val r = reader.readZ()
      return org.sireum.logika.Config.BranchPar.byOrdinal(r).get
    }

    def read_logikaConfigStrictPureModeType(): org.sireum.logika.Config.StrictPureMode.Type = {
      val r = reader.readZ()
      return org.sireum.logika.Config.StrictPureMode.byOrdinal(r).get
    }

    def read_logikaConfigBackgroundModeType(): org.sireum.logika.Config.BackgroundMode.Type = {
      val r = reader.readZ()
      return org.sireum.logika.Config.BackgroundMode.byOrdinal(r).get
    }

    def readorgsireumlogikaSmt2Config(): org.sireum.logika.Smt2Config = {
      val r = readorgsireumlogikaSmt2ConfigT(F)
      return r
    }

    def readorgsireumlogikaSmt2ConfigT(typeParsed: B): org.sireum.logika.Smt2Config = {
      if (!typeParsed) {
        reader.expectZ(Constants.orgsireumlogikaSmt2Config)
      }
      val isSat = reader.readB()
      val name = reader.readString()
      val exe = reader.readString()
      val opts = reader.readISZ(reader.readString _)
      return org.sireum.logika.Smt2Config(isSat, name, exe, opts)
    }

    def readorgsireumlogikaLoopId(): org.sireum.logika.LoopId = {
      val r = readorgsireumlogikaLoopIdT(F)
      return r
    }

    def readorgsireumlogikaLoopIdT(typeParsed: B): org.sireum.logika.LoopId = {
      if (!typeParsed) {
        reader.expectZ(Constants.orgsireumlogikaLoopId)
      }
      val ids = reader.readISZ(reader.readString _)
      return org.sireum.logika.LoopId(ids)
    }

    def read_logikaSmt2QueryResultKindType(): org.sireum.logika.Smt2Query.Result.Kind.Type = {
      val r = reader.readZ()
      return org.sireum.logika.Smt2Query.Result.Kind.byOrdinal(r).get
    }

    def read_logikaSmt2QueryResult(): org.sireum.logika.Smt2Query.Result = {
      val r = read_logikaSmt2QueryResultT(F)
      return r
    }

    def read_logikaSmt2QueryResultT(typeParsed: B): org.sireum.logika.Smt2Query.Result = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaSmt2QueryResult)
      }
      val kind = read_logikaSmt2QueryResultKindType()
      val solverName = reader.readString()
      val query = reader.readString()
      val info = reader.readString()
      val output = reader.readString()
      val timeMillis = reader.readZ()
      val totalTimeMillis = reader.readZ()
      val cached = reader.readB()
      return org.sireum.logika.Smt2Query.Result(kind, solverName, query, info, output, timeMillis, totalTimeMillis, cached)
    }

    def read_langastPurityType(): org.sireum.lang.ast.Purity.Type = {
      val r = reader.readZ()
      return org.sireum.lang.ast.Purity.byOrdinal(r).get
    }

    def read_langastMethodModeType(): org.sireum.lang.ast.MethodMode.Type = {
      val r = reader.readZ()
      return org.sireum.lang.ast.MethodMode.byOrdinal(r).get
    }

    def read_langastTyped(): org.sireum.lang.ast.Typed = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._langastTypedName => val r = read_langastTypedNameT(T); return r
        case Constants._langastTypedTuple => val r = read_langastTypedTupleT(T); return r
        case Constants._langastTypedFun => val r = read_langastTypedFunT(T); return r
        case Constants._langastTypedTypeVar => val r = read_langastTypedTypeVarT(T); return r
        case Constants._langastTypedPackage => val r = read_langastTypedPackageT(T); return r
        case Constants._langastTypedObject => val r = read_langastTypedObjectT(T); return r
        case Constants._langastTypedEnum => val r = read_langastTypedEnumT(T); return r
        case Constants._langastTypedMethod => val r = read_langastTypedMethodT(T); return r
        case Constants._langastTypedMethods => val r = read_langastTypedMethodsT(T); return r
        case Constants._langastTypedFact => val r = read_langastTypedFactT(T); return r
        case Constants._langastTypedTheorem => val r = read_langastTypedTheoremT(T); return r
        case Constants._langastTypedInv => val r = read_langastTypedInvT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.lang.ast.Typed.")
          val r = read_langastTypedInvT(T)
          return r
      }
    }

    def read_langastTypedVarKindType(): org.sireum.lang.ast.Typed.VarKind.Type = {
      val r = reader.readZ()
      return org.sireum.lang.ast.Typed.VarKind.byOrdinal(r).get
    }

    def read_langastTypedName(): org.sireum.lang.ast.Typed.Name = {
      val r = read_langastTypedNameT(F)
      return r
    }

    def read_langastTypedNameT(typeParsed: B): org.sireum.lang.ast.Typed.Name = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedName)
      }
      val ids = reader.readISZ(reader.readString _)
      val args = reader.readISZ(read_langastTyped _)
      return org.sireum.lang.ast.Typed.Name(ids, args)
    }

    def read_langastTypedTuple(): org.sireum.lang.ast.Typed.Tuple = {
      val r = read_langastTypedTupleT(F)
      return r
    }

    def read_langastTypedTupleT(typeParsed: B): org.sireum.lang.ast.Typed.Tuple = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedTuple)
      }
      val args = reader.readISZ(read_langastTyped _)
      return org.sireum.lang.ast.Typed.Tuple(args)
    }

    def read_langastTypedFun(): org.sireum.lang.ast.Typed.Fun = {
      val r = read_langastTypedFunT(F)
      return r
    }

    def read_langastTypedFunT(typeParsed: B): org.sireum.lang.ast.Typed.Fun = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedFun)
      }
      val purity = read_langastPurityType()
      val isByName = reader.readB()
      val args = reader.readISZ(read_langastTyped _)
      val ret = read_langastTyped()
      return org.sireum.lang.ast.Typed.Fun(purity, isByName, args, ret)
    }

    def read_langastTypedTypeVar(): org.sireum.lang.ast.Typed.TypeVar = {
      val r = read_langastTypedTypeVarT(F)
      return r
    }

    def read_langastTypedTypeVarT(typeParsed: B): org.sireum.lang.ast.Typed.TypeVar = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedTypeVar)
      }
      val id = reader.readString()
      val kind = read_langastTypedVarKindType()
      return org.sireum.lang.ast.Typed.TypeVar(id, kind)
    }

    def read_langastTypedPackage(): org.sireum.lang.ast.Typed.Package = {
      val r = read_langastTypedPackageT(F)
      return r
    }

    def read_langastTypedPackageT(typeParsed: B): org.sireum.lang.ast.Typed.Package = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedPackage)
      }
      val name = reader.readISZ(reader.readString _)
      return org.sireum.lang.ast.Typed.Package(name)
    }

    def read_langastTypedObject(): org.sireum.lang.ast.Typed.Object = {
      val r = read_langastTypedObjectT(F)
      return r
    }

    def read_langastTypedObjectT(typeParsed: B): org.sireum.lang.ast.Typed.Object = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedObject)
      }
      val owner = reader.readISZ(reader.readString _)
      val id = reader.readString()
      return org.sireum.lang.ast.Typed.Object(owner, id)
    }

    def read_langastTypedEnum(): org.sireum.lang.ast.Typed.Enum = {
      val r = read_langastTypedEnumT(F)
      return r
    }

    def read_langastTypedEnumT(typeParsed: B): org.sireum.lang.ast.Typed.Enum = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedEnum)
      }
      val name = reader.readISZ(reader.readString _)
      return org.sireum.lang.ast.Typed.Enum(name)
    }

    def read_langastTypedMethod(): org.sireum.lang.ast.Typed.Method = {
      val r = read_langastTypedMethodT(F)
      return r
    }

    def read_langastTypedMethodT(typeParsed: B): org.sireum.lang.ast.Typed.Method = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedMethod)
      }
      val isInObject = reader.readB()
      val mode = read_langastMethodModeType()
      val typeParams = reader.readISZ(reader.readString _)
      val owner = reader.readISZ(reader.readString _)
      val name = reader.readString()
      val paramNames = reader.readISZ(reader.readString _)
      val tpe = read_langastTypedFun()
      return org.sireum.lang.ast.Typed.Method(isInObject, mode, typeParams, owner, name, paramNames, tpe)
    }

    def read_langastTypedMethods(): org.sireum.lang.ast.Typed.Methods = {
      val r = read_langastTypedMethodsT(F)
      return r
    }

    def read_langastTypedMethodsT(typeParsed: B): org.sireum.lang.ast.Typed.Methods = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedMethods)
      }
      val methods = reader.readISZ(read_langastTypedMethod _)
      return org.sireum.lang.ast.Typed.Methods(methods)
    }

    def read_langastTypedFact(): org.sireum.lang.ast.Typed.Fact = {
      val r = read_langastTypedFactT(F)
      return r
    }

    def read_langastTypedFactT(typeParsed: B): org.sireum.lang.ast.Typed.Fact = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedFact)
      }
      val owner = reader.readISZ(reader.readString _)
      val id = reader.readString()
      return org.sireum.lang.ast.Typed.Fact(owner, id)
    }

    def read_langastTypedTheorem(): org.sireum.lang.ast.Typed.Theorem = {
      val r = read_langastTypedTheoremT(F)
      return r
    }

    def read_langastTypedTheoremT(typeParsed: B): org.sireum.lang.ast.Typed.Theorem = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedTheorem)
      }
      val owner = reader.readISZ(reader.readString _)
      val id = reader.readString()
      return org.sireum.lang.ast.Typed.Theorem(owner, id)
    }

    def read_langastTypedInv(): org.sireum.lang.ast.Typed.Inv = {
      val r = read_langastTypedInvT(F)
      return r
    }

    def read_langastTypedInvT(typeParsed: B): org.sireum.lang.ast.Typed.Inv = {
      if (!typeParsed) {
        reader.expectZ(Constants._langastTypedInv)
      }
      val isInObject = reader.readB()
      val owner = reader.readISZ(reader.readString _)
      val id = reader.readString()
      return org.sireum.lang.ast.Typed.Inv(isInObject, owner, id)
    }

  }

  def to[T](data: ISZ[U8], f: Reader => T): Either[T, MessagePack.ErrorMsg] = {
    val rd = Reader.Default(MessagePack.reader(data))
    rd.reader.init()
    val r = f(rd)
    rd.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromRequest(o: Request, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeRequest(o)
    return w.result
  }

  def toRequest(data: ISZ[U8]): Either[Request, MessagePack.ErrorMsg] = {
    def fRequest(reader: Reader): Request = {
      val r = reader.readRequest()
      return r
    }
    val r = to(data, fRequest _)
    return r
  }

  def fromTerminate(o: Terminate, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeTerminate(o)
    return w.result
  }

  def toTerminate(data: ISZ[U8]): Either[Terminate, MessagePack.ErrorMsg] = {
    def fTerminate(reader: Reader): Terminate = {
      val r = reader.readTerminate()
      return r
    }
    val r = to(data, fTerminate _)
    return r
  }

  def fromResponse(o: Response, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeResponse(o)
    return w.result
  }

  def toResponse(data: ISZ[U8]): Either[Response, MessagePack.ErrorMsg] = {
    def fResponse(reader: Reader): Response = {
      val r = reader.readResponse()
      return r
    }
    val r = to(data, fResponse _)
    return r
  }

  def fromCancel(o: Cancel, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeCancel(o)
    return w.result
  }

  def toCancel(data: ISZ[U8]): Either[Cancel, MessagePack.ErrorMsg] = {
    def fCancel(reader: Reader): Cancel = {
      val r = reader.readCancel()
      return r
    }
    val r = to(data, fCancel _)
    return r
  }

  def fromTiming(o: Timing, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeTiming(o)
    return w.result
  }

  def toTiming(data: ISZ[U8]): Either[Timing, MessagePack.ErrorMsg] = {
    def fTiming(reader: Reader): Timing = {
      val r = reader.readTiming()
      return r
    }
    val r = to(data, fTiming _)
    return r
  }

  def fromSocketPort(o: SocketPort, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeSocketPort(o)
    return w.result
  }

  def toSocketPort(data: ISZ[U8]): Either[SocketPort, MessagePack.ErrorMsg] = {
    def fSocketPort(reader: Reader): SocketPort = {
      val r = reader.readSocketPort()
      return r
    }
    val r = to(data, fSocketPort _)
    return r
  }

  def fromReport(o: Report, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeReport(o)
    return w.result
  }

  def toReport(data: ISZ[U8]): Either[Report, MessagePack.ErrorMsg] = {
    def fReport(reader: Reader): Report = {
      val r = reader.readReport()
      return r
    }
    val r = to(data, fReport _)
    return r
  }

  def fromVersionRequest(o: Version.Request, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeVersionRequest(o)
    return w.result
  }

  def toVersionRequest(data: ISZ[U8]): Either[Version.Request, MessagePack.ErrorMsg] = {
    def fVersionRequest(reader: Reader): Version.Request = {
      val r = reader.readVersionRequest()
      return r
    }
    val r = to(data, fVersionRequest _)
    return r
  }

  def fromVersionResponse(o: Version.Response, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeVersionResponse(o)
    return w.result
  }

  def toVersionResponse(data: ISZ[U8]): Either[Version.Response, MessagePack.ErrorMsg] = {
    def fVersionResponse(reader: Reader): Version.Response = {
      val r = reader.readVersionResponse()
      return r
    }
    val r = to(data, fVersionResponse _)
    return r
  }

  def fromStatusRequest(o: Status.Request, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeStatusRequest(o)
    return w.result
  }

  def toStatusRequest(data: ISZ[U8]): Either[Status.Request, MessagePack.ErrorMsg] = {
    def fStatusRequest(reader: Reader): Status.Request = {
      val r = reader.readStatusRequest()
      return r
    }
    val r = to(data, fStatusRequest _)
    return r
  }

  def fromStatusResponse(o: Status.Response, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeStatusResponse(o)
    return w.result
  }

  def toStatusResponse(data: ISZ[U8]): Either[Status.Response, MessagePack.ErrorMsg] = {
    def fStatusResponse(reader: Reader): Status.Response = {
      val r = reader.readStatusResponse()
      return r
    }
    val r = to(data, fStatusResponse _)
    return r
  }

  def fromSysMLv2CheckFiles(o: SysMLv2.Check.Files, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeSysMLv2CheckFiles(o)
    return w.result
  }

  def toSysMLv2CheckFiles(data: ISZ[U8]): Either[SysMLv2.Check.Files, MessagePack.ErrorMsg] = {
    def fSysMLv2CheckFiles(reader: Reader): SysMLv2.Check.Files = {
      val r = reader.readSysMLv2CheckFiles()
      return r
    }
    val r = to(data, fSysMLv2CheckFiles _)
    return r
  }

  def fromSlangCheck(o: Slang.Check, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeSlangCheck(o)
    return w.result
  }

  def toSlangCheck(data: ISZ[U8]): Either[Slang.Check, MessagePack.ErrorMsg] = {
    def fSlangCheck(reader: Reader): Slang.Check = {
      val r = reader.readSlangCheck()
      return r
    }
    val r = to(data, fSlangCheck _)
    return r
  }

  def fromSlangCheckScript(o: Slang.Check.Script, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeSlangCheckScript(o)
    return w.result
  }

  def toSlangCheckScript(data: ISZ[U8]): Either[Slang.Check.Script, MessagePack.ErrorMsg] = {
    def fSlangCheckScript(reader: Reader): Slang.Check.Script = {
      val r = reader.readSlangCheckScript()
      return r
    }
    val r = to(data, fSlangCheckScript _)
    return r
  }

  def fromSlangCheckProject(o: Slang.Check.Project, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeSlangCheckProject(o)
    return w.result
  }

  def toSlangCheckProject(data: ISZ[U8]): Either[Slang.Check.Project, MessagePack.ErrorMsg] = {
    def fSlangCheckProject(reader: Reader): Slang.Check.Project = {
      val r = reader.readSlangCheckProject()
      return r
    }
    val r = to(data, fSlangCheckProject _)
    return r
  }

  def fromSlangRewriteRequest(o: Slang.Rewrite.Request, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeSlangRewriteRequest(o)
    return w.result
  }

  def toSlangRewriteRequest(data: ISZ[U8]): Either[Slang.Rewrite.Request, MessagePack.ErrorMsg] = {
    def fSlangRewriteRequest(reader: Reader): Slang.Rewrite.Request = {
      val r = reader.readSlangRewriteRequest()
      return r
    }
    val r = to(data, fSlangRewriteRequest _)
    return r
  }

  def fromSlangRewriteResponse(o: Slang.Rewrite.Response, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeSlangRewriteResponse(o)
    return w.result
  }

  def toSlangRewriteResponse(data: ISZ[U8]): Either[Slang.Rewrite.Response, MessagePack.ErrorMsg] = {
    def fSlangRewriteResponse(reader: Reader): Slang.Rewrite.Response = {
      val r = reader.readSlangRewriteResponse()
      return r
    }
    val r = to(data, fSlangRewriteResponse _)
    return r
  }

  def fromAnalysisStart(o: Analysis.Start, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeAnalysisStart(o)
    return w.result
  }

  def toAnalysisStart(data: ISZ[U8]): Either[Analysis.Start, MessagePack.ErrorMsg] = {
    def fAnalysisStart(reader: Reader): Analysis.Start = {
      val r = reader.readAnalysisStart()
      return r
    }
    val r = to(data, fAnalysisStart _)
    return r
  }

  def fromAnalysisCoverage(o: Analysis.Coverage, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeAnalysisCoverage(o)
    return w.result
  }

  def toAnalysisCoverage(data: ISZ[U8]): Either[Analysis.Coverage, MessagePack.ErrorMsg] = {
    def fAnalysisCoverage(reader: Reader): Analysis.Coverage = {
      val r = reader.readAnalysisCoverage()
      return r
    }
    val r = to(data, fAnalysisCoverage _)
    return r
  }

  def fromAnalysisResolvedAst(o: Analysis.ResolvedAst, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeAnalysisResolvedAst(o)
    return w.result
  }

  def toAnalysisResolvedAst(data: ISZ[U8]): Either[Analysis.ResolvedAst, MessagePack.ErrorMsg] = {
    def fAnalysisResolvedAst(reader: Reader): Analysis.ResolvedAst = {
      val r = reader.readAnalysisResolvedAst()
      return r
    }
    val r = to(data, fAnalysisResolvedAst _)
    return r
  }

  def fromAnalysisEnd(o: Analysis.End, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeAnalysisEnd(o)
    return w.result
  }

  def toAnalysisEnd(data: ISZ[U8]): Either[Analysis.End, MessagePack.ErrorMsg] = {
    def fAnalysisEnd(reader: Reader): Analysis.End = {
      val r = reader.readAnalysisEnd()
      return r
    }
    val r = to(data, fAnalysisEnd _)
    return r
  }

  def fromAnalysisCacheClear(o: Analysis.Cache.Clear, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeAnalysisCacheClear(o)
    return w.result
  }

  def toAnalysisCacheClear(data: ISZ[U8]): Either[Analysis.Cache.Clear, MessagePack.ErrorMsg] = {
    def fAnalysisCacheClear(reader: Reader): Analysis.Cache.Clear = {
      val r = reader.readAnalysisCacheClear()
      return r
    }
    val r = to(data, fAnalysisCacheClear _)
    return r
  }

  def fromAnalysisCacheCleared(o: Analysis.Cache.Cleared, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeAnalysisCacheCleared(o)
    return w.result
  }

  def toAnalysisCacheCleared(data: ISZ[U8]): Either[Analysis.Cache.Cleared, MessagePack.ErrorMsg] = {
    def fAnalysisCacheCleared(reader: Reader): Analysis.Cache.Cleared = {
      val r = reader.readAnalysisCacheCleared()
      return r
    }
    val r = to(data, fAnalysisCacheCleared _)
    return r
  }

  def fromLogikaVerifyConfig(o: Logika.Verify.Config, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeLogikaVerifyConfig(o)
    return w.result
  }

  def toLogikaVerifyConfig(data: ISZ[U8]): Either[Logika.Verify.Config, MessagePack.ErrorMsg] = {
    def fLogikaVerifyConfig(reader: Reader): Logika.Verify.Config = {
      val r = reader.readLogikaVerifyConfig()
      return r
    }
    val r = to(data, fLogikaVerifyConfig _)
    return r
  }

  def fromLogikaVerifyState(o: Logika.Verify.State, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeLogikaVerifyState(o)
    return w.result
  }

  def toLogikaVerifyState(data: ISZ[U8]): Either[Logika.Verify.State, MessagePack.ErrorMsg] = {
    def fLogikaVerifyState(reader: Reader): Logika.Verify.State = {
      val r = reader.readLogikaVerifyState()
      return r
    }
    val r = to(data, fLogikaVerifyState _)
    return r
  }

  def fromLogikaVerifySmt2Query(o: Logika.Verify.Smt2Query, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeLogikaVerifySmt2Query(o)
    return w.result
  }

  def toLogikaVerifySmt2Query(data: ISZ[U8]): Either[Logika.Verify.Smt2Query, MessagePack.ErrorMsg] = {
    def fLogikaVerifySmt2Query(reader: Reader): Logika.Verify.Smt2Query = {
      val r = reader.readLogikaVerifySmt2Query()
      return r
    }
    val r = to(data, fLogikaVerifySmt2Query _)
    return r
  }

  def fromLogikaVerifyInfo(o: Logika.Verify.Info, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeLogikaVerifyInfo(o)
    return w.result
  }

  def toLogikaVerifyInfo(data: ISZ[U8]): Either[Logika.Verify.Info, MessagePack.ErrorMsg] = {
    def fLogikaVerifyInfo(reader: Reader): Logika.Verify.Info = {
      val r = reader.readLogikaVerifyInfo()
      return r
    }
    val r = to(data, fLogikaVerifyInfo _)
    return r
  }

  def fromorgsireumlogikaConfig(o: org.sireum.logika.Config, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeorgsireumlogikaConfig(o)
    return w.result
  }

  def toorgsireumlogikaConfig(data: ISZ[U8]): Either[org.sireum.logika.Config, MessagePack.ErrorMsg] = {
    def forgsireumlogikaConfig(reader: Reader): org.sireum.logika.Config = {
      val r = reader.readorgsireumlogikaConfig()
      return r
    }
    val r = to(data, forgsireumlogikaConfig _)
    return r
  }

  def fromorgsireumlogikaSmt2Config(o: org.sireum.logika.Smt2Config, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeorgsireumlogikaSmt2Config(o)
    return w.result
  }

  def toorgsireumlogikaSmt2Config(data: ISZ[U8]): Either[org.sireum.logika.Smt2Config, MessagePack.ErrorMsg] = {
    def forgsireumlogikaSmt2Config(reader: Reader): org.sireum.logika.Smt2Config = {
      val r = reader.readorgsireumlogikaSmt2Config()
      return r
    }
    val r = to(data, forgsireumlogikaSmt2Config _)
    return r
  }

  def fromorgsireumlogikaLoopId(o: org.sireum.logika.LoopId, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeorgsireumlogikaLoopId(o)
    return w.result
  }

  def toorgsireumlogikaLoopId(data: ISZ[U8]): Either[org.sireum.logika.LoopId, MessagePack.ErrorMsg] = {
    def forgsireumlogikaLoopId(reader: Reader): org.sireum.logika.LoopId = {
      val r = reader.readorgsireumlogikaLoopId()
      return r
    }
    val r = to(data, forgsireumlogikaLoopId _)
    return r
  }

  def from_logikaSmt2QueryResult(o: org.sireum.logika.Smt2Query.Result, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaSmt2QueryResult(o)
    return w.result
  }

  def to_logikaSmt2QueryResult(data: ISZ[U8]): Either[org.sireum.logika.Smt2Query.Result, MessagePack.ErrorMsg] = {
    def f_logikaSmt2QueryResult(reader: Reader): org.sireum.logika.Smt2Query.Result = {
      val r = reader.read_logikaSmt2QueryResult()
      return r
    }
    val r = to(data, f_logikaSmt2QueryResult _)
    return r
  }

  def from_langastTyped(o: org.sireum.lang.ast.Typed, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTyped(o)
    return w.result
  }

  def to_langastTyped(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed, MessagePack.ErrorMsg] = {
    def f_langastTyped(reader: Reader): org.sireum.lang.ast.Typed = {
      val r = reader.read_langastTyped()
      return r
    }
    val r = to(data, f_langastTyped _)
    return r
  }

  def from_langastTypedName(o: org.sireum.lang.ast.Typed.Name, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedName(o)
    return w.result
  }

  def to_langastTypedName(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Name, MessagePack.ErrorMsg] = {
    def f_langastTypedName(reader: Reader): org.sireum.lang.ast.Typed.Name = {
      val r = reader.read_langastTypedName()
      return r
    }
    val r = to(data, f_langastTypedName _)
    return r
  }

  def from_langastTypedTuple(o: org.sireum.lang.ast.Typed.Tuple, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedTuple(o)
    return w.result
  }

  def to_langastTypedTuple(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Tuple, MessagePack.ErrorMsg] = {
    def f_langastTypedTuple(reader: Reader): org.sireum.lang.ast.Typed.Tuple = {
      val r = reader.read_langastTypedTuple()
      return r
    }
    val r = to(data, f_langastTypedTuple _)
    return r
  }

  def from_langastTypedFun(o: org.sireum.lang.ast.Typed.Fun, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedFun(o)
    return w.result
  }

  def to_langastTypedFun(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Fun, MessagePack.ErrorMsg] = {
    def f_langastTypedFun(reader: Reader): org.sireum.lang.ast.Typed.Fun = {
      val r = reader.read_langastTypedFun()
      return r
    }
    val r = to(data, f_langastTypedFun _)
    return r
  }

  def from_langastTypedTypeVar(o: org.sireum.lang.ast.Typed.TypeVar, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedTypeVar(o)
    return w.result
  }

  def to_langastTypedTypeVar(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.TypeVar, MessagePack.ErrorMsg] = {
    def f_langastTypedTypeVar(reader: Reader): org.sireum.lang.ast.Typed.TypeVar = {
      val r = reader.read_langastTypedTypeVar()
      return r
    }
    val r = to(data, f_langastTypedTypeVar _)
    return r
  }

  def from_langastTypedPackage(o: org.sireum.lang.ast.Typed.Package, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedPackage(o)
    return w.result
  }

  def to_langastTypedPackage(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Package, MessagePack.ErrorMsg] = {
    def f_langastTypedPackage(reader: Reader): org.sireum.lang.ast.Typed.Package = {
      val r = reader.read_langastTypedPackage()
      return r
    }
    val r = to(data, f_langastTypedPackage _)
    return r
  }

  def from_langastTypedObject(o: org.sireum.lang.ast.Typed.Object, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedObject(o)
    return w.result
  }

  def to_langastTypedObject(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Object, MessagePack.ErrorMsg] = {
    def f_langastTypedObject(reader: Reader): org.sireum.lang.ast.Typed.Object = {
      val r = reader.read_langastTypedObject()
      return r
    }
    val r = to(data, f_langastTypedObject _)
    return r
  }

  def from_langastTypedEnum(o: org.sireum.lang.ast.Typed.Enum, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedEnum(o)
    return w.result
  }

  def to_langastTypedEnum(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Enum, MessagePack.ErrorMsg] = {
    def f_langastTypedEnum(reader: Reader): org.sireum.lang.ast.Typed.Enum = {
      val r = reader.read_langastTypedEnum()
      return r
    }
    val r = to(data, f_langastTypedEnum _)
    return r
  }

  def from_langastTypedMethod(o: org.sireum.lang.ast.Typed.Method, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedMethod(o)
    return w.result
  }

  def to_langastTypedMethod(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Method, MessagePack.ErrorMsg] = {
    def f_langastTypedMethod(reader: Reader): org.sireum.lang.ast.Typed.Method = {
      val r = reader.read_langastTypedMethod()
      return r
    }
    val r = to(data, f_langastTypedMethod _)
    return r
  }

  def from_langastTypedMethods(o: org.sireum.lang.ast.Typed.Methods, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedMethods(o)
    return w.result
  }

  def to_langastTypedMethods(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Methods, MessagePack.ErrorMsg] = {
    def f_langastTypedMethods(reader: Reader): org.sireum.lang.ast.Typed.Methods = {
      val r = reader.read_langastTypedMethods()
      return r
    }
    val r = to(data, f_langastTypedMethods _)
    return r
  }

  def from_langastTypedFact(o: org.sireum.lang.ast.Typed.Fact, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedFact(o)
    return w.result
  }

  def to_langastTypedFact(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Fact, MessagePack.ErrorMsg] = {
    def f_langastTypedFact(reader: Reader): org.sireum.lang.ast.Typed.Fact = {
      val r = reader.read_langastTypedFact()
      return r
    }
    val r = to(data, f_langastTypedFact _)
    return r
  }

  def from_langastTypedTheorem(o: org.sireum.lang.ast.Typed.Theorem, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedTheorem(o)
    return w.result
  }

  def to_langastTypedTheorem(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Theorem, MessagePack.ErrorMsg] = {
    def f_langastTypedTheorem(reader: Reader): org.sireum.lang.ast.Typed.Theorem = {
      val r = reader.read_langastTypedTheorem()
      return r
    }
    val r = to(data, f_langastTypedTheorem _)
    return r
  }

  def from_langastTypedInv(o: org.sireum.lang.ast.Typed.Inv, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_langastTypedInv(o)
    return w.result
  }

  def to_langastTypedInv(data: ISZ[U8]): Either[org.sireum.lang.ast.Typed.Inv, MessagePack.ErrorMsg] = {
    def f_langastTypedInv(reader: Reader): org.sireum.lang.ast.Typed.Inv = {
      val r = reader.read_langastTypedInv()
      return r
    }
    val r = to(data, f_langastTypedInv _)
    return r
  }

}