package org.sireum.server

import org.sireum._
import org.sireum.server.protocol.CustomMessagePack
import org.sireum.test._
import org.sireum.server.protocol._

import java.io._

class AnalysisServerTest extends TestSuite {

  val WAIT_THRESHOLD_IN_MILLIS: Int = 2 * 60 * 1000

  val tests = Tests {

    * - test(T, id => Seq(
      Slang.Check.Script(F, T, 0, id, None(),
        s"""// #Sireum #Logika
           |import org.sireum._
           |
           |var x: Z = 1
           |
           |def bar(): Unit = {
           |  Contract(Modifies(x))
           |}
           |
           |def foo(): Unit = {
           |  bar()
           |}""".stripMargin, 0)))

    * - test(T, id => Seq(
      Slang.Check.Script(F, T, 0, id, Some("script.cmd"),
        """::#! 2> /dev/null                                              #
           |@ 2>/dev/null # 2>nul & echo off & goto BOF                   #
           |if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then             #
           |  exec "$0.com" "$@"                                          #
           |fi                                                            #
           |rm -f "$0.com"                                                #
           |if [ -z ${SIREUM_HOME} ]; then                                #
           |  echo "Please set SIREUM_HOME env var"                       #
           |  exit -1                                                     #
           |fi                                                            #
           |exec ${SIREUM_HOME}/bin/sireum slang run -n "$0" "$@"         #
           |:BOF
           |if not defined SIREUM_HOME (
           |  echo Please set SIREUM_HOME env var
           |  exit /B -1
           |)
           |%SIREUM_HOME%\bin\sireum.bat slang run -n "%0" %*
           |exit /B %errorlevel%
           |::!#
           |// #Sireum #Logika
           |
           |import org.sireum._
           |
           |assert(T)""".stripMargin, 0)))

    * - test(T, id => Seq(
      Slang.Check.Script(F, T, 0, id, None(),
        s"""// #Sireum #Logika
           |import org.sireum._
           |
           |assert(F)""".stripMargin, 0)))

    * - test(T, id => Seq(
      Slang.Check.Script(F, T, 0, id, None(),
        s"""// #Sireum #Logika
           |import org.sireum._
           |
           |assert(T)""".stripMargin, 0)))

    * - test(F, id => Seq(
      Slang.Check.Script(F, T, 0, id, None(),
        s"""// #Sireum #Logika
           |import org.sireum._
           |
           |assert(T)""".stripMargin, 0),
      Cancel(id)))

  }

  def test(isMsgPack: B, freqs: ISZ[String] => Seq[Request])(implicit line: sourcecode.Line): Unit = {
    class ServerThread extends Thread {
      override def run(): Unit = {
        val sireumHome = Os.path(Os.env("SIREUM_HOME").get)
        val plat: String = Os.kind match {
          case Os.Kind.Win => "win"
          case Os.Kind.Linux => "linux"
          case Os.Kind.LinuxArm => "linux/arm"
          case Os.Kind.Mac => "mac"
          case _ => "unsupported"
        }
        val javaHome = sireumHome / "bin" / plat / "java"
        val scalaHome = sireumHome / "bin" / "scala"
        Server.run("test", isMsgPack, 1, F, F, javaHome, scalaHome, sireumHome, ISZ())
      }
    }

    ServerExt.prefix = "Sireum:"
    val prefixBytes = ServerExt.prefix.getBytes("UTF-8")
    while (System.in.available() > 0) System.in.read()
    val oldIn = System.in
    val oldOut = System.out
    val oldErr = System.err
    try {
      val pisIn = new PipedInputStream()
      val posIn = new PipedOutputStream()
      pisIn.connect(posIn)
      val posOut = new PipedOutputStream()
      val pisOut = new PipedInputStream()
      pisOut.connect(posOut)
      val pw = new PrintStream(posOut, true)
      System.setIn(pisIn)
      System.setOut(pw)
      System.setErr(pw)
      def writeRequest(req: Request): Unit = {
        posIn.write(prefixBytes)
        val msg = if (isMsgPack) CustomMessagePack.fromRequest(req) else JSON.fromRequest(req, T)
        posIn.write(msg.value.getBytes("UTF-8"))
        posIn.write('\n')
      }
      val t = new ServerThread
      t.start()
      var first = T
      for (req <- freqs(ISZ(s"L${line.value}"))) {
        if (!first) {
          Thread.sleep(100)
        }
        first = F
        oldOut.println(req)
        oldOut.flush()
        writeRequest(req)
      }

      def readResponse(): Either[protocol.Response, _] = {
        val baos = new ByteArrayOutputStream
        val startWaitTime = System.currentTimeMillis
        while (true) {
          if (pisOut.available > 0) {
            var b = pisOut.read()
            while (b >= 0) {
              if (b == '\n') {
                val r = new Predef.String(baos.toByteArray, "UTF-8")
                if (r.startsWith(ServerExt.prefix)) {
                  val resp = r.substring(ServerExt.prefix.length)
                  return if (isMsgPack) CustomMessagePack.toResponse(resp) else JSON.toResponse(resp)
                } else {
                  oldOut.println(r)
                  oldOut.flush()
                  return Either.Right("")
                }
              } else {
                baos.write(b)
              }
              b = pisOut.read()
            }
          } else {
            Thread.sleep(100)
            if (System.currentTimeMillis - startWaitTime > WAIT_THRESHOLD_IN_MILLIS) {
              t.interrupt()
              throw new RuntimeException("Expecting further responses from server but it took too long")
            }
          }
        }
        return Either.Right(new Predef.String(baos.toByteArray, "UTF-8"))
      }

      var foundEnd = F
      while (!foundEnd) {
        readResponse() match {
          case Either.Left(response) =>
            oldOut.println(response)
            oldOut.flush()
            if (response.isInstanceOf[server.protocol.Analysis.End]) {
              foundEnd = T
            }
          case Either.Right(value) =>
            val text = value match {
              case value: org.sireum.String => value.value
              case value: Predef.String => value
              case value: Json.ErrorMsg => value.message.value
            }
            if (text.nonEmpty) {
              t.interrupt()
              assert(F, s"Unexpected response: '$value''")
            }
        }
      }
      writeRequest(protocol.Terminate())
      t.synchronized(t.join())
    } catch {
      case t: Throwable =>
        t.printStackTrace(oldErr)
        oldErr.flush()
        throw t
    } finally {
      System.setIn(oldIn)
      System.setOut(oldOut)
      System.setErr(oldErr)
    }
  }

}
