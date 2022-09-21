// #Sireum
// @formatter:off

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

// This file is auto-generated from Message.scala, Config.scala, Smt2Query.scala, Typed.scala

package org.sireum.server.protocol

import org.sireum._
import org.sireum.Json.Printer._

object JSON {

  object Printer {

    @pure def printRequest(o: Request): ST = {
      o match {
        case o: Terminate => return printTerminate(o)
        case o: Cancel => return printCancel(o)
        case o: Version.Request => return printVersionRequest(o)
        case o: Status.Request => return printStatusRequest(o)
        case o: Slang.Check.Script => return printSlangCheckScript(o)
        case o: Slang.Check.Project => return printSlangCheckProject(o)
        case o: Slang.Rewrite.Request => return printSlangRewriteRequest(o)
        case o: Logika.Verify.Config => return printLogikaVerifyConfig(o)
      }
    }

    @pure def printTerminate(o: Terminate): ST = {
      return printObject(ISZ(
        ("type", st""""Terminate"""")
      ))
    }

    @pure def printResponse(o: Response): ST = {
      o match {
        case o: Timing => return printTiming(o)
        case o: Report => return printReport(o)
        case o: Version.Response => return printVersionResponse(o)
        case o: Status.Response => return printStatusResponse(o)
        case o: Slang.Rewrite.Response => return printSlangRewriteResponse(o)
        case o: Analysis.Start => return printAnalysisStart(o)
        case o: Analysis.End => return printAnalysisEnd(o)
        case o: Logika.Verify.State => return printLogikaVerifyState(o)
        case o: Logika.Verify.Smt2Query => return printLogikaVerifySmt2Query(o)
        case o: Logika.Verify.Info => return printLogikaVerifyInfo(o)
      }
    }

    @pure def printCancel(o: Cancel): ST = {
      return printObject(ISZ(
        ("type", st""""Cancel""""),
        ("id", printISZ(T, o.id, printString _))
      ))
    }

    @pure def printTiming(o: Timing): ST = {
      return printObject(ISZ(
        ("type", st""""Timing""""),
        ("id", printISZ(T, o.id, printString _)),
        ("desc", printString(o.desc)),
        ("timeInMs", printZ(o.timeInMs))
      ))
    }

    @pure def printReport(o: Report): ST = {
      return printObject(ISZ(
        ("type", st""""Report""""),
        ("id", printISZ(T, o.id, printString _)),
        ("message", printMessage(o.message))
      ))
    }

    @pure def printVersionRequest(o: Version.Request): ST = {
      return printObject(ISZ(
        ("type", st""""Version.Request"""")
      ))
    }

    @pure def printVersionResponse(o: Version.Response): ST = {
      return printObject(ISZ(
        ("type", st""""Version.Response""""),
        ("version", printString(o.version))
      ))
    }

    @pure def printStatusRequest(o: Status.Request): ST = {
      return printObject(ISZ(
        ("type", st""""Status.Request"""")
      ))
    }

    @pure def printStatusResponse(o: Status.Response): ST = {
      return printObject(ISZ(
        ("type", st""""Status.Response""""),
        ("totalMemory", printZ(o.totalMemory)),
        ("freeMemory", printZ(o.freeMemory))
      ))
    }

    @pure def printSlangCheck(o: Slang.Check): ST = {
      o match {
        case o: Slang.Check.Script => return printSlangCheckScript(o)
        case o: Slang.Check.Project => return printSlangCheckProject(o)
      }
    }

    @pure def printSlangCheckScript(o: Slang.Check.Script): ST = {
      return printObject(ISZ(
        ("type", st""""Slang.Check.Script""""),
        ("isBackground", printB(o.isBackground)),
        ("logikaEnabled", printB(o.logikaEnabled)),
        ("id", printISZ(T, o.id, printString _)),
        ("uriOpt", printOption(T, o.uriOpt, printString _)),
        ("content", printString(o.content)),
        ("line", printZ(o.line))
      ))
    }

    @pure def printSlangCheckProject(o: Slang.Check.Project): ST = {
      return printObject(ISZ(
        ("type", st""""Slang.Check.Project""""),
        ("isBackground", printB(o.isBackground)),
        ("id", printISZ(T, o.id, printString _)),
        ("proyek", printString(o.proyek)),
        ("files", printHashSMap(T, o.files, printString _, printString _)),
        ("vfiles", printISZ(T, o.vfiles, printString _)),
        ("line", printZ(o.line))
      ))
    }

    @pure def printSlangRewriteKindType(o: Slang.Rewrite.Kind.Type): ST = {
      val value: String = o match {
        case Slang.Rewrite.Kind.InsertConstructorVals => "InsertConstructorVals"
        case Slang.Rewrite.Kind.RenumberProofSteps => "RenumberProofSteps"
        case Slang.Rewrite.Kind.ReplaceEnumSymbols => "ReplaceEnumSymbols"
      }
      return printObject(ISZ(
        ("type", printString("Slang.Rewrite.Kind")),
        ("value", printString(value))
      ))
    }

    @pure def printSlangRewriteRequest(o: Slang.Rewrite.Request): ST = {
      return printObject(ISZ(
        ("type", st""""Slang.Rewrite.Request""""),
        ("id", printISZ(T, o.id, printString _)),
        ("kind", printSlangRewriteKindType(o.kind)),
        ("isScript", printB(o.isScript)),
        ("fileUriOpt", printOption(T, o.fileUriOpt, printString _)),
        ("text", printString(o.text))
      ))
    }

    @pure def printSlangRewriteResponse(o: Slang.Rewrite.Response): ST = {
      return printObject(ISZ(
        ("type", st""""Slang.Rewrite.Response""""),
        ("id", printISZ(T, o.id, printString _)),
        ("kind", printSlangRewriteKindType(o.kind)),
        ("message", printMessage(o.message)),
        ("newTextOpt", printOption(T, o.newTextOpt, printString _)),
        ("numOfRewrites", printZ(o.numOfRewrites))
      ))
    }

    @pure def printAnalysisStart(o: Analysis.Start): ST = {
      return printObject(ISZ(
        ("type", st""""Analysis.Start""""),
        ("id", printISZ(T, o.id, printString _)),
        ("currentTimeMillis", printZ(o.currentTimeMillis))
      ))
    }

    @pure def printAnalysisEnd(o: Analysis.End): ST = {
      return printObject(ISZ(
        ("type", st""""Analysis.End""""),
        ("isBackground", printB(o.isBackground)),
        ("id", printISZ(T, o.id, printString _)),
        ("wasCancelled", printB(o.wasCancelled)),
        ("isIllFormed", printB(o.isIllFormed)),
        ("hasLogika", printB(o.hasLogika)),
        ("totalTimeMillis", printZ(o.totalTimeMillis)),
        ("numOfSmt2Calls", printZ(o.numOfSmt2Calls)),
        ("smt2TimeMillis", printZ(o.smt2TimeMillis)),
        ("numOfInternalErrors", printZ(o.numOfInternalErrors)),
        ("numOfErrors", printZ(o.numOfErrors)),
        ("numOfWarnings", printZ(o.numOfWarnings))
      ))
    }

    @pure def printLogikaVerifyConfig(o: Logika.Verify.Config): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.Config""""),
        ("hint", printB(o.hint)),
        ("smt2query", printB(o.smt2query)),
        ("config", printorgsireumlogikaConfig(o.config))
      ))
    }

    @pure def printLogikaVerifyState(o: Logika.Verify.State): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.State""""),
        ("id", printISZ(T, o.id, printString _)),
        ("posOpt", printOption(F, o.posOpt, printPosition _)),
        ("terminated", printB(o.terminated)),
        ("labels", printISZ(T, o.labels, printString _)),
        ("claims", printString(o.claims))
      ))
    }

    @pure def printLogikaVerifySmt2Query(o: Logika.Verify.Smt2Query): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.Smt2Query""""),
        ("id", printISZ(T, o.id, printString _)),
        ("pos", printPosition(o.pos)),
        ("timeInMs", printZ(o.timeInMs)),
        ("title", printString(o.title)),
        ("kind", print_logikaSmt2QueryResultKindType(o.kind)),
        ("solverName", printString(o.solverName)),
        ("query", printString(o.query)),
        ("info", printString(o.info)),
        ("output", printString(o.output))
      ))
    }

    @pure def printLogikaVerifyInfo(o: Logika.Verify.Info): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.Info""""),
        ("id", printISZ(T, o.id, printString _)),
        ("pos", printPosition(o.pos)),
        ("kind", printLogikaVerifyInfoKindType(o.kind)),
        ("message", printString(o.message))
      ))
    }

    @pure def printLogikaVerifyInfoKindType(o: Logika.Verify.Info.Kind.Type): ST = {
      val value: String = o match {
        case Logika.Verify.Info.Kind.Verified => "Verified"
      }
      return printObject(ISZ(
        ("type", printString("Logika.Verify.Info.Kind")),
        ("value", printString(value))
      ))
    }

    @pure def printorgsireumlogikaConfig(o: org.sireum.logika.Config): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.Config""""),
        ("smt2Configs", printISZ(F, o.smt2Configs, printorgsireumlogikaSmt2Config _)),
        ("parCores", printZ(o.parCores)),
        ("sat", printB(o.sat)),
        ("rlimit", printZ(o.rlimit)),
        ("timeoutInMs", printZ(o.timeoutInMs)),
        ("defaultLoopBound", printZ(o.defaultLoopBound)),
        ("loopBounds", printHashMap(F, o.loopBounds, printorgsireumlogikaLoopId _, printZ _)),
        ("unroll", printB(o.unroll)),
        ("charBitWidth", printZ(o.charBitWidth)),
        ("intBitWidth", printZ(o.intBitWidth)),
        ("useReal", printB(o.useReal)),
        ("logPc", printB(o.logPc)),
        ("logRawPc", printB(o.logRawPc)),
        ("logVc", printB(o.logVc)),
        ("logVcDirOpt", printOption(T, o.logVcDirOpt, printString _)),
        ("dontSplitPfq", printB(o.dontSplitPfq)),
        ("splitAll", printB(o.splitAll)),
        ("splitIf", printB(o.splitIf)),
        ("splitMatch", printB(o.splitMatch)),
        ("splitContract", printB(o.splitContract)),
        ("simplifiedQuery", printB(o.simplifiedQuery)),
        ("checkInfeasiblePatternMatch", printB(o.checkInfeasiblePatternMatch)),
        ("fpRoundingMode", printString(o.fpRoundingMode)),
        ("caching", printB(o.caching)),
        ("smt2Seq", printB(o.smt2Seq)),
        ("branchPar", print_logikaConfigBranchParType(o.branchPar)),
        ("branchParCores", printZ(o.branchParCores)),
        ("atLinesFresh", printB(o.atLinesFresh))
      ))
    }

    @pure def print_logikaConfigBranchParType(o: org.sireum.logika.Config.BranchPar.Type): ST = {
      val value: String = o match {
        case org.sireum.logika.Config.BranchPar.Disabled => "Disabled"
        case org.sireum.logika.Config.BranchPar.OnlyAllReturns => "OnlyAllReturns"
        case org.sireum.logika.Config.BranchPar.All => "All"
      }
      return printObject(ISZ(
        ("type", printString("org.sireum.logika.Config.BranchPar")),
        ("value", printString(value))
      ))
    }

    @pure def printorgsireumlogikaSmt2Config(o: org.sireum.logika.Smt2Config): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.Smt2Config""""),
        ("isSat", printB(o.isSat)),
        ("name", printString(o.name)),
        ("exe", printString(o.exe)),
        ("opts", printISZ(T, o.opts, printString _))
      ))
    }

    @pure def printorgsireumlogikaLoopId(o: org.sireum.logika.LoopId): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.LoopId""""),
        ("ids", printISZ(T, o.ids, printString _))
      ))
    }

    @pure def print_logikaSmt2QueryResultKindType(o: org.sireum.logika.Smt2Query.Result.Kind.Type): ST = {
      val value: String = o match {
        case org.sireum.logika.Smt2Query.Result.Kind.Sat => "Sat"
        case org.sireum.logika.Smt2Query.Result.Kind.Unsat => "Unsat"
        case org.sireum.logika.Smt2Query.Result.Kind.Unknown => "Unknown"
        case org.sireum.logika.Smt2Query.Result.Kind.Timeout => "Timeout"
        case org.sireum.logika.Smt2Query.Result.Kind.Error => "Error"
      }
      return printObject(ISZ(
        ("type", printString("org.sireum.logika.Smt2Query.Result.Kind")),
        ("value", printString(value))
      ))
    }

    @pure def print_logikaSmt2QueryResult(o: org.sireum.logika.Smt2Query.Result): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.Smt2Query.Result""""),
        ("kind", print_logikaSmt2QueryResultKindType(o.kind)),
        ("solverName", printString(o.solverName)),
        ("query", printString(o.query)),
        ("info", printString(o.info)),
        ("output", printString(o.output)),
        ("timeMillis", printZ(o.timeMillis)),
        ("cached", printB(o.cached))
      ))
    }

    @pure def print_langastMethodModeType(o: org.sireum.lang.ast.MethodMode.Type): ST = {
      val value: String = o match {
        case org.sireum.lang.ast.MethodMode.Method => "Method"
        case org.sireum.lang.ast.MethodMode.Spec => "Spec"
        case org.sireum.lang.ast.MethodMode.Ext => "Ext"
        case org.sireum.lang.ast.MethodMode.Just => "Just"
        case org.sireum.lang.ast.MethodMode.Constructor => "Constructor"
        case org.sireum.lang.ast.MethodMode.Copy => "Copy"
        case org.sireum.lang.ast.MethodMode.Extractor => "Extractor"
        case org.sireum.lang.ast.MethodMode.ObjectConstructor => "ObjectConstructor"
        case org.sireum.lang.ast.MethodMode.Select => "Select"
        case org.sireum.lang.ast.MethodMode.Store => "Store"
      }
      return printObject(ISZ(
        ("type", printString("org.sireum.lang.ast.MethodMode")),
        ("value", printString(value))
      ))
    }

    @pure def print_langastTyped(o: org.sireum.lang.ast.Typed): ST = {
      o match {
        case o: org.sireum.lang.ast.Typed.Name => return print_langastTypedName(o)
        case o: org.sireum.lang.ast.Typed.Tuple => return print_langastTypedTuple(o)
        case o: org.sireum.lang.ast.Typed.Fun => return print_langastTypedFun(o)
        case o: org.sireum.lang.ast.Typed.TypeVar => return print_langastTypedTypeVar(o)
        case o: org.sireum.lang.ast.Typed.Package => return print_langastTypedPackage(o)
        case o: org.sireum.lang.ast.Typed.Object => return print_langastTypedObject(o)
        case o: org.sireum.lang.ast.Typed.Enum => return print_langastTypedEnum(o)
        case o: org.sireum.lang.ast.Typed.Method => return print_langastTypedMethod(o)
        case o: org.sireum.lang.ast.Typed.Methods => return print_langastTypedMethods(o)
        case o: org.sireum.lang.ast.Typed.Fact => return print_langastTypedFact(o)
        case o: org.sireum.lang.ast.Typed.Theorem => return print_langastTypedTheorem(o)
        case o: org.sireum.lang.ast.Typed.Inv => return print_langastTypedInv(o)
      }
    }

    @pure def print_langastTypedName(o: org.sireum.lang.ast.Typed.Name): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Name""""),
        ("ids", printISZ(T, o.ids, printString _)),
        ("args", printISZ(F, o.args, print_langastTyped _))
      ))
    }

    @pure def print_langastTypedTuple(o: org.sireum.lang.ast.Typed.Tuple): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Tuple""""),
        ("args", printISZ(F, o.args, print_langastTyped _))
      ))
    }

