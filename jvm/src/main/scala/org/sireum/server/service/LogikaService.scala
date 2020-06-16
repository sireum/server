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
import org.sireum.server.protocol._

object LogikaService {

  class Thread(terminated: _root_.java.util.concurrent.atomic.AtomicBoolean) extends _root_.java.lang.Thread {
    override def run(): Unit = {
      while (!terminated.get()) {
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
    halt("TODO")
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
      case req: Cancel => LogikaService.idMap.containsKey(req.id)
      case req: Logika.Verify.Config =>
        LogikaService.defaultConfig = req.config
        return T
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
    halt("TODO")
  }

  def finalise(): Unit = {
    terminated.set(true)
    for (t <- threads) {
      t.interrupt()
    }
  }

  override def string: String = "LogikaService"
}
