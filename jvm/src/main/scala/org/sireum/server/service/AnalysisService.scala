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
import org.sireum.logika.{CvcConfig, Smt2Config, Z3Config}
import org.sireum.message._
import org.sireum.project.{DependencyManager, Project}
import org.sireum.proyek.{Analysis, Proyek}
import org.sireum.server.ServerExt
import org.sireum.server.protocol._

import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

object AnalysisService {

  class Thread(serverAPI: server.ServerAPI,
               terminated: _root_.java.util.concurrent.atomic.AtomicBoolean) extends _root_.java.lang.Thread {
    override def run(): Unit = {
      def check(req: Slang.Check, f: ReporterImpl => (B, B)): Unit = {
        idMap.put(req.id, this)
        val outputDirOpt: Option[Os.Path] = req match {
          case req: Slang.Check.Project =>
            val p = Os.path(req.proyek) / "out" / "logika"
            (p / "smt2").mkdirAll()
            val dirs = (p / "smt2").list
            val max = 5
            if (dirs.size >= max) {
              val sdirs = ops.ISZOps(dirs).sortWith((p1: Os.Path, p2: Os.Path) => p1.lastModified < p2.lastModified)
              for (i <- 0 until dirs.size - max + 1) {
                sdirs(i).removeAll()
              }
            }
            Some(Os.path(req.proyek) / "out" / "logika")
          case _ => None()
        }
        val reporter = new ReporterImpl(_hint, _smt2query, serverAPI, req.id, outputDirOpt)
        var cancelled = false
        var hasLogika = false
        val startTime = extension.Time.currentMillis
        serverAPI.sendRespond(Logika.Verify.Start(req.id, startTime))
        try {
          val p = f(reporter)
          hasLogika = p._1
          cancelled = p._2
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
          serverAPI.reportStatus()
        }
      }

      while (!terminated.get()) {
        val req = try checkQueue.poll(ServerExt.pauseTime, TimeUnit.MILLISECONDS) catch {
          case _: InterruptedException => null
        }
        if (req != null) {
          try {
            req match {
              case req: Slang.Check.Script =>
                check(req, (reporter: ReporterImpl) => {
                  val (hasSireum, compactFirstLine, _) = org.sireum.lang.parser.SlangParser.detectSlang(req.uriOpt, req.content)
                  val hasLogika = req.uriOpt.map(_.value.endsWith(".sc")).getOrElseEager(T) &&
                    req.logikaEnabled && hasSireum && compactFirstLine.contains("#Logika")
                  var cancelled = true
                  extension.Cancel.handleCancellable { () =>
                    checkScript(req, reporter, hasLogika)
                    cancelled = false
                  }
                  (hasLogika, cancelled)
                })
              case req: Slang.Check.Project =>
                check(req, (reporter: ReporterImpl) => {
                  var cancelled = true
                  extension.Cancel.handleCancellable { () =>
                    checkProgram(req, reporter)
                    cancelled = false
                  }
                  (req.vfiles.nonEmpty, cancelled)
                })
            }
          } finally {
            idMap.remove(req.id)
            _root_.java.lang.Thread.interrupted()
          }
        }
      }
    }

    def checkProgram(req: Slang.Check.Project, reporter: ReporterImpl): Unit = {
      val key = req.proyek.value.intern
      val root = org.sireum.Os.path(key)
      var cache = proyekCache.get(key)
      if (cache == null) {
        Proyek.getProject(serverAPI.sireumHome, root, None(), None()) match {
          case Some(prj) =>
            Proyek.getVersions(prj, root, ISZ(), serverAPI.defaultVersions) match {
              case Some(versions) =>
                val dm = DependencyManager(
                  project = prj,
                  versions = versions,
                  isJs = F,
                  withSource = F,
                  withDoc = F,
                  javaHome = serverAPI.javaHome,
                  scalaHome = serverAPI.scalaHome,
                  sireumHome = serverAPI.sireumHome,
                  cacheOpt = None()
                )
                cache = createCache(org.sireum.Some(org.sireum.Os.path(key).toUri), prj, Some(dm))
              case _ =>
                reporter.error(None(), "LogikaService", s"Could not load versions from ${root / "versions.properties"}")
                return
            }
          case _ =>
            reporter.error(None(), "LogikaService", s"Could not load project from ${root / "bin" / "project.cmd"}")
            return
        }
        proyekCache.put(key, cache)
      }
      val mapBox = MBox2(cache.uriMap, cache.thMap)
      Analysis.run(
        root = root,
        project = cache.project,
        dm = cache.dmOpt.get,
        cacheInput = serverAPI.cacheInput,
        cacheTypeHierarchy = T,
        mapBox = mapBox,
        config = defaultConfig,
        cache = cache,
        files = req.files,
        vfiles = req.vfiles,
        line = req.line,
        par = req.par,
        strictAliasing = T,
        followSymLink = F,
        all = F,
        verify = req.vfiles.nonEmpty,
        disableOutput = F,
        verbose = F,
        sanityCheck = F,
        plugins = logika.Logika.defaultPlugins,
        skipMethods = ISZ(),
        skipTypes = ISZ(),
        reporter = reporter
      )
      cache.uriMap = mapBox.value1
      cache.thMap = mapBox.value2
    }
    System.gc()
  }

