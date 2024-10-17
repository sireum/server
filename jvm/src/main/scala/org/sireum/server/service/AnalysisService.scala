/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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
import org.sireum.lang.ast.MethodContract
import org.sireum.lang.symbol.{Info, TypeInfo}
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
import java.util.concurrent.atomic.AtomicLong

object AnalysisService {

  class Thread(serverAPI: server.ServerAPI,
               terminated: _root_.java.util.concurrent.atomic.AtomicBoolean) extends _root_.java.lang.Thread {
    override def run(): Unit = {
      def check(reqId: ISZ[String], rootDirOpt: Option[String], isBackground: B, f: ReporterImpl => (B, B)): Unit = {
        idMap.put(reqId, this)
        val outputDirOpt: Option[Os.Path] = rootDirOpt match {
          case Some(rootDir) =>
            val p = Os.path(rootDir) / "out" / "logika"
            def dir(name: String): Unit = {
              (p / name).removeAll()
              (p / name).mkdirAll()
            }
            dir("smt2")
            dir("hint")
            Some(p)
          case _ => None()
        }
        val reporter = new ReporterImpl(_defaultConfig.logPc, _defaultConfig.logVc, _defaultConfig.detailedInfo,
          serverAPI, reqId, outputDirOpt, F)
        var cancelled = false
        var hasLogika = false
        val startTime = extension.Time.currentMillis
        serverAPI.sendRespond(server.protocol.Analysis.Start(reqId, startTime))
        serverAPI.reportStatus()
        try {
          val p = f(reporter)
          hasLogika = p._1
          cancelled = p._2
        } catch {
          case t: Throwable =>
            cancelled = true
            val baos = new ByteArrayOutputStream()
            t.printStackTrace(new java.io.PrintStream(baos))
            serverAPI.sendRespond(Report(reqId, Message(Level.InternalError, None(), "logika",
              s"""Internal error occurred:
                 |${new Predef.String(baos.toByteArray, "UTF-8")}""".stripMargin)))
        } finally {
          serverAPI.sendRespond(server.protocol.Analysis.End(
            isBackground = isBackground,
            id = reqId,
            wasCancelled = cancelled,
            hasLogika = hasLogika,
            isIllFormed = reporter.isIllFormed,
            totalTimeMillis = extension.Time.currentMillis - startTime,
            numOfVCs = reporter.numOfVCs,
            numOfSats = reporter.numOfSats,
            vcTimeMillis = reporter.vcMillis,
            satTimeMillis = reporter.satMillis,
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
              case req: SysMLv2.Check.Files =>
                check(req.id, Some(req.rootDir), req.isBackground, (reporter: ReporterImpl) => {
                  var cancelled = true
                  extension.Cancel.handleCancellable { () =>
                    checkSysMLv2Files(serverAPI.sireumHome, req, reporter)
                    cancelled = false
                  }
                  (F, cancelled)
                })
              case req: Slang.Check.Script =>
                if (req.rewriteKindOpt.nonEmpty) {
                  val reporter = new ReporterImpl(_defaultConfig.logPc, _defaultConfig.logVc, _defaultConfig.detailedInfo,
                    serverAPI, req.id, None(), F)
                  lang.parser.Parser.parseTopUnit[lang.ast.TopUnit.Program](req.content, T, F, req.uriOpt, reporter) match {
                    case Some(program) if !reporter.hasError =>
                      val (th, program2) = lang.FrontEnd.checkWorksheet(0, None(), program, reporter)
                      req.rewriteKindOpt.get match {
                        case server.protocol.Slang.Rewrite.Kind.RenumberProofSteps =>
                          val trans = lang.ast.Util.ProofStepsNumberMapper(1, HashMap.empty, HashMap.empty, Reporter.create)
                          trans.transformTopUnit(program2)
                          renumberProofSteps(req, trans, req.content, reporter)
                        case server.protocol.Slang.Rewrite.Kind.ExpandInduct =>
                          val trans = lang.FrontEnd.InductExpander(th, MethodContract.Simple.empty, None(), HashMap.empty, Reporter.create)
                          trans.transformTopUnit(program2)
                          expandInduct(req, req.uriOpt, trans, req.content, reporter)
                        case _ =>
                      }
                    case _ =>
                  }
                } else {
                  check(req.id, req.rootDirOpt, req.isBackground, (reporter: ReporterImpl) => {
                    val (hasSireum, compactFirstLine, _) = org.sireum.lang.parser.SlangParser.detectSlang(req.uriOpt, req.content)
                    val hasLogika = req.uriOpt.map(_.value.endsWith(".logika")).getOrElseEager(F) ||
                      (req.uriOpt.map(_.value.endsWith(".sc")).getOrElseEager(T) &&
                        req.logikaEnabled && hasSireum && (compactFirstLine.contains("#Logika") || defaultConfig.interp))
                    var cancelled = true
                    extension.Cancel.handleCancellable { () =>
                      checkScript(serverAPI.sireumHome, req, reporter, hasLogika)
                      cancelled = false
                    }
                    (hasLogika, cancelled)
                  })
                }
              case req: Slang.Check.Project =>
                check(req.id, req.rootDirOpt, req.isBackground, (reporter: ReporterImpl) => {
                  var cancelled = true
                  extension.Cancel.handleCancellable { () =>
                    checkProgram(req, reporter)
                    cancelled = false
                  }
                  (req.vfiles.nonEmpty, cancelled)
                })
              case _ => serverAPI.sendRespond(server.protocol.Report(req.id,
                message.Message(message.Level.InternalError, None(), "Analysis Service", s"Infeasible request: $req")))
            }
          } finally {
            idMap.remove(req.id)
            _root_.java.lang.Thread.interrupted()
          }
        }
      }
    }

    def checkProgram(req: Slang.Check.Project, reporter: ReporterImpl): Unit = {
      val key = req.rootDir.value.intern
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
                  cacheOpt = None(),
                  proxy = Coursier.Proxy.empty
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
        cache = if (defaultConfig.smt2Caching) cache else logika.NoTransitionSmt2Cache.create,
        files = req.files,
        filesWatched = T,
        vfiles = req.vfiles,
        line = req.line,
        par = defaultConfig.parCores,
        strictAliasing = T,
        followSymLink = F,
        all = F,
        verify = req.vfiles.nonEmpty && req.rewriteUriOpt.isEmpty,
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
      req.rewriteUriOpt match {
        case Some(uri) =>
          var found = F
          for ((mid, map) <- cache.uriMap.entries if !found) {
            if (map.contains(uri)) {
              found = T
              cache.thMap.get(mid) match {
                case Some(th) =>
                  val trans: lang.ast.MTransformer = req.rewriteKind match {
                    case server.protocol.Slang.Rewrite.Kind.RenumberProofSteps =>
                      lang.ast.Util.ProofStepsNumberMapper(1, HashMap.empty, HashMap.empty, Reporter.create)
                    case server.protocol.Slang.Rewrite.Kind.ExpandInduct =>
                      lang.FrontEnd.InductExpander(th, MethodContract.Simple.empty, None(), HashMap.empty, Reporter.create)
                    case _ => halt("Infeasible")
                  }
                  for (info <- th.nameMap.values if info.isInstanceOf[Info.Method]) {
                    info.posOpt match {
                      case Some(pos) if pos.uriOpt == req.rewriteUriOpt => info.mtransform(trans)
                      case _ =>
                    }
                  }
                  for (ti <- th.typeMap.values) {
                    ti match {
                      case ti: TypeInfo.Sig if ti.ast.posOpt.get.uriOpt == req.rewriteUriOpt =>
                        for (info <- ti.methods.values) {
                          info.mtransform(trans)
                        }
                      case ti: TypeInfo.Adt if ti.ast.posOpt.get.uriOpt == req.rewriteUriOpt =>
                        for (info <- ti.methods.values) {
                          info.mtransform(trans)
                        }
                      case _ =>
                    }
                  }
                  req.rewriteKind match {
                    case server.protocol.Slang.Rewrite.Kind.RenumberProofSteps =>
                      renumberProofSteps(req, trans.asInstanceOf[lang.ast.Util.ProofStepsNumberMapper], map.get(uri).get.content, reporter)
                    case server.protocol.Slang.Rewrite.Kind.ExpandInduct =>
                      expandInduct(req, req.rewriteUriOpt, trans.asInstanceOf[lang.FrontEnd.InductExpander], map.get(uri).get.content, reporter)
                    case _ =>
                  }
                case _ =>
              }
            }
          }
          if (!found) {
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, req.rewriteKind, Message(message.Level.Error, None(), "Slang Rewrite",
                s"Could not find file ${Os.uriToPath(uri)}"), None(), 0))
          }
        case _ =>
      }
      System.gc()
    }

    def expandInduct(req: Slang.Check, uriOpt: Option[String], trans: lang.FrontEnd.InductExpander, text: String, reporter: Reporter): Unit = {
      def expandInductResult(req: Slang.Check, n: Z, newText: String, reporter: Reporter): Unit = {
        if (n != 0) {
          if (reporter.hasWarning) {
            var warnings = 0
            for (m <- reporter.messages) {
              if (m.level == message.Level.Warning) {
                warnings = warnings + 1
              }
            }
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.ExpandInduct, Message(message.Level.Info, None(), "Slang Rewrite",
                s"Expanded $n @induct with $warnings warning(s)"), Some(newText), n))
          } else {
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.ExpandInduct, Message(message.Level.Info, None(), "Slang Rewrite",
                s"Successfully expanded $n @induct"), Some(newText), n))
          }
        } else {
          if (reporter.hasError) {
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.ExpandInduct, Message(message.Level.Error, None(), "Slang Rewrite",
                "Cannot expand @induct for an ill-formed program"), None(), 0))
          } else {
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.ExpandInduct, Message(message.Level.Info, None(), "Slang Rewrite",
                "All @induct have been expanded"), None(), n))
          }
        }
      }

      reporter.reports(trans.reporter.messages)
      val n = trans.map.size
      if (n == 0 || reporter.hasError) {
        expandInductResult(req, 0, text, reporter)
      }
      val content = conversions.String.toCis(text)
      ops.StringOps.replace(content, trans.map) match {
        case Either.Left(value) =>
          lang.parser.Parser.parseTopUnit[lang.ast.TopUnit.Program](value,
            req.isInstanceOf[server.protocol.Slang.Check.Script], F, uriOpt, Reporter.create) match {
            case Some(topUnit) =>
              lang.ast.Util.reformatProof(value, topUnit) match {
                case Some((value2, _)) =>
                  expandInductResult(req, n, value2, reporter)
                  return
                case _ =>
              }
            case _ =>
          }
          expandInductResult(req, n, value, reporter)
        case Either.Right(message) => halt(s"Internal error: $message")
      }
    }

    def renumberProofSteps(req: Slang.Check, trans: lang.ast.Util.ProofStepsNumberMapper, text: String, reporter: Reporter): Unit = {
      def renumberProofResult(req: Slang.Check, n: Z, newText: String, reporter: Reporter): Unit = {
        if (n != 0) {
          if (reporter.hasWarning) {
            var warnings = 0
            for (m <- reporter.messages) {
              if (m.level == message.Level.Warning) {
                warnings = warnings + 1
              }
            }
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.RenumberProofSteps, Message(message.Level.Info, None(), "Slang Rewrite",
                s"Renumbered $n proof step(s) with $warnings warning(s)"), Some(newText), n))
          } else {
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.RenumberProofSteps, Message(message.Level.Info, None(), "Slang Rewrite",
                s"Successfully renumbered $n proof step(s)"), Some(newText), n))
          }
        } else {
          if (reporter.hasError) {
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.RenumberProofSteps, Message(message.Level.Error, None(), "Slang Rewrite",
                "Cannot renumber proof steps for an ill-formed program"), None(), 0))
          } else {
            serverAPI.sendRespond(server.protocol.Slang.Rewrite.Response(
              req.id, server.protocol.Slang.Rewrite.Kind.RenumberProofSteps, Message(message.Level.Info, None(), "Slang Rewrite",
                "All proof steps have already been numbered in order"), None(), n))
          }
        }
      }

      reporter.reports(trans.reporter.messages)
      val n = trans.map.size
      if (n == 0 || reporter.hasError) {
        renumberProofResult(req, 0, text, reporter)
      }
      val content = conversions.String.toCis(text)
      ops.StringOps.replace(content, trans.map) match {
        case Either.Left(value) => renumberProofResult(req, n, value, reporter)
        case Either.Right(message) => halt(s"Internal error: $message")
      }
    }
  }

  final class FileCache(val uriOpt: Option[String],
                        val project: Project,
                        val dmOpt: Option[DependencyManager],
                        val persistentCache: java.util.concurrent.ConcurrentHashMap[logika.Logika.Cache.Key, logika.Logika.Cache.Value],
                        val taskCache: java.util.concurrent.ConcurrentHashMap[logika.Logika.Cache.Key, logika.Logika.Cache.Value],
                        var uriMap: HashMap[String, HashMap[String, lang.FrontEnd.Input]],
                        var thMap: HashMap[String, lang.tipe.TypeHierarchy],
                        val transitionCache: java.util.Map[(Long, Long, logika.Logika.Cache.Transition, ISZ[String], logika.State), SoftReference[(ISZ[logika.State], logika.Smt2, U64, HashSet[String])]] =
                        new java.util.concurrent.ConcurrentHashMap,
                        val expTransitionCache: java.util.Map[(Long, Long, lang.ast.AssignExp, ISZ[String], logika.State), SoftReference[(ISZ[(logika.State, logika.State.Value)], logika.Smt2, U64)]] =
                        new java.util.concurrent.ConcurrentHashMap,
                        val smt2Cache: java.util.Map[(Long, Long, ISZ[logika.State.Claim]), SoftReference[logika.Smt2Query.Result]] =
                        new java.util.concurrent.ConcurrentHashMap,
                        val patternsCache: java.util.Map[(Long, Boolean, ISZ[String]), SoftReference[ISZ[logika.RewritingSystem.Rewriter.Pattern]]] =
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

    def getTransitionAndUpdateSmt2(th: TypeHierarchy, config: logika.Config, transition: logika.Logika.Cache.Transition,
                                   context: ISZ[String], state: logika.State, smt2: logika.Smt2): Option[(ISZ[logika.State], U64, HashSet[String])] = {
      val thf: U64 = if (config.interp) th.fingerprintKeepMethodBody else th.fingerprintNoMethodBody
      val key = (thf.value, config.fingerprint.value, transition, context, state)
      val rRef = transitionCache.get(key)
      var r = Option.none[(ISZ[logika.State], U64, HashSet[String])]()
      if (rRef != null) {
        if (rRef.get != null) {
          val (ss, csmt2, cached, mods) = rRef.get
          smt2.updateFrom(csmt2)
          r = Some((ss, cached, mods))
        } else {
          transitionCache.remove(key)
        }
      }
      return r
    }

    def setTransition(th: TypeHierarchy, config: logika.Config, transition: logika.Logika.Cache.Transition,
                      context: ISZ[String], state: logika.State, nextStates: ISZ[logika.State], smt2: logika.Smt2,
                      modifiables: HashSet[String]): U64 = {
      val thf: U64 = if (config.interp) th.fingerprintKeepMethodBody else th.fingerprintNoMethodBody
      val key = (thf.value, config.fingerprint.value, transition, context, state)
      val cached = ops.StringOps(st"(${key._1}, ${key._2}, ${key._3.toST}, ${(key._4, ".")}, ${key._5.toST})".render).sha3U64(T, T)
      transitionCache.put(key, new SoftReference((nextStates, smt2.minimize, cached, modifiables)))
      return cached
    }

    def getAssignExpTransitionAndUpdateSmt2(th: TypeHierarchy, config: logika.Config, exp: lang.ast.AssignExp,
                                            context: ISZ[String], state: logika.State, smt2: logika.Smt2): Option[(ISZ[(logika.State, logika.State.Value)], U64)] = {
      val thf: U64 = if (config.interp) th.fingerprintKeepMethodBody else th.fingerprintNoMethodBody
      val key = (thf.value, config.fingerprint.value, context, exp, state)
      val rRef = expTransitionCache.get(key)
      var r = Option.none[(ISZ[(logika.State, logika.State.Value)], U64)]()
      if (rRef != null) {
        if (rRef.get != null) {
          val (svs, csmt2, cached) = rRef.get
          smt2.updateFrom(csmt2)
          r = Some((svs, cached))
        } else {
          expTransitionCache.remove(key)
        }
      }
      return r
    }

    def setAssignExpTransition(th: TypeHierarchy, config: logika.Config, exp: lang.ast.AssignExp, context: ISZ[String],
                               state: logika.State, nextStatesValues: ISZ[(logika.State, logika.State.Value)], smt2: logika.Smt2): U64 = {
      val thf: U64 = if (config.interp) th.fingerprintKeepMethodBody else th.fingerprintNoMethodBody
      val key = (thf.value, config.fingerprint.value, exp, context, state)
      val cached = ops.StringOps(st"(${key._1}, ${key._2}, ${key._3.prettyST}, ${(key._4, ".")}, ${key._5.toST}, ${exp.prettyST})".render).sha3U64(T, T)
      expTransitionCache.put(key, new SoftReference((nextStatesValues, smt2.minimize, cached)))
      return cached
    }

    def getSmt2(isSat: B, th: TypeHierarchy, config: logika.Config, timeoutInMs: Z, claims: ISZ[logika.State.Claim]): Option[logika.Smt2Query.Result] = {
      val thf = (if (config.interp) th.fingerprintKeepMethodBody else th.fingerprintNoMethodBody).value +
        timeoutInMs.toLong + (if (isSat) 0 else 1)
      val key = (thf, config.fingerprint.value, claims)
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

    def setSmt2(isSat: B, th: TypeHierarchy, config: logika.Config, timeoutInMs: Z, claims: ISZ[logika.State.Claim],
                result: logika.Smt2Query.Result): Unit = {
      def makeResultCached(s: Predef.String): Predef.String = {
        val result = "Result:"
        val i = s.indexOf(result)
        if (i < 0) return s
        return s"${s.substring(0, i)}Result (Cached):${s.substring(i + result.length, s.length)}"
      }
      val r = result(query = makeResultCached(result.query.value), info = makeResultCached(result.info.value))
      val thf = (if (config.interp) th.fingerprintKeepMethodBody else th.fingerprintNoMethodBody).value +
        timeoutInMs.toLong + (if (isSat) 0 else 1)
      smt2Cache.put((thf, config.fingerprint.value, claims), new SoftReference(r))
    }

    def getPatterns(th: TypeHierarchy, isInObject: B, name: ISZ[String]): Option[ISZ[logika.RewritingSystem.Rewriter.Pattern]] = {
      val key = (th.fingerprintNoMethodBody.value, isInObject.value, name)
      val rRef = patternsCache.get(key)
      var r = Option.none[ISZ[logika.RewritingSystem.Rewriter.Pattern]]()
      if (rRef != null) {
        if (rRef.get != null) {
          r = Some(rRef.get)
        } else {
          patternsCache.remove(key)
        }
      }
      return r
    }

    def setPatterns(th: TypeHierarchy, isInObject: B, name: ISZ[String], patterns: ISZ[logika.RewritingSystem.Rewriter.Pattern]): Unit = {
      val key = (th.fingerprintNoMethodBody.value, isInObject.value, name)
      patternsCache.put(key, new SoftReference(patterns))
    }

    def clearTaskCache(): Unit = {
      taskCache.clear()
    }

  }

  final class ReporterImpl(hint: B,
                           smt2query: B,
                           detailedInfo: B,
                           serverAPI: server.ServerAPI,
                           id: ISZ[String],
                           outputDirOpt: Option[Os.Path],
                           collectStats: B,
                           _numOfVCs: AtomicLong = new AtomicLong(0),
                           _numOfSats: AtomicLong = new AtomicLong(0),
                           _vcMillis: AtomicLong = new AtomicLong(0),
                           _satMillis: AtomicLong = new AtomicLong(0),
                           val _messages: _root_.java.util.concurrent.ConcurrentLinkedQueue[Message] =
                           new _root_.java.util.concurrent.ConcurrentLinkedQueue) extends logika.Logika.Reporter {
    import org.sireum.$internal.CollectionCompat.Converters._

    val maxSize: Z = 2 * 1024

    private var isClonable: scala.Boolean = true
    private var isOwned: scala.Boolean = false
    var _ignore: B = F
    var isIllFormed: B = F
    var numOfErrors: Z = 0
    var numOfInternalErrors: Z = 0
    var numOfWarnings: Z = 0

    override def numOfVCs: Z = _numOfVCs.get

    override def numOfSats: Z = _numOfSats.get

    override def vcMillis: Z = _vcMillis.get

    override def satMillis: Z = _satMillis.get

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

    override def combine(other: logika.Logika.Reporter): logika.Logika.Reporter = synchronized {
      other match {
        case other: ReporterImpl =>
          _messages.addAll(other._messages)
          isIllFormed = isIllFormed || other.isIllFormed
          numOfErrors = numOfErrors + other.numOfErrors
          numOfInternalErrors = numOfInternalErrors + other.numOfInternalErrors
          numOfWarnings = numOfWarnings + other.numOfWarnings
          return this
      }
    }

    override def $clone: ReporterImpl = {
      val r = new ReporterImpl(hint, smt2query, detailedInfo, serverAPI, id, outputDirOpt, collectStats, _numOfVCs,
        _numOfSats, _vcMillis, _satMillis, _messages)
      r.isIllFormed = isIllFormed
      r.numOfWarnings = numOfWarnings
      r.numOfErrors = numOfErrors
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
                       th: TypeHierarchy, s: logika.State, atLinesFresh: B, atRewrite: B): Unit = if (hint) {
      var claims: String = ""
      var err: String = ""
      posOpt match {
        case Some(pos) =>
          try {
            val (es, _) = logika.Util.claimsToExps(plugins, pos, context, s.claims, th, atLinesFresh, atRewrite)
            claims =
              st"""{ // State claims at line ${pos.beginLine}
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
          st"""{${if (posOpt.nonEmpty) st"// State claims at line ${posOpt.get.beginLine}" else ""}
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
      val msg: String = outputDirOpt match {
        case Some(outputDir) if claims.size > maxSize =>
          val d = outputDir / "hint"
          d.mkdirAll()
          val pos = posOpt.get
          val filename = s"state-${pos.beginLine}-${pos.beginColumn}-${extension.Time.currentNanos}.txt"
          val f = d / conversions.String.fromCis(for (c <- conversions.String.toCis(filename)) yield replaceChar(c))
          f.writeOver(claims)
          s"@${f.canon}"
        case _ => claims
      }
      serverAPI.sendRespond(Logika.Verify.State(id, posOpt, !s.ok, labels, msg))
    }

    @strictpure def replaceChar(c: C): C =
      if (('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || (c.value == '.')) c else '-'

    override def inform(pos: Position, kind: org.sireum.logika.Logika.Reporter.Info.Kind.Type,
                        message: String): Unit = if (detailedInfo) {
      val k: Logika.Verify.Info.Kind.Type = kind match {
        case org.sireum.logika.Logika.Reporter.Info.Kind.Verified => Logika.Verify.Info.Kind.Verified
        case org.sireum.logika.Logika.Reporter.Info.Kind.Error => Logika.Verify.Info.Kind.Error
      }
      val msg: String = outputDirOpt match {
        case Some(outputDir) if message.size > maxSize =>
          val d = outputDir / "hint"
          d.mkdirAll()
          val filename = s"inform-${pos.beginLine}-${pos.beginColumn}-${extension.Time.currentNanos}.txt"
          val f = d / conversions.String.fromCis(for (c <- conversions.String.toCis(filename)) yield replaceChar(c))
          f.writeOver(message)
          s"@${f.canon}"
        case _ => message
      }
      serverAPI.sendRespond(Logika.Verify.Info(id, pos, k, msg))
    }

    override def query(pos: Position, title: String, isSat: B, time: Z, forceReport: B, detailElided: B,
                       r: logika.Smt2Query.Result): Unit = {
      if (collectStats) {
        if (isSat) {
          _numOfSats.incrementAndGet
          _satMillis.addAndGet(time.toLong)
        } else {
          _numOfVCs.incrementAndGet
          _vcMillis.addAndGet(time.toLong)
        }
      }
      if (smt2query || forceReport) {
        val query: String = outputDirOpt match {
          case Some(outputDir) if !detailElided && r.query.size > maxSize =>
            val d = outputDir / "smt2"
            d.mkdirAll()
            val filename = s"$title-${extension.Time.currentNanos}.smt2"
            val f = d / conversions.String.fromCis(for (c <- conversions.String.toCis(filename)) yield replaceChar(c))
            f.writeOver(r.query)
            s"@${f.canon}"
          case _ => r.query
        }
        serverAPI.sendRespond(Logika.Verify.Smt2Query(id, pos, isSat, time, title, r.kind, r.solverName, query, r.info, r.output))
      }
    }

    override def timing(desc: String, timeInMs: Z): Unit = {
      serverAPI.sendRespond(Timing(id, desc, timeInMs))
    }

    override def coverage(setCache: B, cached: U64, pos: Position): Unit = {
      serverAPI.sendRespond(server.protocol.Analysis.Coverage(id, setCache, cached, pos))
      if (pos.beginLine != pos.endLine) {
        serverAPI.reportStatus()
      }
    }

    override def empty: logika.Logika.Reporter = {
      return new ReporterImpl(hint, smt2query, detailedInfo, serverAPI, id, outputDirOpt, collectStats)
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

  val checkQueue = new _root_.java.util.concurrent.LinkedBlockingQueue[Request]()
  val idMap = new _root_.java.util.concurrent.ConcurrentHashMap[ISZ[String], Thread]()

  var _defaultConfig: logika.Config = Logika.Verify.defaultConfig
  var _infoFlow: B = T

  def defaultConfig: logika.Config = synchronized {
    return _defaultConfig
  }

  def setConfig(newInfoFlow: B, newConfig: logika.Config): Unit = synchronized {
    _infoFlow = newInfoFlow
    _defaultConfig = newConfig(transitionCache = newConfig.transitionCache)
  }

  def createCache(uriOpt: Option[String], project: Project = Project.empty, dmOpt: Option[DependencyManager] = None()): FileCache =
    new FileCache(uriOpt, project, dmOpt, new java.util.concurrent.ConcurrentHashMap,
      new java.util.concurrent.ConcurrentHashMap, org.sireum.HashMap.empty, org.sireum.HashMap.empty)

  var scriptCache: FileCache = createCache(None())
  val proyekCache: _root_.java.util.concurrent.ConcurrentHashMap[Predef.String, FileCache] = new _root_.java.util.concurrent.ConcurrentHashMap

  def checkSysMLv2Files(sireumHome: Os.Path, req: SysMLv2.Check.Files, reporter: ReporterImpl): Unit = {
    import org.sireum.hamr.sysml.FrontEnd

    val root = Os.path(req.rootDir)
    val sysmlFiles = Os.Path.walk(path = root, includeDir = T, followLink = T, pred = p => p.isFile && p.ext == string"sysml")
    val inputs = for (f <- sysmlFiles) yield if (req.files.contains(f.value)) FrontEnd.Input(req.files.get(f.value).get, Some(f.toUri)) else FrontEnd.Input(f.read, Some(f.toUri))

    FrontEnd.typeCheck(par = 0, inputs = inputs, reporter = reporter)

    System.gc()
  }

  var (th, rep): (TypeHierarchy, message.Reporter) = (null, null)

  def checkScript(sireumHome: Os.Path, req: Slang.Check.Script, reporter: ReporterImpl, hasLogika: Boolean): Unit = {
    if (scriptCache.uriOpt != req.uriOpt) {
      scriptCache = createCache(req.uriOpt)
    }
    val config = defaultConfig
    if (!config.transitionCache) {
      scriptCache.clearTransition()
    }
    val plugins = logika.Logika.defaultPlugins ++
      (if (_infoFlow) logika.infoflow.InfoFlowPlugins.defaultPlugins else ISZ[logika.plugin.Plugin]())
    val nameExePathMap = logika.Smt2Invoke.nameExePathMap(sireumHome)
    logika.Logika.checkScript(req.uriOpt, req.content, config, nameExePathMap, Os.numOfProcessors,
      (th: lang.tipe.TypeHierarchy) => logika.Smt2Impl.create(defaultConfig, logika.plugin.Plugin.claimPlugins(plugins),
        th, reporter),
      if (config.smt2Caching) scriptCache else logika.NoTransitionSmt2Cache.create,
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
      case _: SysMLv2.Check.Files => return T
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
        AnalysisService.setConfig(req.infoFlow, req.config(smt2Configs =
          for (smt2Config <- smt2Configs if nameExePathMap.contains(smt2Config.name)) yield
            smt2Config(exe = nameExePathMap.get(smt2Config.name).get)))
      case req: Slang.Check => AnalysisService.checkQueue.add(req)
      case req: SysMLv2.Check.Files => AnalysisService.checkQueue.add(req)
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
