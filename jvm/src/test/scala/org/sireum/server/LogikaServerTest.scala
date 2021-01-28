package org.sireum.server

import org.sireum._
import org.sireum.server.protocol.CustomMessagePack
import org.sireum.test._
import org.sireum.server.protocol._
import org.sireum.server.protocol.Logika.Verify._

import java.io._

class LogikaServerTest extends TestSuite {

  val prefixBytes = Server.prefix.value.getBytes("UTF-8")

  val tests = Tests {

    * - test(id => Seq(
      StartScript(id, None(),
        s"""// #Sireum #Logika
           |import org.sireum._
           |
           |assert(T)""".stripMargin)))

    * - test(id => Seq(
      StartScript(id, None(),
        s"""// #Sireum #Logika
           |import org.sireum._
           |
           |assert(T)""".stripMargin),
      Cancel(id)))

  }

  def test(freqs: ISZ[String] => Seq[Request])(implicit line: sourcecode.Line): Unit = {
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
        posIn.write(CustomMessagePack.fromRequest(req).value.getBytes("UTF-8"))
        posIn.write('\n')
      }
      val t = new ServerThread
      t.start()
      var first = T
      for (req <- freqs(ISZ(s"L${line.value}"))) {
        if (!first) {
          Thread.sleep(1000)
        }
        first = F
        oldOut.println(req)
        oldOut.flush()
        writeRequest(req)
      }

      def readResponse(): Either[protocol.Response, String] = {
        val baos = new ByteArrayOutputStream
        var b = pisOut.read()
        while (b >= 0) {
          if (b == '\n') {
            val r = new Predef.String(baos.toByteArray, "UTF-8")
            if (r.startsWith(Server.prefix.value)) {
              return CustomMessagePack.toResponse(r.substring(Server.prefix.value.length))
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
        return Either.Right(new Predef.String(baos.toByteArray, "UTF-8"))
      }

      var foundEnd = F
      while (!foundEnd) {
        readResponse() match {
          case Either.Left(response) =>
            oldOut.println(response)
            oldOut.flush()
            if (response.isInstanceOf[protocol.Logika.Verify.End]) {
              foundEnd = T
            }
          case Either.Right(value) =>
            if (value.size != 0) {
              t.interrupt()
              assert(F, s"Unexpected response: '$value''")
            }
        }
      }
      posIn.write(CustomMessagePack.fromRequest(protocol.Terminate()).value.getBytes("UTF-8"))
      posIn.write('\n')
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

  class ServerThread extends Thread {
    override def run(): Unit = {
      Server.run(1)
    }
  }

}
