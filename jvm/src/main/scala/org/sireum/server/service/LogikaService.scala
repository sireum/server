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
package org.sireum.server.service

import org.sireum._
import org.sireum.message._
import org.sireum.server.ServerExt
import org.sireum.server.protocol._

import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

object LogikaService {

  class Thread(serverAPI: server.ServerAPI,
               terminated: _root_.java.util.concurrent.atomic.AtomicBoolean) extends _root_.java.lang.Thread {
    override def run(): Unit = {
      while (!terminated.get()) {
        val req = try checkQueue.poll(ServerExt.pauseTime, TimeUnit.MILLISECONDS) catch { case _: InterruptedException => null }
        if (req != null) {
          idMap.put(req.id, this)
          try {
            val reporter = new ReporterImpl(_hint, _smt2query, serverAPI, req.id, ISZ())
            val startTime = extension.Time.currentMillis
            var cancelled = true
            val (hasSireum, compactFirstLine, _) = org.sireum.lang.parser.SlangParser.detectSlang(req.uriOpt, req.content)
            val hasLogika = req.logikaEnabled && hasSireum && compactFirstLine.contains("#Logika")
            try {
              serverAPI.sendRespond(Logika.Verify.Start(req.id, startTime))
              extension.Cancel.handleCancellable { () =>
                checkScript(req, reporter, hasLogika)
                cancelled = false
              }
            } catch {
              case t: Throwable =>
                cancelled = true
                val baos = new ByteArrayOutputStream()
                t.printStackTrace(new java.io.PrintStream(baos))
                serverAPI.sendRespond(Report(req.id, Message(Level.InternalError, None(), "logika",
                  s"""Internal error occurred:
                     |${new Predef.String(baos.toByteArray, "UTF-8")}""".stripMargin)))
            } finally {
              serverAPI.sendRespond(Logika.Verify.End(
                isBackground = req.isBackground,
                id = req.id,
                wasCancelled = cancelled,
                hasLogika = hasLogika,
                isIllFormed = reporter.isIllFormed,
                totalTimeMillis = extension.Time.currentMillis - startTime,
                numOfSmt2Calls = reporter.numOfSmt2Calls,
                smt2TimeMillis = reporter.smt2TimeMillis,
                numOfInternalErrors = reporter.numOfInternalErrors,
                numOfErrors = reporter.numOfErrors,
                numOfWarnings = reporter.numOfWarnings
              ))
            }
          } finally {
            idMap.remove(req.id)
            _root_.java.lang.Thread.interrupted()
          }
        }
      }
    }
  }

  class ScriptCache(val req: Slang.CheckScript,
                    val storage: java.util.Map[(Z, Predef.String), logika.Smt2Query.Result] =
                      new java.util.concurrent.ConcurrentHashMap[(Z, Predef.String), logika.Smt2Query.Result]) extends logika.Smt2Impl.Cache {
    var _owned: Boolean = false
    var _ignore: B = F

    override def $owned: Boolean = _owned

    override def $owned_=(b: Boolean): ScriptCache = {
      _owned = b
      this
    }

    override def $clone: ScriptCache = this

    override def string: String = {
      return "Smt2Cache"
    }

    def get(isSat: B, query: String, timeoutInMs: Z): Option[logika.Smt2Query.Result] = {
      val r = storage.get((timeoutInMs, query.value))
      return if (r == null) None() else Some(r)
    }

    def set(isSat: B, query: String, timeoutInMs: Z, result: logika.Smt2Query.Result): Unit = {
      storage.put((timeoutInMs, query.value), result)
    }
  }

  class ReporterImpl(hint: B, smt2query: B, serverAPI: server.ServerAPI, id: ISZ[String], var _messages: ISZ[Message]) extends logika.Logika.Reporter {
    var _owned: Boolean = false
    var _ignore: B = F
    var isIllFormed: B = F
    var numOfSmt2Calls: Z = 0
    var smt2TimeMillis: Z = 0
    var numOfErrors: Z = 0
    var numOfInternalErrors: Z = 0
    var numOfWarnings: Z = 0

