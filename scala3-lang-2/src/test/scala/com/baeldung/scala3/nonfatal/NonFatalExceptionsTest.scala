package com.baeldung.scala3.nonfatal

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class NonFatalExceptionsTest extends AnyWordSpec with Matchers {

  "NonFatalExceptionsTest" should {
    "catch all exceptions" in {

      def fatalExceptionFn: String = throw new LinkageError("Could not link the native library")

      def exceptionHandlingFn = {
        try {
          fatalExceptionFn
        } catch {
          case ex: Throwable => s"Caught exception(${ex.getClass.getSimpleName}) from exception block"
        }
      }
      exceptionHandlingFn shouldBe "Caught exception from exception block"

    }
  }

}
