::#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF         #
if [ -z ${SIREUM_HOME} ]; then                      #
  echo "Please set SIREUM_HOME env var"             #
  exit -1                                           #
fi                                                  #
exec ${SIREUM_HOME}/bin/sireum slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
%SIREUM_HOME%\bin\sireum.bat slang run "%0" %*
exit /B %errorlevel%
::!#
// #Sireum

import org.sireum._
import org.sireum.project.ProjectUtil._
import org.sireum.project.Project

val tipe = "slang-tipe"

val anvil = "anvil"

val tools = "tools"

val logika = "logika"

val codegen = "hamr-codegen"

val phantom = "hamr-phantom"

val proyek = "proyek"

val server = "server"

val homeDir = Os.slashDir.up.canon

val (serverShared, serverJvm) = moduleSharedJvmPub(
  baseId = server,
  baseDir = homeDir,
  sharedDeps = sharedId(logika),
  sharedIvyDeps = ISZ(),
  jvmDeps = ISZ(anvil, tools, logika, codegen, phantom, proyek),
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
