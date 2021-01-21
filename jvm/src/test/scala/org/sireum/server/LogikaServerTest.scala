package org.sireum.server

import org.sireum._
import org.sireum.server.protocol.CustomMessagePack
import org.sireum.test._
import org.sireum.server.protocol.Logika.Verify.StartScript

import java.io._

class LogikaServerTest extends TestSuite {

  val tests = Tests {
    * - test(StartScript("", None(),
      s"""// #Sireum #Logika
         |import org.sireum._
         |
         |assert(T)""".stripMargin))
  }

  def test(req: StartScript): Unit = {
    val oldIn = System.in
    val oldOut = System.out
    val oldErr = System.err
    try {
      oldOut.println(req)
      oldOut.flush()
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
      val t = new ServerThread
      t.start()
      posIn.write(CustomMessagePack.fromRequest(req).value.getBytes("UTF-8"))
      posIn.write('\n')
      def readResponse(): Either[protocol.Response, String] = {
        val baos = new ByteArrayOutputStream
        var b = pisOut.read()
        while (b >= 0) {
          if (b == '\n') {
            val r = new Predef.String(baos.toByteArray, "UTF-8")
            return CustomMessagePack.toResponse(r)
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
            t.interrupt()
            assert(F, s"Unexpected response: $value")
        }
      }
      posIn.write(CustomMessagePack.fromRequest(protocol.Terminate()).value.getBytes("UTF-8"))
      posIn.write('\n')
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
