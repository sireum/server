// #Sireum
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

package org.sireum.server

import org.sireum._
import org.sireum.cli.CliOpt._

object cli {

  val serverTool: Tool = Tool(
    name = "server",
    command = "server",
    description = "Sireum server",
    header = "Sireum Server",
    usage = "<option>*",
    usageDescOpt = None(),
    opts = ISZ(
      Opt(name = "message", longKey = "message", shortKey = Some('m'),
        tpe = Type.Choice("ServerMessage", None(), ISZ("msgpack", "json")),
        description = "Message format"),
      Opt(name = "log", longKey = "log", shortKey = Some('l'),
        tpe = Type.Flag(F),
        description = "Enable logging"),
      Opt(name = "noInputCache", longKey = "no-input-cache", shortKey = Some('i'),
        tpe = Type.Flag(F),
        description = "Disable file input caching"),
      Opt(name = "noTypeCache", longKey = "no-type-cache", shortKey = Some('t'),
        tpe = Type.Flag(F),
        description = "Disable type information caching"),
      Opt(name = "verbose", longKey = "verbose", shortKey = Some('v'),
        tpe = Type.Flag(F),
        description = "Enable verbose logging"),
      Opt(name = "workers", longKey = "workers", shortKey = Some('w'),
        tpe = Type.Num(None(), 1, Some(1), None()),
        description = "Number of analysis thread workers"),
    ),
    groups = ISZ()
  )
}
