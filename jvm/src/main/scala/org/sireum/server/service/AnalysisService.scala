/*
 Copyright (c) 2017-2023, Robby, Kansas State University
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
import org.sireum.lang.tipe.TypeHierarchy
import org.sireum.logika.{Smt2Config, Smt2Invoke}
import org.sireum.message._
import org.sireum.project.{DependencyManager, Project}
import org.sireum.proyek.{Analysis, Proyek}
import org.sireum.server.ServerExt
import org.sireum.server.protocol.Analysis.Cache
import org.sireum.server.protocol._
import org.sireum.server.service.AnalysisService.{proyekCache, scriptCache}

import java.io.ByteArrayOutputStream
import java.lang.ref.SoftReference
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
        serverAPI.sendRespond(server.protocol.Analysis.Start(req.id, startTime))
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
          serverAPI.sendRespond(server.protocol.Analysis.End(
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
                    req.logikaEnabled && hasSireum && (compactFirstLine.contains("#Logika") || defaultConfig.interp)
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
        outDirName = "out",
        project = cache.project,
        dm = cache.dmOpt.get,
        cacheInput = serverAPI.cacheInput,
        cacheTypeHierarchy = serverAPI.cacheType,
        mapBox = mapBox,
        config = defaultConfig,
        cache = if (defaultConfig.caching) cache else logika.NoTransitionSmt2Cache.create,
        files = req.files,
        filesWatched = T,
        vfiles = req.vfiles,
        line = req.line,
        par = defaultConfig.parCores,
        strictAliasing = T,
        followSymLink = F,
        all = F,
        verify = req.vfiles.nonEmpty,
        disableOutput = F,
        verbose = serverAPI.isVerbose,
        sanityCheck = F,
        plugins = logika.Logika.defaultPlugins ++
          (if (_infoFlow) logika.infoflow.InfoFlowPlugins.defaultPlugins else ISZ[logika.plugin.Plugin]()),
        skipMethods = ISZ(),
        skipTypes = ISZ(),
        reporter = reporter
      )
      cache.uriMap = mapBox.value1
      cache.thMap = mapBox.value2
      cache.clearTaskCache()
    }
    System.gc()
  }

  final class FileCache(val uriOpt: Option[String],
                        val project: Project,
                        val dmOpt: Option[DependencyManager],
                        val persistentCache: java.util.concurrent.ConcurrentHashMap[logika.Logika.Cache.Key, logika.Logika.Cache.Value],
                        val taskCache: java.util.concurrent.ConcurrentHashMap[logika.Logika.Cache.Key, logika.Logika.Cache.Value],
                        var uriMap: HashMap[String, HashMap[String, lang.FrontEnd.Input]],
                        var thMap: HashMap[String, lang.tipe.TypeHierarchy],
                        val transitionCache: java.util.Map[(Long, logika.Logika.Cache.Transition, logika.State), SoftReference[(ISZ[logika.State], logika.Smt2)]] =
                        new java.util.concurrent.ConcurrentHashMap,
                        val smt2Cache: java.util.Map[(ISZ[String], Predef.String), SoftReference[logika.Smt2Query.Result]] =
                        new java.util.concurrent.ConcurrentHashMap) extends logika.CacheProperties {

    private var isOwned: scala.Boolean = false

    override def $clonable: Boolean = false

    override def $clonable_=(b: Boolean): this.type = halt("Cannot update FileCache clonable")

    override def $owned: scala.Boolean = isOwned

    override def $owned_=(b: scala.Boolean): this.type = {
      isOwned = b
      this
    }

    override def $clone: this.type = halt("Cannot clone FileCache")

    override def string: String = "FileCache"

    def clearTransition(): Unit = {
      transitionCache.clear()
    }

    def getTransitionAndUpdateSmt2(th: TypeHierarchy, transition: logika.Logika.Cache.Transition,
                                   state: logika.State, smt2: logika.Smt2): Option[ISZ[logika.State]] = {
      val key = (th.fingerprint.value, transition, state)
      val rRef = transitionCache.get(key)
      var r = Option.none[ISZ[logika.State]]()
      if (rRef != null) {
        if (rRef.get != null) {
          val (ss, csmt2) = rRef.get
          smt2.updateFrom(csmt2)
          r = Some(ss)
        } else {
          transitionCache.remove(key)
        }
      }
      return r
    }

    def setTransition(th: TypeHierarchy, transition: logika.Logika.Cache.Transition, state: logika.State,
                      nextStates: ISZ[logika.State], smt2: logika.Smt2): Unit = {
      transitionCache.put((th.fingerprint.value, transition, state), new SoftReference((nextStates, smt2.minimize)))
    }

    def getSmt2(isSat: B, query: String, args: ISZ[String]): Option[logika.Smt2Query.Result] = {
      val key = (args, query.value)
      val rRef = smt2Cache.get(key)
      var r = Option.none[logika.Smt2Query.Result]()
      if (rRef != null) {
        if (rRef.get != null) {
          r = Some(rRef.get)
        } else {
          smt2Cache.remove(key)
        }
      }
      return r
    }

    def setSmt2(isSat: B, query: String, args: ISZ[String], result: logika.Smt2Query.Result): Unit = {
      smt2Cache.put((args, query.value), new SoftReference(result))
    }

    def clearTaskCache(): Unit = {
      taskCache.clear()
    }

  }

  final class ReporterImpl(hint: B,
                           smt2query: B,
                           serverAPI: server.ServerAPI,
                           id: ISZ[String],
                           outputDirOpt: Option[Os.Path],
                           val _messages: _root_.java.util.concurrent.ConcurrentLinkedQueue[Message] =
                           new _root_.java.util.concurrent.ConcurrentLinkedQueue) extends logika.Logika.Reporter {
    import org.sireum.$internal.CollectionCompat.Converters._

    private var isClonable: scala.Boolean = true
    private var isOwned: scala.Boolean = false
    var _ignore: B = F
    var isIllFormed: B = F
    var numOfSmt2Calls: Z = 0
    var smt2TimeMillis: Z = 0
    var numOfErrors: Z = 0
    var numOfInternalErrors: Z = 0
    var numOfWarnings: Z = 0

    override def $clonable: Boolean = isClonable

    override def $clonable_=(b: Boolean): this.type = {
      isClonable = false
      this
    }

    override def $owned: scala.Boolean = isOwned

    override def $owned_=(b: scala.Boolean): this.type = {
      isOwned = b
      this
    }

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

    override def state(plugins: ISZ[logika.plugin.ClaimPlugin], posOpt: Option[Position], context: ISZ[String],
                       th: TypeHierarchy, s: logika.State, atLinesFresh: B): Unit = if (hint) {
      var claims: String = ""
      var err: String = ""
      posOpt match {
        case Some(pos) =>
          try {
            val (es, _) = logika.Util.claimsToExps(plugins, pos, context, s.claims, th, atLinesFresh)
            claims =
              st"""{
                  |  ${(for (e <- es) yield e.prettyST, ";\n")}
                  |}""".render
          } catch {
            case e: Throwable =>
              val baos = new java.io.ByteArrayOutputStream
              e.printStackTrace(new java.io.PrintStream(baos))
              err = new java.lang.String(baos.toByteArray, "UTF-8")
              server.Server.Ext.log(serverAPI.logFile, err)
              baos.close()
          }
        case _ =>
      }
      if (claims.size == 0) {
        val sts = logika.State.Claim.claimsSTs(s.claims, logika.Util.ClaimDefs.empty)
        claims =
          st"""{
               |  ${(sts, ",\n")}
               |}
               |
               |/* Error occurred when rendering claims:
               |$err
               |*/""".render
      }
      var found = F
      var labels = ISZ[String]()
      for (claim <- s.claims if !found) {
        claim match {
          case claim: logika.State.Claim.Label => labels = labels :+ claim.label; found = T
          case _ =>
        }
      }
      serverAPI.sendRespond(Logika.Verify.State(id, posOpt, !s.ok, labels, claims))
    }

    override def inform(pos: Position, kind: org.sireum.logika.Logika.Reporter.Info.Kind.Type, message: String): Unit = {
      val k: Logika.Verify.Info.Kind.Type = kind match {
        case org.sireum.logika.Logika.Reporter.Info.Kind.Verified => Logika.Verify.Info.Kind.Verified
      }
      serverAPI.sendRespond(Logika.Verify.Info(id, pos, k, message))
    }

    override def query(pos: Position, title: String, time: Z, forceReport: B, detailElided: B,
                       r: logika.Smt2Query.Result): Unit = {
      numOfSmt2Calls = numOfSmt2Calls + 1
      smt2TimeMillis = smt2TimeMillis + r.timeMillis
      if (smt2query || forceReport) {
        val query: String = outputDirOpt match {
          case Some(outputDir) if !detailElided =>
            val d = outputDir / "smt2" / st"${(id, "-")}".render
            d.mkdirAll()
            @strictpure def replaceChar(c: C): C =
              if (('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || (c.value == '.')) c else '-'
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

    override def coverage(cached: B, pos: Position): Unit = {
      serverAPI.sendRespond(server.protocol.Analysis.Coverage(id, cached, pos))
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
  var _infoFlow: B = T

  def defaultConfig: logika.Config = synchronized {
    return _defaultConfig
  }

  def setConfig(newHint: B, newSmt2Query: B,  newInfoFlow: B, newConfig: logika.Config): Unit = synchronized {
    _hint = newHint
    _smt2query = newSmt2Query
    _infoFlow = newInfoFlow
    _defaultConfig = newConfig(transitionCache = newConfig.transitionCache && !newConfig.interp)
  }

  def createCache(uriOpt: Option[String], project: Project = Project.empty, dmOpt: Option[DependencyManager] = None()): FileCache =
    new FileCache(uriOpt, project, dmOpt, new java.util.concurrent.ConcurrentHashMap,
      new java.util.concurrent.ConcurrentHashMap, org.sireum.HashMap.empty, org.sireum.HashMap.empty)

  var scriptCache: FileCache = createCache(None())
  val proyekCache: _root_.java.util.concurrent.ConcurrentHashMap[Predef.String, FileCache] = new _root_.java.util.concurrent.ConcurrentHashMap

  def checkScript(req: Slang.Check.Script, reporter: ReporterImpl, hasLogika: Boolean): Unit = {
    if (scriptCache.uriOpt != req.uriOpt) {
      scriptCache = createCache(req.uriOpt)
    }
    val config = defaultConfig
    if (!config.transitionCache) {
      scriptCache.clearTransition()
    }
    val plugins = logika.Logika.defaultPlugins ++
      (if (_infoFlow) logika.infoflow.InfoFlowPlugins.defaultPlugins else ISZ[logika.plugin.Plugin]())
    logika.Logika.checkScript(req.uriOpt, req.content, config, (th: lang.tipe.TypeHierarchy) =>
      logika.Smt2Impl.create(defaultConfig.smt2Configs, logika.plugin.Plugin.claimPlugins(plugins), th,
        config.timeoutInMs, config.fpRoundingMode, config.charBitWidth, config.intBitWidth, config.useReal,
        config.simplifiedQuery, config.smt2Seq, config.rawInscription, config.elideEncoding, config.atLinesFresh,
        reporter),
      if (config.caching) scriptCache else logika.NoTransitionSmt2Cache.create,
      reporter, hasLogika, plugins, req.line, ISZ(), ISZ())
    scriptCache.clearTaskCache()
    System.gc()
  }
}

final class AnalysisService(numOfThreads: Z) extends Service {

  val terminated = new _root_.java.util.concurrent.atomic.AtomicBoolean()
  var threads: ISZ[AnalysisService.Thread] = ISZ()
  var nameExePathMap: HashMap[String, String] = _
  private var isClonable: scala.Boolean = true
  private var isOwned: scala.Boolean = false

  override def $clonable: Boolean = isClonable

  override def $clonable_=(b: Boolean): this.type = {
    isClonable = false
    this
  }

  override def $owned: scala.Boolean = isOwned

  override def $owned_=(b: scala.Boolean): this.type = {
    isOwned = b
    this
  }

  override def $clone: AnalysisService = {
    halt("Unsupported operation")
  }

  @strictpure def id: String = "analysis"

  def init(serverAPI: server.ServerAPI): Unit = {
    lang.FrontEnd.checkedLibraryReporter
    terminated.set(false)
    threads = for (_ <- z"0" until numOfThreads) yield {
      val t = new AnalysisService.Thread(serverAPI, terminated)
      t.setDaemon(true)
      t
    }
    for (t <- threads) {
      t.start()
    }
  }

  def canHandle(req: Request): B = {
    req match {
      case req: Cancel =>
        return AnalysisService.checkQueue.removeIf(_.id == req.id) || AnalysisService.idMap.containsKey(req.id)
      case _: Logika.Verify.Config => return T
      case _: Slang.Check => return T
      case _: Cache.Clear => return T
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
        if (nameExePathMap == null) {
          if(Smt2Invoke.isSupportedPlatform)
            nameExePathMap = Smt2Invoke.nameExePathMap(serverAPI.sireumHome)
          else
            nameExePathMap = HashMap.empty
        }
        AnalysisService.setConfig(req.hint, req.smt2query, req.infoFlow,
          req.config(smt2Configs =
            for (smt2Config <- smt2Configs if nameExePathMap.contains(smt2Config.name)) yield
              smt2Config(exe = nameExePathMap.get(smt2Config.name).get)))
      case req: Slang.Check => AnalysisService.checkQueue.add(req)
      case req: Cache.Clear =>
        import org.sireum.$internal.CollectionCompat.Converters._
        val prefix = req.kind match {
          case Cache.Kind.All =>
            scriptCache.uriMap = HashMap.empty
            scriptCache.thMap = HashMap.empty
            scriptCache.smt2Cache.clear()
            scriptCache.transitionCache.clear()
            scriptCache.persistentCache.clear()
            proyekCache.clear()
            "All caches"
          case Cache.Kind.Files =>
            scriptCache.uriMap = HashMap.empty
            scriptCache.thMap = HashMap.empty
            for (cache <- proyekCache.values.asScala) {
              cache.uriMap = HashMap.empty
              cache.thMap = HashMap.empty
            }
            "File cache"
          case Cache.Kind.SMT2 =>
            scriptCache.smt2Cache.clear()
            for (cache <- proyekCache.values.asScala) {
              cache.smt2Cache.clear()
            }
            "SMT2 query cache"
          case Cache.Kind.Transitions =>
            scriptCache.transitionCache.clear()
            for (cache <- proyekCache.values.asScala) {
              cache.transitionCache.clear()
            }
            "Transition cache"
          case Cache.Kind.Persistent =>
            scriptCache.persistentCache.clear()
            for (cache <- proyekCache.values.asScala) {
              cache.persistentCache.clear()
            }
            "Persistent cache"
        }
        serverAPI.sendRespond(Cache.Cleared(s"$prefix have been successfully cleared"))
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