    @pure def print_langastTypedFun(o: org.sireum.lang.ast.Typed.Fun): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Fun""""),
        ("isPure", printB(o.isPure)),
        ("isByName", printB(o.isByName)),
        ("args", printISZ(F, o.args, print_langastTyped _)),
        ("ret", print_langastTyped(o.ret))
      ))
    }

    @pure def print_langastTypedTypeVar(o: org.sireum.lang.ast.Typed.TypeVar): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.TypeVar""""),
        ("id", printString(o.id)),
        ("isImmutable", printB(o.isImmutable))
      ))
    }

    @pure def print_langastTypedPackage(o: org.sireum.lang.ast.Typed.Package): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Package""""),
        ("name", printISZ(T, o.name, printString _))
      ))
    }

    @pure def print_langastTypedObject(o: org.sireum.lang.ast.Typed.Object): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Object""""),
        ("owner", printISZ(T, o.owner, printString _)),
        ("id", printString(o.id))
      ))
    }

    @pure def print_langastTypedEnum(o: org.sireum.lang.ast.Typed.Enum): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Enum""""),
        ("name", printISZ(T, o.name, printString _))
      ))
    }

    @pure def print_langastTypedMethod(o: org.sireum.lang.ast.Typed.Method): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Method""""),
        ("isInObject", printB(o.isInObject)),
        ("mode", print_langastMethodModeType(o.mode)),
        ("typeParams", printISZ(T, o.typeParams, printString _)),
        ("owner", printISZ(T, o.owner, printString _)),
        ("name", printString(o.name)),
        ("paramNames", printISZ(T, o.paramNames, printString _)),
        ("tpe", print_langastTypedFun(o.tpe))
      ))
    }

    @pure def print_langastTypedMethods(o: org.sireum.lang.ast.Typed.Methods): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Methods""""),
        ("methods", printISZ(F, o.methods, print_langastTypedMethod _))
      ))
    }

    @pure def print_langastTypedFact(o: org.sireum.lang.ast.Typed.Fact): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Fact""""),
        ("owner", printISZ(T, o.owner, printString _)),
        ("id", printString(o.id))
      ))
    }

    @pure def print_langastTypedTheorem(o: org.sireum.lang.ast.Typed.Theorem): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Theorem""""),
        ("owner", printISZ(T, o.owner, printString _)),
        ("id", printString(o.id))
      ))
    }

    @pure def print_langastTypedInv(o: org.sireum.lang.ast.Typed.Inv): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.lang.ast.Typed.Inv""""),
        ("isInObject", printB(o.isInObject)),
        ("owner", printISZ(T, o.owner, printString _)),
        ("id", printString(o.id))
      ))
    }

  }

  @record class Parser(val input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parseRequest(): Request = {
      val t = parser.parseObjectTypes(ISZ("Terminate", "Cancel", "Version.Request", "Status.Request", "Slang.Check.Script", "Slang.Check.Project", "Slang.Rewrite.Request", "Logika.Verify.Config"))
      t.native match {
        case "Terminate" => val r = parseTerminateT(T); return r
        case "Cancel" => val r = parseCancelT(T); return r
        case "Version.Request" => val r = parseVersionRequestT(T); return r
        case "Status.Request" => val r = parseStatusRequestT(T); return r
        case "Slang.Check.Script" => val r = parseSlangCheckScriptT(T); return r
        case "Slang.Check.Project" => val r = parseSlangCheckProjectT(T); return r
        case "Slang.Rewrite.Request" => val r = parseSlangRewriteRequestT(T); return r
        case "Logika.Verify.Config" => val r = parseLogikaVerifyConfigT(T); return r
        case _ => val r = parseLogikaVerifyConfigT(T); return r
      }
    }

    def parseTerminate(): Terminate = {
      val r = parseTerminateT(F)
      return r
    }

    def parseTerminateT(typeParsed: B): Terminate = {
      if (!typeParsed) {
        parser.parseObjectType("Terminate")
      }
      return Terminate()
    }

    def parseResponse(): Response = {
      val t = parser.parseObjectTypes(ISZ("Timing", "Report", "Version.Response", "Status.Response", "Slang.Rewrite.Response", "Analysis.Start", "Analysis.End", "Logika.Verify.State", "Logika.Verify.Smt2Query", "Logika.Verify.Info"))
      t.native match {
        case "Timing" => val r = parseTimingT(T); return r
        case "Report" => val r = parseReportT(T); return r
        case "Version.Response" => val r = parseVersionResponseT(T); return r
        case "Status.Response" => val r = parseStatusResponseT(T); return r
        case "Slang.Rewrite.Response" => val r = parseSlangRewriteResponseT(T); return r
        case "Analysis.Start" => val r = parseAnalysisStartT(T); return r
        case "Analysis.End" => val r = parseAnalysisEndT(T); return r
        case "Logika.Verify.State" => val r = parseLogikaVerifyStateT(T); return r
        case "Logika.Verify.Smt2Query" => val r = parseLogikaVerifySmt2QueryT(T); return r
        case "Logika.Verify.Info" => val r = parseLogikaVerifyInfoT(T); return r
        case _ => val r = parseLogikaVerifyInfoT(T); return r
      }
    }

    def parseCancel(): Cancel = {
      val r = parseCancelT(F)
      return r
    }

    def parseCancelT(typeParsed: B): Cancel = {
      if (!typeParsed) {
        parser.parseObjectType("Cancel")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return Cancel(id)
    }

    def parseTiming(): Timing = {
      val r = parseTimingT(F)
      return r
    }

    def parseTimingT(typeParsed: B): Timing = {
      if (!typeParsed) {
        parser.parseObjectType("Timing")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("desc")
      val desc = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("timeInMs")
      val timeInMs = parser.parseZ()
      parser.parseObjectNext()
      return Timing(id, desc, timeInMs)
    }

    def parseReport(): Report = {
      val r = parseReportT(F)
      return r
    }

    def parseReportT(typeParsed: B): Report = {
      if (!typeParsed) {
        parser.parseObjectType("Report")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("message")
      val message = parser.parseMessage()
      parser.parseObjectNext()
      return Report(id, message)
    }

    def parseVersionRequest(): Version.Request = {
      val r = parseVersionRequestT(F)
      return r
    }

    def parseVersionRequestT(typeParsed: B): Version.Request = {
      if (!typeParsed) {
        parser.parseObjectType("Version.Request")
      }
      return Version.Request()
    }

    def parseVersionResponse(): Version.Response = {
      val r = parseVersionResponseT(F)
      return r
    }

    def parseVersionResponseT(typeParsed: B): Version.Response = {
      if (!typeParsed) {
        parser.parseObjectType("Version.Response")
      }
      parser.parseObjectKey("version")
      val version = parser.parseString()
      parser.parseObjectNext()
      return Version.Response(version)
    }

    def parseStatusRequest(): Status.Request = {
      val r = parseStatusRequestT(F)
      return r
    }

    def parseStatusRequestT(typeParsed: B): Status.Request = {
      if (!typeParsed) {
        parser.parseObjectType("Status.Request")
      }
      return Status.Request()
    }

    def parseStatusResponse(): Status.Response = {
      val r = parseStatusResponseT(F)
      return r
    }

    def parseStatusResponseT(typeParsed: B): Status.Response = {
      if (!typeParsed) {
        parser.parseObjectType("Status.Response")
      }
      parser.parseObjectKey("totalMemory")
      val totalMemory = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("freeMemory")
      val freeMemory = parser.parseZ()
      parser.parseObjectNext()
      return Status.Response(totalMemory, freeMemory)
    }

    def parseSlangCheck(): Slang.Check = {
      val t = parser.parseObjectTypes(ISZ("Slang.Check.Script", "Slang.Check.Project"))
      t.native match {
        case "Slang.Check.Script" => val r = parseSlangCheckScriptT(T); return r
        case "Slang.Check.Project" => val r = parseSlangCheckProjectT(T); return r
        case _ => val r = parseSlangCheckProjectT(T); return r
      }
    }

    def parseSlangCheckScript(): Slang.Check.Script = {
      val r = parseSlangCheckScriptT(F)
      return r
    }

    def parseSlangCheckScriptT(typeParsed: B): Slang.Check.Script = {
      if (!typeParsed) {
        parser.parseObjectType("Slang.Check.Script")
      }
      parser.parseObjectKey("isBackground")
      val isBackground = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("logikaEnabled")
      val logikaEnabled = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("uriOpt")
      val uriOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("content")
      val content = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("line")
      val line = parser.parseZ()
      parser.parseObjectNext()
      return Slang.Check.Script(isBackground, logikaEnabled, id, uriOpt, content, line)
    }

    def parseSlangCheckProject(): Slang.Check.Project = {
      val r = parseSlangCheckProjectT(F)
      return r
    }

    def parseSlangCheckProjectT(typeParsed: B): Slang.Check.Project = {
      if (!typeParsed) {
        parser.parseObjectType("Slang.Check.Project")
      }
      parser.parseObjectKey("isBackground")
      val isBackground = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("proyek")
      val proyek = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("files")
      val files = parser.parseHashSMap(parser.parseString _, parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("vfiles")
      val vfiles = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("line")
      val line = parser.parseZ()
      parser.parseObjectNext()
      return Slang.Check.Project(isBackground, id, proyek, files, vfiles, line)
    }

    def parseSlangRewriteKindType(): Slang.Rewrite.Kind.Type = {
      val r = parseSlangRewriteKindT(F)
      return r
    }

    def parseSlangRewriteKindT(typeParsed: B): Slang.Rewrite.Kind.Type = {
      if (!typeParsed) {
        parser.parseObjectType("Slang.Rewrite.Kind")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      Slang.Rewrite.Kind.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for Slang.Rewrite.Kind.")
          return Slang.Rewrite.Kind.byOrdinal(0).get
      }
    }

    def parseSlangRewriteRequest(): Slang.Rewrite.Request = {
      val r = parseSlangRewriteRequestT(F)
      return r
    }

    def parseSlangRewriteRequestT(typeParsed: B): Slang.Rewrite.Request = {
      if (!typeParsed) {
        parser.parseObjectType("Slang.Rewrite.Request")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("kind")
      val kind = parseSlangRewriteKindType()
      parser.parseObjectNext()
      parser.parseObjectKey("isScript")
      val isScript = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("fileUriOpt")
      val fileUriOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("text")
      val text = parser.parseString()
      parser.parseObjectNext()
      return Slang.Rewrite.Request(id, kind, isScript, fileUriOpt, text)
    }

    def parseSlangRewriteResponse(): Slang.Rewrite.Response = {
      val r = parseSlangRewriteResponseT(F)
      return r
    }

    def parseSlangRewriteResponseT(typeParsed: B): Slang.Rewrite.Response = {
      if (!typeParsed) {
        parser.parseObjectType("Slang.Rewrite.Response")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("kind")
      val kind = parseSlangRewriteKindType()
      parser.parseObjectNext()
      parser.parseObjectKey("message")
      val message = parser.parseMessage()
      parser.parseObjectNext()
      parser.parseObjectKey("newTextOpt")
      val newTextOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("numOfRewrites")
      val numOfRewrites = parser.parseZ()
      parser.parseObjectNext()
      return Slang.Rewrite.Response(id, kind, message, newTextOpt, numOfRewrites)
    }

    def parseAnalysisStart(): Analysis.Start = {
      val r = parseAnalysisStartT(F)
      return r
    }

    def parseAnalysisStartT(typeParsed: B): Analysis.Start = {
      if (!typeParsed) {
        parser.parseObjectType("Analysis.Start")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("currentTimeMillis")
      val currentTimeMillis = parser.parseZ()
      parser.parseObjectNext()
      return Analysis.Start(id, currentTimeMillis)
    }

    def parseAnalysisEnd(): Analysis.End = {
      val r = parseAnalysisEndT(F)
      return r
    }

    def parseAnalysisEndT(typeParsed: B): Analysis.End = {
      if (!typeParsed) {
        parser.parseObjectType("Analysis.End")
      }
      parser.parseObjectKey("isBackground")
      val isBackground = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("wasCancelled")
      val wasCancelled = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("isIllFormed")
      val isIllFormed = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("hasLogika")
      val hasLogika = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("totalTimeMillis")
      val totalTimeMillis = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("numOfSmt2Calls")
      val numOfSmt2Calls = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("smt2TimeMillis")
      val smt2TimeMillis = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("numOfInternalErrors")
      val numOfInternalErrors = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("numOfErrors")
      val numOfErrors = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("numOfWarnings")
      val numOfWarnings = parser.parseZ()
      parser.parseObjectNext()
      return Analysis.End(isBackground, id, wasCancelled, isIllFormed, hasLogika, totalTimeMillis, numOfSmt2Calls, smt2TimeMillis, numOfInternalErrors, numOfErrors, numOfWarnings)
    }

    def parseLogikaVerifyConfig(): Logika.Verify.Config = {
      val r = parseLogikaVerifyConfigT(F)
      return r
    }

    def parseLogikaVerifyConfigT(typeParsed: B): Logika.Verify.Config = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.Config")
      }
      parser.parseObjectKey("hint")
      val hint = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("smt2query")
      val smt2query = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("config")
      val config = parseorgsireumlogikaConfig()
      parser.parseObjectNext()
      return Logika.Verify.Config(hint, smt2query, config)
    }

    def parseLogikaVerifyState(): Logika.Verify.State = {
      val r = parseLogikaVerifyStateT(F)
      return r
    }

    def parseLogikaVerifyStateT(typeParsed: B): Logika.Verify.State = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.State")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("posOpt")
      val posOpt = parser.parseOption(parser.parsePosition _)
      parser.parseObjectNext()
      parser.parseObjectKey("terminated")
      val terminated = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("labels")
      val labels = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("claims")
      val claims = parser.parseString()
      parser.parseObjectNext()
      return Logika.Verify.State(id, posOpt, terminated, labels, claims)
    }

    def parseLogikaVerifySmt2Query(): Logika.Verify.Smt2Query = {
      val r = parseLogikaVerifySmt2QueryT(F)
      return r
    }

    def parseLogikaVerifySmt2QueryT(typeParsed: B): Logika.Verify.Smt2Query = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.Smt2Query")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      parser.parseObjectKey("timeInMs")
      val timeInMs = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("title")
      val title = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("kind")
      val kind = parse_logikaSmt2QueryResultKindType()
      parser.parseObjectNext()
      parser.parseObjectKey("solverName")
      val solverName = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("query")
      val query = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("info")
      val info = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("output")
      val output = parser.parseString()
      parser.parseObjectNext()
      return Logika.Verify.Smt2Query(id, pos, timeInMs, title, kind, solverName, query, info, output)
    }

    def parseLogikaVerifyInfo(): Logika.Verify.Info = {
      val r = parseLogikaVerifyInfoT(F)
      return r
    }

    def parseLogikaVerifyInfoT(typeParsed: B): Logika.Verify.Info = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.Info")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      parser.parseObjectKey("kind")
      val kind = parseLogikaVerifyInfoKindType()
      parser.parseObjectNext()
      parser.parseObjectKey("message")
      val message = parser.parseString()
      parser.parseObjectNext()
      return Logika.Verify.Info(id, pos, kind, message)
    }

    def parseLogikaVerifyInfoKindType(): Logika.Verify.Info.Kind.Type = {
      val r = parseLogikaVerifyInfoKindT(F)
      return r
    }

    def parseLogikaVerifyInfoKindT(typeParsed: B): Logika.Verify.Info.Kind.Type = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.Info.Kind")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      Logika.Verify.Info.Kind.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for Logika.Verify.Info.Kind.")
          return Logika.Verify.Info.Kind.byOrdinal(0).get
      }
    }

    def parseorgsireumlogikaConfig(): org.sireum.logika.Config = {
      val r = parseorgsireumlogikaConfigT(F)
      return r
    }

    def parseorgsireumlogikaConfigT(typeParsed: B): org.sireum.logika.Config = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.Config")
      }
      parser.parseObjectKey("smt2Configs")
      val smt2Configs = parser.parseISZ(parseorgsireumlogikaSmt2Config _)
      parser.parseObjectNext()
      parser.parseObjectKey("parCores")
      val parCores = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("sat")
      val sat = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("rlimit")
      val rlimit = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("timeoutInMs")
      val timeoutInMs = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("defaultLoopBound")
      val defaultLoopBound = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("loopBounds")
      val loopBounds = parser.parseHashMap(parseorgsireumlogikaLoopId _, parser.parseZ _)
      parser.parseObjectNext()
      parser.parseObjectKey("unroll")
      val unroll = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("charBitWidth")
      val charBitWidth = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("intBitWidth")
      val intBitWidth = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("useReal")
      val useReal = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("logPc")
      val logPc = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("logRawPc")
      val logRawPc = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("logVc")
      val logVc = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("logVcDirOpt")
      val logVcDirOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("dontSplitPfq")
      val dontSplitPfq = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("splitAll")
      val splitAll = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("splitIf")
      val splitIf = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("splitMatch")
      val splitMatch = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("splitContract")
      val splitContract = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("simplifiedQuery")
      val simplifiedQuery = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("checkInfeasiblePatternMatch")
      val checkInfeasiblePatternMatch = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("fpRoundingMode")
      val fpRoundingMode = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("caching")
      val caching = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("smt2Seq")
      val smt2Seq = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("branchPar")
      val branchPar = parse_logikaConfigBranchParType()
      parser.parseObjectNext()
      parser.parseObjectKey("branchParCores")
      val branchParCores = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("atLinesFresh")
      val atLinesFresh = parser.parseB()
      parser.parseObjectNext()
      return org.sireum.logika.Config(smt2Configs, parCores, sat, rlimit, timeoutInMs, defaultLoopBound, loopBounds, unroll, charBitWidth, intBitWidth, useReal, logPc, logRawPc, logVc, logVcDirOpt, dontSplitPfq, splitAll, splitIf, splitMatch, splitContract, simplifiedQuery, checkInfeasiblePatternMatch, fpRoundingMode, caching, smt2Seq, branchPar, branchParCores, atLinesFresh)
    }

    def parse_logikaConfigBranchParType(): org.sireum.logika.Config.BranchPar.Type = {
      val r = parse_logikaConfigBranchParT(F)
      return r
    }

    def parse_logikaConfigBranchParT(typeParsed: B): org.sireum.logika.Config.BranchPar.Type = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.Config.BranchPar")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      org.sireum.logika.Config.BranchPar.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for org.sireum.logika.Config.BranchPar.")
          return org.sireum.logika.Config.BranchPar.byOrdinal(0).get
      }
    }

    def parseorgsireumlogikaSmt2Config(): org.sireum.logika.Smt2Config = {
      val r = parseorgsireumlogikaSmt2ConfigT(F)
      return r
    }

    def parseorgsireumlogikaSmt2ConfigT(typeParsed: B): org.sireum.logika.Smt2Config = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.Smt2Config")
      }
      parser.parseObjectKey("isSat")
      val isSat = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("exe")
      val exe = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("opts")
      val opts = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return org.sireum.logika.Smt2Config(isSat, name, exe, opts)
    }

    def parseorgsireumlogikaLoopId(): org.sireum.logika.LoopId = {
      val r = parseorgsireumlogikaLoopIdT(F)
      return r
    }

    def parseorgsireumlogikaLoopIdT(typeParsed: B): org.sireum.logika.LoopId = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.LoopId")
      }
      parser.parseObjectKey("ids")
      val ids = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return org.sireum.logika.LoopId(ids)
    }

    def parse_logikaSmt2QueryResultKindType(): org.sireum.logika.Smt2Query.Result.Kind.Type = {
      val r = parse_logikaSmt2QueryResultKindT(F)
      return r
    }

    def parse_logikaSmt2QueryResultKindT(typeParsed: B): org.sireum.logika.Smt2Query.Result.Kind.Type = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.Smt2Query.Result.Kind")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      org.sireum.logika.Smt2Query.Result.Kind.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for org.sireum.logika.Smt2Query.Result.Kind.")
          return org.sireum.logika.Smt2Query.Result.Kind.byOrdinal(0).get
      }
    }

    def parse_logikaSmt2QueryResult(): org.sireum.logika.Smt2Query.Result = {
      val r = parse_logikaSmt2QueryResultT(F)
      return r
    }

    def parse_logikaSmt2QueryResultT(typeParsed: B): org.sireum.logika.Smt2Query.Result = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.Smt2Query.Result")
      }
      parser.parseObjectKey("kind")
      val kind = parse_logikaSmt2QueryResultKindType()
      parser.parseObjectNext()
      parser.parseObjectKey("solverName")
      val solverName = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("query")
      val query = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("info")
      val info = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("output")
      val output = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("timeMillis")
      val timeMillis = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("cached")
      val cached = parser.parseB()
      parser.parseObjectNext()
      return org.sireum.logika.Smt2Query.Result(kind, solverName, query, info, output, timeMillis, cached)
    }

    def parse_langastMethodModeType(): org.sireum.lang.ast.MethodMode.Type = {
      val r = parse_langastMethodModeT(F)
      return r
    }

    def parse_langastMethodModeT(typeParsed: B): org.sireum.lang.ast.MethodMode.Type = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.MethodMode")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      org.sireum.lang.ast.MethodMode.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for org.sireum.lang.ast.MethodMode.")
          return org.sireum.lang.ast.MethodMode.byOrdinal(0).get
      }
    }

    def parse_langastTyped(): org.sireum.lang.ast.Typed = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.lang.ast.Typed.Name", "org.sireum.lang.ast.Typed.Tuple", "org.sireum.lang.ast.Typed.Fun", "org.sireum.lang.ast.Typed.TypeVar", "org.sireum.lang.ast.Typed.Package", "org.sireum.lang.ast.Typed.Object", "org.sireum.lang.ast.Typed.Enum", "org.sireum.lang.ast.Typed.Method", "org.sireum.lang.ast.Typed.Methods", "org.sireum.lang.ast.Typed.Fact", "org.sireum.lang.ast.Typed.Theorem", "org.sireum.lang.ast.Typed.Inv"))
      t.native match {
        case "org.sireum.lang.ast.Typed.Name" => val r = parse_langastTypedNameT(T); return r
        case "org.sireum.lang.ast.Typed.Tuple" => val r = parse_langastTypedTupleT(T); return r
        case "org.sireum.lang.ast.Typed.Fun" => val r = parse_langastTypedFunT(T); return r
        case "org.sireum.lang.ast.Typed.TypeVar" => val r = parse_langastTypedTypeVarT(T); return r
        case "org.sireum.lang.ast.Typed.Package" => val r = parse_langastTypedPackageT(T); return r
        case "org.sireum.lang.ast.Typed.Object" => val r = parse_langastTypedObjectT(T); return r
        case "org.sireum.lang.ast.Typed.Enum" => val r = parse_langastTypedEnumT(T); return r
        case "org.sireum.lang.ast.Typed.Method" => val r = parse_langastTypedMethodT(T); return r
        case "org.sireum.lang.ast.Typed.Methods" => val r = parse_langastTypedMethodsT(T); return r
        case "org.sireum.lang.ast.Typed.Fact" => val r = parse_langastTypedFactT(T); return r
        case "org.sireum.lang.ast.Typed.Theorem" => val r = parse_langastTypedTheoremT(T); return r
        case "org.sireum.lang.ast.Typed.Inv" => val r = parse_langastTypedInvT(T); return r
        case _ => val r = parse_langastTypedInvT(T); return r
      }
    }

    def parse_langastTypedName(): org.sireum.lang.ast.Typed.Name = {
      val r = parse_langastTypedNameT(F)
      return r
    }

    def parse_langastTypedNameT(typeParsed: B): org.sireum.lang.ast.Typed.Name = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Name")
      }
      parser.parseObjectKey("ids")
      val ids = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_langastTyped _)
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Name(ids, args)
    }

    def parse_langastTypedTuple(): org.sireum.lang.ast.Typed.Tuple = {
      val r = parse_langastTypedTupleT(F)
      return r
    }

    def parse_langastTypedTupleT(typeParsed: B): org.sireum.lang.ast.Typed.Tuple = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Tuple")
      }
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_langastTyped _)
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Tuple(args)
    }

    def parse_langastTypedFun(): org.sireum.lang.ast.Typed.Fun = {
      val r = parse_langastTypedFunT(F)
      return r
    }

    def parse_langastTypedFunT(typeParsed: B): org.sireum.lang.ast.Typed.Fun = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Fun")
      }
      parser.parseObjectKey("isPure")
      val isPure = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("isByName")
      val isByName = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_langastTyped _)
      parser.parseObjectNext()
      parser.parseObjectKey("ret")
      val ret = parse_langastTyped()
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Fun(isPure, isByName, args, ret)
    }

    def parse_langastTypedTypeVar(): org.sireum.lang.ast.Typed.TypeVar = {
      val r = parse_langastTypedTypeVarT(F)
      return r
    }

    def parse_langastTypedTypeVarT(typeParsed: B): org.sireum.lang.ast.Typed.TypeVar = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.TypeVar")
      }
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("isImmutable")
      val isImmutable = parser.parseB()
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.TypeVar(id, isImmutable)
    }

    def parse_langastTypedPackage(): org.sireum.lang.ast.Typed.Package = {
      val r = parse_langastTypedPackageT(F)
      return r
    }

    def parse_langastTypedPackageT(typeParsed: B): org.sireum.lang.ast.Typed.Package = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Package")
      }
      parser.parseObjectKey("name")
      val name = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Package(name)
    }

    def parse_langastTypedObject(): org.sireum.lang.ast.Typed.Object = {
      val r = parse_langastTypedObjectT(F)
      return r
    }

    def parse_langastTypedObjectT(typeParsed: B): org.sireum.lang.ast.Typed.Object = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Object")
      }
      parser.parseObjectKey("owner")
      val owner = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Object(owner, id)
    }

    def parse_langastTypedEnum(): org.sireum.lang.ast.Typed.Enum = {
      val r = parse_langastTypedEnumT(F)
      return r
    }

    def parse_langastTypedEnumT(typeParsed: B): org.sireum.lang.ast.Typed.Enum = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Enum")
      }
      parser.parseObjectKey("name")
      val name = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Enum(name)
    }

    def parse_langastTypedMethod(): org.sireum.lang.ast.Typed.Method = {
      val r = parse_langastTypedMethodT(F)
      return r
    }

    def parse_langastTypedMethodT(typeParsed: B): org.sireum.lang.ast.Typed.Method = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Method")
      }
      parser.parseObjectKey("isInObject")
      val isInObject = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("mode")
      val mode = parse_langastMethodModeType()
      parser.parseObjectNext()
      parser.parseObjectKey("typeParams")
      val typeParams = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("owner")
      val owner = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("paramNames")
      val paramNames = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("tpe")
      val tpe = parse_langastTypedFun()
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Method(isInObject, mode, typeParams, owner, name, paramNames, tpe)
    }

    def parse_langastTypedMethods(): org.sireum.lang.ast.Typed.Methods = {
      val r = parse_langastTypedMethodsT(F)
      return r
    }

    def parse_langastTypedMethodsT(typeParsed: B): org.sireum.lang.ast.Typed.Methods = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Methods")
      }
      parser.parseObjectKey("methods")
      val methods = parser.parseISZ(parse_langastTypedMethod _)
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Methods(methods)
    }

    def parse_langastTypedFact(): org.sireum.lang.ast.Typed.Fact = {
      val r = parse_langastTypedFactT(F)
      return r
    }

    def parse_langastTypedFactT(typeParsed: B): org.sireum.lang.ast.Typed.Fact = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Fact")
      }
      parser.parseObjectKey("owner")
      val owner = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Fact(owner, id)
    }

    def parse_langastTypedTheorem(): org.sireum.lang.ast.Typed.Theorem = {
      val r = parse_langastTypedTheoremT(F)
      return r
    }

    def parse_langastTypedTheoremT(typeParsed: B): org.sireum.lang.ast.Typed.Theorem = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Theorem")
      }
      parser.parseObjectKey("owner")
      val owner = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Theorem(owner, id)
    }

    def parse_langastTypedInv(): org.sireum.lang.ast.Typed.Inv = {
      val r = parse_langastTypedInvT(F)
      return r
    }

    def parse_langastTypedInvT(typeParsed: B): org.sireum.lang.ast.Typed.Inv = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.lang.ast.Typed.Inv")
      }
      parser.parseObjectKey("isInObject")
      val isInObject = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("owner")
      val owner = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      return org.sireum.lang.ast.Typed.Inv(isInObject, owner, id)
    }

    def eof(): B = {
      val r = parser.eof()
      return r
    }

  }

  def to[T](s: String, f: Parser => T): Either[T, Json.ErrorMsg] = {
    val parser = Parser(s)
    val r = f(parser)
    parser.eof()
    parser.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromRequest(o: Request, isCompact: B): String = {
    val st = Printer.printRequest(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toRequest(s: String): Either[Request, Json.ErrorMsg] = {
    def fRequest(parser: Parser): Request = {
      val r = parser.parseRequest()
      return r
    }
    val r = to(s, fRequest _)
    return r
  }

  def fromTerminate(o: Terminate, isCompact: B): String = {
    val st = Printer.printTerminate(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toTerminate(s: String): Either[Terminate, Json.ErrorMsg] = {
    def fTerminate(parser: Parser): Terminate = {
      val r = parser.parseTerminate()
      return r
    }
    val r = to(s, fTerminate _)
    return r
  }

  def fromResponse(o: Response, isCompact: B): String = {
    val st = Printer.printResponse(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toResponse(s: String): Either[Response, Json.ErrorMsg] = {
    def fResponse(parser: Parser): Response = {
      val r = parser.parseResponse()
      return r
    }
    val r = to(s, fResponse _)
    return r
  }

  def fromCancel(o: Cancel, isCompact: B): String = {
    val st = Printer.printCancel(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCancel(s: String): Either[Cancel, Json.ErrorMsg] = {
    def fCancel(parser: Parser): Cancel = {
      val r = parser.parseCancel()
      return r
    }
    val r = to(s, fCancel _)
    return r
  }

  def fromTiming(o: Timing, isCompact: B): String = {
    val st = Printer.printTiming(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toTiming(s: String): Either[Timing, Json.ErrorMsg] = {
    def fTiming(parser: Parser): Timing = {
      val r = parser.parseTiming()
      return r
    }
    val r = to(s, fTiming _)
    return r
  }

  def fromReport(o: Report, isCompact: B): String = {
    val st = Printer.printReport(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toReport(s: String): Either[Report, Json.ErrorMsg] = {
    def fReport(parser: Parser): Report = {
      val r = parser.parseReport()
      return r
    }
    val r = to(s, fReport _)
    return r
  }

  def fromVersionRequest(o: Version.Request, isCompact: B): String = {
    val st = Printer.printVersionRequest(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toVersionRequest(s: String): Either[Version.Request, Json.ErrorMsg] = {
    def fVersionRequest(parser: Parser): Version.Request = {
      val r = parser.parseVersionRequest()
      return r
    }
    val r = to(s, fVersionRequest _)
    return r
  }

  def fromVersionResponse(o: Version.Response, isCompact: B): String = {
    val st = Printer.printVersionResponse(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toVersionResponse(s: String): Either[Version.Response, Json.ErrorMsg] = {
    def fVersionResponse(parser: Parser): Version.Response = {
      val r = parser.parseVersionResponse()
      return r
    }
    val r = to(s, fVersionResponse _)
    return r
  }

  def fromStatusRequest(o: Status.Request, isCompact: B): String = {
    val st = Printer.printStatusRequest(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toStatusRequest(s: String): Either[Status.Request, Json.ErrorMsg] = {
    def fStatusRequest(parser: Parser): Status.Request = {
      val r = parser.parseStatusRequest()
      return r
    }
    val r = to(s, fStatusRequest _)
    return r
  }

  def fromStatusResponse(o: Status.Response, isCompact: B): String = {
    val st = Printer.printStatusResponse(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toStatusResponse(s: String): Either[Status.Response, Json.ErrorMsg] = {
    def fStatusResponse(parser: Parser): Status.Response = {
      val r = parser.parseStatusResponse()
      return r
    }
    val r = to(s, fStatusResponse _)
    return r
  }

  def fromSlangCheck(o: Slang.Check, isCompact: B): String = {
    val st = Printer.printSlangCheck(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSlangCheck(s: String): Either[Slang.Check, Json.ErrorMsg] = {
    def fSlangCheck(parser: Parser): Slang.Check = {
      val r = parser.parseSlangCheck()
      return r
    }
    val r = to(s, fSlangCheck _)
    return r
  }

  def fromSlangCheckScript(o: Slang.Check.Script, isCompact: B): String = {
    val st = Printer.printSlangCheckScript(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSlangCheckScript(s: String): Either[Slang.Check.Script, Json.ErrorMsg] = {
    def fSlangCheckScript(parser: Parser): Slang.Check.Script = {
      val r = parser.parseSlangCheckScript()
      return r
    }
    val r = to(s, fSlangCheckScript _)
    return r
  }

  def fromSlangCheckProject(o: Slang.Check.Project, isCompact: B): String = {
    val st = Printer.printSlangCheckProject(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSlangCheckProject(s: String): Either[Slang.Check.Project, Json.ErrorMsg] = {
    def fSlangCheckProject(parser: Parser): Slang.Check.Project = {
      val r = parser.parseSlangCheckProject()
      return r
    }
    val r = to(s, fSlangCheckProject _)
    return r
  }

  def fromSlangRewriteRequest(o: Slang.Rewrite.Request, isCompact: B): String = {
    val st = Printer.printSlangRewriteRequest(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSlangRewriteRequest(s: String): Either[Slang.Rewrite.Request, Json.ErrorMsg] = {
    def fSlangRewriteRequest(parser: Parser): Slang.Rewrite.Request = {
      val r = parser.parseSlangRewriteRequest()
      return r
    }
    val r = to(s, fSlangRewriteRequest _)
    return r
  }

  def fromSlangRewriteResponse(o: Slang.Rewrite.Response, isCompact: B): String = {
    val st = Printer.printSlangRewriteResponse(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSlangRewriteResponse(s: String): Either[Slang.Rewrite.Response, Json.ErrorMsg] = {
    def fSlangRewriteResponse(parser: Parser): Slang.Rewrite.Response = {
      val r = parser.parseSlangRewriteResponse()
      return r
    }
    val r = to(s, fSlangRewriteResponse _)
    return r
  }

  def fromAnalysisStart(o: Analysis.Start, isCompact: B): String = {
    val st = Printer.printAnalysisStart(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toAnalysisStart(s: String): Either[Analysis.Start, Json.ErrorMsg] = {
    def fAnalysisStart(parser: Parser): Analysis.Start = {
      val r = parser.parseAnalysisStart()
      return r
    }
    val r = to(s, fAnalysisStart _)
    return r
  }

  def fromAnalysisEnd(o: Analysis.End, isCompact: B): String = {
    val st = Printer.printAnalysisEnd(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toAnalysisEnd(s: String): Either[Analysis.End, Json.ErrorMsg] = {
    def fAnalysisEnd(parser: Parser): Analysis.End = {
      val r = parser.parseAnalysisEnd()
      return r
    }
    val r = to(s, fAnalysisEnd _)
    return r
  }

  def fromLogikaVerifyConfig(o: Logika.Verify.Config, isCompact: B): String = {
    val st = Printer.printLogikaVerifyConfig(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toLogikaVerifyConfig(s: String): Either[Logika.Verify.Config, Json.ErrorMsg] = {
    def fLogikaVerifyConfig(parser: Parser): Logika.Verify.Config = {
      val r = parser.parseLogikaVerifyConfig()
      return r
    }
    val r = to(s, fLogikaVerifyConfig _)
    return r
  }

  def fromLogikaVerifyState(o: Logika.Verify.State, isCompact: B): String = {
    val st = Printer.printLogikaVerifyState(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toLogikaVerifyState(s: String): Either[Logika.Verify.State, Json.ErrorMsg] = {
    def fLogikaVerifyState(parser: Parser): Logika.Verify.State = {
      val r = parser.parseLogikaVerifyState()
      return r
    }
    val r = to(s, fLogikaVerifyState _)
    return r
  }

  def fromLogikaVerifySmt2Query(o: Logika.Verify.Smt2Query, isCompact: B): String = {
    val st = Printer.printLogikaVerifySmt2Query(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toLogikaVerifySmt2Query(s: String): Either[Logika.Verify.Smt2Query, Json.ErrorMsg] = {
    def fLogikaVerifySmt2Query(parser: Parser): Logika.Verify.Smt2Query = {
      val r = parser.parseLogikaVerifySmt2Query()
      return r
    }
    val r = to(s, fLogikaVerifySmt2Query _)
    return r
  }

  def fromLogikaVerifyInfo(o: Logika.Verify.Info, isCompact: B): String = {
    val st = Printer.printLogikaVerifyInfo(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toLogikaVerifyInfo(s: String): Either[Logika.Verify.Info, Json.ErrorMsg] = {
    def fLogikaVerifyInfo(parser: Parser): Logika.Verify.Info = {
      val r = parser.parseLogikaVerifyInfo()
      return r
    }
    val r = to(s, fLogikaVerifyInfo _)
    return r
  }

  def fromorgsireumlogikaConfig(o: org.sireum.logika.Config, isCompact: B): String = {
    val st = Printer.printorgsireumlogikaConfig(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toorgsireumlogikaConfig(s: String): Either[org.sireum.logika.Config, Json.ErrorMsg] = {
    def forgsireumlogikaConfig(parser: Parser): org.sireum.logika.Config = {
      val r = parser.parseorgsireumlogikaConfig()
      return r
    }
    val r = to(s, forgsireumlogikaConfig _)
    return r
  }

  def fromorgsireumlogikaSmt2Config(o: org.sireum.logika.Smt2Config, isCompact: B): String = {
    val st = Printer.printorgsireumlogikaSmt2Config(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toorgsireumlogikaSmt2Config(s: String): Either[org.sireum.logika.Smt2Config, Json.ErrorMsg] = {
    def forgsireumlogikaSmt2Config(parser: Parser): org.sireum.logika.Smt2Config = {
      val r = parser.parseorgsireumlogikaSmt2Config()
      return r
    }
    val r = to(s, forgsireumlogikaSmt2Config _)
    return r
  }

  def fromorgsireumlogikaLoopId(o: org.sireum.logika.LoopId, isCompact: B): String = {
    val st = Printer.printorgsireumlogikaLoopId(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toorgsireumlogikaLoopId(s: String): Either[org.sireum.logika.LoopId, Json.ErrorMsg] = {
    def forgsireumlogikaLoopId(parser: Parser): org.sireum.logika.LoopId = {
      val r = parser.parseorgsireumlogikaLoopId()
      return r
    }
    val r = to(s, forgsireumlogikaLoopId _)
    return r
  }

  def from_logikaSmt2QueryResult(o: org.sireum.logika.Smt2Query.Result, isCompact: B): String = {
    val st = Printer.print_logikaSmt2QueryResult(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaSmt2QueryResult(s: String): Either[org.sireum.logika.Smt2Query.Result, Json.ErrorMsg] = {
    def f_logikaSmt2QueryResult(parser: Parser): org.sireum.logika.Smt2Query.Result = {
      val r = parser.parse_logikaSmt2QueryResult()
      return r
    }
    val r = to(s, f_logikaSmt2QueryResult _)
    return r
  }

  def from_langastTyped(o: org.sireum.lang.ast.Typed, isCompact: B): String = {
    val st = Printer.print_langastTyped(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTyped(s: String): Either[org.sireum.lang.ast.Typed, Json.ErrorMsg] = {
    def f_langastTyped(parser: Parser): org.sireum.lang.ast.Typed = {
      val r = parser.parse_langastTyped()
      return r
    }
    val r = to(s, f_langastTyped _)
    return r
  }

  def from_langastTypedName(o: org.sireum.lang.ast.Typed.Name, isCompact: B): String = {
    val st = Printer.print_langastTypedName(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedName(s: String): Either[org.sireum.lang.ast.Typed.Name, Json.ErrorMsg] = {
    def f_langastTypedName(parser: Parser): org.sireum.lang.ast.Typed.Name = {
      val r = parser.parse_langastTypedName()
      return r
    }
    val r = to(s, f_langastTypedName _)
    return r
  }

  def from_langastTypedTuple(o: org.sireum.lang.ast.Typed.Tuple, isCompact: B): String = {
    val st = Printer.print_langastTypedTuple(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedTuple(s: String): Either[org.sireum.lang.ast.Typed.Tuple, Json.ErrorMsg] = {
    def f_langastTypedTuple(parser: Parser): org.sireum.lang.ast.Typed.Tuple = {
      val r = parser.parse_langastTypedTuple()
      return r
    }
    val r = to(s, f_langastTypedTuple _)
    return r
  }

  def from_langastTypedFun(o: org.sireum.lang.ast.Typed.Fun, isCompact: B): String = {
    val st = Printer.print_langastTypedFun(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedFun(s: String): Either[org.sireum.lang.ast.Typed.Fun, Json.ErrorMsg] = {
    def f_langastTypedFun(parser: Parser): org.sireum.lang.ast.Typed.Fun = {
      val r = parser.parse_langastTypedFun()
      return r
    }
    val r = to(s, f_langastTypedFun _)
    return r
  }

  def from_langastTypedTypeVar(o: org.sireum.lang.ast.Typed.TypeVar, isCompact: B): String = {
    val st = Printer.print_langastTypedTypeVar(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedTypeVar(s: String): Either[org.sireum.lang.ast.Typed.TypeVar, Json.ErrorMsg] = {
    def f_langastTypedTypeVar(parser: Parser): org.sireum.lang.ast.Typed.TypeVar = {
      val r = parser.parse_langastTypedTypeVar()
      return r
    }
    val r = to(s, f_langastTypedTypeVar _)
    return r
  }

  def from_langastTypedPackage(o: org.sireum.lang.ast.Typed.Package, isCompact: B): String = {
    val st = Printer.print_langastTypedPackage(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedPackage(s: String): Either[org.sireum.lang.ast.Typed.Package, Json.ErrorMsg] = {
    def f_langastTypedPackage(parser: Parser): org.sireum.lang.ast.Typed.Package = {
      val r = parser.parse_langastTypedPackage()
      return r
    }
    val r = to(s, f_langastTypedPackage _)
    return r
  }

  def from_langastTypedObject(o: org.sireum.lang.ast.Typed.Object, isCompact: B): String = {
    val st = Printer.print_langastTypedObject(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedObject(s: String): Either[org.sireum.lang.ast.Typed.Object, Json.ErrorMsg] = {
    def f_langastTypedObject(parser: Parser): org.sireum.lang.ast.Typed.Object = {
      val r = parser.parse_langastTypedObject()
      return r
    }
    val r = to(s, f_langastTypedObject _)
    return r
  }

  def from_langastTypedEnum(o: org.sireum.lang.ast.Typed.Enum, isCompact: B): String = {
    val st = Printer.print_langastTypedEnum(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedEnum(s: String): Either[org.sireum.lang.ast.Typed.Enum, Json.ErrorMsg] = {
    def f_langastTypedEnum(parser: Parser): org.sireum.lang.ast.Typed.Enum = {
      val r = parser.parse_langastTypedEnum()
      return r
    }
    val r = to(s, f_langastTypedEnum _)
    return r
  }

  def from_langastTypedMethod(o: org.sireum.lang.ast.Typed.Method, isCompact: B): String = {
    val st = Printer.print_langastTypedMethod(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedMethod(s: String): Either[org.sireum.lang.ast.Typed.Method, Json.ErrorMsg] = {
    def f_langastTypedMethod(parser: Parser): org.sireum.lang.ast.Typed.Method = {
      val r = parser.parse_langastTypedMethod()
      return r
    }
    val r = to(s, f_langastTypedMethod _)
    return r
  }

  def from_langastTypedMethods(o: org.sireum.lang.ast.Typed.Methods, isCompact: B): String = {
    val st = Printer.print_langastTypedMethods(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedMethods(s: String): Either[org.sireum.lang.ast.Typed.Methods, Json.ErrorMsg] = {
    def f_langastTypedMethods(parser: Parser): org.sireum.lang.ast.Typed.Methods = {
      val r = parser.parse_langastTypedMethods()
      return r
    }
    val r = to(s, f_langastTypedMethods _)
    return r
  }

  def from_langastTypedFact(o: org.sireum.lang.ast.Typed.Fact, isCompact: B): String = {
    val st = Printer.print_langastTypedFact(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedFact(s: String): Either[org.sireum.lang.ast.Typed.Fact, Json.ErrorMsg] = {
    def f_langastTypedFact(parser: Parser): org.sireum.lang.ast.Typed.Fact = {
      val r = parser.parse_langastTypedFact()
      return r
    }
    val r = to(s, f_langastTypedFact _)
    return r
  }

  def from_langastTypedTheorem(o: org.sireum.lang.ast.Typed.Theorem, isCompact: B): String = {
    val st = Printer.print_langastTypedTheorem(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedTheorem(s: String): Either[org.sireum.lang.ast.Typed.Theorem, Json.ErrorMsg] = {
    def f_langastTypedTheorem(parser: Parser): org.sireum.lang.ast.Typed.Theorem = {
      val r = parser.parse_langastTypedTheorem()
      return r
    }
    val r = to(s, f_langastTypedTheorem _)
    return r
  }

  def from_langastTypedInv(o: org.sireum.lang.ast.Typed.Inv, isCompact: B): String = {
    val st = Printer.print_langastTypedInv(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_langastTypedInv(s: String): Either[org.sireum.lang.ast.Typed.Inv, Json.ErrorMsg] = {
    def f_langastTypedInv(parser: Parser): org.sireum.lang.ast.Typed.Inv = {
      val r = parser.parse_langastTypedInv()
      return r
    }
    val r = to(s, f_langastTypedInv _)
    return r
  }

}