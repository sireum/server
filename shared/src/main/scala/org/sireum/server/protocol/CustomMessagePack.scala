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

import org.sireum.S8._
import org.sireum.U8._
import org.sireum._
import org.sireum.lang.symbol._
import org.sireum.lang.{ast => AST}

/*
Pools:
org.sireum.lang.ast.Typed
 */
object CustomMessagePack {

  val TypedPoolExtType: S8 = MessagePack.LastExtType + s8"1"

  @record class Reader(val reader: MessagePack.Reader.Impl) extends MsgPack.Reader {

    var typedPool: MSZ[AST.Typed] = MSZ()

    def errorOpt: Option[MessagePack.ErrorMsg] = {
      return reader.errorOpt
    }

    def init(): Unit = {
      reader.initialized = T
      reader.pooling = F
      var pOpt = reader.readExtTypeHeader()
      pOpt match {
        case Some((t, size)) =>
          assert(t == MessagePack.StringPoolExtType)
          reader.stringPool = MSZ.create(size, "")
          var i = 0
          while (i < size) {
            val s = reader.readStringNoPool()
            reader.stringPool(i) = s
            i = i + 1
          }
        case _ =>
      }
      pOpt = reader.readExtTypeHeader()
      pOpt match {
        case Some((t, size)) =>
          assert(t == MessagePack.DocInfoExtType)
          reader.docInfoPool = MSZ.create(size, message.DocInfo(None(), ISZ()))
          var i = 0
          while (i < size) {
            val docInfo = reader.readDocInfoNoPool()
            reader.docInfoPool(i) = docInfo
            i = i + 1
          }
        case _ =>
      }
      pOpt = reader.readExtTypeHeader()
      pOpt match {
        case Some((t, size)) =>
          assert(t == TypedPoolExtType)
          typedPool = MSZ.create(size, AST.Typed.Tuple(ISZ()))
          var i = 0
          while (i < size) {
            val typed = super.read_langastTyped()
            typedPool(i) = typed
            i = i + 1
          }
        case _ =>
      }
      reader.pooling = T
    }

    override def read_langastTyped(): AST.Typed = {
      if (reader.pooling) {
        val n = reader.readZ()
        return typedPool(n)
      } else {
        val r = super.read_langastTyped()
        return r
      }
    }

    override def read_langastTypedFun(): AST.Typed.Fun = {
      val o = read_langastTyped()
      o match {
        case o: AST.Typed.Fun => return o
        case _ =>
          if (errorOpt.nonEmpty) {
            return AST.Typed.Fun(F, F, ISZ(), AST.Typed.unit)
          } else {
            halt("Unexpected situation when reading AST.Typed.Fun MessagePack")
          }
      }
    }

  }

  @record class Writer(val writer: MessagePack.Writer.Impl) extends MsgPack.Writer {

    var typedPool: HashSMap[AST.Typed, Z] = HashSMap.emptyInit(1024)

    override def write_langastTyped(o: AST.Typed): Unit = {
      val n: Z = typedPool.get(o) match {
        case Some(m) => m
        case _ =>
          val m = typedPool.size
          typedPool = typedPool + o ~> m
          m
      }
      writer.writeZ(n)
    }

    override def write_langastTypedFun(o: AST.Typed.Fun): Unit = {
      write_langastTyped(o)
    }

    override def result: ISZ[U8] = {
      val strings = writer.stringPool.keys
      val poolBufferSize: Z = {
        var r: Z = 0
        for (s <- strings) {
          r = r + s.size * 2
        }
        r + 4
      }
      val (poolBuf, poolBufSize): (MSZ[U8], Z) = {
        val r = MsgPack.Writer.Default(MessagePack.Writer.Impl(F, MSZ.create(poolBufferSize, u8"0"), 0))
        r.writer.writeExtTypeHeader(MessagePack.StringPoolExtType, strings.size)
        for (s <- strings) {
          r.writer.writeStringNoPool(s)
        }
        r.writer.writeExtTypeHeader(MessagePack.DocInfoExtType, writer.docInfoPool.size)
        for (di <- writer.docInfoPool.keys) {
          r.writer.writeDocInfoNoPool(di)
        }
        r.writer.writeExtTypeHeader(TypedPoolExtType, typedPool.size)
        for (t <- typedPool.keys) {
          r.write_langastTyped(t)
        }
        (r.writer.buf, r.writer.size)
      }

      val r = MSZ.create(poolBufSize + writer.size, u8"0")
      var i = 0
      while (i < poolBufSize) {
        r(i) = poolBuf(i)
        i = i + 1
      }
      i = 0
      while (i < writer.size) {
        r(i + poolBufSize) = writer.buf(i)
        i = i + 1
      }
      return r.toIS
    }
  }

  def fromRequest(o: Request): String = {
    val writer = Writer(MessagePack.Writer.Impl(T, MS.create(1024, u8"0"), 0))
    writer.writeRequest(o)
    val r = writer.result
    return conversions.String.toBase64(r)
  }

  def toRequest(base64: String): Either[Request, String] = {
    conversions.String.fromBase64(base64) match {
      case Either.Left(data) =>
        val reader = Reader(MessagePack.Reader.Impl(data, 0))
        reader.init()
        val r = reader.readRequest()
        reader.reader.errorOpt match {
          case Some(err) => return Either.Right(err.message)
          case _ => return Either.Left(r)
        }
      case Either.Right(msg) => return Either.Right(msg)
    }
  }

  def fromResponse(o: Response): String = {
    val writer = Writer(MessagePack.Writer.Impl(T, MS.create(1024, u8"0"), 0))
    writer.writeResponse(o)
    val r = writer.result
    return conversions.String.toBase64(r)
  }

  def toResponse(base64: String): Either[Response, String] = {
    conversions.String.fromBase64(base64) match {
      case Either.Left(data) =>
        val reader = Reader(MessagePack.Reader.Impl(data, 0))
        reader.init()
        val r = reader.readResponse()
        reader.reader.errorOpt match {
          case Some(err) => return Either.Right(err.message)
          case _ => return Either.Left(r)
        }
      case Either.Right(msg) => return Either.Right(msg)
    }
  }
}
