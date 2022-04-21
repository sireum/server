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

// This file is auto-generated from Message.scala, State.scala, Config.scala, Smt2Query.scala, Typed.scala

package org.sireum.server.protocol

import org.sireum._

object MsgPack {

  object Constants {

    val Terminate: Z = -32

    val Cancel: Z = -31

    val Timing: Z = -30

    val Report: Z = -29

    val VersionRequest: Z = -28

    val VersionResponse: Z = -27

    val StatusRequest: Z = -26

    val StatusResponse: Z = -25

    val SlangCheckScript: Z = -24

    val SlangCheckProject: Z = -23

    val SlangRewriteRequest: Z = -22

    val SlangRewriteResponse: Z = -21

    val AnalysisStart: Z = -20

    val AnalysisEnd: Z = -19

    val LogikaVerifyConfig: Z = -18

    val LogikaVerifyState: Z = -17

    val LogikaVerifyHalted: Z = -16

    val LogikaVerifySmt2Query: Z = -15

    val LogikaVerifyInfo: Z = -14

    val orgsireumlogikaState: Z = -13

    val _logikaStateIFun: Z = -12

    val _logikaStateOFun: Z = -11

    val _logikaStateValueUnit: Z = -10

    val _logikaStateValueB: Z = -9

    val _logikaStateValueZ: Z = -8

    val _logikaStateValueC: Z = -7

    val _logikaStateValueF32: Z = -6

    val _logikaStateValueF64: Z = -5

    val _logikaStateValueR: Z = -4

    val _logikaStateValueString: Z = -3

    val _logikaStateValueRange: Z = -2

    val _logikaStateValueS8: Z = -1

    val _logikaStateValueS16: Z = 0

    val _logikaStateValueS32: Z = 1

    val _logikaStateValueS64: Z = 2

    val _logikaStateValueU8: Z = 3

    val _logikaStateValueU16: Z = 4

    val _logikaStateValueU32: Z = 5

    val _logikaStateValueU64: Z = 6

    val _logikaStateValueEnum: Z = 7

    val _logikaStateValueSym: Z = 8

    val _logikaStateClaimLabel: Z = 9

    val _logikaStateClaimProp: Z = 10

    val _logikaStateClaimAnd: Z = 11

    val _logikaStateClaimOr: Z = 12

    val _logikaStateClaimImply: Z = 13

    val _logikaStateClaimIf: Z = 14

    val _logikaStateClaimLetAdtLit: Z = 15

    val _logikaStateClaimLetSeqLit: Z = 16

    val _logikaStateClaimLetSeqLitArg: Z = 17

    val _logikaStateClaimLetCurrentName: Z = 18

    val _logikaStateClaimLetSeqStore: Z = 19

    val _logikaStateClaimLetFieldStore: Z = 20

    val _logikaStateClaimLetRandom: Z = 21

    val _logikaStateClaimLetName: Z = 22

    val _logikaStateClaimLetCurrentId: Z = 23

    val _logikaStateClaimLetId: Z = 24

    val _logikaStateClaimLetEq: Z = 25

    val _logikaStateClaimLetTypeTest: Z = 26

    val _logikaStateClaimLetQuantVarId: Z = 27

    val _logikaStateClaimLetQuantVarSym: Z = 28

    val _logikaStateClaimLetQuant: Z = 29

    val _logikaStateClaimLetIte: Z = 30

    val _logikaStateClaimLetBinary: Z = 31

    val _logikaStateClaimLetUnary: Z = 32

    val _logikaStateClaimLetSeqLookup: Z = 33

    val _logikaStateClaimLetSeqInBound: Z = 34

    val _logikaStateClaimLetFieldLookup: Z = 35

    val _logikaStateClaimLetProofFunApply: Z = 36

    val _logikaStateClaimLetApply: Z = 37

    val _logikaStateClaimLetIApply: Z = 38

    val _logikaStateClaimLetTupleLit: Z = 39

    val _logikaStateClaimLetAnd: Z = 40

    val _logikaStateClaimLetOr: Z = 41

    val _logikaStateClaimLetImply: Z = 42

    val _logikaStateProofFun: Z = 43

    val orgsireumlogikaConfig: Z = 44

    val orgsireumlogikaZ3Config: Z = 45

    val orgsireumlogikaCvcConfig: Z = 46

    val orgsireumlogikaLoopId: Z = 47

    val _logikaSmt2QueryResult: Z = 48

    val _langastTypedName: Z = 49

    val _langastTypedTuple: Z = 50

    val _langastTypedFun: Z = 51

    val _langastTypedTypeVar: Z = 52

    val _langastTypedPackage: Z = 53

    val _langastTypedObject: Z = 54

    val _langastTypedEnum: Z = 55

    val _langastTypedMethod: Z = 56

    val _langastTypedMethods: Z = 57

    val _langastTypedFact: Z = 58

    val _langastTypedTheorem: Z = 59