    override def combine(other: logika.Logika.Reporter): logika.Logika.Reporter = {
      other match {
        case other: ReporterImpl =>
          _messages = _messages ++ other._messages
          isIllFormed = isIllFormed || other.isIllFormed
          numOfSmt2Calls = numOfSmt2Calls + other.numOfSmt2Calls
          smt2TimeMillis = smt2TimeMillis + other.smt2TimeMillis
          numOfErrors = numOfErrors + other.numOfErrors
          numOfInternalErrors = numOfInternalErrors + other.numOfInternalErrors
          numOfWarnings = numOfWarnings + other.numOfWarnings
          return this
      }
    }

    override def $owned: Boolean = _owned

    override def $owned_=(b: Boolean): ReporterImpl = {
      _owned = b
      this
    }

    override def $clone: ReporterImpl = {
      val r = new ReporterImpl(hint, smt2query, serverAPI, id, _messages)
      r.isIllFormed = isIllFormed
      r.numOfWarnings = numOfWarnings
      r.numOfErrors = numOfErrors
      r.numOfSmt2Calls = numOfSmt2Calls
      r.smt2TimeMillis = smt2TimeMillis
      r.numOfInternalErrors = numOfInternalErrors
      r
    }

    override def string: String = {
      return "ReporterImpl"
    }

    override def illFormed(): Unit = {
      isIllFormed = T
    }

    override def state(posOpt: Option[Position], s: logika.State): Unit = if (hint) {
      serverAPI.sendRespond(Logika.Verify.State(id, posOpt, s))
    }

    override def inform(pos: Position, kind: org.sireum.logika.Logika.Reporter.Info.Kind.Type, message: String): Unit = {
      val k: Logika.Verify.Info.Kind.Type = kind match {
        case org.sireum.logika.Logika.Reporter.Info.Kind.Verified => Logika.Verify.Info.Kind.Verified
      }
      serverAPI.sendRespond(Logika.Verify.Info(id, pos, k, message))
    }

    override def query(pos: Position, time: Z, r: logika.Smt2Query.Result): Unit = {
      numOfSmt2Calls = numOfSmt2Calls + 1
      smt2TimeMillis = smt2TimeMillis + r.timeMillis
      if (smt2query) {
        serverAPI.sendRespond(Logika.Verify.Smt2Query(id, pos, time, r))
      }
    }

    override def timing(desc: String, timeInMs: Z): Unit = {
      serverAPI.sendRespond(Timing(id, desc, timeInMs))
    }

    override def empty: logika.Logika.Reporter = {
      return new ReporterImpl(hint, smt2query, serverAPI, id, ISZ())
    }

    override def messages: ISZ[Message] = {
      return _messages
    }

    override def ignore: B = {
      return _ignore
    }

    override def setIgnore(newIgnore: B): Unit = {
      _ignore = newIgnore
    }

    override def setMessages(newMessages: ISZ[Message]): Unit = {
      _messages = newMessages
    }

    override def report(m: Message): Unit = {
      if (!ignore) {
        m.level match {
          case Level.InternalError => numOfInternalErrors = numOfInternalErrors + 1
          case Level.Error => numOfErrors = numOfErrors + 1
          case Level.Warning => numOfWarnings = numOfWarnings + 1
          case _ =>
        }
        serverAPI.sendRespond(Report(id, m))
      }
      super.report(m)
    }
  }

  val z3Exe: String = {
    val platform: String = Os.kind match {
      case Os.Kind.Win => "win"
      case Os.Kind.Linux => "linux"
      case Os.Kind.Mac => "mac"
      case _ => "unsupported"
    }
    def z3Path(home: String): Option[Os.Path] = {
      val r = Os.path(home) / "bin" / platform / "z3" / "bin" / (if (Os.isWin) "z3.exe" else "z3")
      if (r.exists) {
        return Some(r.canon)
      }
      return None()
    }
    def h(): String = {
      if (Os.kind == Os.Kind.Unsupported) {
        return "z3"
      }
      Os.env("SIREUM_HOME") match {
        case Some(home) =>
          z3Path(home) match {
            case Some(p) => return p.string
            case _ =>
          }
        case _ =>
      }
      _root_.java.lang.System.getProperty("org.sireum.home") match {
        case home if home != null =>
          z3Path(home) match {
            case Some(p) => return p.string
            case _ =>
          }
        case _ =>
      }
      return "z3"
    }
    h()
  }
  val cvc4Exe: String = {
    val platform: String = Os.kind match {
      case Os.Kind.Win => "win"
      case Os.Kind.Linux => "linux"
      case Os.Kind.Mac => "mac"
      case _ => "unsupported"
    }
    def cvc4Path(home: String): Option[Os.Path] = {
      val r = Os.path(home) / "bin" / platform / (if (Os.isWin) "cvc4.exe" else "cvc4")
      if (r.exists) {
        return Some(r.canon)
      }
      return None()
    }
    def h(): String = {
      if (Os.kind == Os.Kind.Unsupported) {
        return "cvc4"
      }
      Os.env("SIREUM_HOME") match {
        case Some(home) =>
          cvc4Path(home) match {
            case Some(p) => return p.string
            case _ =>
          }
        case _ =>
      }
      _root_.java.lang.System.getProperty("org.sireum.home") match {
        case home if home != null =>
          cvc4Path(home) match {
            case Some(p) => return p.string
            case _ =>
          }
        case _ =>
      }
      return "cvc4"
    }
    h()
  }
  val checkQueue = new _root_.java.util.concurrent.LinkedBlockingQueue[Slang.CheckScript]()
  val idMap = new _root_.java.util.concurrent.ConcurrentHashMap[ISZ[String], Thread]()