  class FileCache(val uriOpt: Option[String],
                  val project: Project,
                  val dmOpt: Option[DependencyManager],
                  var uriMap: HashMap[String, HashMap[String, lang.FrontEnd.Input]],
                  var thMap: HashMap[String, lang.tipe.TypeHierarchy],
                  val storage: java.util.Map[(Z, Predef.String), logika.Smt2Query.Result] =
                  new java.util.concurrent.ConcurrentHashMap[(Z, Predef.String), logika.Smt2Query.Result]) extends logika.Smt2.Cache {
    var _owned: Boolean = false
    var _ignore: B = F

    override def $owned: Boolean = _owned

    override def $owned_=(b: Boolean): FileCache = {
      _owned = b
      this
    }

    override def $clone: FileCache = this

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

  class ReporterImpl(hint: B,
                     smt2query: B,
                     serverAPI: server.ServerAPI,
                     id: ISZ[String],
                     outputDirOpt: Option[Os.Path],
                     val _messages: _root_.java.util.concurrent.ConcurrentLinkedQueue[Message] =
                     new _root_.java.util.concurrent.ConcurrentLinkedQueue) extends logika.Logika.Reporter {
    import org.sireum.$internal.CollectionCompat.Converters._

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
          _messages.addAll(other._messages)
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
      val r = new ReporterImpl(hint, smt2query, serverAPI, id, outputDirOpt, _messages)
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

    override def query(pos: Position, title: String, time: Z, r: logika.Smt2Query.Result): Unit = {
      numOfSmt2Calls = numOfSmt2Calls + 1
      smt2TimeMillis = smt2TimeMillis + r.timeMillis
      if (smt2query) {
        val query: String = outputDirOpt match {
          case Some(outputDir) =>
            val d = outputDir / "smt2" / st"${(id, "-")}".render
            d.mkdirAll()
            @strictpure def replaceChar(c: C): C =
              if (('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || (c === '.')) c else '-'
            val filename = s"$title-${extension.Time.currentMillis}.smt2"
            val f = d / conversions.String.fromCis(for (c <- conversions.String.toCis(filename)) yield replaceChar(c))
            f.writeOver(r.query)
            f.canon.string
          case _ => r.query
        }
        serverAPI.sendRespond(Logika.Verify.Smt2Query(id, pos, time, title, r.kind, r.solverName, query, r.info, r.output))
      }
    }

    override def timing(desc: String, timeInMs: Z): Unit = {
      serverAPI.sendRespond(Timing(id, desc, timeInMs))
    }

    override def empty: logika.Logika.Reporter = {
      return new ReporterImpl(hint, smt2query, serverAPI, id, outputDirOpt)
    }

    override def messages: ISZ[Message] = {
      return ISZ(_messages.asScala.toSeq: _*)
    }

    override def ignore: B = {
      return _ignore
    }

    override def setIgnore(newIgnore: B): Unit = {
      _ignore = newIgnore
    }

    override def setMessages(newMessages: ISZ[Message]): Unit = {
      _messages.clear()
      for (m <- newMessages) {
        _messages.add(m)
      }
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
        _messages.add(m)
      }
    }

    override def hasInternalError: B = {
      for (m <- _messages.iterator.asScala) {
        m.level match {
          case Level.InternalError => return T
          case _ =>
        }
      }
      return F
    }

    override def hasError: B = {
      for (m <- _messages.iterator.asScala if m.isError || m.isInternalError) {
        return T
      }
      return F
    }

    override def hasWarning: B = {
      for (m <- _messages.iterator.asScala if m.isWarning) {
        return T
      }
      return F
    }

    override def hasIssue: B = {
      for (m <- _messages.iterator.asScala if m.isError || m.isWarning || m.isInternalError) {
        return T
      }
      return F
    }

    override def hasInfo: B = {
      for (m <- _messages.iterator.asScala if m.isInfo) {
        return T
      }
      return F
    }

    override def hasMessage: B = {
      return !_messages.isEmpty
    }
  }

  val checkQueue = new _root_.java.util.concurrent.LinkedBlockingQueue[Slang.Check]()
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

  def createCache(uriOpt: Option[String], project: Project = Project.empty, dmOpt: Option[DependencyManager] = None()): FileCache =
    new FileCache(uriOpt, project, dmOpt, org.sireum.HashMap.empty, org.sireum.HashMap.empty)

  var scriptCache: FileCache = createCache(None())
  val proyekCache: _root_.java.util.concurrent.ConcurrentHashMap[Predef.String, FileCache] = new _root_.java.util.concurrent.ConcurrentHashMap

  def checkScript(req: Slang.Check.Script, reporter: ReporterImpl, hasLogika: Boolean): Unit = {
    if (scriptCache.uriOpt != req.uriOpt) {
      scriptCache = createCache(req.uriOpt)
    }
    val config = defaultConfig
    logika.Logika.checkScript(req.uriOpt, req.content, config, (th: lang.tipe.TypeHierarchy) =>
      logika.Smt2Impl.create(defaultConfig.smt2Configs, th, config.timeoutInMs, config.cvc4RLimit,
        config.fpRoundingMode, config.charBitWidth, config.intBitWidth, config.useReal, config.simplifiedQuery,
        reporter), scriptCache, reporter, req.par, hasLogika, logika.Logika.defaultPlugins, req.line, ISZ(), ISZ())
    System.gc()
  }
}

class AnalysisService(numOfThreads: Z) extends Service {

  val terminated = new _root_.java.util.concurrent.atomic.AtomicBoolean()
  var threads: ISZ[AnalysisService.Thread] = ISZ()
  var _owned: Boolean = false

  override def $owned: Boolean = _owned

  override def $owned_=(b: Boolean): AnalysisService = {
    _owned = b
    this
  }

  override def $clone: AnalysisService = {
    halt("Unsupported operation")
  }

  @strictpure def id: String = "logika"

  def init(serverAPI: server.ServerAPI): Unit = {
    lang.FrontEnd.checkedLibraryReporter
    terminated.set(false)
    threads = for (_ <- z"0" until numOfThreads) yield new AnalysisService.Thread(serverAPI, terminated)
    for (t <- threads) {
      t.start()
    }
  }

  def canHandle(req: Request): B = {
    req match {
      case req: Cancel => return AnalysisService.idMap.containsKey(req.id)
      case _: Logika.Verify.Config => return T
      case _: Slang.Check => return T
      case _ => return F
    }
  }

  def handle(serverAPI: server.ServerAPI, req: Request): Unit = {
    req match {
      case req: Cancel =>
        AnalysisService.idMap.remove(req.id) match {
          case t if t != null => t.interrupt()
          case _ =>
        }
      case req: Logika.Verify.Config =>
        val smt2Configs: ISZ[Smt2Config] =
          if (req.config.smt2Configs.isEmpty) AnalysisService.defaultConfig.smt2Configs
          else req.config.smt2Configs
        AnalysisService.setConfig(req.hint, req.smt2query, req.config(smt2Configs =
          for (c <- smt2Configs) yield c match {
            case c: CvcConfig => c(exe = ServerExt.cvcExe(serverAPI.sireumHome).string)
            case c: Z3Config => c(exe = ServerExt.z3Exe(serverAPI.sireumHome).string)
          }))
      case req: Slang.Check => AnalysisService.checkQueue.add(req)
      case _ => halt(s"Infeasible: $req")
    }
  }

  def finalise(): Unit = {
    terminated.set(true)
    for (t <- threads) {
      t.interrupt()
    }
  }

  override def string: String = "AnalysisService"
}