    val _langastTypedInv: Z = 60

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
        case o: Slang.Check.Script => writeSlangCheckScript(o)
        case o: Slang.Check.Project => writeSlangCheckProject(o)
        case o: Slang.Rewrite.Request => writeSlangRewriteRequest(o)
        case o: Logika.Verify.Config => writeLogikaVerifyConfig(o)
      }
    }

    def writeTerminate(o: Terminate): Unit = {
      writer.writeZ(Constants.Terminate)
    }

    def writeResponse(o: Response): Unit = {
      o match {
        case o: Timing => writeTiming(o)
        case o: Report => writeReport(o)
        case o: Version.Response => writeVersionResponse(o)
        case o: Status.Response => writeStatusResponse(o)
        case o: Slang.Rewrite.Response => writeSlangRewriteResponse(o)
        case o: Analysis.Start => writeAnalysisStart(o)
        case o: Analysis.End => writeAnalysisEnd(o)
        case o: Logika.Verify.State => writeLogikaVerifyState(o)
        case o: Logika.Verify.Halted => writeLogikaVerifyHalted(o)
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
      writer.writeZ(o.par)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeOption(o.uriOpt, writer.writeString _)
      writer.writeString(o.content)
      writer.writeZ(o.line)
    }

    def writeSlangCheckProject(o: Slang.Check.Project): Unit = {
      writer.writeZ(Constants.SlangCheckProject)
      writer.writeB(o.isBackground)
      writer.writeZ(o.par)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeString(o.proyek)
      writer.writeHashSMap(o.files, writer.writeString _, writer.writeString _)
      writer.writeISZ(o.vfiles, writer.writeString _)
      writer.writeZ(o.line)
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

    def writeAnalysisEnd(o: Analysis.End): Unit = {
      writer.writeZ(Constants.AnalysisEnd)
      writer.writeB(o.isBackground)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeB(o.wasCancelled)
      writer.writeB(o.isIllFormed)
      writer.writeB(o.hasLogika)
      writer.writeZ(o.totalTimeMillis)
      writer.writeZ(o.numOfSmt2Calls)
      writer.writeZ(o.smt2TimeMillis)
      writer.writeZ(o.numOfInternalErrors)
      writer.writeZ(o.numOfErrors)
      writer.writeZ(o.numOfWarnings)
    }

    def writeLogikaVerifyConfig(o: Logika.Verify.Config): Unit = {
      writer.writeZ(Constants.LogikaVerifyConfig)
      writer.writeB(o.hint)
      writer.writeB(o.smt2query)
      writeorgsireumlogikaConfig(o.config)
    }

    def writeLogikaVerifyState(o: Logika.Verify.State): Unit = {
      writer.writeZ(Constants.LogikaVerifyState)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeOption(o.posOpt, writer.writePosition _)
      writeorgsireumlogikaState(o.state)
    }

    def writeLogikaVerifyHalted(o: Logika.Verify.Halted): Unit = {
      writer.writeZ(Constants.LogikaVerifyHalted)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writeOption(o.posOpt, writer.writePosition _)
      writeorgsireumlogikaState(o.state)
    }

    def writeLogikaVerifySmt2Query(o: Logika.Verify.Smt2Query): Unit = {
      writer.writeZ(Constants.LogikaVerifySmt2Query)
      writer.writeISZ(o.id, writer.writeString _)
      writer.writePosition(o.pos)
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

    def writeorgsireumlogikaState(o: org.sireum.logika.State): Unit = {
      writer.writeZ(Constants.orgsireumlogikaState)
      writer.writeB(o.status)
      writer.writeISZ(o.claims, write_logikaStateClaim _)
      writer.writeZ(o.nextFresh)
    }

    def write_logikaStateValue(o: org.sireum.logika.State.Value): Unit = {
      o match {
        case o: org.sireum.logika.State.Value.Unit => write_logikaStateValueUnit(o)
        case o: org.sireum.logika.State.Value.B => write_logikaStateValueB(o)
        case o: org.sireum.logika.State.Value.Z => write_logikaStateValueZ(o)
        case o: org.sireum.logika.State.Value.C => write_logikaStateValueC(o)
        case o: org.sireum.logika.State.Value.F32 => write_logikaStateValueF32(o)
        case o: org.sireum.logika.State.Value.F64 => write_logikaStateValueF64(o)
        case o: org.sireum.logika.State.Value.R => write_logikaStateValueR(o)
        case o: org.sireum.logika.State.Value.String => write_logikaStateValueString(o)
        case o: org.sireum.logika.State.Value.Range => write_logikaStateValueRange(o)
        case o: org.sireum.logika.State.Value.S8 => write_logikaStateValueS8(o)
        case o: org.sireum.logika.State.Value.S16 => write_logikaStateValueS16(o)
        case o: org.sireum.logika.State.Value.S32 => write_logikaStateValueS32(o)
        case o: org.sireum.logika.State.Value.S64 => write_logikaStateValueS64(o)
        case o: org.sireum.logika.State.Value.U8 => write_logikaStateValueU8(o)
        case o: org.sireum.logika.State.Value.U16 => write_logikaStateValueU16(o)
        case o: org.sireum.logika.State.Value.U32 => write_logikaStateValueU32(o)
        case o: org.sireum.logika.State.Value.U64 => write_logikaStateValueU64(o)
        case o: org.sireum.logika.State.Value.Enum => write_logikaStateValueEnum(o)
        case o: org.sireum.logika.State.Value.Sym => write_logikaStateValueSym(o)
      }
    }

    def write_logikaStateFun(o: org.sireum.logika.State.Fun): Unit = {
      o match {
        case o: org.sireum.logika.State.IFun => write_logikaStateIFun(o)
        case o: org.sireum.logika.State.OFun => write_logikaStateOFun(o)
      }
    }

    def write_logikaStateIFun(o: org.sireum.logika.State.IFun): Unit = {
      writer.writeZ(Constants._logikaStateIFun)
      write_langastTypedName(o.tipe)
      writer.writeString(o.id)
    }

    def write_logikaStateOFun(o: org.sireum.logika.State.OFun): Unit = {
      writer.writeZ(Constants._logikaStateOFun)
      writer.writeISZ(o.name, writer.writeString _)
    }

    def write_logikaStateValueUnit(o: org.sireum.logika.State.Value.Unit): Unit = {
      writer.writeZ(Constants._logikaStateValueUnit)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueB(o: org.sireum.logika.State.Value.B): Unit = {
      writer.writeZ(Constants._logikaStateValueB)
      writer.writeB(o.value)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueZ(o: org.sireum.logika.State.Value.Z): Unit = {
      writer.writeZ(Constants._logikaStateValueZ)
      writer.writeZ(o.value)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueC(o: org.sireum.logika.State.Value.C): Unit = {
      writer.writeZ(Constants._logikaStateValueC)
      writer.writeC(o.value)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueF32(o: org.sireum.logika.State.Value.F32): Unit = {
      writer.writeZ(Constants._logikaStateValueF32)
      writer.writeF32(o.value)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueF64(o: org.sireum.logika.State.Value.F64): Unit = {
      writer.writeZ(Constants._logikaStateValueF64)
      writer.writeF64(o.value)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueR(o: org.sireum.logika.State.Value.R): Unit = {
      writer.writeZ(Constants._logikaStateValueR)
      writer.writeR(o.value)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueString(o: org.sireum.logika.State.Value.String): Unit = {
      writer.writeZ(Constants._logikaStateValueString)
      writer.writeString(o.value)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueSubZ(o: org.sireum.logika.State.Value.SubZ): Unit = {
      o match {
        case o: org.sireum.logika.State.Value.Range => write_logikaStateValueRange(o)
        case o: org.sireum.logika.State.Value.S8 => write_logikaStateValueS8(o)
        case o: org.sireum.logika.State.Value.S16 => write_logikaStateValueS16(o)
        case o: org.sireum.logika.State.Value.S32 => write_logikaStateValueS32(o)
        case o: org.sireum.logika.State.Value.S64 => write_logikaStateValueS64(o)
        case o: org.sireum.logika.State.Value.U8 => write_logikaStateValueU8(o)
        case o: org.sireum.logika.State.Value.U16 => write_logikaStateValueU16(o)
        case o: org.sireum.logika.State.Value.U32 => write_logikaStateValueU32(o)
        case o: org.sireum.logika.State.Value.U64 => write_logikaStateValueU64(o)
      }
    }

    def write_logikaStateValueRange(o: org.sireum.logika.State.Value.Range): Unit = {
      writer.writeZ(Constants._logikaStateValueRange)
      writer.writeZ(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueS8(o: org.sireum.logika.State.Value.S8): Unit = {
      writer.writeZ(Constants._logikaStateValueS8)
      writer.writeS8(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueS16(o: org.sireum.logika.State.Value.S16): Unit = {
      writer.writeZ(Constants._logikaStateValueS16)
      writer.writeS16(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueS32(o: org.sireum.logika.State.Value.S32): Unit = {
      writer.writeZ(Constants._logikaStateValueS32)
      writer.writeS32(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueS64(o: org.sireum.logika.State.Value.S64): Unit = {
      writer.writeZ(Constants._logikaStateValueS64)
      writer.writeS64(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueU8(o: org.sireum.logika.State.Value.U8): Unit = {
      writer.writeZ(Constants._logikaStateValueU8)
      writer.writeU8(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueU16(o: org.sireum.logika.State.Value.U16): Unit = {
      writer.writeZ(Constants._logikaStateValueU16)
      writer.writeU16(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueU32(o: org.sireum.logika.State.Value.U32): Unit = {
      writer.writeZ(Constants._logikaStateValueU32)
      writer.writeU32(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueU64(o: org.sireum.logika.State.Value.U64): Unit = {
      writer.writeZ(Constants._logikaStateValueU64)
      writer.writeU64(o.value)
      write_langastTypedName(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueEnum(o: org.sireum.logika.State.Value.Enum): Unit = {
      writer.writeZ(Constants._logikaStateValueEnum)
      write_langastTypedName(o.tipe)
      writer.writeISZ(o.owner, writer.writeString _)
      writer.writeString(o.id)
      writer.writeZ(o.ordinal)
      writer.writePosition(o.pos)
    }

    def write_logikaStateValueSym(o: org.sireum.logika.State.Value.Sym): Unit = {
      writer.writeZ(Constants._logikaStateValueSym)
      writer.writeZ(o.num)
      write_langastTyped(o.tipe)
      writer.writePosition(o.pos)
    }

    def write_logikaStateClaim(o: org.sireum.logika.State.Claim): Unit = {
      o match {
        case o: org.sireum.logika.State.Claim.Label => write_logikaStateClaimLabel(o)
        case o: org.sireum.logika.State.Claim.Prop => write_logikaStateClaimProp(o)
        case o: org.sireum.logika.State.Claim.And => write_logikaStateClaimAnd(o)
        case o: org.sireum.logika.State.Claim.Or => write_logikaStateClaimOr(o)
        case o: org.sireum.logika.State.Claim.Imply => write_logikaStateClaimImply(o)
        case o: org.sireum.logika.State.Claim.If => write_logikaStateClaimIf(o)
        case o: org.sireum.logika.State.Claim.Let.AdtLit => write_logikaStateClaimLetAdtLit(o)
        case o: org.sireum.logika.State.Claim.Let.SeqLit => write_logikaStateClaimLetSeqLit(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentName => write_logikaStateClaimLetCurrentName(o)
        case o: org.sireum.logika.State.Claim.Let.SeqStore => write_logikaStateClaimLetSeqStore(o)
        case o: org.sireum.logika.State.Claim.Let.FieldStore => write_logikaStateClaimLetFieldStore(o)
        case o: org.sireum.logika.State.Claim.Let.Random => write_logikaStateClaimLetRandom(o)
        case o: org.sireum.logika.State.Claim.Let.Name => write_logikaStateClaimLetName(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentId => write_logikaStateClaimLetCurrentId(o)
        case o: org.sireum.logika.State.Claim.Let.Id => write_logikaStateClaimLetId(o)
        case o: org.sireum.logika.State.Claim.Let.Eq => write_logikaStateClaimLetEq(o)
        case o: org.sireum.logika.State.Claim.Let.TypeTest => write_logikaStateClaimLetTypeTest(o)
        case o: org.sireum.logika.State.Claim.Let.Quant => write_logikaStateClaimLetQuant(o)
        case o: org.sireum.logika.State.Claim.Let.Ite => write_logikaStateClaimLetIte(o)
        case o: org.sireum.logika.State.Claim.Let.Binary => write_logikaStateClaimLetBinary(o)
        case o: org.sireum.logika.State.Claim.Let.Unary => write_logikaStateClaimLetUnary(o)
        case o: org.sireum.logika.State.Claim.Let.SeqLookup => write_logikaStateClaimLetSeqLookup(o)
        case o: org.sireum.logika.State.Claim.Let.SeqInBound => write_logikaStateClaimLetSeqInBound(o)
        case o: org.sireum.logika.State.Claim.Let.FieldLookup => write_logikaStateClaimLetFieldLookup(o)
        case o: org.sireum.logika.State.Claim.Let.ProofFunApply => write_logikaStateClaimLetProofFunApply(o)
        case o: org.sireum.logika.State.Claim.Let.Apply => write_logikaStateClaimLetApply(o)
        case o: org.sireum.logika.State.Claim.Let.IApply => write_logikaStateClaimLetIApply(o)
        case o: org.sireum.logika.State.Claim.Let.TupleLit => write_logikaStateClaimLetTupleLit(o)
        case o: org.sireum.logika.State.Claim.Let.And => write_logikaStateClaimLetAnd(o)
        case o: org.sireum.logika.State.Claim.Let.Or => write_logikaStateClaimLetOr(o)
        case o: org.sireum.logika.State.Claim.Let.Imply => write_logikaStateClaimLetImply(o)
      }
    }

    def write_logikaStateClaimComposite(o: org.sireum.logika.State.Claim.Composite): Unit = {
      o match {
        case o: org.sireum.logika.State.Claim.And => write_logikaStateClaimAnd(o)
        case o: org.sireum.logika.State.Claim.Or => write_logikaStateClaimOr(o)
        case o: org.sireum.logika.State.Claim.Imply => write_logikaStateClaimImply(o)
        case o: org.sireum.logika.State.Claim.If => write_logikaStateClaimIf(o)
        case o: org.sireum.logika.State.Claim.Let.Quant => write_logikaStateClaimLetQuant(o)
      }
    }

    def write_logikaStateClaimLabel(o: org.sireum.logika.State.Claim.Label): Unit = {
      writer.writeZ(Constants._logikaStateClaimLabel)
      writer.writeString(o.label)
      writer.writePosition(o.pos)
    }

    def write_logikaStateClaimProp(o: org.sireum.logika.State.Claim.Prop): Unit = {
      writer.writeZ(Constants._logikaStateClaimProp)
      writer.writeB(o.isPos)
      write_logikaStateValueSym(o.value)
    }

    def write_logikaStateClaimAnd(o: org.sireum.logika.State.Claim.And): Unit = {
      writer.writeZ(Constants._logikaStateClaimAnd)
      writer.writeISZ(o.claims, write_logikaStateClaim _)
    }

    def write_logikaStateClaimOr(o: org.sireum.logika.State.Claim.Or): Unit = {
      writer.writeZ(Constants._logikaStateClaimOr)
      writer.writeISZ(o.claims, write_logikaStateClaim _)
    }

    def write_logikaStateClaimImply(o: org.sireum.logika.State.Claim.Imply): Unit = {
      writer.writeZ(Constants._logikaStateClaimImply)
      writer.writeISZ(o.claims, write_logikaStateClaim _)
    }

    def write_logikaStateClaimIf(o: org.sireum.logika.State.Claim.If): Unit = {
      writer.writeZ(Constants._logikaStateClaimIf)
      write_logikaStateValueSym(o.cond)
      writer.writeISZ(o.tClaims, write_logikaStateClaim _)
      writer.writeISZ(o.fClaims, write_logikaStateClaim _)
    }

    def write_logikaStateClaimLet(o: org.sireum.logika.State.Claim.Let): Unit = {
      o match {
        case o: org.sireum.logika.State.Claim.Let.AdtLit => write_logikaStateClaimLetAdtLit(o)
        case o: org.sireum.logika.State.Claim.Let.SeqLit => write_logikaStateClaimLetSeqLit(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentName => write_logikaStateClaimLetCurrentName(o)
        case o: org.sireum.logika.State.Claim.Let.SeqStore => write_logikaStateClaimLetSeqStore(o)
        case o: org.sireum.logika.State.Claim.Let.FieldStore => write_logikaStateClaimLetFieldStore(o)
        case o: org.sireum.logika.State.Claim.Let.Random => write_logikaStateClaimLetRandom(o)
        case o: org.sireum.logika.State.Claim.Let.Name => write_logikaStateClaimLetName(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentId => write_logikaStateClaimLetCurrentId(o)
        case o: org.sireum.logika.State.Claim.Let.Id => write_logikaStateClaimLetId(o)
        case o: org.sireum.logika.State.Claim.Let.Eq => write_logikaStateClaimLetEq(o)
        case o: org.sireum.logika.State.Claim.Let.TypeTest => write_logikaStateClaimLetTypeTest(o)
        case o: org.sireum.logika.State.Claim.Let.Quant => write_logikaStateClaimLetQuant(o)
        case o: org.sireum.logika.State.Claim.Let.Ite => write_logikaStateClaimLetIte(o)
        case o: org.sireum.logika.State.Claim.Let.Binary => write_logikaStateClaimLetBinary(o)
        case o: org.sireum.logika.State.Claim.Let.Unary => write_logikaStateClaimLetUnary(o)
        case o: org.sireum.logika.State.Claim.Let.SeqLookup => write_logikaStateClaimLetSeqLookup(o)
        case o: org.sireum.logika.State.Claim.Let.SeqInBound => write_logikaStateClaimLetSeqInBound(o)
        case o: org.sireum.logika.State.Claim.Let.FieldLookup => write_logikaStateClaimLetFieldLookup(o)
        case o: org.sireum.logika.State.Claim.Let.ProofFunApply => write_logikaStateClaimLetProofFunApply(o)
        case o: org.sireum.logika.State.Claim.Let.Apply => write_logikaStateClaimLetApply(o)
        case o: org.sireum.logika.State.Claim.Let.IApply => write_logikaStateClaimLetIApply(o)
        case o: org.sireum.logika.State.Claim.Let.TupleLit => write_logikaStateClaimLetTupleLit(o)
        case o: org.sireum.logika.State.Claim.Let.And => write_logikaStateClaimLetAnd(o)
        case o: org.sireum.logika.State.Claim.Let.Or => write_logikaStateClaimLetOr(o)
        case o: org.sireum.logika.State.Claim.Let.Imply => write_logikaStateClaimLetImply(o)
      }
    }

    def write_logikaStateClaimLetAdtLit(o: org.sireum.logika.State.Claim.Let.AdtLit): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetAdtLit)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateClaimLetSeqLit(o: org.sireum.logika.State.Claim.Let.SeqLit): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetSeqLit)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.args, write_logikaStateClaimLetSeqLitArg _)
    }

    def write_logikaStateClaimLetSeqLitArg(o: org.sireum.logika.State.Claim.Let.SeqLit.Arg): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetSeqLitArg)
      write_logikaStateValue(o.index)
      write_logikaStateValue(o.value)
    }

    def write_logikaStateClaimLetCurrentName(o: org.sireum.logika.State.Claim.Let.CurrentName): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetCurrentName)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.ids, writer.writeString _)
      writer.writeOption(o.defPosOpt, writer.writePosition _)
    }

    def write_logikaStateClaimLetSeqStore(o: org.sireum.logika.State.Claim.Let.SeqStore): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetSeqStore)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.seq)
      write_logikaStateValue(o.index)
      write_logikaStateValue(o.element)
    }

    def write_logikaStateClaimLetFieldStore(o: org.sireum.logika.State.Claim.Let.FieldStore): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetFieldStore)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.adt)
      writer.writeString(o.id)
      write_logikaStateValue(o.value)
    }

    def write_logikaStateClaimLetRandom(o: org.sireum.logika.State.Claim.Let.Random): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetRandom)
      write_logikaStateValueSym(o.sym)
      writer.writePosition(o.pos)
    }

    def write_logikaStateClaimLetName(o: org.sireum.logika.State.Claim.Let.Name): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetName)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.ids, writer.writeString _)
      writer.writeZ(o.num)
      writer.writeISZ(o.poss, writer.writePosition _)
    }

    def write_logikaStateClaimLetCurrentId(o: org.sireum.logika.State.Claim.Let.CurrentId): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetCurrentId)
      writer.writeB(o.declId)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.context, writer.writeString _)
      writer.writeString(o.id)
      writer.writeOption(o.defPosOpt, writer.writePosition _)
    }

    def write_logikaStateClaimLetId(o: org.sireum.logika.State.Claim.Let.Id): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetId)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.context, writer.writeString _)
      writer.writeString(o.id)
      writer.writeZ(o.num)
      writer.writeISZ(o.poss, writer.writePosition _)
    }

    def write_logikaStateClaimLetEq(o: org.sireum.logika.State.Claim.Let.Eq): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetEq)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.value)
    }

    def write_logikaStateClaimLetTypeTest(o: org.sireum.logika.State.Claim.Let.TypeTest): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetTypeTest)
      write_logikaStateValueSym(o.sym)
      writer.writeB(o.isEq)
      write_logikaStateValue(o.value)
      write_langastTyped(o.tipe)
    }

    def write_logikaStateClaimLetQuantVar(o: org.sireum.logika.State.Claim.Let.Quant.Var): Unit = {
      o match {
        case o: org.sireum.logika.State.Claim.Let.Quant.Var.Id => write_logikaStateClaimLetQuantVarId(o)
        case o: org.sireum.logika.State.Claim.Let.Quant.Var.Sym => write_logikaStateClaimLetQuantVarSym(o)
      }
    }

    def write_logikaStateClaimLetQuantVarId(o: org.sireum.logika.State.Claim.Let.Quant.Var.Id): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetQuantVarId)
      writer.writeISZ(o.context, writer.writeString _)
      writer.writeString(o.id)
      write_langastTyped(o.tipe)
    }

    def write_logikaStateClaimLetQuantVarSym(o: org.sireum.logika.State.Claim.Let.Quant.Var.Sym): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetQuantVarSym)
      write_logikaStateValueSym(o.sym)
    }

    def write_logikaStateClaimLetQuant(o: org.sireum.logika.State.Claim.Let.Quant): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetQuant)
      write_logikaStateValueSym(o.sym)
      writer.writeB(o.isAll)
      writer.writeISZ(o.vars, write_logikaStateClaimLetQuantVar _)
      writer.writeISZ(o.claims, write_logikaStateClaim _)
    }

    def write_logikaStateClaimLetIte(o: org.sireum.logika.State.Claim.Let.Ite): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetIte)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.cond)
      write_logikaStateValue(o.left)
      write_logikaStateValue(o.right)
    }

    def write_logikaStateClaimLetBinary(o: org.sireum.logika.State.Claim.Let.Binary): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetBinary)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.left)
      writer.writeString(o.op)
      write_logikaStateValue(o.right)
      write_langastTyped(o.tipe)
    }

    def write_logikaStateClaimLetUnary(o: org.sireum.logika.State.Claim.Let.Unary): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetUnary)
      write_logikaStateValueSym(o.sym)
      writer.writeString(o.op)
      write_logikaStateValue(o.value)
    }

    def write_logikaStateClaimLetSeqLookup(o: org.sireum.logika.State.Claim.Let.SeqLookup): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetSeqLookup)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.seq)
      write_logikaStateValue(o.index)
    }

    def write_logikaStateClaimLetSeqInBound(o: org.sireum.logika.State.Claim.Let.SeqInBound): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetSeqInBound)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.seq)
      write_logikaStateValue(o.index)
    }

    def write_logikaStateClaimLetFieldLookup(o: org.sireum.logika.State.Claim.Let.FieldLookup): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetFieldLookup)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.adt)
      writer.writeString(o.id)
    }

    def write_logikaStateClaimLetProofFunApply(o: org.sireum.logika.State.Claim.Let.ProofFunApply): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetProofFunApply)
      write_logikaStateValueSym(o.sym)
      write_logikaStateProofFun(o.pf)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateClaimLetApply(o: org.sireum.logika.State.Claim.Let.Apply): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetApply)
      write_logikaStateValueSym(o.sym)
      writer.writeB(o.isLocal)
      writer.writeISZ(o.context, writer.writeString _)
      writer.writeString(o.id)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateClaimLetIApply(o: org.sireum.logika.State.Claim.Let.IApply): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetIApply)
      write_logikaStateValueSym(o.sym)
      write_logikaStateValue(o.o)
      write_langastTypedName(o.oTipe)
      writer.writeString(o.id)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateClaimLetTupleLit(o: org.sireum.logika.State.Claim.Let.TupleLit): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetTupleLit)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateClaimLetAnd(o: org.sireum.logika.State.Claim.Let.And): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetAnd)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateClaimLetOr(o: org.sireum.logika.State.Claim.Let.Or): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetOr)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateClaimLetImply(o: org.sireum.logika.State.Claim.Let.Imply): Unit = {
      writer.writeZ(Constants._logikaStateClaimLetImply)
      write_logikaStateValueSym(o.sym)
      writer.writeISZ(o.args, write_logikaStateValue _)
    }

    def write_logikaStateProofFun(o: org.sireum.logika.State.ProofFun): Unit = {
      writer.writeZ(Constants._logikaStateProofFun)
      writer.writeOption(o.receiverTypeOpt, write_langastTyped _)
      writer.writeISZ(o.context, writer.writeString _)
      writer.writeString(o.id)
      writer.writeISZ(o.paramIds, writer.writeString _)
      writer.writeISZ(o.paramTypes, write_langastTyped _)
      write_langastTyped(o.returnType)
    }

    def writeorgsireumlogikaConfig(o: org.sireum.logika.Config): Unit = {
      writer.writeZ(Constants.orgsireumlogikaConfig)
      writer.writeISZ(o.smt2Configs, writeorgsireumlogikaSmt2Config _)
      writer.writeB(o.sat)
      writer.writeZ(o.timeoutInMs)
      writer.writeZ(o.defaultLoopBound)
      writer.writeHashMap(o.loopBounds, writeorgsireumlogikaLoopId _, writer.writeZ _)
      writer.writeB(o.unroll)
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
      writer.writeZ(o.cvcRLimit)
      writer.writeString(o.fpRoundingMode)
      writer.writeB(o.caching)
    }

    def writeorgsireumlogikaSmt2Config(o: org.sireum.logika.Smt2Config): Unit = {
      o match {
        case o: org.sireum.logika.Z3Config => writeorgsireumlogikaZ3Config(o)
        case o: org.sireum.logika.CvcConfig => writeorgsireumlogikaCvcConfig(o)
      }
    }

    def writeorgsireumlogikaZ3Config(o: org.sireum.logika.Z3Config): Unit = {
      writer.writeZ(Constants.orgsireumlogikaZ3Config)
      writer.writeString(o.exe)
      writer.writeISZ(o.validOpts, writer.writeString _)
      writer.writeISZ(o.satOpts, writer.writeString _)
    }

    def writeorgsireumlogikaCvcConfig(o: org.sireum.logika.CvcConfig): Unit = {
      writer.writeZ(Constants.orgsireumlogikaCvcConfig)
      writer.writeString(o.exe)
      writer.writeISZ(o.validOpts, writer.writeString _)
      writer.writeISZ(o.satOpts, writer.writeString _)
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
      writer.writeB(o.cached)
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
      writer.writeB(o.isPure)
      writer.writeB(o.isByName)
      writer.writeISZ(o.args, write_langastTyped _)
      write_langastTyped(o.ret)
    }

    def write_langastTypedTypeVar(o: org.sireum.lang.ast.Typed.TypeVar): Unit = {
      writer.writeZ(Constants._langastTypedTypeVar)
      writer.writeString(o.id)
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
        case Constants.SlangCheckScript => val r = readSlangCheckScriptT(T); return r
        case Constants.SlangCheckProject => val r = readSlangCheckProjectT(T); return r
        case Constants.SlangRewriteRequest => val r = readSlangRewriteRequestT(T); return r
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
        case Constants.Report => val r = readReportT(T); return r
        case Constants.VersionResponse => val r = readVersionResponseT(T); return r
        case Constants.StatusResponse => val r = readStatusResponseT(T); return r
        case Constants.SlangRewriteResponse => val r = readSlangRewriteResponseT(T); return r
        case Constants.AnalysisStart => val r = readAnalysisStartT(T); return r
        case Constants.AnalysisEnd => val r = readAnalysisEndT(T); return r
        case Constants.LogikaVerifyState => val r = readLogikaVerifyStateT(T); return r
        case Constants.LogikaVerifyHalted => val r = readLogikaVerifyHaltedT(T); return r
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
      val par = reader.readZ()
      val id = reader.readISZ(reader.readString _)
      val uriOpt = reader.readOption(reader.readString _)
      val content = reader.readString()
      val line = reader.readZ()
      return Slang.Check.Script(isBackground, logikaEnabled, par, id, uriOpt, content, line)
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
      val par = reader.readZ()
      val id = reader.readISZ(reader.readString _)
      val proyek = reader.readString()
      val files = reader.readHashSMap(reader.readString _, reader.readString _)
      val vfiles = reader.readISZ(reader.readString _)
      val line = reader.readZ()
      return Slang.Check.Project(isBackground, par, id, proyek, files, vfiles, line)
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
      val numOfSmt2Calls = reader.readZ()
      val smt2TimeMillis = reader.readZ()
      val numOfInternalErrors = reader.readZ()
      val numOfErrors = reader.readZ()
      val numOfWarnings = reader.readZ()
      return Analysis.End(isBackground, id, wasCancelled, isIllFormed, hasLogika, totalTimeMillis, numOfSmt2Calls, smt2TimeMillis, numOfInternalErrors, numOfErrors, numOfWarnings)
    }

    def readLogikaVerifyConfig(): Logika.Verify.Config = {
      val r = readLogikaVerifyConfigT(F)
      return r
    }

    def readLogikaVerifyConfigT(typeParsed: B): Logika.Verify.Config = {
      if (!typeParsed) {
        reader.expectZ(Constants.LogikaVerifyConfig)
      }
      val hint = reader.readB()
      val smt2query = reader.readB()
      val config = readorgsireumlogikaConfig()
      return Logika.Verify.Config(hint, smt2query, config)
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
      val state = readorgsireumlogikaState()
      return Logika.Verify.State(id, posOpt, state)
    }

    def readLogikaVerifyHalted(): Logika.Verify.Halted = {
      val r = readLogikaVerifyHaltedT(F)
      return r
    }

    def readLogikaVerifyHaltedT(typeParsed: B): Logika.Verify.Halted = {
      if (!typeParsed) {
        reader.expectZ(Constants.LogikaVerifyHalted)
      }
      val id = reader.readISZ(reader.readString _)
      val posOpt = reader.readOption(reader.readPosition _)
      val state = readorgsireumlogikaState()
      return Logika.Verify.Halted(id, posOpt, state)
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
      val timeInMs = reader.readZ()
      val title = reader.readString()
      val kind = read_logikaSmt2QueryResultKindType()
      val solverName = reader.readString()
      val query = reader.readString()
      val info = reader.readString()
      val output = reader.readString()
      return Logika.Verify.Smt2Query(id, pos, timeInMs, title, kind, solverName, query, info, output)
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

    def readorgsireumlogikaState(): org.sireum.logika.State = {
      val r = readorgsireumlogikaStateT(F)
      return r
    }

    def readorgsireumlogikaStateT(typeParsed: B): org.sireum.logika.State = {
      if (!typeParsed) {
        reader.expectZ(Constants.orgsireumlogikaState)
      }
      val status = reader.readB()
      val claims = reader.readISZ(read_logikaStateClaim _)
      val nextFresh = reader.readZ()
      return org.sireum.logika.State(status, claims, nextFresh)
    }

    def read_logikaStateValue(): org.sireum.logika.State.Value = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._logikaStateValueUnit => val r = read_logikaStateValueUnitT(T); return r
        case Constants._logikaStateValueB => val r = read_logikaStateValueBT(T); return r
        case Constants._logikaStateValueZ => val r = read_logikaStateValueZT(T); return r
        case Constants._logikaStateValueC => val r = read_logikaStateValueCT(T); return r
        case Constants._logikaStateValueF32 => val r = read_logikaStateValueF32T(T); return r
        case Constants._logikaStateValueF64 => val r = read_logikaStateValueF64T(T); return r
        case Constants._logikaStateValueR => val r = read_logikaStateValueRT(T); return r
        case Constants._logikaStateValueString => val r = read_logikaStateValueStringT(T); return r
        case Constants._logikaStateValueRange => val r = read_logikaStateValueRangeT(T); return r
        case Constants._logikaStateValueS8 => val r = read_logikaStateValueS8T(T); return r
        case Constants._logikaStateValueS16 => val r = read_logikaStateValueS16T(T); return r
        case Constants._logikaStateValueS32 => val r = read_logikaStateValueS32T(T); return r
        case Constants._logikaStateValueS64 => val r = read_logikaStateValueS64T(T); return r
        case Constants._logikaStateValueU8 => val r = read_logikaStateValueU8T(T); return r
        case Constants._logikaStateValueU16 => val r = read_logikaStateValueU16T(T); return r
        case Constants._logikaStateValueU32 => val r = read_logikaStateValueU32T(T); return r
        case Constants._logikaStateValueU64 => val r = read_logikaStateValueU64T(T); return r
        case Constants._logikaStateValueEnum => val r = read_logikaStateValueEnumT(T); return r
        case Constants._logikaStateValueSym => val r = read_logikaStateValueSymT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.State.Value.")
          val r = read_logikaStateValueSymT(T)
          return r
      }
    }

    def read_logikaStateFun(): org.sireum.logika.State.Fun = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._logikaStateIFun => val r = read_logikaStateIFunT(T); return r
        case Constants._logikaStateOFun => val r = read_logikaStateOFunT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.State.Fun.")
          val r = read_logikaStateOFunT(T)
          return r
      }
    }

    def read_logikaStateIFun(): org.sireum.logika.State.IFun = {
      val r = read_logikaStateIFunT(F)
      return r
    }

    def read_logikaStateIFunT(typeParsed: B): org.sireum.logika.State.IFun = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateIFun)
      }
      val tipe = read_langastTypedName()
      val id = reader.readString()
      return org.sireum.logika.State.IFun(tipe, id)
    }

    def read_logikaStateOFun(): org.sireum.logika.State.OFun = {
      val r = read_logikaStateOFunT(F)
      return r
    }

    def read_logikaStateOFunT(typeParsed: B): org.sireum.logika.State.OFun = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateOFun)
      }
      val name = reader.readISZ(reader.readString _)
      return org.sireum.logika.State.OFun(name)
    }

    def read_logikaStateValueUnit(): org.sireum.logika.State.Value.Unit = {
      val r = read_logikaStateValueUnitT(F)
      return r
    }

    def read_logikaStateValueUnitT(typeParsed: B): org.sireum.logika.State.Value.Unit = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueUnit)
      }
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.Unit(pos)
    }

    def read_logikaStateValueB(): org.sireum.logika.State.Value.B = {
      val r = read_logikaStateValueBT(F)
      return r
    }

    def read_logikaStateValueBT(typeParsed: B): org.sireum.logika.State.Value.B = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueB)
      }
      val value = reader.readB()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.B(value, pos)
    }

    def read_logikaStateValueZ(): org.sireum.logika.State.Value.Z = {
      val r = read_logikaStateValueZT(F)
      return r
    }

    def read_logikaStateValueZT(typeParsed: B): org.sireum.logika.State.Value.Z = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueZ)
      }
      val value = reader.readZ()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.Z(value, pos)
    }

    def read_logikaStateValueC(): org.sireum.logika.State.Value.C = {
      val r = read_logikaStateValueCT(F)
      return r
    }

    def read_logikaStateValueCT(typeParsed: B): org.sireum.logika.State.Value.C = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueC)
      }
      val value = reader.readC()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.C(value, pos)
    }

    def read_logikaStateValueF32(): org.sireum.logika.State.Value.F32 = {
      val r = read_logikaStateValueF32T(F)
      return r
    }

    def read_logikaStateValueF32T(typeParsed: B): org.sireum.logika.State.Value.F32 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueF32)
      }
      val value = reader.readF32()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.F32(value, pos)
    }

    def read_logikaStateValueF64(): org.sireum.logika.State.Value.F64 = {
      val r = read_logikaStateValueF64T(F)
      return r
    }

    def read_logikaStateValueF64T(typeParsed: B): org.sireum.logika.State.Value.F64 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueF64)
      }
      val value = reader.readF64()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.F64(value, pos)
    }

    def read_logikaStateValueR(): org.sireum.logika.State.Value.R = {
      val r = read_logikaStateValueRT(F)
      return r
    }

    def read_logikaStateValueRT(typeParsed: B): org.sireum.logika.State.Value.R = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueR)
      }
      val value = reader.readR()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.R(value, pos)
    }

    def read_logikaStateValueString(): org.sireum.logika.State.Value.String = {
      val r = read_logikaStateValueStringT(F)
      return r
    }

    def read_logikaStateValueStringT(typeParsed: B): org.sireum.logika.State.Value.String = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueString)
      }
      val value = reader.readString()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.String(value, pos)
    }

    def read_logikaStateValueSubZ(): org.sireum.logika.State.Value.SubZ = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._logikaStateValueRange => val r = read_logikaStateValueRangeT(T); return r
        case Constants._logikaStateValueS8 => val r = read_logikaStateValueS8T(T); return r
        case Constants._logikaStateValueS16 => val r = read_logikaStateValueS16T(T); return r
        case Constants._logikaStateValueS32 => val r = read_logikaStateValueS32T(T); return r
        case Constants._logikaStateValueS64 => val r = read_logikaStateValueS64T(T); return r
        case Constants._logikaStateValueU8 => val r = read_logikaStateValueU8T(T); return r
        case Constants._logikaStateValueU16 => val r = read_logikaStateValueU16T(T); return r
        case Constants._logikaStateValueU32 => val r = read_logikaStateValueU32T(T); return r
        case Constants._logikaStateValueU64 => val r = read_logikaStateValueU64T(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.State.Value.SubZ.")
          val r = read_logikaStateValueU64T(T)
          return r
      }
    }

    def read_logikaStateValueRange(): org.sireum.logika.State.Value.Range = {
      val r = read_logikaStateValueRangeT(F)
      return r
    }

    def read_logikaStateValueRangeT(typeParsed: B): org.sireum.logika.State.Value.Range = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueRange)
      }
      val value = reader.readZ()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.Range(value, tipe, pos)
    }

    def read_logikaStateValueS8(): org.sireum.logika.State.Value.S8 = {
      val r = read_logikaStateValueS8T(F)
      return r
    }

    def read_logikaStateValueS8T(typeParsed: B): org.sireum.logika.State.Value.S8 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueS8)
      }
      val value = reader.readS8()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.S8(value, tipe, pos)
    }

    def read_logikaStateValueS16(): org.sireum.logika.State.Value.S16 = {
      val r = read_logikaStateValueS16T(F)
      return r
    }

    def read_logikaStateValueS16T(typeParsed: B): org.sireum.logika.State.Value.S16 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueS16)
      }
      val value = reader.readS16()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.S16(value, tipe, pos)
    }

    def read_logikaStateValueS32(): org.sireum.logika.State.Value.S32 = {
      val r = read_logikaStateValueS32T(F)
      return r
    }

    def read_logikaStateValueS32T(typeParsed: B): org.sireum.logika.State.Value.S32 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueS32)
      }
      val value = reader.readS32()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.S32(value, tipe, pos)
    }

    def read_logikaStateValueS64(): org.sireum.logika.State.Value.S64 = {
      val r = read_logikaStateValueS64T(F)
      return r
    }

    def read_logikaStateValueS64T(typeParsed: B): org.sireum.logika.State.Value.S64 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueS64)
      }
      val value = reader.readS64()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.S64(value, tipe, pos)
    }

    def read_logikaStateValueU8(): org.sireum.logika.State.Value.U8 = {
      val r = read_logikaStateValueU8T(F)
      return r
    }

    def read_logikaStateValueU8T(typeParsed: B): org.sireum.logika.State.Value.U8 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueU8)
      }
      val value = reader.readU8()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.U8(value, tipe, pos)
    }

    def read_logikaStateValueU16(): org.sireum.logika.State.Value.U16 = {
      val r = read_logikaStateValueU16T(F)
      return r
    }

    def read_logikaStateValueU16T(typeParsed: B): org.sireum.logika.State.Value.U16 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueU16)
      }
      val value = reader.readU16()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.U16(value, tipe, pos)
    }

    def read_logikaStateValueU32(): org.sireum.logika.State.Value.U32 = {
      val r = read_logikaStateValueU32T(F)
      return r
    }

    def read_logikaStateValueU32T(typeParsed: B): org.sireum.logika.State.Value.U32 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueU32)
      }
      val value = reader.readU32()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.U32(value, tipe, pos)
    }

    def read_logikaStateValueU64(): org.sireum.logika.State.Value.U64 = {
      val r = read_logikaStateValueU64T(F)
      return r
    }

    def read_logikaStateValueU64T(typeParsed: B): org.sireum.logika.State.Value.U64 = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueU64)
      }
      val value = reader.readU64()
      val tipe = read_langastTypedName()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.U64(value, tipe, pos)
    }

    def read_logikaStateValueEnum(): org.sireum.logika.State.Value.Enum = {
      val r = read_logikaStateValueEnumT(F)
      return r
    }

    def read_logikaStateValueEnumT(typeParsed: B): org.sireum.logika.State.Value.Enum = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueEnum)
      }
      val tipe = read_langastTypedName()
      val owner = reader.readISZ(reader.readString _)
      val id = reader.readString()
      val ordinal = reader.readZ()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.Enum(tipe, owner, id, ordinal, pos)
    }

    def read_logikaStateValueSym(): org.sireum.logika.State.Value.Sym = {
      val r = read_logikaStateValueSymT(F)
      return r
    }

    def read_logikaStateValueSymT(typeParsed: B): org.sireum.logika.State.Value.Sym = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateValueSym)
      }
      val num = reader.readZ()
      val tipe = read_langastTyped()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Value.Sym(num, tipe, pos)
    }

    def read_logikaStateClaim(): org.sireum.logika.State.Claim = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._logikaStateClaimLabel => val r = read_logikaStateClaimLabelT(T); return r
        case Constants._logikaStateClaimProp => val r = read_logikaStateClaimPropT(T); return r
        case Constants._logikaStateClaimAnd => val r = read_logikaStateClaimAndT(T); return r
        case Constants._logikaStateClaimOr => val r = read_logikaStateClaimOrT(T); return r
        case Constants._logikaStateClaimImply => val r = read_logikaStateClaimImplyT(T); return r
        case Constants._logikaStateClaimIf => val r = read_logikaStateClaimIfT(T); return r
        case Constants._logikaStateClaimLetAdtLit => val r = read_logikaStateClaimLetAdtLitT(T); return r
        case Constants._logikaStateClaimLetSeqLit => val r = read_logikaStateClaimLetSeqLitT(T); return r
        case Constants._logikaStateClaimLetCurrentName => val r = read_logikaStateClaimLetCurrentNameT(T); return r
        case Constants._logikaStateClaimLetSeqStore => val r = read_logikaStateClaimLetSeqStoreT(T); return r
        case Constants._logikaStateClaimLetFieldStore => val r = read_logikaStateClaimLetFieldStoreT(T); return r
        case Constants._logikaStateClaimLetRandom => val r = read_logikaStateClaimLetRandomT(T); return r
        case Constants._logikaStateClaimLetName => val r = read_logikaStateClaimLetNameT(T); return r
        case Constants._logikaStateClaimLetCurrentId => val r = read_logikaStateClaimLetCurrentIdT(T); return r
        case Constants._logikaStateClaimLetId => val r = read_logikaStateClaimLetIdT(T); return r
        case Constants._logikaStateClaimLetEq => val r = read_logikaStateClaimLetEqT(T); return r
        case Constants._logikaStateClaimLetTypeTest => val r = read_logikaStateClaimLetTypeTestT(T); return r
        case Constants._logikaStateClaimLetQuant => val r = read_logikaStateClaimLetQuantT(T); return r
        case Constants._logikaStateClaimLetIte => val r = read_logikaStateClaimLetIteT(T); return r
        case Constants._logikaStateClaimLetBinary => val r = read_logikaStateClaimLetBinaryT(T); return r
        case Constants._logikaStateClaimLetUnary => val r = read_logikaStateClaimLetUnaryT(T); return r
        case Constants._logikaStateClaimLetSeqLookup => val r = read_logikaStateClaimLetSeqLookupT(T); return r
        case Constants._logikaStateClaimLetSeqInBound => val r = read_logikaStateClaimLetSeqInBoundT(T); return r
        case Constants._logikaStateClaimLetFieldLookup => val r = read_logikaStateClaimLetFieldLookupT(T); return r
        case Constants._logikaStateClaimLetProofFunApply => val r = read_logikaStateClaimLetProofFunApplyT(T); return r
        case Constants._logikaStateClaimLetApply => val r = read_logikaStateClaimLetApplyT(T); return r
        case Constants._logikaStateClaimLetIApply => val r = read_logikaStateClaimLetIApplyT(T); return r
        case Constants._logikaStateClaimLetTupleLit => val r = read_logikaStateClaimLetTupleLitT(T); return r
        case Constants._logikaStateClaimLetAnd => val r = read_logikaStateClaimLetAndT(T); return r
        case Constants._logikaStateClaimLetOr => val r = read_logikaStateClaimLetOrT(T); return r
        case Constants._logikaStateClaimLetImply => val r = read_logikaStateClaimLetImplyT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.State.Claim.")
          val r = read_logikaStateClaimLetImplyT(T)
          return r
      }
    }

    def read_logikaStateClaimComposite(): org.sireum.logika.State.Claim.Composite = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._logikaStateClaimAnd => val r = read_logikaStateClaimAndT(T); return r
        case Constants._logikaStateClaimOr => val r = read_logikaStateClaimOrT(T); return r
        case Constants._logikaStateClaimImply => val r = read_logikaStateClaimImplyT(T); return r
        case Constants._logikaStateClaimIf => val r = read_logikaStateClaimIfT(T); return r
        case Constants._logikaStateClaimLetQuant => val r = read_logikaStateClaimLetQuantT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.State.Claim.Composite.")
          val r = read_logikaStateClaimLetQuantT(T)
          return r
      }
    }

    def read_logikaStateClaimLabel(): org.sireum.logika.State.Claim.Label = {
      val r = read_logikaStateClaimLabelT(F)
      return r
    }

    def read_logikaStateClaimLabelT(typeParsed: B): org.sireum.logika.State.Claim.Label = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLabel)
      }
      val label = reader.readString()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Claim.Label(label, pos)
    }

    def read_logikaStateClaimProp(): org.sireum.logika.State.Claim.Prop = {
      val r = read_logikaStateClaimPropT(F)
      return r
    }

    def read_logikaStateClaimPropT(typeParsed: B): org.sireum.logika.State.Claim.Prop = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimProp)
      }
      val isPos = reader.readB()
      val value = read_logikaStateValueSym()
      return org.sireum.logika.State.Claim.Prop(isPos, value)
    }

    def read_logikaStateClaimAnd(): org.sireum.logika.State.Claim.And = {
      val r = read_logikaStateClaimAndT(F)
      return r
    }

    def read_logikaStateClaimAndT(typeParsed: B): org.sireum.logika.State.Claim.And = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimAnd)
      }
      val claims = reader.readISZ(read_logikaStateClaim _)
      return org.sireum.logika.State.Claim.And(claims)
    }

    def read_logikaStateClaimOr(): org.sireum.logika.State.Claim.Or = {
      val r = read_logikaStateClaimOrT(F)
      return r
    }

    def read_logikaStateClaimOrT(typeParsed: B): org.sireum.logika.State.Claim.Or = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimOr)
      }
      val claims = reader.readISZ(read_logikaStateClaim _)
      return org.sireum.logika.State.Claim.Or(claims)
    }

    def read_logikaStateClaimImply(): org.sireum.logika.State.Claim.Imply = {
      val r = read_logikaStateClaimImplyT(F)
      return r
    }

    def read_logikaStateClaimImplyT(typeParsed: B): org.sireum.logika.State.Claim.Imply = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimImply)
      }
      val claims = reader.readISZ(read_logikaStateClaim _)
      return org.sireum.logika.State.Claim.Imply(claims)
    }

    def read_logikaStateClaimIf(): org.sireum.logika.State.Claim.If = {
      val r = read_logikaStateClaimIfT(F)
      return r
    }

    def read_logikaStateClaimIfT(typeParsed: B): org.sireum.logika.State.Claim.If = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimIf)
      }
      val cond = read_logikaStateValueSym()
      val tClaims = reader.readISZ(read_logikaStateClaim _)
      val fClaims = reader.readISZ(read_logikaStateClaim _)
      return org.sireum.logika.State.Claim.If(cond, tClaims, fClaims)
    }

    def read_logikaStateClaimLet(): org.sireum.logika.State.Claim.Let = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._logikaStateClaimLetAdtLit => val r = read_logikaStateClaimLetAdtLitT(T); return r
        case Constants._logikaStateClaimLetSeqLit => val r = read_logikaStateClaimLetSeqLitT(T); return r
        case Constants._logikaStateClaimLetCurrentName => val r = read_logikaStateClaimLetCurrentNameT(T); return r
        case Constants._logikaStateClaimLetSeqStore => val r = read_logikaStateClaimLetSeqStoreT(T); return r
        case Constants._logikaStateClaimLetFieldStore => val r = read_logikaStateClaimLetFieldStoreT(T); return r
        case Constants._logikaStateClaimLetRandom => val r = read_logikaStateClaimLetRandomT(T); return r
        case Constants._logikaStateClaimLetName => val r = read_logikaStateClaimLetNameT(T); return r
        case Constants._logikaStateClaimLetCurrentId => val r = read_logikaStateClaimLetCurrentIdT(T); return r
        case Constants._logikaStateClaimLetId => val r = read_logikaStateClaimLetIdT(T); return r
        case Constants._logikaStateClaimLetEq => val r = read_logikaStateClaimLetEqT(T); return r
        case Constants._logikaStateClaimLetTypeTest => val r = read_logikaStateClaimLetTypeTestT(T); return r
        case Constants._logikaStateClaimLetQuant => val r = read_logikaStateClaimLetQuantT(T); return r
        case Constants._logikaStateClaimLetIte => val r = read_logikaStateClaimLetIteT(T); return r
        case Constants._logikaStateClaimLetBinary => val r = read_logikaStateClaimLetBinaryT(T); return r
        case Constants._logikaStateClaimLetUnary => val r = read_logikaStateClaimLetUnaryT(T); return r
        case Constants._logikaStateClaimLetSeqLookup => val r = read_logikaStateClaimLetSeqLookupT(T); return r
        case Constants._logikaStateClaimLetSeqInBound => val r = read_logikaStateClaimLetSeqInBoundT(T); return r
        case Constants._logikaStateClaimLetFieldLookup => val r = read_logikaStateClaimLetFieldLookupT(T); return r
        case Constants._logikaStateClaimLetProofFunApply => val r = read_logikaStateClaimLetProofFunApplyT(T); return r
        case Constants._logikaStateClaimLetApply => val r = read_logikaStateClaimLetApplyT(T); return r
        case Constants._logikaStateClaimLetIApply => val r = read_logikaStateClaimLetIApplyT(T); return r
        case Constants._logikaStateClaimLetTupleLit => val r = read_logikaStateClaimLetTupleLitT(T); return r
        case Constants._logikaStateClaimLetAnd => val r = read_logikaStateClaimLetAndT(T); return r
        case Constants._logikaStateClaimLetOr => val r = read_logikaStateClaimLetOrT(T); return r
        case Constants._logikaStateClaimLetImply => val r = read_logikaStateClaimLetImplyT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.State.Claim.Let.")
          val r = read_logikaStateClaimLetImplyT(T)
          return r
      }
    }

    def read_logikaStateClaimLetAdtLit(): org.sireum.logika.State.Claim.Let.AdtLit = {
      val r = read_logikaStateClaimLetAdtLitT(F)
      return r
    }

    def read_logikaStateClaimLetAdtLitT(typeParsed: B): org.sireum.logika.State.Claim.Let.AdtLit = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetAdtLit)
      }
      val sym = read_logikaStateValueSym()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.AdtLit(sym, args)
    }

    def read_logikaStateClaimLetSeqLit(): org.sireum.logika.State.Claim.Let.SeqLit = {
      val r = read_logikaStateClaimLetSeqLitT(F)
      return r
    }

    def read_logikaStateClaimLetSeqLitT(typeParsed: B): org.sireum.logika.State.Claim.Let.SeqLit = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetSeqLit)
      }
      val sym = read_logikaStateValueSym()
      val args = reader.readISZ(read_logikaStateClaimLetSeqLitArg _)
      return org.sireum.logika.State.Claim.Let.SeqLit(sym, args)
    }

    def read_logikaStateClaimLetSeqLitArg(): org.sireum.logika.State.Claim.Let.SeqLit.Arg = {
      val r = read_logikaStateClaimLetSeqLitArgT(F)
      return r
    }

    def read_logikaStateClaimLetSeqLitArgT(typeParsed: B): org.sireum.logika.State.Claim.Let.SeqLit.Arg = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetSeqLitArg)
      }
      val index = read_logikaStateValue()
      val value = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.SeqLit.Arg(index, value)
    }

    def read_logikaStateClaimLetCurrentName(): org.sireum.logika.State.Claim.Let.CurrentName = {
      val r = read_logikaStateClaimLetCurrentNameT(F)
      return r
    }

    def read_logikaStateClaimLetCurrentNameT(typeParsed: B): org.sireum.logika.State.Claim.Let.CurrentName = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetCurrentName)
      }
      val sym = read_logikaStateValueSym()
      val ids = reader.readISZ(reader.readString _)
      val defPosOpt = reader.readOption(reader.readPosition _)
      return org.sireum.logika.State.Claim.Let.CurrentName(sym, ids, defPosOpt)
    }

    def read_logikaStateClaimLetSeqStore(): org.sireum.logika.State.Claim.Let.SeqStore = {
      val r = read_logikaStateClaimLetSeqStoreT(F)
      return r
    }

    def read_logikaStateClaimLetSeqStoreT(typeParsed: B): org.sireum.logika.State.Claim.Let.SeqStore = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetSeqStore)
      }
      val sym = read_logikaStateValueSym()
      val seq = read_logikaStateValue()
      val index = read_logikaStateValue()
      val element = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.SeqStore(sym, seq, index, element)
    }

    def read_logikaStateClaimLetFieldStore(): org.sireum.logika.State.Claim.Let.FieldStore = {
      val r = read_logikaStateClaimLetFieldStoreT(F)
      return r
    }

    def read_logikaStateClaimLetFieldStoreT(typeParsed: B): org.sireum.logika.State.Claim.Let.FieldStore = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetFieldStore)
      }
      val sym = read_logikaStateValueSym()
      val adt = read_logikaStateValue()
      val id = reader.readString()
      val value = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.FieldStore(sym, adt, id, value)
    }

    def read_logikaStateClaimLetRandom(): org.sireum.logika.State.Claim.Let.Random = {
      val r = read_logikaStateClaimLetRandomT(F)
      return r
    }

    def read_logikaStateClaimLetRandomT(typeParsed: B): org.sireum.logika.State.Claim.Let.Random = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetRandom)
      }
      val sym = read_logikaStateValueSym()
      val pos = reader.readPosition()
      return org.sireum.logika.State.Claim.Let.Random(sym, pos)
    }

    def read_logikaStateClaimLetName(): org.sireum.logika.State.Claim.Let.Name = {
      val r = read_logikaStateClaimLetNameT(F)
      return r
    }

    def read_logikaStateClaimLetNameT(typeParsed: B): org.sireum.logika.State.Claim.Let.Name = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetName)
      }
      val sym = read_logikaStateValueSym()
      val ids = reader.readISZ(reader.readString _)
      val num = reader.readZ()
      val poss = reader.readISZ(reader.readPosition _)
      return org.sireum.logika.State.Claim.Let.Name(sym, ids, num, poss)
    }

    def read_logikaStateClaimLetCurrentId(): org.sireum.logika.State.Claim.Let.CurrentId = {
      val r = read_logikaStateClaimLetCurrentIdT(F)
      return r
    }

    def read_logikaStateClaimLetCurrentIdT(typeParsed: B): org.sireum.logika.State.Claim.Let.CurrentId = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetCurrentId)
      }
      val declId = reader.readB()
      val sym = read_logikaStateValueSym()
      val context = reader.readISZ(reader.readString _)
      val id = reader.readString()
      val defPosOpt = reader.readOption(reader.readPosition _)
      return org.sireum.logika.State.Claim.Let.CurrentId(declId, sym, context, id, defPosOpt)
    }

    def read_logikaStateClaimLetId(): org.sireum.logika.State.Claim.Let.Id = {
      val r = read_logikaStateClaimLetIdT(F)
      return r
    }

    def read_logikaStateClaimLetIdT(typeParsed: B): org.sireum.logika.State.Claim.Let.Id = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetId)
      }
      val sym = read_logikaStateValueSym()
      val context = reader.readISZ(reader.readString _)
      val id = reader.readString()
      val num = reader.readZ()
      val poss = reader.readISZ(reader.readPosition _)
      return org.sireum.logika.State.Claim.Let.Id(sym, context, id, num, poss)
    }

    def read_logikaStateClaimLetEq(): org.sireum.logika.State.Claim.Let.Eq = {
      val r = read_logikaStateClaimLetEqT(F)
      return r
    }

    def read_logikaStateClaimLetEqT(typeParsed: B): org.sireum.logika.State.Claim.Let.Eq = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetEq)
      }
      val sym = read_logikaStateValueSym()
      val value = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.Eq(sym, value)
    }

    def read_logikaStateClaimLetTypeTest(): org.sireum.logika.State.Claim.Let.TypeTest = {
      val r = read_logikaStateClaimLetTypeTestT(F)
      return r
    }

    def read_logikaStateClaimLetTypeTestT(typeParsed: B): org.sireum.logika.State.Claim.Let.TypeTest = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetTypeTest)
      }
      val sym = read_logikaStateValueSym()
      val isEq = reader.readB()
      val value = read_logikaStateValue()
      val tipe = read_langastTyped()
      return org.sireum.logika.State.Claim.Let.TypeTest(sym, isEq, value, tipe)
    }

    def read_logikaStateClaimLetQuantVar(): org.sireum.logika.State.Claim.Let.Quant.Var = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants._logikaStateClaimLetQuantVarId => val r = read_logikaStateClaimLetQuantVarIdT(T); return r
        case Constants._logikaStateClaimLetQuantVarSym => val r = read_logikaStateClaimLetQuantVarSymT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.State.Claim.Let.Quant.Var.")
          val r = read_logikaStateClaimLetQuantVarSymT(T)
          return r
      }
    }

    def read_logikaStateClaimLetQuantVarId(): org.sireum.logika.State.Claim.Let.Quant.Var.Id = {
      val r = read_logikaStateClaimLetQuantVarIdT(F)
      return r
    }

    def read_logikaStateClaimLetQuantVarIdT(typeParsed: B): org.sireum.logika.State.Claim.Let.Quant.Var.Id = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetQuantVarId)
      }
      val context = reader.readISZ(reader.readString _)
      val id = reader.readString()
      val tipe = read_langastTyped()
      return org.sireum.logika.State.Claim.Let.Quant.Var.Id(context, id, tipe)
    }

    def read_logikaStateClaimLetQuantVarSym(): org.sireum.logika.State.Claim.Let.Quant.Var.Sym = {
      val r = read_logikaStateClaimLetQuantVarSymT(F)
      return r
    }

    def read_logikaStateClaimLetQuantVarSymT(typeParsed: B): org.sireum.logika.State.Claim.Let.Quant.Var.Sym = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetQuantVarSym)
      }
      val sym = read_logikaStateValueSym()
      return org.sireum.logika.State.Claim.Let.Quant.Var.Sym(sym)
    }

    def read_logikaStateClaimLetQuant(): org.sireum.logika.State.Claim.Let.Quant = {
      val r = read_logikaStateClaimLetQuantT(F)
      return r
    }

    def read_logikaStateClaimLetQuantT(typeParsed: B): org.sireum.logika.State.Claim.Let.Quant = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetQuant)
      }
      val sym = read_logikaStateValueSym()
      val isAll = reader.readB()
      val vars = reader.readISZ(read_logikaStateClaimLetQuantVar _)
      val claims = reader.readISZ(read_logikaStateClaim _)
      return org.sireum.logika.State.Claim.Let.Quant(sym, isAll, vars, claims)
    }

    def read_logikaStateClaimLetIte(): org.sireum.logika.State.Claim.Let.Ite = {
      val r = read_logikaStateClaimLetIteT(F)
      return r
    }

    def read_logikaStateClaimLetIteT(typeParsed: B): org.sireum.logika.State.Claim.Let.Ite = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetIte)
      }
      val sym = read_logikaStateValueSym()
      val cond = read_logikaStateValue()
      val left = read_logikaStateValue()
      val right = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.Ite(sym, cond, left, right)
    }

    def read_logikaStateClaimLetBinary(): org.sireum.logika.State.Claim.Let.Binary = {
      val r = read_logikaStateClaimLetBinaryT(F)
      return r
    }

    def read_logikaStateClaimLetBinaryT(typeParsed: B): org.sireum.logika.State.Claim.Let.Binary = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetBinary)
      }
      val sym = read_logikaStateValueSym()
      val left = read_logikaStateValue()
      val op = reader.readString()
      val right = read_logikaStateValue()
      val tipe = read_langastTyped()
      return org.sireum.logika.State.Claim.Let.Binary(sym, left, op, right, tipe)
    }

    def read_logikaStateClaimLetUnary(): org.sireum.logika.State.Claim.Let.Unary = {
      val r = read_logikaStateClaimLetUnaryT(F)
      return r
    }

    def read_logikaStateClaimLetUnaryT(typeParsed: B): org.sireum.logika.State.Claim.Let.Unary = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetUnary)
      }
      val sym = read_logikaStateValueSym()
      val op = reader.readString()
      val value = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.Unary(sym, op, value)
    }

    def read_logikaStateClaimLetSeqLookup(): org.sireum.logika.State.Claim.Let.SeqLookup = {
      val r = read_logikaStateClaimLetSeqLookupT(F)
      return r
    }

    def read_logikaStateClaimLetSeqLookupT(typeParsed: B): org.sireum.logika.State.Claim.Let.SeqLookup = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetSeqLookup)
      }
      val sym = read_logikaStateValueSym()
      val seq = read_logikaStateValue()
      val index = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.SeqLookup(sym, seq, index)
    }

    def read_logikaStateClaimLetSeqInBound(): org.sireum.logika.State.Claim.Let.SeqInBound = {
      val r = read_logikaStateClaimLetSeqInBoundT(F)
      return r
    }

    def read_logikaStateClaimLetSeqInBoundT(typeParsed: B): org.sireum.logika.State.Claim.Let.SeqInBound = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetSeqInBound)
      }
      val sym = read_logikaStateValueSym()
      val seq = read_logikaStateValue()
      val index = read_logikaStateValue()
      return org.sireum.logika.State.Claim.Let.SeqInBound(sym, seq, index)
    }

    def read_logikaStateClaimLetFieldLookup(): org.sireum.logika.State.Claim.Let.FieldLookup = {
      val r = read_logikaStateClaimLetFieldLookupT(F)
      return r
    }

    def read_logikaStateClaimLetFieldLookupT(typeParsed: B): org.sireum.logika.State.Claim.Let.FieldLookup = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetFieldLookup)
      }
      val sym = read_logikaStateValueSym()
      val adt = read_logikaStateValue()
      val id = reader.readString()
      return org.sireum.logika.State.Claim.Let.FieldLookup(sym, adt, id)
    }

    def read_logikaStateClaimLetProofFunApply(): org.sireum.logika.State.Claim.Let.ProofFunApply = {
      val r = read_logikaStateClaimLetProofFunApplyT(F)
      return r
    }

    def read_logikaStateClaimLetProofFunApplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.ProofFunApply = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetProofFunApply)
      }
      val sym = read_logikaStateValueSym()
      val pf = read_logikaStateProofFun()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.ProofFunApply(sym, pf, args)
    }

    def read_logikaStateClaimLetApply(): org.sireum.logika.State.Claim.Let.Apply = {
      val r = read_logikaStateClaimLetApplyT(F)
      return r
    }

    def read_logikaStateClaimLetApplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.Apply = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetApply)
      }
      val sym = read_logikaStateValueSym()
      val isLocal = reader.readB()
      val context = reader.readISZ(reader.readString _)
      val id = reader.readString()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.Apply(sym, isLocal, context, id, args)
    }

    def read_logikaStateClaimLetIApply(): org.sireum.logika.State.Claim.Let.IApply = {
      val r = read_logikaStateClaimLetIApplyT(F)
      return r
    }

    def read_logikaStateClaimLetIApplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.IApply = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetIApply)
      }
      val sym = read_logikaStateValueSym()
      val o = read_logikaStateValue()
      val oTipe = read_langastTypedName()
      val id = reader.readString()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.IApply(sym, o, oTipe, id, args)
    }

    def read_logikaStateClaimLetTupleLit(): org.sireum.logika.State.Claim.Let.TupleLit = {
      val r = read_logikaStateClaimLetTupleLitT(F)
      return r
    }

    def read_logikaStateClaimLetTupleLitT(typeParsed: B): org.sireum.logika.State.Claim.Let.TupleLit = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetTupleLit)
      }
      val sym = read_logikaStateValueSym()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.TupleLit(sym, args)
    }

    def read_logikaStateClaimLetAnd(): org.sireum.logika.State.Claim.Let.And = {
      val r = read_logikaStateClaimLetAndT(F)
      return r
    }

    def read_logikaStateClaimLetAndT(typeParsed: B): org.sireum.logika.State.Claim.Let.And = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetAnd)
      }
      val sym = read_logikaStateValueSym()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.And(sym, args)
    }

    def read_logikaStateClaimLetOr(): org.sireum.logika.State.Claim.Let.Or = {
      val r = read_logikaStateClaimLetOrT(F)
      return r
    }

    def read_logikaStateClaimLetOrT(typeParsed: B): org.sireum.logika.State.Claim.Let.Or = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetOr)
      }
      val sym = read_logikaStateValueSym()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.Or(sym, args)
    }

    def read_logikaStateClaimLetImply(): org.sireum.logika.State.Claim.Let.Imply = {
      val r = read_logikaStateClaimLetImplyT(F)
      return r
    }

    def read_logikaStateClaimLetImplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.Imply = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateClaimLetImply)
      }
      val sym = read_logikaStateValueSym()
      val args = reader.readISZ(read_logikaStateValue _)
      return org.sireum.logika.State.Claim.Let.Imply(sym, args)
    }

    def read_logikaStateProofFun(): org.sireum.logika.State.ProofFun = {
      val r = read_logikaStateProofFunT(F)
      return r
    }

    def read_logikaStateProofFunT(typeParsed: B): org.sireum.logika.State.ProofFun = {
      if (!typeParsed) {
        reader.expectZ(Constants._logikaStateProofFun)
      }
      val receiverTypeOpt = reader.readOption(read_langastTyped _)
      val context = reader.readISZ(reader.readString _)
      val id = reader.readString()
      val paramIds = reader.readISZ(reader.readString _)
      val paramTypes = reader.readISZ(read_langastTyped _)
      val returnType = read_langastTyped()
      return org.sireum.logika.State.ProofFun(receiverTypeOpt, context, id, paramIds, paramTypes, returnType)
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
      val sat = reader.readB()
      val timeoutInMs = reader.readZ()
      val defaultLoopBound = reader.readZ()
      val loopBounds = reader.readHashMap(readorgsireumlogikaLoopId _, reader.readZ _)
      val unroll = reader.readB()
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
      val cvcRLimit = reader.readZ()
      val fpRoundingMode = reader.readString()
      val caching = reader.readB()
      return org.sireum.logika.Config(smt2Configs, sat, timeoutInMs, defaultLoopBound, loopBounds, unroll, charBitWidth, intBitWidth, useReal, logPc, logRawPc, logVc, logVcDirOpt, dontSplitPfq, splitAll, splitIf, splitMatch, splitContract, simplifiedQuery, checkInfeasiblePatternMatch, cvcRLimit, fpRoundingMode, caching)
    }

    def readorgsireumlogikaSmt2Config(): org.sireum.logika.Smt2Config = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants.orgsireumlogikaZ3Config => val r = readorgsireumlogikaZ3ConfigT(T); return r
        case Constants.orgsireumlogikaCvcConfig => val r = readorgsireumlogikaCvcConfigT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of org.sireum.logika.Smt2Config.")
          val r = readorgsireumlogikaCvcConfigT(T)
          return r
      }
    }

    def readorgsireumlogikaZ3Config(): org.sireum.logika.Z3Config = {
      val r = readorgsireumlogikaZ3ConfigT(F)
      return r
    }

    def readorgsireumlogikaZ3ConfigT(typeParsed: B): org.sireum.logika.Z3Config = {
      if (!typeParsed) {
        reader.expectZ(Constants.orgsireumlogikaZ3Config)
      }
      val exe = reader.readString()
      val validOpts = reader.readISZ(reader.readString _)
      val satOpts = reader.readISZ(reader.readString _)
      return org.sireum.logika.Z3Config(exe, validOpts, satOpts)
    }

    def readorgsireumlogikaCvcConfig(): org.sireum.logika.CvcConfig = {
      val r = readorgsireumlogikaCvcConfigT(F)
      return r
    }

    def readorgsireumlogikaCvcConfigT(typeParsed: B): org.sireum.logika.CvcConfig = {
      if (!typeParsed) {
        reader.expectZ(Constants.orgsireumlogikaCvcConfig)
      }
      val exe = reader.readString()
      val validOpts = reader.readISZ(reader.readString _)
      val satOpts = reader.readISZ(reader.readString _)
      return org.sireum.logika.CvcConfig(exe, validOpts, satOpts)
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
      val cached = reader.readB()
      return org.sireum.logika.Smt2Query.Result(kind, solverName, query, info, output, timeMillis, cached)
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
      val isPure = reader.readB()
      val isByName = reader.readB()
      val args = reader.readISZ(read_langastTyped _)
      val ret = read_langastTyped()
      return org.sireum.lang.ast.Typed.Fun(isPure, isByName, args, ret)
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
      return org.sireum.lang.ast.Typed.TypeVar(id)
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

  def fromLogikaVerifyHalted(o: Logika.Verify.Halted, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeLogikaVerifyHalted(o)
    return w.result
  }

  def toLogikaVerifyHalted(data: ISZ[U8]): Either[Logika.Verify.Halted, MessagePack.ErrorMsg] = {
    def fLogikaVerifyHalted(reader: Reader): Logika.Verify.Halted = {
      val r = reader.readLogikaVerifyHalted()
      return r
    }
    val r = to(data, fLogikaVerifyHalted _)
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

  def fromorgsireumlogikaState(o: org.sireum.logika.State, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeorgsireumlogikaState(o)
    return w.result
  }

  def toorgsireumlogikaState(data: ISZ[U8]): Either[org.sireum.logika.State, MessagePack.ErrorMsg] = {
    def forgsireumlogikaState(reader: Reader): org.sireum.logika.State = {
      val r = reader.readorgsireumlogikaState()
      return r
    }
    val r = to(data, forgsireumlogikaState _)
    return r
  }

  def from_logikaStateValue(o: org.sireum.logika.State.Value, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValue(o)
    return w.result
  }

  def to_logikaStateValue(data: ISZ[U8]): Either[org.sireum.logika.State.Value, MessagePack.ErrorMsg] = {
    def f_logikaStateValue(reader: Reader): org.sireum.logika.State.Value = {
      val r = reader.read_logikaStateValue()
      return r
    }
    val r = to(data, f_logikaStateValue _)
    return r
  }

  def from_logikaStateFun(o: org.sireum.logika.State.Fun, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateFun(o)
    return w.result
  }

  def to_logikaStateFun(data: ISZ[U8]): Either[org.sireum.logika.State.Fun, MessagePack.ErrorMsg] = {
    def f_logikaStateFun(reader: Reader): org.sireum.logika.State.Fun = {
      val r = reader.read_logikaStateFun()
      return r
    }
    val r = to(data, f_logikaStateFun _)
    return r
  }

  def from_logikaStateIFun(o: org.sireum.logika.State.IFun, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateIFun(o)
    return w.result
  }

  def to_logikaStateIFun(data: ISZ[U8]): Either[org.sireum.logika.State.IFun, MessagePack.ErrorMsg] = {
    def f_logikaStateIFun(reader: Reader): org.sireum.logika.State.IFun = {
      val r = reader.read_logikaStateIFun()
      return r
    }
    val r = to(data, f_logikaStateIFun _)
    return r
  }

  def from_logikaStateOFun(o: org.sireum.logika.State.OFun, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateOFun(o)
    return w.result
  }

  def to_logikaStateOFun(data: ISZ[U8]): Either[org.sireum.logika.State.OFun, MessagePack.ErrorMsg] = {
    def f_logikaStateOFun(reader: Reader): org.sireum.logika.State.OFun = {
      val r = reader.read_logikaStateOFun()
      return r
    }
    val r = to(data, f_logikaStateOFun _)
    return r
  }

  def from_logikaStateValueUnit(o: org.sireum.logika.State.Value.Unit, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueUnit(o)
    return w.result
  }

  def to_logikaStateValueUnit(data: ISZ[U8]): Either[org.sireum.logika.State.Value.Unit, MessagePack.ErrorMsg] = {
    def f_logikaStateValueUnit(reader: Reader): org.sireum.logika.State.Value.Unit = {
      val r = reader.read_logikaStateValueUnit()
      return r
    }
    val r = to(data, f_logikaStateValueUnit _)
    return r
  }

  def from_logikaStateValueB(o: org.sireum.logika.State.Value.B, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueB(o)
    return w.result
  }

  def to_logikaStateValueB(data: ISZ[U8]): Either[org.sireum.logika.State.Value.B, MessagePack.ErrorMsg] = {
    def f_logikaStateValueB(reader: Reader): org.sireum.logika.State.Value.B = {
      val r = reader.read_logikaStateValueB()
      return r
    }
    val r = to(data, f_logikaStateValueB _)
    return r
  }

  def from_logikaStateValueZ(o: org.sireum.logika.State.Value.Z, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueZ(o)
    return w.result
  }

  def to_logikaStateValueZ(data: ISZ[U8]): Either[org.sireum.logika.State.Value.Z, MessagePack.ErrorMsg] = {
    def f_logikaStateValueZ(reader: Reader): org.sireum.logika.State.Value.Z = {
      val r = reader.read_logikaStateValueZ()
      return r
    }
    val r = to(data, f_logikaStateValueZ _)
    return r
  }

  def from_logikaStateValueC(o: org.sireum.logika.State.Value.C, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueC(o)
    return w.result
  }

  def to_logikaStateValueC(data: ISZ[U8]): Either[org.sireum.logika.State.Value.C, MessagePack.ErrorMsg] = {
    def f_logikaStateValueC(reader: Reader): org.sireum.logika.State.Value.C = {
      val r = reader.read_logikaStateValueC()
      return r
    }
    val r = to(data, f_logikaStateValueC _)
    return r
  }

  def from_logikaStateValueF32(o: org.sireum.logika.State.Value.F32, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueF32(o)
    return w.result
  }

  def to_logikaStateValueF32(data: ISZ[U8]): Either[org.sireum.logika.State.Value.F32, MessagePack.ErrorMsg] = {
    def f_logikaStateValueF32(reader: Reader): org.sireum.logika.State.Value.F32 = {
      val r = reader.read_logikaStateValueF32()
      return r
    }
    val r = to(data, f_logikaStateValueF32 _)
    return r
  }

  def from_logikaStateValueF64(o: org.sireum.logika.State.Value.F64, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueF64(o)
    return w.result
  }

  def to_logikaStateValueF64(data: ISZ[U8]): Either[org.sireum.logika.State.Value.F64, MessagePack.ErrorMsg] = {
    def f_logikaStateValueF64(reader: Reader): org.sireum.logika.State.Value.F64 = {
      val r = reader.read_logikaStateValueF64()
      return r
    }
    val r = to(data, f_logikaStateValueF64 _)
    return r
  }

  def from_logikaStateValueR(o: org.sireum.logika.State.Value.R, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueR(o)
    return w.result
  }

  def to_logikaStateValueR(data: ISZ[U8]): Either[org.sireum.logika.State.Value.R, MessagePack.ErrorMsg] = {
    def f_logikaStateValueR(reader: Reader): org.sireum.logika.State.Value.R = {
      val r = reader.read_logikaStateValueR()
      return r
    }
    val r = to(data, f_logikaStateValueR _)
    return r
  }

  def from_logikaStateValueString(o: org.sireum.logika.State.Value.String, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueString(o)
    return w.result
  }

  def to_logikaStateValueString(data: ISZ[U8]): Either[org.sireum.logika.State.Value.String, MessagePack.ErrorMsg] = {
    def f_logikaStateValueString(reader: Reader): org.sireum.logika.State.Value.String = {
      val r = reader.read_logikaStateValueString()
      return r
    }
    val r = to(data, f_logikaStateValueString _)
    return r
  }

  def from_logikaStateValueSubZ(o: org.sireum.logika.State.Value.SubZ, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueSubZ(o)
    return w.result
  }

  def to_logikaStateValueSubZ(data: ISZ[U8]): Either[org.sireum.logika.State.Value.SubZ, MessagePack.ErrorMsg] = {
    def f_logikaStateValueSubZ(reader: Reader): org.sireum.logika.State.Value.SubZ = {
      val r = reader.read_logikaStateValueSubZ()
      return r
    }
    val r = to(data, f_logikaStateValueSubZ _)
    return r
  }

  def from_logikaStateValueRange(o: org.sireum.logika.State.Value.Range, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueRange(o)
    return w.result
  }

  def to_logikaStateValueRange(data: ISZ[U8]): Either[org.sireum.logika.State.Value.Range, MessagePack.ErrorMsg] = {
    def f_logikaStateValueRange(reader: Reader): org.sireum.logika.State.Value.Range = {
      val r = reader.read_logikaStateValueRange()
      return r
    }
    val r = to(data, f_logikaStateValueRange _)
    return r
  }

  def from_logikaStateValueS8(o: org.sireum.logika.State.Value.S8, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueS8(o)
    return w.result
  }

  def to_logikaStateValueS8(data: ISZ[U8]): Either[org.sireum.logika.State.Value.S8, MessagePack.ErrorMsg] = {
    def f_logikaStateValueS8(reader: Reader): org.sireum.logika.State.Value.S8 = {
      val r = reader.read_logikaStateValueS8()
      return r
    }
    val r = to(data, f_logikaStateValueS8 _)
    return r
  }

  def from_logikaStateValueS16(o: org.sireum.logika.State.Value.S16, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueS16(o)
    return w.result
  }

  def to_logikaStateValueS16(data: ISZ[U8]): Either[org.sireum.logika.State.Value.S16, MessagePack.ErrorMsg] = {
    def f_logikaStateValueS16(reader: Reader): org.sireum.logika.State.Value.S16 = {
      val r = reader.read_logikaStateValueS16()
      return r
    }
    val r = to(data, f_logikaStateValueS16 _)
    return r
  }

  def from_logikaStateValueS32(o: org.sireum.logika.State.Value.S32, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueS32(o)
    return w.result
  }

  def to_logikaStateValueS32(data: ISZ[U8]): Either[org.sireum.logika.State.Value.S32, MessagePack.ErrorMsg] = {
    def f_logikaStateValueS32(reader: Reader): org.sireum.logika.State.Value.S32 = {
      val r = reader.read_logikaStateValueS32()
      return r
    }
    val r = to(data, f_logikaStateValueS32 _)
    return r
  }

  def from_logikaStateValueS64(o: org.sireum.logika.State.Value.S64, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueS64(o)
    return w.result
  }

  def to_logikaStateValueS64(data: ISZ[U8]): Either[org.sireum.logika.State.Value.S64, MessagePack.ErrorMsg] = {
    def f_logikaStateValueS64(reader: Reader): org.sireum.logika.State.Value.S64 = {
      val r = reader.read_logikaStateValueS64()
      return r
    }
    val r = to(data, f_logikaStateValueS64 _)
    return r
  }

  def from_logikaStateValueU8(o: org.sireum.logika.State.Value.U8, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueU8(o)
    return w.result
  }

  def to_logikaStateValueU8(data: ISZ[U8]): Either[org.sireum.logika.State.Value.U8, MessagePack.ErrorMsg] = {
    def f_logikaStateValueU8(reader: Reader): org.sireum.logika.State.Value.U8 = {
      val r = reader.read_logikaStateValueU8()
      return r
    }
    val r = to(data, f_logikaStateValueU8 _)
    return r
  }

  def from_logikaStateValueU16(o: org.sireum.logika.State.Value.U16, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueU16(o)
    return w.result
  }

  def to_logikaStateValueU16(data: ISZ[U8]): Either[org.sireum.logika.State.Value.U16, MessagePack.ErrorMsg] = {
    def f_logikaStateValueU16(reader: Reader): org.sireum.logika.State.Value.U16 = {
      val r = reader.read_logikaStateValueU16()
      return r
    }
    val r = to(data, f_logikaStateValueU16 _)
    return r
  }

  def from_logikaStateValueU32(o: org.sireum.logika.State.Value.U32, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueU32(o)
    return w.result
  }

  def to_logikaStateValueU32(data: ISZ[U8]): Either[org.sireum.logika.State.Value.U32, MessagePack.ErrorMsg] = {
    def f_logikaStateValueU32(reader: Reader): org.sireum.logika.State.Value.U32 = {
      val r = reader.read_logikaStateValueU32()
      return r
    }
    val r = to(data, f_logikaStateValueU32 _)
    return r
  }

  def from_logikaStateValueU64(o: org.sireum.logika.State.Value.U64, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueU64(o)
    return w.result
  }

  def to_logikaStateValueU64(data: ISZ[U8]): Either[org.sireum.logika.State.Value.U64, MessagePack.ErrorMsg] = {
    def f_logikaStateValueU64(reader: Reader): org.sireum.logika.State.Value.U64 = {
      val r = reader.read_logikaStateValueU64()
      return r
    }
    val r = to(data, f_logikaStateValueU64 _)
    return r
  }

  def from_logikaStateValueEnum(o: org.sireum.logika.State.Value.Enum, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueEnum(o)
    return w.result
  }

  def to_logikaStateValueEnum(data: ISZ[U8]): Either[org.sireum.logika.State.Value.Enum, MessagePack.ErrorMsg] = {
    def f_logikaStateValueEnum(reader: Reader): org.sireum.logika.State.Value.Enum = {
      val r = reader.read_logikaStateValueEnum()
      return r
    }
    val r = to(data, f_logikaStateValueEnum _)
    return r
  }

  def from_logikaStateValueSym(o: org.sireum.logika.State.Value.Sym, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateValueSym(o)
    return w.result
  }

  def to_logikaStateValueSym(data: ISZ[U8]): Either[org.sireum.logika.State.Value.Sym, MessagePack.ErrorMsg] = {
    def f_logikaStateValueSym(reader: Reader): org.sireum.logika.State.Value.Sym = {
      val r = reader.read_logikaStateValueSym()
      return r
    }
    val r = to(data, f_logikaStateValueSym _)
    return r
  }

  def from_logikaStateClaim(o: org.sireum.logika.State.Claim, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaim(o)
    return w.result
  }

  def to_logikaStateClaim(data: ISZ[U8]): Either[org.sireum.logika.State.Claim, MessagePack.ErrorMsg] = {
    def f_logikaStateClaim(reader: Reader): org.sireum.logika.State.Claim = {
      val r = reader.read_logikaStateClaim()
      return r
    }
    val r = to(data, f_logikaStateClaim _)
    return r
  }

  def from_logikaStateClaimComposite(o: org.sireum.logika.State.Claim.Composite, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimComposite(o)
    return w.result
  }

  def to_logikaStateClaimComposite(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Composite, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimComposite(reader: Reader): org.sireum.logika.State.Claim.Composite = {
      val r = reader.read_logikaStateClaimComposite()
      return r
    }
    val r = to(data, f_logikaStateClaimComposite _)
    return r
  }

  def from_logikaStateClaimLabel(o: org.sireum.logika.State.Claim.Label, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLabel(o)
    return w.result
  }

  def to_logikaStateClaimLabel(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Label, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLabel(reader: Reader): org.sireum.logika.State.Claim.Label = {
      val r = reader.read_logikaStateClaimLabel()
      return r
    }
    val r = to(data, f_logikaStateClaimLabel _)
    return r
  }

  def from_logikaStateClaimProp(o: org.sireum.logika.State.Claim.Prop, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimProp(o)
    return w.result
  }

  def to_logikaStateClaimProp(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Prop, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimProp(reader: Reader): org.sireum.logika.State.Claim.Prop = {
      val r = reader.read_logikaStateClaimProp()
      return r
    }
    val r = to(data, f_logikaStateClaimProp _)
    return r
  }

  def from_logikaStateClaimAnd(o: org.sireum.logika.State.Claim.And, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimAnd(o)
    return w.result
  }

  def to_logikaStateClaimAnd(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.And, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimAnd(reader: Reader): org.sireum.logika.State.Claim.And = {
      val r = reader.read_logikaStateClaimAnd()
      return r
    }
    val r = to(data, f_logikaStateClaimAnd _)
    return r
  }

  def from_logikaStateClaimOr(o: org.sireum.logika.State.Claim.Or, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimOr(o)
    return w.result
  }

  def to_logikaStateClaimOr(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Or, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimOr(reader: Reader): org.sireum.logika.State.Claim.Or = {
      val r = reader.read_logikaStateClaimOr()
      return r
    }
    val r = to(data, f_logikaStateClaimOr _)
    return r
  }

  def from_logikaStateClaimImply(o: org.sireum.logika.State.Claim.Imply, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimImply(o)
    return w.result
  }

  def to_logikaStateClaimImply(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Imply, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimImply(reader: Reader): org.sireum.logika.State.Claim.Imply = {
      val r = reader.read_logikaStateClaimImply()
      return r
    }
    val r = to(data, f_logikaStateClaimImply _)
    return r
  }

  def from_logikaStateClaimIf(o: org.sireum.logika.State.Claim.If, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimIf(o)
    return w.result
  }

  def to_logikaStateClaimIf(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.If, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimIf(reader: Reader): org.sireum.logika.State.Claim.If = {
      val r = reader.read_logikaStateClaimIf()
      return r
    }
    val r = to(data, f_logikaStateClaimIf _)
    return r
  }

  def from_logikaStateClaimLet(o: org.sireum.logika.State.Claim.Let, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLet(o)
    return w.result
  }

  def to_logikaStateClaimLet(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLet(reader: Reader): org.sireum.logika.State.Claim.Let = {
      val r = reader.read_logikaStateClaimLet()
      return r
    }
    val r = to(data, f_logikaStateClaimLet _)
    return r
  }

  def from_logikaStateClaimLetAdtLit(o: org.sireum.logika.State.Claim.Let.AdtLit, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetAdtLit(o)
    return w.result
  }

  def to_logikaStateClaimLetAdtLit(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.AdtLit, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetAdtLit(reader: Reader): org.sireum.logika.State.Claim.Let.AdtLit = {
      val r = reader.read_logikaStateClaimLetAdtLit()
      return r
    }
    val r = to(data, f_logikaStateClaimLetAdtLit _)
    return r
  }

  def from_logikaStateClaimLetSeqLit(o: org.sireum.logika.State.Claim.Let.SeqLit, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetSeqLit(o)
    return w.result
  }

  def to_logikaStateClaimLetSeqLit(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.SeqLit, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetSeqLit(reader: Reader): org.sireum.logika.State.Claim.Let.SeqLit = {
      val r = reader.read_logikaStateClaimLetSeqLit()
      return r
    }
    val r = to(data, f_logikaStateClaimLetSeqLit _)
    return r
  }

  def from_logikaStateClaimLetSeqLitArg(o: org.sireum.logika.State.Claim.Let.SeqLit.Arg, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetSeqLitArg(o)
    return w.result
  }

  def to_logikaStateClaimLetSeqLitArg(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.SeqLit.Arg, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetSeqLitArg(reader: Reader): org.sireum.logika.State.Claim.Let.SeqLit.Arg = {
      val r = reader.read_logikaStateClaimLetSeqLitArg()
      return r
    }
    val r = to(data, f_logikaStateClaimLetSeqLitArg _)
    return r
  }

  def from_logikaStateClaimLetCurrentName(o: org.sireum.logika.State.Claim.Let.CurrentName, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetCurrentName(o)
    return w.result
  }

  def to_logikaStateClaimLetCurrentName(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.CurrentName, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetCurrentName(reader: Reader): org.sireum.logika.State.Claim.Let.CurrentName = {
      val r = reader.read_logikaStateClaimLetCurrentName()
      return r
    }
    val r = to(data, f_logikaStateClaimLetCurrentName _)
    return r
  }

  def from_logikaStateClaimLetSeqStore(o: org.sireum.logika.State.Claim.Let.SeqStore, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetSeqStore(o)
    return w.result
  }

  def to_logikaStateClaimLetSeqStore(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.SeqStore, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetSeqStore(reader: Reader): org.sireum.logika.State.Claim.Let.SeqStore = {
      val r = reader.read_logikaStateClaimLetSeqStore()
      return r
    }
    val r = to(data, f_logikaStateClaimLetSeqStore _)
    return r
  }

  def from_logikaStateClaimLetFieldStore(o: org.sireum.logika.State.Claim.Let.FieldStore, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetFieldStore(o)
    return w.result
  }

  def to_logikaStateClaimLetFieldStore(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.FieldStore, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetFieldStore(reader: Reader): org.sireum.logika.State.Claim.Let.FieldStore = {
      val r = reader.read_logikaStateClaimLetFieldStore()
      return r
    }
    val r = to(data, f_logikaStateClaimLetFieldStore _)
    return r
  }

  def from_logikaStateClaimLetRandom(o: org.sireum.logika.State.Claim.Let.Random, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetRandom(o)
    return w.result
  }

  def to_logikaStateClaimLetRandom(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Random, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetRandom(reader: Reader): org.sireum.logika.State.Claim.Let.Random = {
      val r = reader.read_logikaStateClaimLetRandom()
      return r
    }
    val r = to(data, f_logikaStateClaimLetRandom _)
    return r
  }

  def from_logikaStateClaimLetName(o: org.sireum.logika.State.Claim.Let.Name, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetName(o)
    return w.result
  }

  def to_logikaStateClaimLetName(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Name, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetName(reader: Reader): org.sireum.logika.State.Claim.Let.Name = {
      val r = reader.read_logikaStateClaimLetName()
      return r
    }
    val r = to(data, f_logikaStateClaimLetName _)
    return r
  }

  def from_logikaStateClaimLetCurrentId(o: org.sireum.logika.State.Claim.Let.CurrentId, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetCurrentId(o)
    return w.result
  }

  def to_logikaStateClaimLetCurrentId(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.CurrentId, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetCurrentId(reader: Reader): org.sireum.logika.State.Claim.Let.CurrentId = {
      val r = reader.read_logikaStateClaimLetCurrentId()
      return r
    }
    val r = to(data, f_logikaStateClaimLetCurrentId _)
    return r
  }

  def from_logikaStateClaimLetId(o: org.sireum.logika.State.Claim.Let.Id, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetId(o)
    return w.result
  }

  def to_logikaStateClaimLetId(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Id, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetId(reader: Reader): org.sireum.logika.State.Claim.Let.Id = {
      val r = reader.read_logikaStateClaimLetId()
      return r
    }
    val r = to(data, f_logikaStateClaimLetId _)
    return r
  }

  def from_logikaStateClaimLetEq(o: org.sireum.logika.State.Claim.Let.Eq, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetEq(o)
    return w.result
  }

  def to_logikaStateClaimLetEq(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Eq, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetEq(reader: Reader): org.sireum.logika.State.Claim.Let.Eq = {
      val r = reader.read_logikaStateClaimLetEq()
      return r
    }
    val r = to(data, f_logikaStateClaimLetEq _)
    return r
  }

  def from_logikaStateClaimLetTypeTest(o: org.sireum.logika.State.Claim.Let.TypeTest, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetTypeTest(o)
    return w.result
  }

  def to_logikaStateClaimLetTypeTest(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.TypeTest, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetTypeTest(reader: Reader): org.sireum.logika.State.Claim.Let.TypeTest = {
      val r = reader.read_logikaStateClaimLetTypeTest()
      return r
    }
    val r = to(data, f_logikaStateClaimLetTypeTest _)
    return r
  }

  def from_logikaStateClaimLetQuantVar(o: org.sireum.logika.State.Claim.Let.Quant.Var, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetQuantVar(o)
    return w.result
  }

  def to_logikaStateClaimLetQuantVar(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Quant.Var, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetQuantVar(reader: Reader): org.sireum.logika.State.Claim.Let.Quant.Var = {
      val r = reader.read_logikaStateClaimLetQuantVar()
      return r
    }
    val r = to(data, f_logikaStateClaimLetQuantVar _)
    return r
  }

  def from_logikaStateClaimLetQuantVarId(o: org.sireum.logika.State.Claim.Let.Quant.Var.Id, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetQuantVarId(o)
    return w.result
  }

  def to_logikaStateClaimLetQuantVarId(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Quant.Var.Id, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetQuantVarId(reader: Reader): org.sireum.logika.State.Claim.Let.Quant.Var.Id = {
      val r = reader.read_logikaStateClaimLetQuantVarId()
      return r
    }
    val r = to(data, f_logikaStateClaimLetQuantVarId _)
    return r
  }

  def from_logikaStateClaimLetQuantVarSym(o: org.sireum.logika.State.Claim.Let.Quant.Var.Sym, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetQuantVarSym(o)
    return w.result
  }

  def to_logikaStateClaimLetQuantVarSym(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Quant.Var.Sym, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetQuantVarSym(reader: Reader): org.sireum.logika.State.Claim.Let.Quant.Var.Sym = {
      val r = reader.read_logikaStateClaimLetQuantVarSym()
      return r
    }
    val r = to(data, f_logikaStateClaimLetQuantVarSym _)
    return r
  }

  def from_logikaStateClaimLetQuant(o: org.sireum.logika.State.Claim.Let.Quant, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetQuant(o)
    return w.result
  }

  def to_logikaStateClaimLetQuant(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Quant, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetQuant(reader: Reader): org.sireum.logika.State.Claim.Let.Quant = {
      val r = reader.read_logikaStateClaimLetQuant()
      return r
    }
    val r = to(data, f_logikaStateClaimLetQuant _)
    return r
  }

  def from_logikaStateClaimLetIte(o: org.sireum.logika.State.Claim.Let.Ite, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetIte(o)
    return w.result
  }

  def to_logikaStateClaimLetIte(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Ite, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetIte(reader: Reader): org.sireum.logika.State.Claim.Let.Ite = {
      val r = reader.read_logikaStateClaimLetIte()
      return r
    }
    val r = to(data, f_logikaStateClaimLetIte _)
    return r
  }

  def from_logikaStateClaimLetBinary(o: org.sireum.logika.State.Claim.Let.Binary, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetBinary(o)
    return w.result
  }

  def to_logikaStateClaimLetBinary(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Binary, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetBinary(reader: Reader): org.sireum.logika.State.Claim.Let.Binary = {
      val r = reader.read_logikaStateClaimLetBinary()
      return r
    }
    val r = to(data, f_logikaStateClaimLetBinary _)
    return r
  }

  def from_logikaStateClaimLetUnary(o: org.sireum.logika.State.Claim.Let.Unary, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetUnary(o)
    return w.result
  }

  def to_logikaStateClaimLetUnary(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Unary, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetUnary(reader: Reader): org.sireum.logika.State.Claim.Let.Unary = {
      val r = reader.read_logikaStateClaimLetUnary()
      return r
    }
    val r = to(data, f_logikaStateClaimLetUnary _)
    return r
  }

  def from_logikaStateClaimLetSeqLookup(o: org.sireum.logika.State.Claim.Let.SeqLookup, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetSeqLookup(o)
    return w.result
  }

  def to_logikaStateClaimLetSeqLookup(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.SeqLookup, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetSeqLookup(reader: Reader): org.sireum.logika.State.Claim.Let.SeqLookup = {
      val r = reader.read_logikaStateClaimLetSeqLookup()
      return r
    }
    val r = to(data, f_logikaStateClaimLetSeqLookup _)
    return r
  }

  def from_logikaStateClaimLetSeqInBound(o: org.sireum.logika.State.Claim.Let.SeqInBound, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetSeqInBound(o)
    return w.result
  }

  def to_logikaStateClaimLetSeqInBound(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.SeqInBound, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetSeqInBound(reader: Reader): org.sireum.logika.State.Claim.Let.SeqInBound = {
      val r = reader.read_logikaStateClaimLetSeqInBound()
      return r
    }
    val r = to(data, f_logikaStateClaimLetSeqInBound _)
    return r
  }

  def from_logikaStateClaimLetFieldLookup(o: org.sireum.logika.State.Claim.Let.FieldLookup, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetFieldLookup(o)
    return w.result
  }

  def to_logikaStateClaimLetFieldLookup(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.FieldLookup, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetFieldLookup(reader: Reader): org.sireum.logika.State.Claim.Let.FieldLookup = {
      val r = reader.read_logikaStateClaimLetFieldLookup()
      return r
    }
    val r = to(data, f_logikaStateClaimLetFieldLookup _)
    return r
  }

  def from_logikaStateClaimLetProofFunApply(o: org.sireum.logika.State.Claim.Let.ProofFunApply, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetProofFunApply(o)
    return w.result
  }

  def to_logikaStateClaimLetProofFunApply(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.ProofFunApply, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetProofFunApply(reader: Reader): org.sireum.logika.State.Claim.Let.ProofFunApply = {
      val r = reader.read_logikaStateClaimLetProofFunApply()
      return r
    }
    val r = to(data, f_logikaStateClaimLetProofFunApply _)
    return r
  }

  def from_logikaStateClaimLetApply(o: org.sireum.logika.State.Claim.Let.Apply, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetApply(o)
    return w.result
  }

  def to_logikaStateClaimLetApply(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Apply, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetApply(reader: Reader): org.sireum.logika.State.Claim.Let.Apply = {
      val r = reader.read_logikaStateClaimLetApply()
      return r
    }
    val r = to(data, f_logikaStateClaimLetApply _)
    return r
  }

  def from_logikaStateClaimLetIApply(o: org.sireum.logika.State.Claim.Let.IApply, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetIApply(o)
    return w.result
  }

  def to_logikaStateClaimLetIApply(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.IApply, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetIApply(reader: Reader): org.sireum.logika.State.Claim.Let.IApply = {
      val r = reader.read_logikaStateClaimLetIApply()
      return r
    }
    val r = to(data, f_logikaStateClaimLetIApply _)
    return r
  }

  def from_logikaStateClaimLetTupleLit(o: org.sireum.logika.State.Claim.Let.TupleLit, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetTupleLit(o)
    return w.result
  }

  def to_logikaStateClaimLetTupleLit(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.TupleLit, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetTupleLit(reader: Reader): org.sireum.logika.State.Claim.Let.TupleLit = {
      val r = reader.read_logikaStateClaimLetTupleLit()
      return r
    }
    val r = to(data, f_logikaStateClaimLetTupleLit _)
    return r
  }

  def from_logikaStateClaimLetAnd(o: org.sireum.logika.State.Claim.Let.And, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetAnd(o)
    return w.result
  }

  def to_logikaStateClaimLetAnd(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.And, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetAnd(reader: Reader): org.sireum.logika.State.Claim.Let.And = {
      val r = reader.read_logikaStateClaimLetAnd()
      return r
    }
    val r = to(data, f_logikaStateClaimLetAnd _)
    return r
  }

  def from_logikaStateClaimLetOr(o: org.sireum.logika.State.Claim.Let.Or, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetOr(o)
    return w.result
  }

  def to_logikaStateClaimLetOr(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Or, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetOr(reader: Reader): org.sireum.logika.State.Claim.Let.Or = {
      val r = reader.read_logikaStateClaimLetOr()
      return r
    }
    val r = to(data, f_logikaStateClaimLetOr _)
    return r
  }

  def from_logikaStateClaimLetImply(o: org.sireum.logika.State.Claim.Let.Imply, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateClaimLetImply(o)
    return w.result
  }

  def to_logikaStateClaimLetImply(data: ISZ[U8]): Either[org.sireum.logika.State.Claim.Let.Imply, MessagePack.ErrorMsg] = {
    def f_logikaStateClaimLetImply(reader: Reader): org.sireum.logika.State.Claim.Let.Imply = {
      val r = reader.read_logikaStateClaimLetImply()
      return r
    }
    val r = to(data, f_logikaStateClaimLetImply _)
    return r
  }

  def from_logikaStateProofFun(o: org.sireum.logika.State.ProofFun, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.write_logikaStateProofFun(o)
    return w.result
  }

  def to_logikaStateProofFun(data: ISZ[U8]): Either[org.sireum.logika.State.ProofFun, MessagePack.ErrorMsg] = {
    def f_logikaStateProofFun(reader: Reader): org.sireum.logika.State.ProofFun = {
      val r = reader.read_logikaStateProofFun()
      return r
    }
    val r = to(data, f_logikaStateProofFun _)
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

  def fromorgsireumlogikaZ3Config(o: org.sireum.logika.Z3Config, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeorgsireumlogikaZ3Config(o)
    return w.result
  }

  def toorgsireumlogikaZ3Config(data: ISZ[U8]): Either[org.sireum.logika.Z3Config, MessagePack.ErrorMsg] = {
    def forgsireumlogikaZ3Config(reader: Reader): org.sireum.logika.Z3Config = {
      val r = reader.readorgsireumlogikaZ3Config()
      return r
    }
    val r = to(data, forgsireumlogikaZ3Config _)
    return r
  }

  def fromorgsireumlogikaCvcConfig(o: org.sireum.logika.CvcConfig, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeorgsireumlogikaCvcConfig(o)
    return w.result
  }

  def toorgsireumlogikaCvcConfig(data: ISZ[U8]): Either[org.sireum.logika.CvcConfig, MessagePack.ErrorMsg] = {
    def forgsireumlogikaCvcConfig(reader: Reader): org.sireum.logika.CvcConfig = {
      val r = reader.readorgsireumlogikaCvcConfig()
      return r
    }
    val r = to(data, forgsireumlogikaCvcConfig _)
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