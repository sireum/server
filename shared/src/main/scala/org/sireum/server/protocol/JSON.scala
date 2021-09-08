// #Sireum
// @formatter:off

/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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
import org.sireum.Json.Printer._

object JSON {

  object Printer {

    @pure def printRequest(o: Request): ST = {
      o match {
        case o: Terminate => return printTerminate(o)
        case o: Cancel => return printCancel(o)
        case o: Version.Request => return printVersionRequest(o)
        case o: Slang.CheckScript => return printSlangCheckScript(o)
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
        case o: Slang.Rewrite.Response => return printSlangRewriteResponse(o)
        case o: Logika.Verify.Start => return printLogikaVerifyStart(o)
        case o: Logika.Verify.End => return printLogikaVerifyEnd(o)
        case o: Logika.Verify.State => return printLogikaVerifyState(o)
        case o: Logika.Verify.Halted => return printLogikaVerifyHalted(o)
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

    @pure def printSlangCheckScript(o: Slang.CheckScript): ST = {
      return printObject(ISZ(
        ("type", st""""Slang.CheckScript""""),
        ("isBackground", printB(o.isBackground)),
        ("logikaEnabled", printB(o.logikaEnabled)),
        ("par", printZ(o.par)),
        ("id", printISZ(T, o.id, printString _)),
        ("uriOpt", printOption(T, o.uriOpt, printString _)),
        ("content", printString(o.content)),
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

    @pure def printLogikaVerifyStart(o: Logika.Verify.Start): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.Start""""),
        ("id", printISZ(T, o.id, printString _)),
        ("currentTimeMillis", printZ(o.currentTimeMillis))
      ))
    }

    @pure def printLogikaVerifyEnd(o: Logika.Verify.End): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.End""""),
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
        ("state", printorgsireumlogikaState(o.state))
      ))
    }

    @pure def printLogikaVerifyHalted(o: Logika.Verify.Halted): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.Halted""""),
        ("id", printISZ(T, o.id, printString _)),
        ("posOpt", printOption(F, o.posOpt, printPosition _)),
        ("state", printorgsireumlogikaState(o.state))
      ))
    }

    @pure def printLogikaVerifySmt2Query(o: Logika.Verify.Smt2Query): ST = {
      return printObject(ISZ(
        ("type", st""""Logika.Verify.Smt2Query""""),
        ("id", printISZ(T, o.id, printString _)),
        ("pos", printPosition(o.pos)),
        ("timeInMs", printZ(o.timeInMs)),
        ("result", print_logikaSmt2QueryResult(o.result))
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

    @pure def printorgsireumlogikaState(o: org.sireum.logika.State): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State""""),
        ("status", printB(o.status)),
        ("claims", printISZ(F, o.claims, print_logikaStateClaim _)),
        ("nextFresh", printZ(o.nextFresh))
      ))
    }

    @pure def print_logikaStateValue(o: org.sireum.logika.State.Value): ST = {
      o match {
        case o: org.sireum.logika.State.Value.Unit => return print_logikaStateValueUnit(o)
        case o: org.sireum.logika.State.Value.B => return print_logikaStateValueB(o)
        case o: org.sireum.logika.State.Value.Z => return print_logikaStateValueZ(o)
        case o: org.sireum.logika.State.Value.C => return print_logikaStateValueC(o)
        case o: org.sireum.logika.State.Value.F32 => return print_logikaStateValueF32(o)
        case o: org.sireum.logika.State.Value.F64 => return print_logikaStateValueF64(o)
        case o: org.sireum.logika.State.Value.R => return print_logikaStateValueR(o)
        case o: org.sireum.logika.State.Value.String => return print_logikaStateValueString(o)
        case o: org.sireum.logika.State.Value.Range => return print_logikaStateValueRange(o)
        case o: org.sireum.logika.State.Value.S8 => return print_logikaStateValueS8(o)
        case o: org.sireum.logika.State.Value.S16 => return print_logikaStateValueS16(o)
        case o: org.sireum.logika.State.Value.S32 => return print_logikaStateValueS32(o)
        case o: org.sireum.logika.State.Value.S64 => return print_logikaStateValueS64(o)
        case o: org.sireum.logika.State.Value.U8 => return print_logikaStateValueU8(o)
        case o: org.sireum.logika.State.Value.U16 => return print_logikaStateValueU16(o)
        case o: org.sireum.logika.State.Value.U32 => return print_logikaStateValueU32(o)
        case o: org.sireum.logika.State.Value.U64 => return print_logikaStateValueU64(o)
        case o: org.sireum.logika.State.Value.Enum => return print_logikaStateValueEnum(o)
        case o: org.sireum.logika.State.Value.Sym => return print_logikaStateValueSym(o)
      }
    }

    @pure def print_logikaStateFun(o: org.sireum.logika.State.Fun): ST = {
      o match {
        case o: org.sireum.logika.State.IFun => return print_logikaStateIFun(o)
        case o: org.sireum.logika.State.OFun => return print_logikaStateOFun(o)
      }
    }

