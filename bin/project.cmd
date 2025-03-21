::/*#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF           #
if [ -z "${SIREUM_HOME}" ]; then                      #
  echo "Please set SIREUM_HOME env var"               #
  exit -1                                             #
fi                                                    #
exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
"%SIREUM_HOME%\bin\sireum.bat" slang run "%0" %*
exit /B %errorlevel%
::!#*/
// #Sireum

import org.sireum._
import org.sireum.project.ProjectUtil._
import org.sireum.project.Project

val eval = "slang-eval"

val transpilersC = "transpilers-c"

val transpilersRust = "transpilers-rust"

val tools = "tools"

val infoflow = "infoflow"

val codegen = "hamr-codegen"

val phantom = "hamr-phantom"

val proyek = "proyek"

val sysml = "hamr-sysml-frontend"

val parser = "parser"

val anvil = "anvil"

val protocol = "protocol"

val server = "server"

val homeDir = Os.slashDir.up.canon

val (serverShared, serverJvm) = moduleSharedJvmPub(
  baseId = server,
  baseDir = homeDir,
  sharedDeps = sharedId(infoflow) ++ sharedId(eval) :+ protocol,
  sharedIvyDeps = ISZ(),
  jvmDeps = ISZ(transpilersC, transpilersRust, tools, codegen, phantom, sysml, proyek, parser, anvil, eval),
  jvmIvyDeps = ISZ(),
  pubOpt = pub(
    desc = "Sireum Server",
    url = "github.com/sireum/server",
    licenses = bsd2,
    devs = ISZ(robby)
  )
)

val project = Project.empty + serverShared + serverJvm

projectCli(Os.cliArgs, project)
