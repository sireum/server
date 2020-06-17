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
package org.sireum.server.service

import org.sireum._
import org.sireum.message._
import org.sireum.server.protocol._

object LogikaService {

  class Thread(terminated: _root_.java.util.concurrent.atomic.AtomicBoolean) extends _root_.java.lang.Thread {
    override def run(): Unit = {
      while (!terminated.get()) {
        _root_.java.lang.Thread.interrupted()
        val req = checkQueue.poll()
        try {
          idMap.put(req.id, this)
          extension.Cancel.handleCancellable(() => checkScript(req))
        } finally {
          idMap.remove(req.id)
        }
      }
    }
  }

  class ReporterImpl(id: String, r: Reporter) extends Reporter {
    var _owned: Boolean = false

    override def $owned: Boolean = _owned

    override def $owned_=(b: Boolean): ReporterImpl = {
      _owned = b
      this
    }

    override def $clone: ReporterImpl = {
      return new ReporterImpl(id, r.$clone.asInstanceOf[Reporter])
    }

    override def string: String = {
      return r.string
    }

    def messages: ISZ[Message] = {
      return r.messages
    }
    def hasInternalError: B = {
      return r.hasInternalError
    }
    def hasError: B = {
      return r.hasError
    }
    def hasWarning: B = {
      return r.hasWarning
    }
    def hasIssue: B = {
      return r.hasIssue
    }
    def hasInfo: B = {
      return r.hasInfo
    }
    def hasMessage: B = {
      return r.hasMessage
    }
    def internalErrors: ISZ[Message] = {
      return r.internalErrors
    }
    def errors: ISZ[Message] = {
      return r.errors
    }
    def warnings: ISZ[Message] = {
      return r.warnings
    }
    def issues: ISZ[Message] = {
      return r.issues
    }
    def infos: ISZ[Message] = {
      return r.infos
    }
    def report(m: Message): Unit = {
      val resp = ReportId(id, m)
      server.Server.Ext.writeOutput(CustomMessagePack.fromResponse(resp))
      r.report(m)
    }
    def messagesByFileUri: HashSMap[Option[String], ISZ[Message]] = {
      return r.messagesByFileUri
    }
    def printMessages(): Unit = {
      r.printMessages()
    }
    def internalError(posOpt: Option[Position], kind: String, message: String): Unit = {
      r.internalError(posOpt, kind, message)
    }
    def error(posOpt: Option[Position], kind: String, message: String): Unit = {
      r.error(posOpt, kind, message)
    }
    def warn(posOpt: Option[Position], kind: String, message: String): Unit = {
      r.warn(posOpt, kind, message)
    }
    def info(posOpt: Option[Position], kind: String, message: String): Unit = {
      r.info(posOpt, kind, message)
    }
    def reports(ms: ISZ[Message]): Unit = {
      r.reports(ms)
    }
    def setIgnore(newIgnore: B): Unit = {
      r.setIgnore(newIgnore)
    }
  }

  val z3Exe: String = {
    val platform: String = Os.kind match {
      case Os.Kind.Win => "win"
      case Os.Kind.Linux => "linux"
      case Os.Kind.Mac => "mac"
      case Os.Kind.Unsupported => "unsupported"
    }
    def z3Path(home: String): Option[Os.Path] = {
      val r = (Os.path(home) / "bin" / platform / "z3" / "bin" / (if (Os.isWin) "z3.exe" else "z3"))
      if (r.exists) {
        return Some(r)
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
  val checkQueue = new _root_.java.util.concurrent.LinkedBlockingQueue[Slang.Check.Script.Start]()
  val idMap = new _root_.java.util.concurrent.ConcurrentHashMap[String, Thread]()

  var _defaultConfig: logika.Config = Logika.Verify.defaultConfig
  def defaultConfig: logika.Config = synchronized {
    return _defaultConfig
  }

  def defaultConfig_=(newConfig: logika.Config): Unit = synchronized {
    _defaultConfig = newConfig
  }

  def checkScript(req: Slang.Check.Script.Start): Unit = {
    val reporter = Reporter.create
    val config = defaultConfig
    logika.Logika.checkWorksheet(req.uriOpt, req.content, config, (th: lang.tipe.TypeHierarchy) =>
      logika.Smt2Impl(z3Exe, logika.Smt2Impl.z3ArgF _, th, config.charBitWidth, config.intBitWidth), reporter)
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

  def init(): Unit = {
    terminated.set(false)
    threads = for (i <- z"0" until numOfThreads) yield new LogikaService.Thread(terminated)
  }

  def canHandle(req: Request): B = {
    req match {
      case req: Cancel => return LogikaService.idMap.containsKey(req.id)
      case _: Logika.Verify.Config => return T
      case req: Slang.Check.Script.Start =>
        val it = req.content.value.linesIterator
        if (!it.hasNext) {
          return F
        }
        return it.next().replaceAllLiterally(" ", "").replaceAllLiterally("\t", "").contains("#Logika")
      case _ => return F
    }
  }

  def handle(req: Request): Unit = {
    req match {
      case req: Cancel =>
        LogikaService.idMap.remove(req.id) match {
          case t if t != null => t.interrupt()
          case _ =>
        }
      case req: Logika.Verify.Config => LogikaService.defaultConfig = req.config
      case req: Slang.Check.Script.Start => LogikaService.checkQueue.add(req)
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