  var _defaultConfig: logika.Config = Logika.Verify.defaultConfig
  var _hint: B = T
  var _smt2query: B = T

  def defaultConfig: logika.Config = synchronized {
    return _defaultConfig
  }

  def setConfig(newHint: B, newSmt2Query: B, newConfig: logika.Config): Unit = synchronized {
    _hint = newHint
    _smt2query = newSmt2Query
    _defaultConfig = newConfig
  }

  var scriptCache: ScriptCache = new ScriptCache(Slang.CheckScript(F, F, ISZ(), None(), "", 0))

  def checkScript(req: Slang.CheckScript, reporter: ReporterImpl, hasLogika: Boolean): Unit = {
    if (scriptCache.req.uriOpt != req.uriOpt) {
      scriptCache = new ScriptCache(req)
    }
    val config = defaultConfig
    logika.Logika.checkScript(req.uriOpt, req.content, config, (th: lang.tipe.TypeHierarchy) =>
      logika.Smt2Impl.create(defaultConfig.smt2Configs, th, scriptCache, config.timeoutInMs, config.charBitWidth,
        config.intBitWidth, config.simplifiedQuery, reporter), reporter, !req.isBackground, hasLogika,
      logika.Logika.defaultPlugins, req.line, ISZ(), ISZ())
  }
}

class LogikaService(numOfThreads: Z) extends Service {

  val terminated = new _root_.java.util.concurrent.atomic.AtomicBoolean()
  var threads: ISZ[LogikaService.Thread] = ISZ()
  var _owned: Boolean = false

  override def $owned: Boolean = _owned

  override def $owned_=(b: Boolean): LogikaService = {
    _owned = b
    this
  }

  override def $clone: LogikaService = {
    halt("Unsupported operation")
  }

  @strictpure def id: String = "logika"

  def init(serverAPI: server.ServerAPI): Unit = {
    lang.FrontEnd.checkedLibraryReporter
    terminated.set(false)
    threads = for (i <- z"0" until numOfThreads) yield new LogikaService.Thread(serverAPI, terminated)
    for (t <- threads) {
      t.start()
    }
  }

  def canHandle(req: Request): B = {
    req match {
      case req: Cancel => return LogikaService.idMap.containsKey(req.id)
      case _: Logika.Verify.Config => return T
      case _: Slang.CheckScript => return T
      case _ => return F
    }
  }

  def handle(serverAPI: server.ServerAPI, req: Request): Unit = {
    req match {
      case req: Cancel =>
        LogikaService.idMap.remove(req.id) match {
          case t if t != null => t.interrupt()
          case _ =>
        }
      case req: Logika.Verify.Config =>
        val config: logika.Config =
          if (req.config.smt2Configs.isEmpty) req.config(smt2Configs = LogikaService.defaultConfig.smt2Configs)
          else req.config
        LogikaService.setConfig(req.hint, req.smt2query, config)
      case req: Slang.CheckScript => LogikaService.checkQueue.add(req)
      case _ => halt(s"Infeasible: $req")
    }
  }

  def finalise(): Unit = {
    terminated.set(true)
    for (t <- threads) {
      t.interrupt()
    }
  }

  override def string: String = "LogikaService"
}