    @pure def print_logikaStateIFun(o: org.sireum.logika.State.IFun): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.IFun""""),
        ("tipe", print_langastTypedName(o.tipe)),
        ("id", printString(o.id))
      ))
    }

    @pure def print_logikaStateOFun(o: org.sireum.logika.State.OFun): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.OFun""""),
        ("name", printISZ(T, o.name, printString _))
      ))
    }

    @pure def print_logikaStateValueUnit(o: org.sireum.logika.State.Value.Unit): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.Unit""""),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueB(o: org.sireum.logika.State.Value.B): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.B""""),
        ("value", printB(o.value)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueZ(o: org.sireum.logika.State.Value.Z): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.Z""""),
        ("value", printZ(o.value)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueC(o: org.sireum.logika.State.Value.C): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.C""""),
        ("value", printC(o.value)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueF32(o: org.sireum.logika.State.Value.F32): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.F32""""),
        ("value", printF32(o.value)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueF64(o: org.sireum.logika.State.Value.F64): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.F64""""),
        ("value", printF64(o.value)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueR(o: org.sireum.logika.State.Value.R): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.R""""),
        ("value", printR(o.value)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueString(o: org.sireum.logika.State.Value.String): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.String""""),
        ("value", printString(o.value)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueSubZ(o: org.sireum.logika.State.Value.SubZ): ST = {
      o match {
        case o: org.sireum.logika.State.Value.Range => return print_logikaStateValueRange(o)
        case o: org.sireum.logika.State.Value.S8 => return print_logikaStateValueS8(o)
        case o: org.sireum.logika.State.Value.S16 => return print_logikaStateValueS16(o)
        case o: org.sireum.logika.State.Value.S32 => return print_logikaStateValueS32(o)
        case o: org.sireum.logika.State.Value.S64 => return print_logikaStateValueS64(o)
        case o: org.sireum.logika.State.Value.U8 => return print_logikaStateValueU8(o)
        case o: org.sireum.logika.State.Value.U16 => return print_logikaStateValueU16(o)
        case o: org.sireum.logika.State.Value.U32 => return print_logikaStateValueU32(o)
        case o: org.sireum.logika.State.Value.U64 => return print_logikaStateValueU64(o)
      }
    }

    @pure def print_logikaStateValueRange(o: org.sireum.logika.State.Value.Range): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.Range""""),
        ("value", printZ(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueS8(o: org.sireum.logika.State.Value.S8): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.S8""""),
        ("value", printS8(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueS16(o: org.sireum.logika.State.Value.S16): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.S16""""),
        ("value", printS16(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueS32(o: org.sireum.logika.State.Value.S32): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.S32""""),
        ("value", printS32(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueS64(o: org.sireum.logika.State.Value.S64): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.S64""""),
        ("value", printS64(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueU8(o: org.sireum.logika.State.Value.U8): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.U8""""),
        ("value", printU8(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueU16(o: org.sireum.logika.State.Value.U16): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.U16""""),
        ("value", printU16(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueU32(o: org.sireum.logika.State.Value.U32): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.U32""""),
        ("value", printU32(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueU64(o: org.sireum.logika.State.Value.U64): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.U64""""),
        ("value", printU64(o.value)),
        ("tipe", print_langastTypedName(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueEnum(o: org.sireum.logika.State.Value.Enum): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.Enum""""),
        ("tipe", print_langastTypedName(o.tipe)),
        ("owner", printISZ(T, o.owner, printString _)),
        ("id", printString(o.id)),
        ("ordinal", printZ(o.ordinal)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateValueSym(o: org.sireum.logika.State.Value.Sym): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Value.Sym""""),
        ("num", printZ(o.num)),
        ("tipe", print_langastTyped(o.tipe)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateClaim(o: org.sireum.logika.State.Claim): ST = {
      o match {
        case o: org.sireum.logika.State.Claim.Label => return print_logikaStateClaimLabel(o)
        case o: org.sireum.logika.State.Claim.Prop => return print_logikaStateClaimProp(o)
        case o: org.sireum.logika.State.Claim.And => return print_logikaStateClaimAnd(o)
        case o: org.sireum.logika.State.Claim.Or => return print_logikaStateClaimOr(o)
        case o: org.sireum.logika.State.Claim.Imply => return print_logikaStateClaimImply(o)
        case o: org.sireum.logika.State.Claim.If => return print_logikaStateClaimIf(o)
        case o: org.sireum.logika.State.Claim.Def.SeqLit => return print_logikaStateClaimDefSeqLit(o)
        case o: org.sireum.logika.State.Claim.Def.SeqStore => return print_logikaStateClaimDefSeqStore(o)
        case o: org.sireum.logika.State.Claim.Def.FieldStore => return print_logikaStateClaimDefFieldStore(o)
        case o: org.sireum.logika.State.Claim.Def.AdtLit => return print_logikaStateClaimDefAdtLit(o)
        case o: org.sireum.logika.State.Claim.Def.Random => return print_logikaStateClaimDefRandom(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentName => return print_logikaStateClaimLetCurrentName(o)
        case o: org.sireum.logika.State.Claim.Let.Name => return print_logikaStateClaimLetName(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentId => return print_logikaStateClaimLetCurrentId(o)
        case o: org.sireum.logika.State.Claim.Let.Id => return print_logikaStateClaimLetId(o)
        case o: org.sireum.logika.State.Claim.Let.Eq => return print_logikaStateClaimLetEq(o)
        case o: org.sireum.logika.State.Claim.Let.TypeTest => return print_logikaStateClaimLetTypeTest(o)
        case o: org.sireum.logika.State.Claim.Let.Quant => return print_logikaStateClaimLetQuant(o)
        case o: org.sireum.logika.State.Claim.Let.Ite => return print_logikaStateClaimLetIte(o)
        case o: org.sireum.logika.State.Claim.Let.Binary => return print_logikaStateClaimLetBinary(o)
        case o: org.sireum.logika.State.Claim.Let.Unary => return print_logikaStateClaimLetUnary(o)
        case o: org.sireum.logika.State.Claim.Let.SeqLookup => return print_logikaStateClaimLetSeqLookup(o)
        case o: org.sireum.logika.State.Claim.Let.SeqInBound => return print_logikaStateClaimLetSeqInBound(o)
        case o: org.sireum.logika.State.Claim.Let.FieldLookup => return print_logikaStateClaimLetFieldLookup(o)
        case o: org.sireum.logika.State.Claim.Let.ProofFunApply => return print_logikaStateClaimLetProofFunApply(o)
        case o: org.sireum.logika.State.Claim.Let.Apply => return print_logikaStateClaimLetApply(o)
        case o: org.sireum.logika.State.Claim.Let.IApply => return print_logikaStateClaimLetIApply(o)
        case o: org.sireum.logika.State.Claim.Let.TupleLit => return print_logikaStateClaimLetTupleLit(o)
        case o: org.sireum.logika.State.Claim.Let.And => return print_logikaStateClaimLetAnd(o)
        case o: org.sireum.logika.State.Claim.Let.Or => return print_logikaStateClaimLetOr(o)
        case o: org.sireum.logika.State.Claim.Let.Imply => return print_logikaStateClaimLetImply(o)
      }
    }

    @pure def print_logikaStateClaimComposite(o: org.sireum.logika.State.Claim.Composite): ST = {
      o match {
        case o: org.sireum.logika.State.Claim.And => return print_logikaStateClaimAnd(o)
        case o: org.sireum.logika.State.Claim.Or => return print_logikaStateClaimOr(o)
        case o: org.sireum.logika.State.Claim.Imply => return print_logikaStateClaimImply(o)
        case o: org.sireum.logika.State.Claim.If => return print_logikaStateClaimIf(o)
        case o: org.sireum.logika.State.Claim.Let.Quant => return print_logikaStateClaimLetQuant(o)
      }
    }

    @pure def print_logikaStateClaimLabel(o: org.sireum.logika.State.Claim.Label): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Label""""),
        ("label", printString(o.label)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateClaimProp(o: org.sireum.logika.State.Claim.Prop): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Prop""""),
        ("isPos", printB(o.isPos)),
        ("value", print_logikaStateValueSym(o.value))
      ))
    }

    @pure def print_logikaStateClaimAnd(o: org.sireum.logika.State.Claim.And): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.And""""),
        ("claims", printISZ(F, o.claims, print_logikaStateClaim _))
      ))
    }

    @pure def print_logikaStateClaimOr(o: org.sireum.logika.State.Claim.Or): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Or""""),
        ("claims", printISZ(F, o.claims, print_logikaStateClaim _))
      ))
    }

    @pure def print_logikaStateClaimImply(o: org.sireum.logika.State.Claim.Imply): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Imply""""),
        ("claims", printISZ(F, o.claims, print_logikaStateClaim _))
      ))
    }

    @pure def print_logikaStateClaimIf(o: org.sireum.logika.State.Claim.If): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.If""""),
        ("cond", print_logikaStateValueSym(o.cond)),
        ("tClaims", printISZ(F, o.tClaims, print_logikaStateClaim _)),
        ("fClaims", printISZ(F, o.fClaims, print_logikaStateClaim _))
      ))
    }

    @pure def print_logikaStateClaimDef(o: org.sireum.logika.State.Claim.Def): ST = {
      o match {
        case o: org.sireum.logika.State.Claim.Def.SeqLit => return print_logikaStateClaimDefSeqLit(o)
        case o: org.sireum.logika.State.Claim.Def.SeqStore => return print_logikaStateClaimDefSeqStore(o)
        case o: org.sireum.logika.State.Claim.Def.FieldStore => return print_logikaStateClaimDefFieldStore(o)
        case o: org.sireum.logika.State.Claim.Def.AdtLit => return print_logikaStateClaimDefAdtLit(o)
        case o: org.sireum.logika.State.Claim.Def.Random => return print_logikaStateClaimDefRandom(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentName => return print_logikaStateClaimLetCurrentName(o)
        case o: org.sireum.logika.State.Claim.Let.Name => return print_logikaStateClaimLetName(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentId => return print_logikaStateClaimLetCurrentId(o)
        case o: org.sireum.logika.State.Claim.Let.Id => return print_logikaStateClaimLetId(o)
        case o: org.sireum.logika.State.Claim.Let.Eq => return print_logikaStateClaimLetEq(o)
        case o: org.sireum.logika.State.Claim.Let.TypeTest => return print_logikaStateClaimLetTypeTest(o)
        case o: org.sireum.logika.State.Claim.Let.Quant => return print_logikaStateClaimLetQuant(o)
        case o: org.sireum.logika.State.Claim.Let.Ite => return print_logikaStateClaimLetIte(o)
        case o: org.sireum.logika.State.Claim.Let.Binary => return print_logikaStateClaimLetBinary(o)
        case o: org.sireum.logika.State.Claim.Let.Unary => return print_logikaStateClaimLetUnary(o)
        case o: org.sireum.logika.State.Claim.Let.SeqLookup => return print_logikaStateClaimLetSeqLookup(o)
        case o: org.sireum.logika.State.Claim.Let.SeqInBound => return print_logikaStateClaimLetSeqInBound(o)
        case o: org.sireum.logika.State.Claim.Let.FieldLookup => return print_logikaStateClaimLetFieldLookup(o)
        case o: org.sireum.logika.State.Claim.Let.ProofFunApply => return print_logikaStateClaimLetProofFunApply(o)
        case o: org.sireum.logika.State.Claim.Let.Apply => return print_logikaStateClaimLetApply(o)
        case o: org.sireum.logika.State.Claim.Let.IApply => return print_logikaStateClaimLetIApply(o)
        case o: org.sireum.logika.State.Claim.Let.TupleLit => return print_logikaStateClaimLetTupleLit(o)
        case o: org.sireum.logika.State.Claim.Let.And => return print_logikaStateClaimLetAnd(o)
        case o: org.sireum.logika.State.Claim.Let.Or => return print_logikaStateClaimLetOr(o)
        case o: org.sireum.logika.State.Claim.Let.Imply => return print_logikaStateClaimLetImply(o)
      }
    }

    @pure def print_logikaStateClaimDefSeqLit(o: org.sireum.logika.State.Claim.Def.SeqLit): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Def.SeqLit""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("args", printISZ(F, o.args, print_logikaStateClaimDefSeqLitArg _))
      ))
    }

    @pure def print_logikaStateClaimDefSeqLitArg(o: org.sireum.logika.State.Claim.Def.SeqLit.Arg): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Def.SeqLit.Arg""""),
        ("index", print_logikaStateValue(o.index)),
        ("value", print_logikaStateValue(o.value))
      ))
    }

    @pure def print_logikaStateClaimDefSeqStore(o: org.sireum.logika.State.Claim.Def.SeqStore): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Def.SeqStore""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("seq", print_logikaStateValue(o.seq)),
        ("index", print_logikaStateValue(o.index)),
        ("element", print_logikaStateValue(o.element))
      ))
    }

    @pure def print_logikaStateClaimDefFieldStore(o: org.sireum.logika.State.Claim.Def.FieldStore): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Def.FieldStore""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("adt", print_logikaStateValue(o.adt)),
        ("id", printString(o.id)),
        ("value", print_logikaStateValue(o.value))
      ))
    }

    @pure def print_logikaStateClaimDefAdtLit(o: org.sireum.logika.State.Claim.Def.AdtLit): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Def.AdtLit""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateClaimDefRandom(o: org.sireum.logika.State.Claim.Def.Random): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Def.Random""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("pos", printPosition(o.pos))
      ))
    }

    @pure def print_logikaStateClaimLet(o: org.sireum.logika.State.Claim.Let): ST = {
      o match {
        case o: org.sireum.logika.State.Claim.Let.CurrentName => return print_logikaStateClaimLetCurrentName(o)
        case o: org.sireum.logika.State.Claim.Let.Name => return print_logikaStateClaimLetName(o)
        case o: org.sireum.logika.State.Claim.Let.CurrentId => return print_logikaStateClaimLetCurrentId(o)
        case o: org.sireum.logika.State.Claim.Let.Id => return print_logikaStateClaimLetId(o)
        case o: org.sireum.logika.State.Claim.Let.Eq => return print_logikaStateClaimLetEq(o)
        case o: org.sireum.logika.State.Claim.Let.TypeTest => return print_logikaStateClaimLetTypeTest(o)
        case o: org.sireum.logika.State.Claim.Let.Quant => return print_logikaStateClaimLetQuant(o)
        case o: org.sireum.logika.State.Claim.Let.Ite => return print_logikaStateClaimLetIte(o)
        case o: org.sireum.logika.State.Claim.Let.Binary => return print_logikaStateClaimLetBinary(o)
        case o: org.sireum.logika.State.Claim.Let.Unary => return print_logikaStateClaimLetUnary(o)
        case o: org.sireum.logika.State.Claim.Let.SeqLookup => return print_logikaStateClaimLetSeqLookup(o)
        case o: org.sireum.logika.State.Claim.Let.SeqInBound => return print_logikaStateClaimLetSeqInBound(o)
        case o: org.sireum.logika.State.Claim.Let.FieldLookup => return print_logikaStateClaimLetFieldLookup(o)
        case o: org.sireum.logika.State.Claim.Let.ProofFunApply => return print_logikaStateClaimLetProofFunApply(o)
        case o: org.sireum.logika.State.Claim.Let.Apply => return print_logikaStateClaimLetApply(o)
        case o: org.sireum.logika.State.Claim.Let.IApply => return print_logikaStateClaimLetIApply(o)
        case o: org.sireum.logika.State.Claim.Let.TupleLit => return print_logikaStateClaimLetTupleLit(o)
        case o: org.sireum.logika.State.Claim.Let.And => return print_logikaStateClaimLetAnd(o)
        case o: org.sireum.logika.State.Claim.Let.Or => return print_logikaStateClaimLetOr(o)
        case o: org.sireum.logika.State.Claim.Let.Imply => return print_logikaStateClaimLetImply(o)
      }
    }

    @pure def print_logikaStateClaimLetCurrentName(o: org.sireum.logika.State.Claim.Let.CurrentName): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.CurrentName""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("ids", printISZ(T, o.ids, printString _)),
        ("defPosOpt", printOption(F, o.defPosOpt, printPosition _))
      ))
    }

    @pure def print_logikaStateClaimLetName(o: org.sireum.logika.State.Claim.Let.Name): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Name""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("ids", printISZ(T, o.ids, printString _)),
        ("num", printZ(o.num)),
        ("poss", printISZ(F, o.poss, printPosition _))
      ))
    }

    @pure def print_logikaStateClaimLetCurrentId(o: org.sireum.logika.State.Claim.Let.CurrentId): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.CurrentId""""),
        ("declId", printB(o.declId)),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("context", printISZ(T, o.context, printString _)),
        ("id", printString(o.id)),
        ("defPosOpt", printOption(F, o.defPosOpt, printPosition _))
      ))
    }

    @pure def print_logikaStateClaimLetId(o: org.sireum.logika.State.Claim.Let.Id): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Id""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("context", printISZ(T, o.context, printString _)),
        ("id", printString(o.id)),
        ("num", printZ(o.num)),
        ("poss", printISZ(F, o.poss, printPosition _))
      ))
    }

    @pure def print_logikaStateClaimLetEq(o: org.sireum.logika.State.Claim.Let.Eq): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Eq""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("value", print_logikaStateValue(o.value))
      ))
    }

    @pure def print_logikaStateClaimLetTypeTest(o: org.sireum.logika.State.Claim.Let.TypeTest): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.TypeTest""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("isEq", printB(o.isEq)),
        ("value", print_logikaStateValue(o.value)),
        ("tipe", print_langastTyped(o.tipe))
      ))
    }

    @pure def print_logikaStateClaimLetQuantVar(o: org.sireum.logika.State.Claim.Let.Quant.Var): ST = {
      o match {
        case o: org.sireum.logika.State.Claim.Let.Quant.Var.Id => return print_logikaStateClaimLetQuantVarId(o)
        case o: org.sireum.logika.State.Claim.Let.Quant.Var.Sym => return print_logikaStateClaimLetQuantVarSym(o)
      }
    }

    @pure def print_logikaStateClaimLetQuantVarId(o: org.sireum.logika.State.Claim.Let.Quant.Var.Id): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Quant.Var.Id""""),
        ("context", printISZ(T, o.context, printString _)),
        ("id", printString(o.id)),
        ("tipe", print_langastTyped(o.tipe))
      ))
    }

    @pure def print_logikaStateClaimLetQuantVarSym(o: org.sireum.logika.State.Claim.Let.Quant.Var.Sym): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Quant.Var.Sym""""),
        ("sym", print_logikaStateValueSym(o.sym))
      ))
    }

    @pure def print_logikaStateClaimLetQuant(o: org.sireum.logika.State.Claim.Let.Quant): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Quant""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("isAll", printB(o.isAll)),
        ("vars", printISZ(F, o.vars, print_logikaStateClaimLetQuantVar _)),
        ("claims", printISZ(F, o.claims, print_logikaStateClaim _))
      ))
    }

    @pure def print_logikaStateClaimLetIte(o: org.sireum.logika.State.Claim.Let.Ite): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Ite""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("cond", print_logikaStateValue(o.cond)),
        ("left", print_logikaStateValue(o.left)),
        ("right", print_logikaStateValue(o.right))
      ))
    }

    @pure def print_logikaStateClaimLetBinary(o: org.sireum.logika.State.Claim.Let.Binary): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Binary""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("left", print_logikaStateValue(o.left)),
        ("op", printString(o.op)),
        ("right", print_logikaStateValue(o.right)),
        ("tipe", print_langastTyped(o.tipe))
      ))
    }

    @pure def print_logikaStateClaimLetUnary(o: org.sireum.logika.State.Claim.Let.Unary): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Unary""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("op", printString(o.op)),
        ("value", print_logikaStateValue(o.value))
      ))
    }

    @pure def print_logikaStateClaimLetSeqLookup(o: org.sireum.logika.State.Claim.Let.SeqLookup): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.SeqLookup""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("seq", print_logikaStateValue(o.seq)),
        ("index", print_logikaStateValue(o.index))
      ))
    }

    @pure def print_logikaStateClaimLetSeqInBound(o: org.sireum.logika.State.Claim.Let.SeqInBound): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.SeqInBound""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("seq", print_logikaStateValue(o.seq)),
        ("index", print_logikaStateValue(o.index))
      ))
    }

    @pure def print_logikaStateClaimLetFieldLookup(o: org.sireum.logika.State.Claim.Let.FieldLookup): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.FieldLookup""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("adt", print_logikaStateValue(o.adt)),
        ("id", printString(o.id))
      ))
    }

    @pure def print_logikaStateClaimLetProofFunApply(o: org.sireum.logika.State.Claim.Let.ProofFunApply): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.ProofFunApply""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("pf", print_logikaStateProofFun(o.pf)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateClaimLetApply(o: org.sireum.logika.State.Claim.Let.Apply): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Apply""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("isLocal", printB(o.isLocal)),
        ("context", printISZ(T, o.context, printString _)),
        ("id", printString(o.id)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateClaimLetIApply(o: org.sireum.logika.State.Claim.Let.IApply): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.IApply""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("o", print_logikaStateValue(o.o)),
        ("oTipe", print_langastTypedName(o.oTipe)),
        ("id", printString(o.id)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateClaimLetTupleLit(o: org.sireum.logika.State.Claim.Let.TupleLit): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.TupleLit""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateClaimLetAnd(o: org.sireum.logika.State.Claim.Let.And): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.And""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateClaimLetOr(o: org.sireum.logika.State.Claim.Let.Or): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Or""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateClaimLetImply(o: org.sireum.logika.State.Claim.Let.Imply): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.Claim.Let.Imply""""),
        ("sym", print_logikaStateValueSym(o.sym)),
        ("args", printISZ(F, o.args, print_logikaStateValue _))
      ))
    }

    @pure def print_logikaStateProofFun(o: org.sireum.logika.State.ProofFun): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.State.ProofFun""""),
        ("receiverTypeOpt", printOption(F, o.receiverTypeOpt, print_langastTyped _)),
        ("context", printISZ(T, o.context, printString _)),
        ("id", printString(o.id)),
        ("paramIds", printISZ(T, o.paramIds, printString _)),
        ("paramTypes", printISZ(F, o.paramTypes, print_langastTyped _)),
        ("returnType", print_langastTyped(o.returnType))
      ))
    }

    @pure def printorgsireumlogikaConfig(o: org.sireum.logika.Config): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.Config""""),
        ("smt2Configs", printISZ(F, o.smt2Configs, printorgsireumlogikaSmt2Config _)),
        ("sat", printB(o.sat)),
        ("timeoutInMs", printZ(o.timeoutInMs)),
        ("defaultLoopBound", printZ(o.defaultLoopBound)),
        ("loopBounds", printHashMap(F, o.loopBounds, printorgsireumlogikaLoopId _, printZ _)),
        ("unroll", printB(o.unroll)),
        ("charBitWidth", printZ(o.charBitWidth)),
        ("intBitWidth", printZ(o.intBitWidth)),
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
        ("cvc4RLimit", printZ(o.cvc4RLimit))
      ))
    }

    @pure def printorgsireumlogikaSmt2Config(o: org.sireum.logika.Smt2Config): ST = {
      o match {
        case o: org.sireum.logika.Z3Config => return printorgsireumlogikaZ3Config(o)
        case o: org.sireum.logika.Cvc4Config => return printorgsireumlogikaCvc4Config(o)
      }
    }

    @pure def printorgsireumlogikaZ3Config(o: org.sireum.logika.Z3Config): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.Z3Config""""),
        ("exe", printString(o.exe)),
        ("validOpts", printISZ(T, o.validOpts, printString _)),
        ("satOpts", printISZ(T, o.satOpts, printString _))
      ))
    }

    @pure def printorgsireumlogikaCvc4Config(o: org.sireum.logika.Cvc4Config): ST = {
      return printObject(ISZ(
        ("type", st""""org.sireum.logika.Cvc4Config""""),
        ("exe", printString(o.exe)),
        ("validOpts", printISZ(T, o.validOpts, printString _)),
        ("satOpts", printISZ(T, o.satOpts, printString _))
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
        ("timeMillis", printZ(o.timeMillis))
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
        ("id", printString(o.id))
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
      val t = parser.parseObjectTypes(ISZ("Terminate", "Cancel", "Version.Request", "Slang.CheckScript", "Slang.Rewrite.Request", "Logika.Verify.Config"))
      t.native match {
        case "Terminate" => val r = parseTerminateT(T); return r
        case "Cancel" => val r = parseCancelT(T); return r
        case "Version.Request" => val r = parseVersionRequestT(T); return r
        case "Slang.CheckScript" => val r = parseSlangCheckScriptT(T); return r
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
      val t = parser.parseObjectTypes(ISZ("Timing", "Report", "Version.Response", "Slang.Rewrite.Response", "Logika.Verify.Start", "Logika.Verify.End", "Logika.Verify.State", "Logika.Verify.Halted", "Logika.Verify.Smt2Query", "Logika.Verify.Info"))
      t.native match {
        case "Timing" => val r = parseTimingT(T); return r
        case "Report" => val r = parseReportT(T); return r
        case "Version.Response" => val r = parseVersionResponseT(T); return r
        case "Slang.Rewrite.Response" => val r = parseSlangRewriteResponseT(T); return r
        case "Logika.Verify.Start" => val r = parseLogikaVerifyStartT(T); return r
        case "Logika.Verify.End" => val r = parseLogikaVerifyEndT(T); return r
        case "Logika.Verify.State" => val r = parseLogikaVerifyStateT(T); return r
        case "Logika.Verify.Halted" => val r = parseLogikaVerifyHaltedT(T); return r
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

    def parseSlangCheckScript(): Slang.CheckScript = {
      val r = parseSlangCheckScriptT(F)
      return r
    }

    def parseSlangCheckScriptT(typeParsed: B): Slang.CheckScript = {
      if (!typeParsed) {
        parser.parseObjectType("Slang.CheckScript")
      }
      parser.parseObjectKey("isBackground")
      val isBackground = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("logikaEnabled")
      val logikaEnabled = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("par")
      val par = parser.parseZ()
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
      return Slang.CheckScript(isBackground, logikaEnabled, par, id, uriOpt, content, line)
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

    def parseLogikaVerifyStart(): Logika.Verify.Start = {
      val r = parseLogikaVerifyStartT(F)
      return r
    }

    def parseLogikaVerifyStartT(typeParsed: B): Logika.Verify.Start = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.Start")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("currentTimeMillis")
      val currentTimeMillis = parser.parseZ()
      parser.parseObjectNext()
      return Logika.Verify.Start(id, currentTimeMillis)
    }

    def parseLogikaVerifyEnd(): Logika.Verify.End = {
      val r = parseLogikaVerifyEndT(F)
      return r
    }

    def parseLogikaVerifyEndT(typeParsed: B): Logika.Verify.End = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.End")
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
      return Logika.Verify.End(isBackground, id, wasCancelled, isIllFormed, hasLogika, totalTimeMillis, numOfSmt2Calls, smt2TimeMillis, numOfInternalErrors, numOfErrors, numOfWarnings)
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
      parser.parseObjectKey("state")
      val state = parseorgsireumlogikaState()
      parser.parseObjectNext()
      return Logika.Verify.State(id, posOpt, state)
    }

    def parseLogikaVerifyHalted(): Logika.Verify.Halted = {
      val r = parseLogikaVerifyHaltedT(F)
      return r
    }

    def parseLogikaVerifyHaltedT(typeParsed: B): Logika.Verify.Halted = {
      if (!typeParsed) {
        parser.parseObjectType("Logika.Verify.Halted")
      }
      parser.parseObjectKey("id")
      val id = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("posOpt")
      val posOpt = parser.parseOption(parser.parsePosition _)
      parser.parseObjectNext()
      parser.parseObjectKey("state")
      val state = parseorgsireumlogikaState()
      parser.parseObjectNext()
      return Logika.Verify.Halted(id, posOpt, state)
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
      parser.parseObjectKey("result")
      val result = parse_logikaSmt2QueryResult()
      parser.parseObjectNext()
      return Logika.Verify.Smt2Query(id, pos, timeInMs, result)
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

    def parseorgsireumlogikaState(): org.sireum.logika.State = {
      val r = parseorgsireumlogikaStateT(F)
      return r
    }

    def parseorgsireumlogikaStateT(typeParsed: B): org.sireum.logika.State = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State")
      }
      parser.parseObjectKey("status")
      val status = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("claims")
      val claims = parser.parseISZ(parse_logikaStateClaim _)
      parser.parseObjectNext()
      parser.parseObjectKey("nextFresh")
      val nextFresh = parser.parseZ()
      parser.parseObjectNext()
      return org.sireum.logika.State(status, claims, nextFresh)
    }

    def parse_logikaStateValue(): org.sireum.logika.State.Value = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.Value.Unit", "org.sireum.logika.State.Value.B", "org.sireum.logika.State.Value.Z", "org.sireum.logika.State.Value.C", "org.sireum.logika.State.Value.F32", "org.sireum.logika.State.Value.F64", "org.sireum.logika.State.Value.R", "org.sireum.logika.State.Value.String", "org.sireum.logika.State.Value.Range", "org.sireum.logika.State.Value.S8", "org.sireum.logika.State.Value.S16", "org.sireum.logika.State.Value.S32", "org.sireum.logika.State.Value.S64", "org.sireum.logika.State.Value.U8", "org.sireum.logika.State.Value.U16", "org.sireum.logika.State.Value.U32", "org.sireum.logika.State.Value.U64", "org.sireum.logika.State.Value.Enum", "org.sireum.logika.State.Value.Sym"))
      t.native match {
        case "org.sireum.logika.State.Value.Unit" => val r = parse_logikaStateValueUnitT(T); return r
        case "org.sireum.logika.State.Value.B" => val r = parse_logikaStateValueBT(T); return r
        case "org.sireum.logika.State.Value.Z" => val r = parse_logikaStateValueZT(T); return r
        case "org.sireum.logika.State.Value.C" => val r = parse_logikaStateValueCT(T); return r
        case "org.sireum.logika.State.Value.F32" => val r = parse_logikaStateValueF32T(T); return r
        case "org.sireum.logika.State.Value.F64" => val r = parse_logikaStateValueF64T(T); return r
        case "org.sireum.logika.State.Value.R" => val r = parse_logikaStateValueRT(T); return r
        case "org.sireum.logika.State.Value.String" => val r = parse_logikaStateValueStringT(T); return r
        case "org.sireum.logika.State.Value.Range" => val r = parse_logikaStateValueRangeT(T); return r
        case "org.sireum.logika.State.Value.S8" => val r = parse_logikaStateValueS8T(T); return r
        case "org.sireum.logika.State.Value.S16" => val r = parse_logikaStateValueS16T(T); return r
        case "org.sireum.logika.State.Value.S32" => val r = parse_logikaStateValueS32T(T); return r
        case "org.sireum.logika.State.Value.S64" => val r = parse_logikaStateValueS64T(T); return r
        case "org.sireum.logika.State.Value.U8" => val r = parse_logikaStateValueU8T(T); return r
        case "org.sireum.logika.State.Value.U16" => val r = parse_logikaStateValueU16T(T); return r
        case "org.sireum.logika.State.Value.U32" => val r = parse_logikaStateValueU32T(T); return r
        case "org.sireum.logika.State.Value.U64" => val r = parse_logikaStateValueU64T(T); return r
        case "org.sireum.logika.State.Value.Enum" => val r = parse_logikaStateValueEnumT(T); return r
        case "org.sireum.logika.State.Value.Sym" => val r = parse_logikaStateValueSymT(T); return r
        case _ => val r = parse_logikaStateValueSymT(T); return r
      }
    }

    def parse_logikaStateFun(): org.sireum.logika.State.Fun = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.IFun", "org.sireum.logika.State.OFun"))
      t.native match {
        case "org.sireum.logika.State.IFun" => val r = parse_logikaStateIFunT(T); return r
        case "org.sireum.logika.State.OFun" => val r = parse_logikaStateOFunT(T); return r
        case _ => val r = parse_logikaStateOFunT(T); return r
      }
    }

    def parse_logikaStateIFun(): org.sireum.logika.State.IFun = {
      val r = parse_logikaStateIFunT(F)
      return r
    }

    def parse_logikaStateIFunT(typeParsed: B): org.sireum.logika.State.IFun = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.IFun")
      }
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      return org.sireum.logika.State.IFun(tipe, id)
    }

    def parse_logikaStateOFun(): org.sireum.logika.State.OFun = {
      val r = parse_logikaStateOFunT(F)
      return r
    }

    def parse_logikaStateOFunT(typeParsed: B): org.sireum.logika.State.OFun = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.OFun")
      }
      parser.parseObjectKey("name")
      val name = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return org.sireum.logika.State.OFun(name)
    }

    def parse_logikaStateValueUnit(): org.sireum.logika.State.Value.Unit = {
      val r = parse_logikaStateValueUnitT(F)
      return r
    }

    def parse_logikaStateValueUnitT(typeParsed: B): org.sireum.logika.State.Value.Unit = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.Unit")
      }
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.Unit(pos)
    }

    def parse_logikaStateValueB(): org.sireum.logika.State.Value.B = {
      val r = parse_logikaStateValueBT(F)
      return r
    }

    def parse_logikaStateValueBT(typeParsed: B): org.sireum.logika.State.Value.B = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.B")
      }
      parser.parseObjectKey("value")
      val value = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.B(value, pos)
    }

    def parse_logikaStateValueZ(): org.sireum.logika.State.Value.Z = {
      val r = parse_logikaStateValueZT(F)
      return r
    }

    def parse_logikaStateValueZT(typeParsed: B): org.sireum.logika.State.Value.Z = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.Z")
      }
      parser.parseObjectKey("value")
      val value = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.Z(value, pos)
    }

    def parse_logikaStateValueC(): org.sireum.logika.State.Value.C = {
      val r = parse_logikaStateValueCT(F)
      return r
    }

    def parse_logikaStateValueCT(typeParsed: B): org.sireum.logika.State.Value.C = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.C")
      }
      parser.parseObjectKey("value")
      val value = parser.parseC()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.C(value, pos)
    }

    def parse_logikaStateValueF32(): org.sireum.logika.State.Value.F32 = {
      val r = parse_logikaStateValueF32T(F)
      return r
    }

    def parse_logikaStateValueF32T(typeParsed: B): org.sireum.logika.State.Value.F32 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.F32")
      }
      parser.parseObjectKey("value")
      val value = parser.parseF32()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.F32(value, pos)
    }

    def parse_logikaStateValueF64(): org.sireum.logika.State.Value.F64 = {
      val r = parse_logikaStateValueF64T(F)
      return r
    }

    def parse_logikaStateValueF64T(typeParsed: B): org.sireum.logika.State.Value.F64 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.F64")
      }
      parser.parseObjectKey("value")
      val value = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.F64(value, pos)
    }

    def parse_logikaStateValueR(): org.sireum.logika.State.Value.R = {
      val r = parse_logikaStateValueRT(F)
      return r
    }

    def parse_logikaStateValueRT(typeParsed: B): org.sireum.logika.State.Value.R = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.R")
      }
      parser.parseObjectKey("value")
      val value = parser.parseR()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.R(value, pos)
    }

    def parse_logikaStateValueString(): org.sireum.logika.State.Value.String = {
      val r = parse_logikaStateValueStringT(F)
      return r
    }

    def parse_logikaStateValueStringT(typeParsed: B): org.sireum.logika.State.Value.String = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.String")
      }
      parser.parseObjectKey("value")
      val value = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.String(value, pos)
    }

    def parse_logikaStateValueSubZ(): org.sireum.logika.State.Value.SubZ = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.Value.Range", "org.sireum.logika.State.Value.S8", "org.sireum.logika.State.Value.S16", "org.sireum.logika.State.Value.S32", "org.sireum.logika.State.Value.S64", "org.sireum.logika.State.Value.U8", "org.sireum.logika.State.Value.U16", "org.sireum.logika.State.Value.U32", "org.sireum.logika.State.Value.U64"))
      t.native match {
        case "org.sireum.logika.State.Value.Range" => val r = parse_logikaStateValueRangeT(T); return r
        case "org.sireum.logika.State.Value.S8" => val r = parse_logikaStateValueS8T(T); return r
        case "org.sireum.logika.State.Value.S16" => val r = parse_logikaStateValueS16T(T); return r
        case "org.sireum.logika.State.Value.S32" => val r = parse_logikaStateValueS32T(T); return r
        case "org.sireum.logika.State.Value.S64" => val r = parse_logikaStateValueS64T(T); return r
        case "org.sireum.logika.State.Value.U8" => val r = parse_logikaStateValueU8T(T); return r
        case "org.sireum.logika.State.Value.U16" => val r = parse_logikaStateValueU16T(T); return r
        case "org.sireum.logika.State.Value.U32" => val r = parse_logikaStateValueU32T(T); return r
        case "org.sireum.logika.State.Value.U64" => val r = parse_logikaStateValueU64T(T); return r
        case _ => val r = parse_logikaStateValueU64T(T); return r
      }
    }

    def parse_logikaStateValueRange(): org.sireum.logika.State.Value.Range = {
      val r = parse_logikaStateValueRangeT(F)
      return r
    }

    def parse_logikaStateValueRangeT(typeParsed: B): org.sireum.logika.State.Value.Range = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.Range")
      }
      parser.parseObjectKey("value")
      val value = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.Range(value, tipe, pos)
    }

    def parse_logikaStateValueS8(): org.sireum.logika.State.Value.S8 = {
      val r = parse_logikaStateValueS8T(F)
      return r
    }

    def parse_logikaStateValueS8T(typeParsed: B): org.sireum.logika.State.Value.S8 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.S8")
      }
      parser.parseObjectKey("value")
      val value = parser.parseS8()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.S8(value, tipe, pos)
    }

    def parse_logikaStateValueS16(): org.sireum.logika.State.Value.S16 = {
      val r = parse_logikaStateValueS16T(F)
      return r
    }

    def parse_logikaStateValueS16T(typeParsed: B): org.sireum.logika.State.Value.S16 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.S16")
      }
      parser.parseObjectKey("value")
      val value = parser.parseS16()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.S16(value, tipe, pos)
    }

    def parse_logikaStateValueS32(): org.sireum.logika.State.Value.S32 = {
      val r = parse_logikaStateValueS32T(F)
      return r
    }

    def parse_logikaStateValueS32T(typeParsed: B): org.sireum.logika.State.Value.S32 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.S32")
      }
      parser.parseObjectKey("value")
      val value = parser.parseS32()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.S32(value, tipe, pos)
    }

    def parse_logikaStateValueS64(): org.sireum.logika.State.Value.S64 = {
      val r = parse_logikaStateValueS64T(F)
      return r
    }

    def parse_logikaStateValueS64T(typeParsed: B): org.sireum.logika.State.Value.S64 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.S64")
      }
      parser.parseObjectKey("value")
      val value = parser.parseS64()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.S64(value, tipe, pos)
    }

    def parse_logikaStateValueU8(): org.sireum.logika.State.Value.U8 = {
      val r = parse_logikaStateValueU8T(F)
      return r
    }

    def parse_logikaStateValueU8T(typeParsed: B): org.sireum.logika.State.Value.U8 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.U8")
      }
      parser.parseObjectKey("value")
      val value = parser.parseU8()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.U8(value, tipe, pos)
    }

    def parse_logikaStateValueU16(): org.sireum.logika.State.Value.U16 = {
      val r = parse_logikaStateValueU16T(F)
      return r
    }

    def parse_logikaStateValueU16T(typeParsed: B): org.sireum.logika.State.Value.U16 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.U16")
      }
      parser.parseObjectKey("value")
      val value = parser.parseU16()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.U16(value, tipe, pos)
    }

    def parse_logikaStateValueU32(): org.sireum.logika.State.Value.U32 = {
      val r = parse_logikaStateValueU32T(F)
      return r
    }

    def parse_logikaStateValueU32T(typeParsed: B): org.sireum.logika.State.Value.U32 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.U32")
      }
      parser.parseObjectKey("value")
      val value = parser.parseU32()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.U32(value, tipe, pos)
    }

    def parse_logikaStateValueU64(): org.sireum.logika.State.Value.U64 = {
      val r = parse_logikaStateValueU64T(F)
      return r
    }

    def parse_logikaStateValueU64T(typeParsed: B): org.sireum.logika.State.Value.U64 = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.U64")
      }
      parser.parseObjectKey("value")
      val value = parser.parseU64()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.U64(value, tipe, pos)
    }

    def parse_logikaStateValueEnum(): org.sireum.logika.State.Value.Enum = {
      val r = parse_logikaStateValueEnumT(F)
      return r
    }

    def parse_logikaStateValueEnumT(typeParsed: B): org.sireum.logika.State.Value.Enum = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.Enum")
      }
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("owner")
      val owner = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("ordinal")
      val ordinal = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.Enum(tipe, owner, id, ordinal, pos)
    }

    def parse_logikaStateValueSym(): org.sireum.logika.State.Value.Sym = {
      val r = parse_logikaStateValueSymT(F)
      return r
    }

    def parse_logikaStateValueSymT(typeParsed: B): org.sireum.logika.State.Value.Sym = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Value.Sym")
      }
      parser.parseObjectKey("num")
      val num = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTyped()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Value.Sym(num, tipe, pos)
    }

    def parse_logikaStateClaim(): org.sireum.logika.State.Claim = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.Claim.Label", "org.sireum.logika.State.Claim.Prop", "org.sireum.logika.State.Claim.And", "org.sireum.logika.State.Claim.Or", "org.sireum.logika.State.Claim.Imply", "org.sireum.logika.State.Claim.If", "org.sireum.logika.State.Claim.Def.SeqLit", "org.sireum.logika.State.Claim.Def.SeqStore", "org.sireum.logika.State.Claim.Def.FieldStore", "org.sireum.logika.State.Claim.Def.AdtLit", "org.sireum.logika.State.Claim.Def.Random", "org.sireum.logika.State.Claim.Let.CurrentName", "org.sireum.logika.State.Claim.Let.Name", "org.sireum.logika.State.Claim.Let.CurrentId", "org.sireum.logika.State.Claim.Let.Id", "org.sireum.logika.State.Claim.Let.Eq", "org.sireum.logika.State.Claim.Let.TypeTest", "org.sireum.logika.State.Claim.Let.Quant", "org.sireum.logika.State.Claim.Let.Ite", "org.sireum.logika.State.Claim.Let.Binary", "org.sireum.logika.State.Claim.Let.Unary", "org.sireum.logika.State.Claim.Let.SeqLookup", "org.sireum.logika.State.Claim.Let.SeqInBound", "org.sireum.logika.State.Claim.Let.FieldLookup", "org.sireum.logika.State.Claim.Let.ProofFunApply", "org.sireum.logika.State.Claim.Let.Apply", "org.sireum.logika.State.Claim.Let.IApply", "org.sireum.logika.State.Claim.Let.TupleLit", "org.sireum.logika.State.Claim.Let.And", "org.sireum.logika.State.Claim.Let.Or", "org.sireum.logika.State.Claim.Let.Imply"))
      t.native match {
        case "org.sireum.logika.State.Claim.Label" => val r = parse_logikaStateClaimLabelT(T); return r
        case "org.sireum.logika.State.Claim.Prop" => val r = parse_logikaStateClaimPropT(T); return r
        case "org.sireum.logika.State.Claim.And" => val r = parse_logikaStateClaimAndT(T); return r
        case "org.sireum.logika.State.Claim.Or" => val r = parse_logikaStateClaimOrT(T); return r
        case "org.sireum.logika.State.Claim.Imply" => val r = parse_logikaStateClaimImplyT(T); return r
        case "org.sireum.logika.State.Claim.If" => val r = parse_logikaStateClaimIfT(T); return r
        case "org.sireum.logika.State.Claim.Def.SeqLit" => val r = parse_logikaStateClaimDefSeqLitT(T); return r
        case "org.sireum.logika.State.Claim.Def.SeqStore" => val r = parse_logikaStateClaimDefSeqStoreT(T); return r
        case "org.sireum.logika.State.Claim.Def.FieldStore" => val r = parse_logikaStateClaimDefFieldStoreT(T); return r
        case "org.sireum.logika.State.Claim.Def.AdtLit" => val r = parse_logikaStateClaimDefAdtLitT(T); return r
        case "org.sireum.logika.State.Claim.Def.Random" => val r = parse_logikaStateClaimDefRandomT(T); return r
        case "org.sireum.logika.State.Claim.Let.CurrentName" => val r = parse_logikaStateClaimLetCurrentNameT(T); return r
        case "org.sireum.logika.State.Claim.Let.Name" => val r = parse_logikaStateClaimLetNameT(T); return r
        case "org.sireum.logika.State.Claim.Let.CurrentId" => val r = parse_logikaStateClaimLetCurrentIdT(T); return r
        case "org.sireum.logika.State.Claim.Let.Id" => val r = parse_logikaStateClaimLetIdT(T); return r
        case "org.sireum.logika.State.Claim.Let.Eq" => val r = parse_logikaStateClaimLetEqT(T); return r
        case "org.sireum.logika.State.Claim.Let.TypeTest" => val r = parse_logikaStateClaimLetTypeTestT(T); return r
        case "org.sireum.logika.State.Claim.Let.Quant" => val r = parse_logikaStateClaimLetQuantT(T); return r
        case "org.sireum.logika.State.Claim.Let.Ite" => val r = parse_logikaStateClaimLetIteT(T); return r
        case "org.sireum.logika.State.Claim.Let.Binary" => val r = parse_logikaStateClaimLetBinaryT(T); return r
        case "org.sireum.logika.State.Claim.Let.Unary" => val r = parse_logikaStateClaimLetUnaryT(T); return r
        case "org.sireum.logika.State.Claim.Let.SeqLookup" => val r = parse_logikaStateClaimLetSeqLookupT(T); return r
        case "org.sireum.logika.State.Claim.Let.SeqInBound" => val r = parse_logikaStateClaimLetSeqInBoundT(T); return r
        case "org.sireum.logika.State.Claim.Let.FieldLookup" => val r = parse_logikaStateClaimLetFieldLookupT(T); return r
        case "org.sireum.logika.State.Claim.Let.ProofFunApply" => val r = parse_logikaStateClaimLetProofFunApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.Apply" => val r = parse_logikaStateClaimLetApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.IApply" => val r = parse_logikaStateClaimLetIApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.TupleLit" => val r = parse_logikaStateClaimLetTupleLitT(T); return r
        case "org.sireum.logika.State.Claim.Let.And" => val r = parse_logikaStateClaimLetAndT(T); return r
        case "org.sireum.logika.State.Claim.Let.Or" => val r = parse_logikaStateClaimLetOrT(T); return r
        case "org.sireum.logika.State.Claim.Let.Imply" => val r = parse_logikaStateClaimLetImplyT(T); return r
        case _ => val r = parse_logikaStateClaimLetImplyT(T); return r
      }
    }

    def parse_logikaStateClaimComposite(): org.sireum.logika.State.Claim.Composite = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.Claim.And", "org.sireum.logika.State.Claim.Or", "org.sireum.logika.State.Claim.Imply", "org.sireum.logika.State.Claim.If", "org.sireum.logika.State.Claim.Let.Quant"))
      t.native match {
        case "org.sireum.logika.State.Claim.And" => val r = parse_logikaStateClaimAndT(T); return r
        case "org.sireum.logika.State.Claim.Or" => val r = parse_logikaStateClaimOrT(T); return r
        case "org.sireum.logika.State.Claim.Imply" => val r = parse_logikaStateClaimImplyT(T); return r
        case "org.sireum.logika.State.Claim.If" => val r = parse_logikaStateClaimIfT(T); return r
        case "org.sireum.logika.State.Claim.Let.Quant" => val r = parse_logikaStateClaimLetQuantT(T); return r
        case _ => val r = parse_logikaStateClaimLetQuantT(T); return r
      }
    }

    def parse_logikaStateClaimLabel(): org.sireum.logika.State.Claim.Label = {
      val r = parse_logikaStateClaimLabelT(F)
      return r
    }

    def parse_logikaStateClaimLabelT(typeParsed: B): org.sireum.logika.State.Claim.Label = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Label")
      }
      parser.parseObjectKey("label")
      val label = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Label(label, pos)
    }

    def parse_logikaStateClaimProp(): org.sireum.logika.State.Claim.Prop = {
      val r = parse_logikaStateClaimPropT(F)
      return r
    }

    def parse_logikaStateClaimPropT(typeParsed: B): org.sireum.logika.State.Claim.Prop = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Prop")
      }
      parser.parseObjectKey("isPos")
      val isPos = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parse_logikaStateValueSym()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Prop(isPos, value)
    }

    def parse_logikaStateClaimAnd(): org.sireum.logika.State.Claim.And = {
      val r = parse_logikaStateClaimAndT(F)
      return r
    }

    def parse_logikaStateClaimAndT(typeParsed: B): org.sireum.logika.State.Claim.And = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.And")
      }
      parser.parseObjectKey("claims")
      val claims = parser.parseISZ(parse_logikaStateClaim _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.And(claims)
    }

    def parse_logikaStateClaimOr(): org.sireum.logika.State.Claim.Or = {
      val r = parse_logikaStateClaimOrT(F)
      return r
    }

    def parse_logikaStateClaimOrT(typeParsed: B): org.sireum.logika.State.Claim.Or = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Or")
      }
      parser.parseObjectKey("claims")
      val claims = parser.parseISZ(parse_logikaStateClaim _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Or(claims)
    }

    def parse_logikaStateClaimImply(): org.sireum.logika.State.Claim.Imply = {
      val r = parse_logikaStateClaimImplyT(F)
      return r
    }

    def parse_logikaStateClaimImplyT(typeParsed: B): org.sireum.logika.State.Claim.Imply = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Imply")
      }
      parser.parseObjectKey("claims")
      val claims = parser.parseISZ(parse_logikaStateClaim _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Imply(claims)
    }

    def parse_logikaStateClaimIf(): org.sireum.logika.State.Claim.If = {
      val r = parse_logikaStateClaimIfT(F)
      return r
    }

    def parse_logikaStateClaimIfT(typeParsed: B): org.sireum.logika.State.Claim.If = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.If")
      }
      parser.parseObjectKey("cond")
      val cond = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("tClaims")
      val tClaims = parser.parseISZ(parse_logikaStateClaim _)
      parser.parseObjectNext()
      parser.parseObjectKey("fClaims")
      val fClaims = parser.parseISZ(parse_logikaStateClaim _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.If(cond, tClaims, fClaims)
    }

    def parse_logikaStateClaimDef(): org.sireum.logika.State.Claim.Def = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.Claim.Def.SeqLit", "org.sireum.logika.State.Claim.Def.SeqStore", "org.sireum.logika.State.Claim.Def.FieldStore", "org.sireum.logika.State.Claim.Def.AdtLit", "org.sireum.logika.State.Claim.Def.Random", "org.sireum.logika.State.Claim.Let.CurrentName", "org.sireum.logika.State.Claim.Let.Name", "org.sireum.logika.State.Claim.Let.CurrentId", "org.sireum.logika.State.Claim.Let.Id", "org.sireum.logika.State.Claim.Let.Eq", "org.sireum.logika.State.Claim.Let.TypeTest", "org.sireum.logika.State.Claim.Let.Quant", "org.sireum.logika.State.Claim.Let.Ite", "org.sireum.logika.State.Claim.Let.Binary", "org.sireum.logika.State.Claim.Let.Unary", "org.sireum.logika.State.Claim.Let.SeqLookup", "org.sireum.logika.State.Claim.Let.SeqInBound", "org.sireum.logika.State.Claim.Let.FieldLookup", "org.sireum.logika.State.Claim.Let.ProofFunApply", "org.sireum.logika.State.Claim.Let.Apply", "org.sireum.logika.State.Claim.Let.IApply", "org.sireum.logika.State.Claim.Let.TupleLit", "org.sireum.logika.State.Claim.Let.And", "org.sireum.logika.State.Claim.Let.Or", "org.sireum.logika.State.Claim.Let.Imply"))
      t.native match {
        case "org.sireum.logika.State.Claim.Def.SeqLit" => val r = parse_logikaStateClaimDefSeqLitT(T); return r
        case "org.sireum.logika.State.Claim.Def.SeqStore" => val r = parse_logikaStateClaimDefSeqStoreT(T); return r
        case "org.sireum.logika.State.Claim.Def.FieldStore" => val r = parse_logikaStateClaimDefFieldStoreT(T); return r
        case "org.sireum.logika.State.Claim.Def.AdtLit" => val r = parse_logikaStateClaimDefAdtLitT(T); return r
        case "org.sireum.logika.State.Claim.Def.Random" => val r = parse_logikaStateClaimDefRandomT(T); return r
        case "org.sireum.logika.State.Claim.Let.CurrentName" => val r = parse_logikaStateClaimLetCurrentNameT(T); return r
        case "org.sireum.logika.State.Claim.Let.Name" => val r = parse_logikaStateClaimLetNameT(T); return r
        case "org.sireum.logika.State.Claim.Let.CurrentId" => val r = parse_logikaStateClaimLetCurrentIdT(T); return r
        case "org.sireum.logika.State.Claim.Let.Id" => val r = parse_logikaStateClaimLetIdT(T); return r
        case "org.sireum.logika.State.Claim.Let.Eq" => val r = parse_logikaStateClaimLetEqT(T); return r
        case "org.sireum.logika.State.Claim.Let.TypeTest" => val r = parse_logikaStateClaimLetTypeTestT(T); return r
        case "org.sireum.logika.State.Claim.Let.Quant" => val r = parse_logikaStateClaimLetQuantT(T); return r
        case "org.sireum.logika.State.Claim.Let.Ite" => val r = parse_logikaStateClaimLetIteT(T); return r
        case "org.sireum.logika.State.Claim.Let.Binary" => val r = parse_logikaStateClaimLetBinaryT(T); return r
        case "org.sireum.logika.State.Claim.Let.Unary" => val r = parse_logikaStateClaimLetUnaryT(T); return r
        case "org.sireum.logika.State.Claim.Let.SeqLookup" => val r = parse_logikaStateClaimLetSeqLookupT(T); return r
        case "org.sireum.logika.State.Claim.Let.SeqInBound" => val r = parse_logikaStateClaimLetSeqInBoundT(T); return r
        case "org.sireum.logika.State.Claim.Let.FieldLookup" => val r = parse_logikaStateClaimLetFieldLookupT(T); return r
        case "org.sireum.logika.State.Claim.Let.ProofFunApply" => val r = parse_logikaStateClaimLetProofFunApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.Apply" => val r = parse_logikaStateClaimLetApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.IApply" => val r = parse_logikaStateClaimLetIApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.TupleLit" => val r = parse_logikaStateClaimLetTupleLitT(T); return r
        case "org.sireum.logika.State.Claim.Let.And" => val r = parse_logikaStateClaimLetAndT(T); return r
        case "org.sireum.logika.State.Claim.Let.Or" => val r = parse_logikaStateClaimLetOrT(T); return r
        case "org.sireum.logika.State.Claim.Let.Imply" => val r = parse_logikaStateClaimLetImplyT(T); return r
        case _ => val r = parse_logikaStateClaimLetImplyT(T); return r
      }
    }

    def parse_logikaStateClaimDefSeqLit(): org.sireum.logika.State.Claim.Def.SeqLit = {
      val r = parse_logikaStateClaimDefSeqLitT(F)
      return r
    }

    def parse_logikaStateClaimDefSeqLitT(typeParsed: B): org.sireum.logika.State.Claim.Def.SeqLit = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Def.SeqLit")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateClaimDefSeqLitArg _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Def.SeqLit(sym, args)
    }

    def parse_logikaStateClaimDefSeqLitArg(): org.sireum.logika.State.Claim.Def.SeqLit.Arg = {
      val r = parse_logikaStateClaimDefSeqLitArgT(F)
      return r
    }

    def parse_logikaStateClaimDefSeqLitArgT(typeParsed: B): org.sireum.logika.State.Claim.Def.SeqLit.Arg = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Def.SeqLit.Arg")
      }
      parser.parseObjectKey("index")
      val index = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Def.SeqLit.Arg(index, value)
    }

    def parse_logikaStateClaimDefSeqStore(): org.sireum.logika.State.Claim.Def.SeqStore = {
      val r = parse_logikaStateClaimDefSeqStoreT(F)
      return r
    }

    def parse_logikaStateClaimDefSeqStoreT(typeParsed: B): org.sireum.logika.State.Claim.Def.SeqStore = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Def.SeqStore")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("seq")
      val seq = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("index")
      val index = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Def.SeqStore(sym, seq, index, element)
    }

    def parse_logikaStateClaimDefFieldStore(): org.sireum.logika.State.Claim.Def.FieldStore = {
      val r = parse_logikaStateClaimDefFieldStoreT(F)
      return r
    }

    def parse_logikaStateClaimDefFieldStoreT(typeParsed: B): org.sireum.logika.State.Claim.Def.FieldStore = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Def.FieldStore")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("adt")
      val adt = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Def.FieldStore(sym, adt, id, value)
    }

    def parse_logikaStateClaimDefAdtLit(): org.sireum.logika.State.Claim.Def.AdtLit = {
      val r = parse_logikaStateClaimDefAdtLitT(F)
      return r
    }

    def parse_logikaStateClaimDefAdtLitT(typeParsed: B): org.sireum.logika.State.Claim.Def.AdtLit = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Def.AdtLit")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Def.AdtLit(sym, args)
    }

    def parse_logikaStateClaimDefRandom(): org.sireum.logika.State.Claim.Def.Random = {
      val r = parse_logikaStateClaimDefRandomT(F)
      return r
    }

    def parse_logikaStateClaimDefRandomT(typeParsed: B): org.sireum.logika.State.Claim.Def.Random = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Def.Random")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("pos")
      val pos = parser.parsePosition()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Def.Random(sym, pos)
    }

    def parse_logikaStateClaimLet(): org.sireum.logika.State.Claim.Let = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.Claim.Let.CurrentName", "org.sireum.logika.State.Claim.Let.Name", "org.sireum.logika.State.Claim.Let.CurrentId", "org.sireum.logika.State.Claim.Let.Id", "org.sireum.logika.State.Claim.Let.Eq", "org.sireum.logika.State.Claim.Let.TypeTest", "org.sireum.logika.State.Claim.Let.Quant", "org.sireum.logika.State.Claim.Let.Ite", "org.sireum.logika.State.Claim.Let.Binary", "org.sireum.logika.State.Claim.Let.Unary", "org.sireum.logika.State.Claim.Let.SeqLookup", "org.sireum.logika.State.Claim.Let.SeqInBound", "org.sireum.logika.State.Claim.Let.FieldLookup", "org.sireum.logika.State.Claim.Let.ProofFunApply", "org.sireum.logika.State.Claim.Let.Apply", "org.sireum.logika.State.Claim.Let.IApply", "org.sireum.logika.State.Claim.Let.TupleLit", "org.sireum.logika.State.Claim.Let.And", "org.sireum.logika.State.Claim.Let.Or", "org.sireum.logika.State.Claim.Let.Imply"))
      t.native match {
        case "org.sireum.logika.State.Claim.Let.CurrentName" => val r = parse_logikaStateClaimLetCurrentNameT(T); return r
        case "org.sireum.logika.State.Claim.Let.Name" => val r = parse_logikaStateClaimLetNameT(T); return r
        case "org.sireum.logika.State.Claim.Let.CurrentId" => val r = parse_logikaStateClaimLetCurrentIdT(T); return r
        case "org.sireum.logika.State.Claim.Let.Id" => val r = parse_logikaStateClaimLetIdT(T); return r
        case "org.sireum.logika.State.Claim.Let.Eq" => val r = parse_logikaStateClaimLetEqT(T); return r
        case "org.sireum.logika.State.Claim.Let.TypeTest" => val r = parse_logikaStateClaimLetTypeTestT(T); return r
        case "org.sireum.logika.State.Claim.Let.Quant" => val r = parse_logikaStateClaimLetQuantT(T); return r
        case "org.sireum.logika.State.Claim.Let.Ite" => val r = parse_logikaStateClaimLetIteT(T); return r
        case "org.sireum.logika.State.Claim.Let.Binary" => val r = parse_logikaStateClaimLetBinaryT(T); return r
        case "org.sireum.logika.State.Claim.Let.Unary" => val r = parse_logikaStateClaimLetUnaryT(T); return r
        case "org.sireum.logika.State.Claim.Let.SeqLookup" => val r = parse_logikaStateClaimLetSeqLookupT(T); return r
        case "org.sireum.logika.State.Claim.Let.SeqInBound" => val r = parse_logikaStateClaimLetSeqInBoundT(T); return r
        case "org.sireum.logika.State.Claim.Let.FieldLookup" => val r = parse_logikaStateClaimLetFieldLookupT(T); return r
        case "org.sireum.logika.State.Claim.Let.ProofFunApply" => val r = parse_logikaStateClaimLetProofFunApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.Apply" => val r = parse_logikaStateClaimLetApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.IApply" => val r = parse_logikaStateClaimLetIApplyT(T); return r
        case "org.sireum.logika.State.Claim.Let.TupleLit" => val r = parse_logikaStateClaimLetTupleLitT(T); return r
        case "org.sireum.logika.State.Claim.Let.And" => val r = parse_logikaStateClaimLetAndT(T); return r
        case "org.sireum.logika.State.Claim.Let.Or" => val r = parse_logikaStateClaimLetOrT(T); return r
        case "org.sireum.logika.State.Claim.Let.Imply" => val r = parse_logikaStateClaimLetImplyT(T); return r
        case _ => val r = parse_logikaStateClaimLetImplyT(T); return r
      }
    }

    def parse_logikaStateClaimLetCurrentName(): org.sireum.logika.State.Claim.Let.CurrentName = {
      val r = parse_logikaStateClaimLetCurrentNameT(F)
      return r
    }

    def parse_logikaStateClaimLetCurrentNameT(typeParsed: B): org.sireum.logika.State.Claim.Let.CurrentName = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.CurrentName")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("ids")
      val ids = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("defPosOpt")
      val defPosOpt = parser.parseOption(parser.parsePosition _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.CurrentName(sym, ids, defPosOpt)
    }

    def parse_logikaStateClaimLetName(): org.sireum.logika.State.Claim.Let.Name = {
      val r = parse_logikaStateClaimLetNameT(F)
      return r
    }

    def parse_logikaStateClaimLetNameT(typeParsed: B): org.sireum.logika.State.Claim.Let.Name = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Name")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("ids")
      val ids = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("num")
      val num = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("poss")
      val poss = parser.parseISZ(parser.parsePosition _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Name(sym, ids, num, poss)
    }

    def parse_logikaStateClaimLetCurrentId(): org.sireum.logika.State.Claim.Let.CurrentId = {
      val r = parse_logikaStateClaimLetCurrentIdT(F)
      return r
    }

    def parse_logikaStateClaimLetCurrentIdT(typeParsed: B): org.sireum.logika.State.Claim.Let.CurrentId = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.CurrentId")
      }
      parser.parseObjectKey("declId")
      val declId = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("context")
      val context = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("defPosOpt")
      val defPosOpt = parser.parseOption(parser.parsePosition _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.CurrentId(declId, sym, context, id, defPosOpt)
    }

    def parse_logikaStateClaimLetId(): org.sireum.logika.State.Claim.Let.Id = {
      val r = parse_logikaStateClaimLetIdT(F)
      return r
    }

    def parse_logikaStateClaimLetIdT(typeParsed: B): org.sireum.logika.State.Claim.Let.Id = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Id")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("context")
      val context = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("num")
      val num = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("poss")
      val poss = parser.parseISZ(parser.parsePosition _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Id(sym, context, id, num, poss)
    }

    def parse_logikaStateClaimLetEq(): org.sireum.logika.State.Claim.Let.Eq = {
      val r = parse_logikaStateClaimLetEqT(F)
      return r
    }

    def parse_logikaStateClaimLetEqT(typeParsed: B): org.sireum.logika.State.Claim.Let.Eq = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Eq")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Eq(sym, value)
    }

    def parse_logikaStateClaimLetTypeTest(): org.sireum.logika.State.Claim.Let.TypeTest = {
      val r = parse_logikaStateClaimLetTypeTestT(F)
      return r
    }

    def parse_logikaStateClaimLetTypeTestT(typeParsed: B): org.sireum.logika.State.Claim.Let.TypeTest = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.TypeTest")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("isEq")
      val isEq = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTyped()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.TypeTest(sym, isEq, value, tipe)
    }

    def parse_logikaStateClaimLetQuantVar(): org.sireum.logika.State.Claim.Let.Quant.Var = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.State.Claim.Let.Quant.Var.Id", "org.sireum.logika.State.Claim.Let.Quant.Var.Sym"))
      t.native match {
        case "org.sireum.logika.State.Claim.Let.Quant.Var.Id" => val r = parse_logikaStateClaimLetQuantVarIdT(T); return r
        case "org.sireum.logika.State.Claim.Let.Quant.Var.Sym" => val r = parse_logikaStateClaimLetQuantVarSymT(T); return r
        case _ => val r = parse_logikaStateClaimLetQuantVarSymT(T); return r
      }
    }

    def parse_logikaStateClaimLetQuantVarId(): org.sireum.logika.State.Claim.Let.Quant.Var.Id = {
      val r = parse_logikaStateClaimLetQuantVarIdT(F)
      return r
    }

    def parse_logikaStateClaimLetQuantVarIdT(typeParsed: B): org.sireum.logika.State.Claim.Let.Quant.Var.Id = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Quant.Var.Id")
      }
      parser.parseObjectKey("context")
      val context = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTyped()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Quant.Var.Id(context, id, tipe)
    }

    def parse_logikaStateClaimLetQuantVarSym(): org.sireum.logika.State.Claim.Let.Quant.Var.Sym = {
      val r = parse_logikaStateClaimLetQuantVarSymT(F)
      return r
    }

    def parse_logikaStateClaimLetQuantVarSymT(typeParsed: B): org.sireum.logika.State.Claim.Let.Quant.Var.Sym = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Quant.Var.Sym")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Quant.Var.Sym(sym)
    }

    def parse_logikaStateClaimLetQuant(): org.sireum.logika.State.Claim.Let.Quant = {
      val r = parse_logikaStateClaimLetQuantT(F)
      return r
    }

    def parse_logikaStateClaimLetQuantT(typeParsed: B): org.sireum.logika.State.Claim.Let.Quant = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Quant")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("isAll")
      val isAll = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("vars")
      val vars = parser.parseISZ(parse_logikaStateClaimLetQuantVar _)
      parser.parseObjectNext()
      parser.parseObjectKey("claims")
      val claims = parser.parseISZ(parse_logikaStateClaim _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Quant(sym, isAll, vars, claims)
    }

    def parse_logikaStateClaimLetIte(): org.sireum.logika.State.Claim.Let.Ite = {
      val r = parse_logikaStateClaimLetIteT(F)
      return r
    }

    def parse_logikaStateClaimLetIteT(typeParsed: B): org.sireum.logika.State.Claim.Let.Ite = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Ite")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("cond")
      val cond = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("left")
      val left = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("right")
      val right = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Ite(sym, cond, left, right)
    }

    def parse_logikaStateClaimLetBinary(): org.sireum.logika.State.Claim.Let.Binary = {
      val r = parse_logikaStateClaimLetBinaryT(F)
      return r
    }

    def parse_logikaStateClaimLetBinaryT(typeParsed: B): org.sireum.logika.State.Claim.Let.Binary = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Binary")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("left")
      val left = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("op")
      val op = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("right")
      val right = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("tipe")
      val tipe = parse_langastTyped()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Binary(sym, left, op, right, tipe)
    }

    def parse_logikaStateClaimLetUnary(): org.sireum.logika.State.Claim.Let.Unary = {
      val r = parse_logikaStateClaimLetUnaryT(F)
      return r
    }

    def parse_logikaStateClaimLetUnaryT(typeParsed: B): org.sireum.logika.State.Claim.Let.Unary = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Unary")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("op")
      val op = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Unary(sym, op, value)
    }

    def parse_logikaStateClaimLetSeqLookup(): org.sireum.logika.State.Claim.Let.SeqLookup = {
      val r = parse_logikaStateClaimLetSeqLookupT(F)
      return r
    }

    def parse_logikaStateClaimLetSeqLookupT(typeParsed: B): org.sireum.logika.State.Claim.Let.SeqLookup = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.SeqLookup")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("seq")
      val seq = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("index")
      val index = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.SeqLookup(sym, seq, index)
    }

    def parse_logikaStateClaimLetSeqInBound(): org.sireum.logika.State.Claim.Let.SeqInBound = {
      val r = parse_logikaStateClaimLetSeqInBoundT(F)
      return r
    }

    def parse_logikaStateClaimLetSeqInBoundT(typeParsed: B): org.sireum.logika.State.Claim.Let.SeqInBound = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.SeqInBound")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("seq")
      val seq = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("index")
      val index = parse_logikaStateValue()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.SeqInBound(sym, seq, index)
    }

    def parse_logikaStateClaimLetFieldLookup(): org.sireum.logika.State.Claim.Let.FieldLookup = {
      val r = parse_logikaStateClaimLetFieldLookupT(F)
      return r
    }

    def parse_logikaStateClaimLetFieldLookupT(typeParsed: B): org.sireum.logika.State.Claim.Let.FieldLookup = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.FieldLookup")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("adt")
      val adt = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.FieldLookup(sym, adt, id)
    }

    def parse_logikaStateClaimLetProofFunApply(): org.sireum.logika.State.Claim.Let.ProofFunApply = {
      val r = parse_logikaStateClaimLetProofFunApplyT(F)
      return r
    }

    def parse_logikaStateClaimLetProofFunApplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.ProofFunApply = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.ProofFunApply")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("pf")
      val pf = parse_logikaStateProofFun()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.ProofFunApply(sym, pf, args)
    }

    def parse_logikaStateClaimLetApply(): org.sireum.logika.State.Claim.Let.Apply = {
      val r = parse_logikaStateClaimLetApplyT(F)
      return r
    }

    def parse_logikaStateClaimLetApplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.Apply = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Apply")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("isLocal")
      val isLocal = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("context")
      val context = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Apply(sym, isLocal, context, id, args)
    }

    def parse_logikaStateClaimLetIApply(): org.sireum.logika.State.Claim.Let.IApply = {
      val r = parse_logikaStateClaimLetIApplyT(F)
      return r
    }

    def parse_logikaStateClaimLetIApplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.IApply = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.IApply")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("o")
      val o = parse_logikaStateValue()
      parser.parseObjectNext()
      parser.parseObjectKey("oTipe")
      val oTipe = parse_langastTypedName()
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.IApply(sym, o, oTipe, id, args)
    }

    def parse_logikaStateClaimLetTupleLit(): org.sireum.logika.State.Claim.Let.TupleLit = {
      val r = parse_logikaStateClaimLetTupleLitT(F)
      return r
    }

    def parse_logikaStateClaimLetTupleLitT(typeParsed: B): org.sireum.logika.State.Claim.Let.TupleLit = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.TupleLit")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.TupleLit(sym, args)
    }

    def parse_logikaStateClaimLetAnd(): org.sireum.logika.State.Claim.Let.And = {
      val r = parse_logikaStateClaimLetAndT(F)
      return r
    }

    def parse_logikaStateClaimLetAndT(typeParsed: B): org.sireum.logika.State.Claim.Let.And = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.And")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.And(sym, args)
    }

    def parse_logikaStateClaimLetOr(): org.sireum.logika.State.Claim.Let.Or = {
      val r = parse_logikaStateClaimLetOrT(F)
      return r
    }

    def parse_logikaStateClaimLetOrT(typeParsed: B): org.sireum.logika.State.Claim.Let.Or = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Or")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Or(sym, args)
    }

    def parse_logikaStateClaimLetImply(): org.sireum.logika.State.Claim.Let.Imply = {
      val r = parse_logikaStateClaimLetImplyT(F)
      return r
    }

    def parse_logikaStateClaimLetImplyT(typeParsed: B): org.sireum.logika.State.Claim.Let.Imply = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.Claim.Let.Imply")
      }
      parser.parseObjectKey("sym")
      val sym = parse_logikaStateValueSym()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parse_logikaStateValue _)
      parser.parseObjectNext()
      return org.sireum.logika.State.Claim.Let.Imply(sym, args)
    }

    def parse_logikaStateProofFun(): org.sireum.logika.State.ProofFun = {
      val r = parse_logikaStateProofFunT(F)
      return r
    }

    def parse_logikaStateProofFunT(typeParsed: B): org.sireum.logika.State.ProofFun = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.State.ProofFun")
      }
      parser.parseObjectKey("receiverTypeOpt")
      val receiverTypeOpt = parser.parseOption(parse_langastTyped _)
      parser.parseObjectNext()
      parser.parseObjectKey("context")
      val context = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("paramIds")
      val paramIds = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("paramTypes")
      val paramTypes = parser.parseISZ(parse_langastTyped _)
      parser.parseObjectNext()
      parser.parseObjectKey("returnType")
      val returnType = parse_langastTyped()
      parser.parseObjectNext()
      return org.sireum.logika.State.ProofFun(receiverTypeOpt, context, id, paramIds, paramTypes, returnType)
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
      parser.parseObjectKey("sat")
      val sat = parser.parseB()
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
      parser.parseObjectKey("cvc4RLimit")
      val cvc4RLimit = parser.parseZ()
      parser.parseObjectNext()
      return org.sireum.logika.Config(smt2Configs, sat, timeoutInMs, defaultLoopBound, loopBounds, unroll, charBitWidth, intBitWidth, logPc, logRawPc, logVc, logVcDirOpt, dontSplitPfq, splitAll, splitIf, splitMatch, splitContract, simplifiedQuery, checkInfeasiblePatternMatch, cvc4RLimit)
    }

    def parseorgsireumlogikaSmt2Config(): org.sireum.logika.Smt2Config = {
      val t = parser.parseObjectTypes(ISZ("org.sireum.logika.Z3Config", "org.sireum.logika.Cvc4Config"))
      t.native match {
        case "org.sireum.logika.Z3Config" => val r = parseorgsireumlogikaZ3ConfigT(T); return r
        case "org.sireum.logika.Cvc4Config" => val r = parseorgsireumlogikaCvc4ConfigT(T); return r
        case _ => val r = parseorgsireumlogikaCvc4ConfigT(T); return r
      }
    }

    def parseorgsireumlogikaZ3Config(): org.sireum.logika.Z3Config = {
      val r = parseorgsireumlogikaZ3ConfigT(F)
      return r
    }

    def parseorgsireumlogikaZ3ConfigT(typeParsed: B): org.sireum.logika.Z3Config = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.Z3Config")
      }
      parser.parseObjectKey("exe")
      val exe = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("validOpts")
      val validOpts = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("satOpts")
      val satOpts = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return org.sireum.logika.Z3Config(exe, validOpts, satOpts)
    }

    def parseorgsireumlogikaCvc4Config(): org.sireum.logika.Cvc4Config = {
      val r = parseorgsireumlogikaCvc4ConfigT(F)
      return r
    }

    def parseorgsireumlogikaCvc4ConfigT(typeParsed: B): org.sireum.logika.Cvc4Config = {
      if (!typeParsed) {
        parser.parseObjectType("org.sireum.logika.Cvc4Config")
      }
      parser.parseObjectKey("exe")
      val exe = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("validOpts")
      val validOpts = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("satOpts")
      val satOpts = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return org.sireum.logika.Cvc4Config(exe, validOpts, satOpts)
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
      return org.sireum.logika.Smt2Query.Result(kind, solverName, query, info, output, timeMillis)
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
      return org.sireum.lang.ast.Typed.TypeVar(id)
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

  def fromSlangCheckScript(o: Slang.CheckScript, isCompact: B): String = {
    val st = Printer.printSlangCheckScript(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSlangCheckScript(s: String): Either[Slang.CheckScript, Json.ErrorMsg] = {
    def fSlangCheckScript(parser: Parser): Slang.CheckScript = {
      val r = parser.parseSlangCheckScript()
      return r
    }
    val r = to(s, fSlangCheckScript _)
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

  def fromLogikaVerifyStart(o: Logika.Verify.Start, isCompact: B): String = {
    val st = Printer.printLogikaVerifyStart(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toLogikaVerifyStart(s: String): Either[Logika.Verify.Start, Json.ErrorMsg] = {
    def fLogikaVerifyStart(parser: Parser): Logika.Verify.Start = {
      val r = parser.parseLogikaVerifyStart()
      return r
    }
    val r = to(s, fLogikaVerifyStart _)
    return r
  }

  def fromLogikaVerifyEnd(o: Logika.Verify.End, isCompact: B): String = {
    val st = Printer.printLogikaVerifyEnd(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toLogikaVerifyEnd(s: String): Either[Logika.Verify.End, Json.ErrorMsg] = {
    def fLogikaVerifyEnd(parser: Parser): Logika.Verify.End = {
      val r = parser.parseLogikaVerifyEnd()
      return r
    }
    val r = to(s, fLogikaVerifyEnd _)
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

  def fromLogikaVerifyHalted(o: Logika.Verify.Halted, isCompact: B): String = {
    val st = Printer.printLogikaVerifyHalted(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toLogikaVerifyHalted(s: String): Either[Logika.Verify.Halted, Json.ErrorMsg] = {
    def fLogikaVerifyHalted(parser: Parser): Logika.Verify.Halted = {
      val r = parser.parseLogikaVerifyHalted()
      return r
    }
    val r = to(s, fLogikaVerifyHalted _)
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

  def fromorgsireumlogikaState(o: org.sireum.logika.State, isCompact: B): String = {
    val st = Printer.printorgsireumlogikaState(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toorgsireumlogikaState(s: String): Either[org.sireum.logika.State, Json.ErrorMsg] = {
    def forgsireumlogikaState(parser: Parser): org.sireum.logika.State = {
      val r = parser.parseorgsireumlogikaState()
      return r
    }
    val r = to(s, forgsireumlogikaState _)
    return r
  }

  def from_logikaStateValue(o: org.sireum.logika.State.Value, isCompact: B): String = {
    val st = Printer.print_logikaStateValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValue(s: String): Either[org.sireum.logika.State.Value, Json.ErrorMsg] = {
    def f_logikaStateValue(parser: Parser): org.sireum.logika.State.Value = {
      val r = parser.parse_logikaStateValue()
      return r
    }
    val r = to(s, f_logikaStateValue _)
    return r
  }

  def from_logikaStateFun(o: org.sireum.logika.State.Fun, isCompact: B): String = {
    val st = Printer.print_logikaStateFun(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateFun(s: String): Either[org.sireum.logika.State.Fun, Json.ErrorMsg] = {
    def f_logikaStateFun(parser: Parser): org.sireum.logika.State.Fun = {
      val r = parser.parse_logikaStateFun()
      return r
    }
    val r = to(s, f_logikaStateFun _)
    return r
  }

  def from_logikaStateIFun(o: org.sireum.logika.State.IFun, isCompact: B): String = {
    val st = Printer.print_logikaStateIFun(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateIFun(s: String): Either[org.sireum.logika.State.IFun, Json.ErrorMsg] = {
    def f_logikaStateIFun(parser: Parser): org.sireum.logika.State.IFun = {
      val r = parser.parse_logikaStateIFun()
      return r
    }
    val r = to(s, f_logikaStateIFun _)
    return r
  }

  def from_logikaStateOFun(o: org.sireum.logika.State.OFun, isCompact: B): String = {
    val st = Printer.print_logikaStateOFun(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateOFun(s: String): Either[org.sireum.logika.State.OFun, Json.ErrorMsg] = {
    def f_logikaStateOFun(parser: Parser): org.sireum.logika.State.OFun = {
      val r = parser.parse_logikaStateOFun()
      return r
    }
    val r = to(s, f_logikaStateOFun _)
    return r
  }

  def from_logikaStateValueUnit(o: org.sireum.logika.State.Value.Unit, isCompact: B): String = {
    val st = Printer.print_logikaStateValueUnit(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueUnit(s: String): Either[org.sireum.logika.State.Value.Unit, Json.ErrorMsg] = {
    def f_logikaStateValueUnit(parser: Parser): org.sireum.logika.State.Value.Unit = {
      val r = parser.parse_logikaStateValueUnit()
      return r
    }
    val r = to(s, f_logikaStateValueUnit _)
    return r
  }

  def from_logikaStateValueB(o: org.sireum.logika.State.Value.B, isCompact: B): String = {
    val st = Printer.print_logikaStateValueB(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueB(s: String): Either[org.sireum.logika.State.Value.B, Json.ErrorMsg] = {
    def f_logikaStateValueB(parser: Parser): org.sireum.logika.State.Value.B = {
      val r = parser.parse_logikaStateValueB()
      return r
    }
    val r = to(s, f_logikaStateValueB _)
    return r
  }

  def from_logikaStateValueZ(o: org.sireum.logika.State.Value.Z, isCompact: B): String = {
    val st = Printer.print_logikaStateValueZ(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueZ(s: String): Either[org.sireum.logika.State.Value.Z, Json.ErrorMsg] = {
    def f_logikaStateValueZ(parser: Parser): org.sireum.logika.State.Value.Z = {
      val r = parser.parse_logikaStateValueZ()
      return r
    }
    val r = to(s, f_logikaStateValueZ _)
    return r
  }

  def from_logikaStateValueC(o: org.sireum.logika.State.Value.C, isCompact: B): String = {
    val st = Printer.print_logikaStateValueC(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueC(s: String): Either[org.sireum.logika.State.Value.C, Json.ErrorMsg] = {
    def f_logikaStateValueC(parser: Parser): org.sireum.logika.State.Value.C = {
      val r = parser.parse_logikaStateValueC()
      return r
    }
    val r = to(s, f_logikaStateValueC _)
    return r
  }

  def from_logikaStateValueF32(o: org.sireum.logika.State.Value.F32, isCompact: B): String = {
    val st = Printer.print_logikaStateValueF32(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueF32(s: String): Either[org.sireum.logika.State.Value.F32, Json.ErrorMsg] = {
    def f_logikaStateValueF32(parser: Parser): org.sireum.logika.State.Value.F32 = {
      val r = parser.parse_logikaStateValueF32()
      return r
    }
    val r = to(s, f_logikaStateValueF32 _)
    return r
  }

  def from_logikaStateValueF64(o: org.sireum.logika.State.Value.F64, isCompact: B): String = {
    val st = Printer.print_logikaStateValueF64(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueF64(s: String): Either[org.sireum.logika.State.Value.F64, Json.ErrorMsg] = {
    def f_logikaStateValueF64(parser: Parser): org.sireum.logika.State.Value.F64 = {
      val r = parser.parse_logikaStateValueF64()
      return r
    }
    val r = to(s, f_logikaStateValueF64 _)
    return r
  }

  def from_logikaStateValueR(o: org.sireum.logika.State.Value.R, isCompact: B): String = {
    val st = Printer.print_logikaStateValueR(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueR(s: String): Either[org.sireum.logika.State.Value.R, Json.ErrorMsg] = {
    def f_logikaStateValueR(parser: Parser): org.sireum.logika.State.Value.R = {
      val r = parser.parse_logikaStateValueR()
      return r
    }
    val r = to(s, f_logikaStateValueR _)
    return r
  }

  def from_logikaStateValueString(o: org.sireum.logika.State.Value.String, isCompact: B): String = {
    val st = Printer.print_logikaStateValueString(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueString(s: String): Either[org.sireum.logika.State.Value.String, Json.ErrorMsg] = {
    def f_logikaStateValueString(parser: Parser): org.sireum.logika.State.Value.String = {
      val r = parser.parse_logikaStateValueString()
      return r
    }
    val r = to(s, f_logikaStateValueString _)
    return r
  }

  def from_logikaStateValueSubZ(o: org.sireum.logika.State.Value.SubZ, isCompact: B): String = {
    val st = Printer.print_logikaStateValueSubZ(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueSubZ(s: String): Either[org.sireum.logika.State.Value.SubZ, Json.ErrorMsg] = {
    def f_logikaStateValueSubZ(parser: Parser): org.sireum.logika.State.Value.SubZ = {
      val r = parser.parse_logikaStateValueSubZ()
      return r
    }
    val r = to(s, f_logikaStateValueSubZ _)
    return r
  }

  def from_logikaStateValueRange(o: org.sireum.logika.State.Value.Range, isCompact: B): String = {
    val st = Printer.print_logikaStateValueRange(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueRange(s: String): Either[org.sireum.logika.State.Value.Range, Json.ErrorMsg] = {
    def f_logikaStateValueRange(parser: Parser): org.sireum.logika.State.Value.Range = {
      val r = parser.parse_logikaStateValueRange()
      return r
    }
    val r = to(s, f_logikaStateValueRange _)
    return r
  }

  def from_logikaStateValueS8(o: org.sireum.logika.State.Value.S8, isCompact: B): String = {
    val st = Printer.print_logikaStateValueS8(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueS8(s: String): Either[org.sireum.logika.State.Value.S8, Json.ErrorMsg] = {
    def f_logikaStateValueS8(parser: Parser): org.sireum.logika.State.Value.S8 = {
      val r = parser.parse_logikaStateValueS8()
      return r
    }
    val r = to(s, f_logikaStateValueS8 _)
    return r
  }

  def from_logikaStateValueS16(o: org.sireum.logika.State.Value.S16, isCompact: B): String = {
    val st = Printer.print_logikaStateValueS16(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueS16(s: String): Either[org.sireum.logika.State.Value.S16, Json.ErrorMsg] = {
    def f_logikaStateValueS16(parser: Parser): org.sireum.logika.State.Value.S16 = {
      val r = parser.parse_logikaStateValueS16()
      return r
    }
    val r = to(s, f_logikaStateValueS16 _)
    return r
  }

  def from_logikaStateValueS32(o: org.sireum.logika.State.Value.S32, isCompact: B): String = {
    val st = Printer.print_logikaStateValueS32(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueS32(s: String): Either[org.sireum.logika.State.Value.S32, Json.ErrorMsg] = {
    def f_logikaStateValueS32(parser: Parser): org.sireum.logika.State.Value.S32 = {
      val r = parser.parse_logikaStateValueS32()
      return r
    }
    val r = to(s, f_logikaStateValueS32 _)
    return r
  }

  def from_logikaStateValueS64(o: org.sireum.logika.State.Value.S64, isCompact: B): String = {
    val st = Printer.print_logikaStateValueS64(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueS64(s: String): Either[org.sireum.logika.State.Value.S64, Json.ErrorMsg] = {
    def f_logikaStateValueS64(parser: Parser): org.sireum.logika.State.Value.S64 = {
      val r = parser.parse_logikaStateValueS64()
      return r
    }
    val r = to(s, f_logikaStateValueS64 _)
    return r
  }

  def from_logikaStateValueU8(o: org.sireum.logika.State.Value.U8, isCompact: B): String = {
    val st = Printer.print_logikaStateValueU8(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueU8(s: String): Either[org.sireum.logika.State.Value.U8, Json.ErrorMsg] = {
    def f_logikaStateValueU8(parser: Parser): org.sireum.logika.State.Value.U8 = {
      val r = parser.parse_logikaStateValueU8()
      return r
    }
    val r = to(s, f_logikaStateValueU8 _)
    return r
  }

  def from_logikaStateValueU16(o: org.sireum.logika.State.Value.U16, isCompact: B): String = {
    val st = Printer.print_logikaStateValueU16(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueU16(s: String): Either[org.sireum.logika.State.Value.U16, Json.ErrorMsg] = {
    def f_logikaStateValueU16(parser: Parser): org.sireum.logika.State.Value.U16 = {
      val r = parser.parse_logikaStateValueU16()
      return r
    }
    val r = to(s, f_logikaStateValueU16 _)
    return r
  }

  def from_logikaStateValueU32(o: org.sireum.logika.State.Value.U32, isCompact: B): String = {
    val st = Printer.print_logikaStateValueU32(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueU32(s: String): Either[org.sireum.logika.State.Value.U32, Json.ErrorMsg] = {
    def f_logikaStateValueU32(parser: Parser): org.sireum.logika.State.Value.U32 = {
      val r = parser.parse_logikaStateValueU32()
      return r
    }
    val r = to(s, f_logikaStateValueU32 _)
    return r
  }

  def from_logikaStateValueU64(o: org.sireum.logika.State.Value.U64, isCompact: B): String = {
    val st = Printer.print_logikaStateValueU64(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueU64(s: String): Either[org.sireum.logika.State.Value.U64, Json.ErrorMsg] = {
    def f_logikaStateValueU64(parser: Parser): org.sireum.logika.State.Value.U64 = {
      val r = parser.parse_logikaStateValueU64()
      return r
    }
    val r = to(s, f_logikaStateValueU64 _)
    return r
  }

  def from_logikaStateValueEnum(o: org.sireum.logika.State.Value.Enum, isCompact: B): String = {
    val st = Printer.print_logikaStateValueEnum(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueEnum(s: String): Either[org.sireum.logika.State.Value.Enum, Json.ErrorMsg] = {
    def f_logikaStateValueEnum(parser: Parser): org.sireum.logika.State.Value.Enum = {
      val r = parser.parse_logikaStateValueEnum()
      return r
    }
    val r = to(s, f_logikaStateValueEnum _)
    return r
  }

  def from_logikaStateValueSym(o: org.sireum.logika.State.Value.Sym, isCompact: B): String = {
    val st = Printer.print_logikaStateValueSym(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateValueSym(s: String): Either[org.sireum.logika.State.Value.Sym, Json.ErrorMsg] = {
    def f_logikaStateValueSym(parser: Parser): org.sireum.logika.State.Value.Sym = {
      val r = parser.parse_logikaStateValueSym()
      return r
    }
    val r = to(s, f_logikaStateValueSym _)
    return r
  }

  def from_logikaStateClaim(o: org.sireum.logika.State.Claim, isCompact: B): String = {
    val st = Printer.print_logikaStateClaim(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaim(s: String): Either[org.sireum.logika.State.Claim, Json.ErrorMsg] = {
    def f_logikaStateClaim(parser: Parser): org.sireum.logika.State.Claim = {
      val r = parser.parse_logikaStateClaim()
      return r
    }
    val r = to(s, f_logikaStateClaim _)
    return r
  }

  def from_logikaStateClaimComposite(o: org.sireum.logika.State.Claim.Composite, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimComposite(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimComposite(s: String): Either[org.sireum.logika.State.Claim.Composite, Json.ErrorMsg] = {
    def f_logikaStateClaimComposite(parser: Parser): org.sireum.logika.State.Claim.Composite = {
      val r = parser.parse_logikaStateClaimComposite()
      return r
    }
    val r = to(s, f_logikaStateClaimComposite _)
    return r
  }

  def from_logikaStateClaimLabel(o: org.sireum.logika.State.Claim.Label, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLabel(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLabel(s: String): Either[org.sireum.logika.State.Claim.Label, Json.ErrorMsg] = {
    def f_logikaStateClaimLabel(parser: Parser): org.sireum.logika.State.Claim.Label = {
      val r = parser.parse_logikaStateClaimLabel()
      return r
    }
    val r = to(s, f_logikaStateClaimLabel _)
    return r
  }

  def from_logikaStateClaimProp(o: org.sireum.logika.State.Claim.Prop, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimProp(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimProp(s: String): Either[org.sireum.logika.State.Claim.Prop, Json.ErrorMsg] = {
    def f_logikaStateClaimProp(parser: Parser): org.sireum.logika.State.Claim.Prop = {
      val r = parser.parse_logikaStateClaimProp()
      return r
    }
    val r = to(s, f_logikaStateClaimProp _)
    return r
  }

  def from_logikaStateClaimAnd(o: org.sireum.logika.State.Claim.And, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimAnd(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimAnd(s: String): Either[org.sireum.logika.State.Claim.And, Json.ErrorMsg] = {
    def f_logikaStateClaimAnd(parser: Parser): org.sireum.logika.State.Claim.And = {
      val r = parser.parse_logikaStateClaimAnd()
      return r
    }
    val r = to(s, f_logikaStateClaimAnd _)
    return r
  }

  def from_logikaStateClaimOr(o: org.sireum.logika.State.Claim.Or, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimOr(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimOr(s: String): Either[org.sireum.logika.State.Claim.Or, Json.ErrorMsg] = {
    def f_logikaStateClaimOr(parser: Parser): org.sireum.logika.State.Claim.Or = {
      val r = parser.parse_logikaStateClaimOr()
      return r
    }
    val r = to(s, f_logikaStateClaimOr _)
    return r
  }

  def from_logikaStateClaimImply(o: org.sireum.logika.State.Claim.Imply, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimImply(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimImply(s: String): Either[org.sireum.logika.State.Claim.Imply, Json.ErrorMsg] = {
    def f_logikaStateClaimImply(parser: Parser): org.sireum.logika.State.Claim.Imply = {
      val r = parser.parse_logikaStateClaimImply()
      return r
    }
    val r = to(s, f_logikaStateClaimImply _)
    return r
  }

  def from_logikaStateClaimIf(o: org.sireum.logika.State.Claim.If, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimIf(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimIf(s: String): Either[org.sireum.logika.State.Claim.If, Json.ErrorMsg] = {
    def f_logikaStateClaimIf(parser: Parser): org.sireum.logika.State.Claim.If = {
      val r = parser.parse_logikaStateClaimIf()
      return r
    }
    val r = to(s, f_logikaStateClaimIf _)
    return r
  }

  def from_logikaStateClaimDef(o: org.sireum.logika.State.Claim.Def, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimDef(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimDef(s: String): Either[org.sireum.logika.State.Claim.Def, Json.ErrorMsg] = {
    def f_logikaStateClaimDef(parser: Parser): org.sireum.logika.State.Claim.Def = {
      val r = parser.parse_logikaStateClaimDef()
      return r
    }
    val r = to(s, f_logikaStateClaimDef _)
    return r
  }

  def from_logikaStateClaimDefSeqLit(o: org.sireum.logika.State.Claim.Def.SeqLit, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimDefSeqLit(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimDefSeqLit(s: String): Either[org.sireum.logika.State.Claim.Def.SeqLit, Json.ErrorMsg] = {
    def f_logikaStateClaimDefSeqLit(parser: Parser): org.sireum.logika.State.Claim.Def.SeqLit = {
      val r = parser.parse_logikaStateClaimDefSeqLit()
      return r
    }
    val r = to(s, f_logikaStateClaimDefSeqLit _)
    return r
  }

  def from_logikaStateClaimDefSeqLitArg(o: org.sireum.logika.State.Claim.Def.SeqLit.Arg, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimDefSeqLitArg(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimDefSeqLitArg(s: String): Either[org.sireum.logika.State.Claim.Def.SeqLit.Arg, Json.ErrorMsg] = {
    def f_logikaStateClaimDefSeqLitArg(parser: Parser): org.sireum.logika.State.Claim.Def.SeqLit.Arg = {
      val r = parser.parse_logikaStateClaimDefSeqLitArg()
      return r
    }
    val r = to(s, f_logikaStateClaimDefSeqLitArg _)
    return r
  }

  def from_logikaStateClaimDefSeqStore(o: org.sireum.logika.State.Claim.Def.SeqStore, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimDefSeqStore(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimDefSeqStore(s: String): Either[org.sireum.logika.State.Claim.Def.SeqStore, Json.ErrorMsg] = {
    def f_logikaStateClaimDefSeqStore(parser: Parser): org.sireum.logika.State.Claim.Def.SeqStore = {
      val r = parser.parse_logikaStateClaimDefSeqStore()
      return r
    }
    val r = to(s, f_logikaStateClaimDefSeqStore _)
    return r
  }

  def from_logikaStateClaimDefFieldStore(o: org.sireum.logika.State.Claim.Def.FieldStore, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimDefFieldStore(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimDefFieldStore(s: String): Either[org.sireum.logika.State.Claim.Def.FieldStore, Json.ErrorMsg] = {
    def f_logikaStateClaimDefFieldStore(parser: Parser): org.sireum.logika.State.Claim.Def.FieldStore = {
      val r = parser.parse_logikaStateClaimDefFieldStore()
      return r
    }
    val r = to(s, f_logikaStateClaimDefFieldStore _)
    return r
  }

  def from_logikaStateClaimDefAdtLit(o: org.sireum.logika.State.Claim.Def.AdtLit, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimDefAdtLit(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimDefAdtLit(s: String): Either[org.sireum.logika.State.Claim.Def.AdtLit, Json.ErrorMsg] = {
    def f_logikaStateClaimDefAdtLit(parser: Parser): org.sireum.logika.State.Claim.Def.AdtLit = {
      val r = parser.parse_logikaStateClaimDefAdtLit()
      return r
    }
    val r = to(s, f_logikaStateClaimDefAdtLit _)
    return r
  }

  def from_logikaStateClaimDefRandom(o: org.sireum.logika.State.Claim.Def.Random, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimDefRandom(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimDefRandom(s: String): Either[org.sireum.logika.State.Claim.Def.Random, Json.ErrorMsg] = {
    def f_logikaStateClaimDefRandom(parser: Parser): org.sireum.logika.State.Claim.Def.Random = {
      val r = parser.parse_logikaStateClaimDefRandom()
      return r
    }
    val r = to(s, f_logikaStateClaimDefRandom _)
    return r
  }

  def from_logikaStateClaimLet(o: org.sireum.logika.State.Claim.Let, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLet(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLet(s: String): Either[org.sireum.logika.State.Claim.Let, Json.ErrorMsg] = {
    def f_logikaStateClaimLet(parser: Parser): org.sireum.logika.State.Claim.Let = {
      val r = parser.parse_logikaStateClaimLet()
      return r
    }
    val r = to(s, f_logikaStateClaimLet _)
    return r
  }

  def from_logikaStateClaimLetCurrentName(o: org.sireum.logika.State.Claim.Let.CurrentName, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetCurrentName(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetCurrentName(s: String): Either[org.sireum.logika.State.Claim.Let.CurrentName, Json.ErrorMsg] = {
    def f_logikaStateClaimLetCurrentName(parser: Parser): org.sireum.logika.State.Claim.Let.CurrentName = {
      val r = parser.parse_logikaStateClaimLetCurrentName()
      return r
    }
    val r = to(s, f_logikaStateClaimLetCurrentName _)
    return r
  }

  def from_logikaStateClaimLetName(o: org.sireum.logika.State.Claim.Let.Name, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetName(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetName(s: String): Either[org.sireum.logika.State.Claim.Let.Name, Json.ErrorMsg] = {
    def f_logikaStateClaimLetName(parser: Parser): org.sireum.logika.State.Claim.Let.Name = {
      val r = parser.parse_logikaStateClaimLetName()
      return r
    }
    val r = to(s, f_logikaStateClaimLetName _)
    return r
  }

  def from_logikaStateClaimLetCurrentId(o: org.sireum.logika.State.Claim.Let.CurrentId, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetCurrentId(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetCurrentId(s: String): Either[org.sireum.logika.State.Claim.Let.CurrentId, Json.ErrorMsg] = {
    def f_logikaStateClaimLetCurrentId(parser: Parser): org.sireum.logika.State.Claim.Let.CurrentId = {
      val r = parser.parse_logikaStateClaimLetCurrentId()
      return r
    }
    val r = to(s, f_logikaStateClaimLetCurrentId _)
    return r
  }

  def from_logikaStateClaimLetId(o: org.sireum.logika.State.Claim.Let.Id, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetId(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetId(s: String): Either[org.sireum.logika.State.Claim.Let.Id, Json.ErrorMsg] = {
    def f_logikaStateClaimLetId(parser: Parser): org.sireum.logika.State.Claim.Let.Id = {
      val r = parser.parse_logikaStateClaimLetId()
      return r
    }
    val r = to(s, f_logikaStateClaimLetId _)
    return r
  }

  def from_logikaStateClaimLetEq(o: org.sireum.logika.State.Claim.Let.Eq, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetEq(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetEq(s: String): Either[org.sireum.logika.State.Claim.Let.Eq, Json.ErrorMsg] = {
    def f_logikaStateClaimLetEq(parser: Parser): org.sireum.logika.State.Claim.Let.Eq = {
      val r = parser.parse_logikaStateClaimLetEq()
      return r
    }
    val r = to(s, f_logikaStateClaimLetEq _)
    return r
  }

  def from_logikaStateClaimLetTypeTest(o: org.sireum.logika.State.Claim.Let.TypeTest, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetTypeTest(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetTypeTest(s: String): Either[org.sireum.logika.State.Claim.Let.TypeTest, Json.ErrorMsg] = {
    def f_logikaStateClaimLetTypeTest(parser: Parser): org.sireum.logika.State.Claim.Let.TypeTest = {
      val r = parser.parse_logikaStateClaimLetTypeTest()
      return r
    }
    val r = to(s, f_logikaStateClaimLetTypeTest _)
    return r
  }

  def from_logikaStateClaimLetQuantVar(o: org.sireum.logika.State.Claim.Let.Quant.Var, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetQuantVar(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetQuantVar(s: String): Either[org.sireum.logika.State.Claim.Let.Quant.Var, Json.ErrorMsg] = {
    def f_logikaStateClaimLetQuantVar(parser: Parser): org.sireum.logika.State.Claim.Let.Quant.Var = {
      val r = parser.parse_logikaStateClaimLetQuantVar()
      return r
    }
    val r = to(s, f_logikaStateClaimLetQuantVar _)
    return r
  }

  def from_logikaStateClaimLetQuantVarId(o: org.sireum.logika.State.Claim.Let.Quant.Var.Id, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetQuantVarId(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetQuantVarId(s: String): Either[org.sireum.logika.State.Claim.Let.Quant.Var.Id, Json.ErrorMsg] = {
    def f_logikaStateClaimLetQuantVarId(parser: Parser): org.sireum.logika.State.Claim.Let.Quant.Var.Id = {
      val r = parser.parse_logikaStateClaimLetQuantVarId()
      return r
    }
    val r = to(s, f_logikaStateClaimLetQuantVarId _)
    return r
  }

  def from_logikaStateClaimLetQuantVarSym(o: org.sireum.logika.State.Claim.Let.Quant.Var.Sym, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetQuantVarSym(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetQuantVarSym(s: String): Either[org.sireum.logika.State.Claim.Let.Quant.Var.Sym, Json.ErrorMsg] = {
    def f_logikaStateClaimLetQuantVarSym(parser: Parser): org.sireum.logika.State.Claim.Let.Quant.Var.Sym = {
      val r = parser.parse_logikaStateClaimLetQuantVarSym()
      return r
    }
    val r = to(s, f_logikaStateClaimLetQuantVarSym _)
    return r
  }

  def from_logikaStateClaimLetQuant(o: org.sireum.logika.State.Claim.Let.Quant, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetQuant(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetQuant(s: String): Either[org.sireum.logika.State.Claim.Let.Quant, Json.ErrorMsg] = {
    def f_logikaStateClaimLetQuant(parser: Parser): org.sireum.logika.State.Claim.Let.Quant = {
      val r = parser.parse_logikaStateClaimLetQuant()
      return r
    }
    val r = to(s, f_logikaStateClaimLetQuant _)
    return r
  }

  def from_logikaStateClaimLetIte(o: org.sireum.logika.State.Claim.Let.Ite, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetIte(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetIte(s: String): Either[org.sireum.logika.State.Claim.Let.Ite, Json.ErrorMsg] = {
    def f_logikaStateClaimLetIte(parser: Parser): org.sireum.logika.State.Claim.Let.Ite = {
      val r = parser.parse_logikaStateClaimLetIte()
      return r
    }
    val r = to(s, f_logikaStateClaimLetIte _)
    return r
  }

  def from_logikaStateClaimLetBinary(o: org.sireum.logika.State.Claim.Let.Binary, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetBinary(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetBinary(s: String): Either[org.sireum.logika.State.Claim.Let.Binary, Json.ErrorMsg] = {
    def f_logikaStateClaimLetBinary(parser: Parser): org.sireum.logika.State.Claim.Let.Binary = {
      val r = parser.parse_logikaStateClaimLetBinary()
      return r
    }
    val r = to(s, f_logikaStateClaimLetBinary _)
    return r
  }

  def from_logikaStateClaimLetUnary(o: org.sireum.logika.State.Claim.Let.Unary, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetUnary(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetUnary(s: String): Either[org.sireum.logika.State.Claim.Let.Unary, Json.ErrorMsg] = {
    def f_logikaStateClaimLetUnary(parser: Parser): org.sireum.logika.State.Claim.Let.Unary = {
      val r = parser.parse_logikaStateClaimLetUnary()
      return r
    }
    val r = to(s, f_logikaStateClaimLetUnary _)
    return r
  }

  def from_logikaStateClaimLetSeqLookup(o: org.sireum.logika.State.Claim.Let.SeqLookup, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetSeqLookup(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetSeqLookup(s: String): Either[org.sireum.logika.State.Claim.Let.SeqLookup, Json.ErrorMsg] = {
    def f_logikaStateClaimLetSeqLookup(parser: Parser): org.sireum.logika.State.Claim.Let.SeqLookup = {
      val r = parser.parse_logikaStateClaimLetSeqLookup()
      return r
    }
    val r = to(s, f_logikaStateClaimLetSeqLookup _)
    return r
  }

  def from_logikaStateClaimLetSeqInBound(o: org.sireum.logika.State.Claim.Let.SeqInBound, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetSeqInBound(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetSeqInBound(s: String): Either[org.sireum.logika.State.Claim.Let.SeqInBound, Json.ErrorMsg] = {
    def f_logikaStateClaimLetSeqInBound(parser: Parser): org.sireum.logika.State.Claim.Let.SeqInBound = {
      val r = parser.parse_logikaStateClaimLetSeqInBound()
      return r
    }
    val r = to(s, f_logikaStateClaimLetSeqInBound _)
    return r
  }

  def from_logikaStateClaimLetFieldLookup(o: org.sireum.logika.State.Claim.Let.FieldLookup, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetFieldLookup(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetFieldLookup(s: String): Either[org.sireum.logika.State.Claim.Let.FieldLookup, Json.ErrorMsg] = {
    def f_logikaStateClaimLetFieldLookup(parser: Parser): org.sireum.logika.State.Claim.Let.FieldLookup = {
      val r = parser.parse_logikaStateClaimLetFieldLookup()
      return r
    }
    val r = to(s, f_logikaStateClaimLetFieldLookup _)
    return r
  }

  def from_logikaStateClaimLetProofFunApply(o: org.sireum.logika.State.Claim.Let.ProofFunApply, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetProofFunApply(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetProofFunApply(s: String): Either[org.sireum.logika.State.Claim.Let.ProofFunApply, Json.ErrorMsg] = {
    def f_logikaStateClaimLetProofFunApply(parser: Parser): org.sireum.logika.State.Claim.Let.ProofFunApply = {
      val r = parser.parse_logikaStateClaimLetProofFunApply()
      return r
    }
    val r = to(s, f_logikaStateClaimLetProofFunApply _)
    return r
  }

  def from_logikaStateClaimLetApply(o: org.sireum.logika.State.Claim.Let.Apply, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetApply(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetApply(s: String): Either[org.sireum.logika.State.Claim.Let.Apply, Json.ErrorMsg] = {
    def f_logikaStateClaimLetApply(parser: Parser): org.sireum.logika.State.Claim.Let.Apply = {
      val r = parser.parse_logikaStateClaimLetApply()
      return r
    }
    val r = to(s, f_logikaStateClaimLetApply _)
    return r
  }

  def from_logikaStateClaimLetIApply(o: org.sireum.logika.State.Claim.Let.IApply, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetIApply(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetIApply(s: String): Either[org.sireum.logika.State.Claim.Let.IApply, Json.ErrorMsg] = {
    def f_logikaStateClaimLetIApply(parser: Parser): org.sireum.logika.State.Claim.Let.IApply = {
      val r = parser.parse_logikaStateClaimLetIApply()
      return r
    }
    val r = to(s, f_logikaStateClaimLetIApply _)
    return r
  }

  def from_logikaStateClaimLetTupleLit(o: org.sireum.logika.State.Claim.Let.TupleLit, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetTupleLit(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetTupleLit(s: String): Either[org.sireum.logika.State.Claim.Let.TupleLit, Json.ErrorMsg] = {
    def f_logikaStateClaimLetTupleLit(parser: Parser): org.sireum.logika.State.Claim.Let.TupleLit = {
      val r = parser.parse_logikaStateClaimLetTupleLit()
      return r
    }
    val r = to(s, f_logikaStateClaimLetTupleLit _)
    return r
  }

  def from_logikaStateClaimLetAnd(o: org.sireum.logika.State.Claim.Let.And, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetAnd(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetAnd(s: String): Either[org.sireum.logika.State.Claim.Let.And, Json.ErrorMsg] = {
    def f_logikaStateClaimLetAnd(parser: Parser): org.sireum.logika.State.Claim.Let.And = {
      val r = parser.parse_logikaStateClaimLetAnd()
      return r
    }
    val r = to(s, f_logikaStateClaimLetAnd _)
    return r
  }

  def from_logikaStateClaimLetOr(o: org.sireum.logika.State.Claim.Let.Or, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetOr(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetOr(s: String): Either[org.sireum.logika.State.Claim.Let.Or, Json.ErrorMsg] = {
    def f_logikaStateClaimLetOr(parser: Parser): org.sireum.logika.State.Claim.Let.Or = {
      val r = parser.parse_logikaStateClaimLetOr()
      return r
    }
    val r = to(s, f_logikaStateClaimLetOr _)
    return r
  }

  def from_logikaStateClaimLetImply(o: org.sireum.logika.State.Claim.Let.Imply, isCompact: B): String = {
    val st = Printer.print_logikaStateClaimLetImply(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateClaimLetImply(s: String): Either[org.sireum.logika.State.Claim.Let.Imply, Json.ErrorMsg] = {
    def f_logikaStateClaimLetImply(parser: Parser): org.sireum.logika.State.Claim.Let.Imply = {
      val r = parser.parse_logikaStateClaimLetImply()
      return r
    }
    val r = to(s, f_logikaStateClaimLetImply _)
    return r
  }

  def from_logikaStateProofFun(o: org.sireum.logika.State.ProofFun, isCompact: B): String = {
    val st = Printer.print_logikaStateProofFun(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def to_logikaStateProofFun(s: String): Either[org.sireum.logika.State.ProofFun, Json.ErrorMsg] = {
    def f_logikaStateProofFun(parser: Parser): org.sireum.logika.State.ProofFun = {
      val r = parser.parse_logikaStateProofFun()
      return r
    }
    val r = to(s, f_logikaStateProofFun _)
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

  def fromorgsireumlogikaZ3Config(o: org.sireum.logika.Z3Config, isCompact: B): String = {
    val st = Printer.printorgsireumlogikaZ3Config(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toorgsireumlogikaZ3Config(s: String): Either[org.sireum.logika.Z3Config, Json.ErrorMsg] = {
    def forgsireumlogikaZ3Config(parser: Parser): org.sireum.logika.Z3Config = {
      val r = parser.parseorgsireumlogikaZ3Config()
      return r
    }
    val r = to(s, forgsireumlogikaZ3Config _)
    return r
  }

  def fromorgsireumlogikaCvc4Config(o: org.sireum.logika.Cvc4Config, isCompact: B): String = {
    val st = Printer.printorgsireumlogikaCvc4Config(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toorgsireumlogikaCvc4Config(s: String): Either[org.sireum.logika.Cvc4Config, Json.ErrorMsg] = {
    def forgsireumlogikaCvc4Config(parser: Parser): org.sireum.logika.Cvc4Config = {
      val r = parser.parseorgsireumlogikaCvc4Config()
      return r
    }
    val r = to(s, forgsireumlogikaCvc4Config _)
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