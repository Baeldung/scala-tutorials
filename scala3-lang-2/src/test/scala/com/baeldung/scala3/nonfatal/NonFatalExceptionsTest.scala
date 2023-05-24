package com.baeldung.scala3.nonfatal

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.*
import scala.util.control.NonFatal

class NonFatalExceptionsTest extends AnyWordSpec with Matchers {

  "NonFatalExceptionsTest" should {
    "catch all exceptions and errors using Throwable case block" in {
      def explodingFn: String = throw new LinkageError("Could not link the native library")
      def exceptionHandlingFn = {
        try {
          explodingFn
        } catch {
          case ex: Throwable =>
            s"Caught exception(${ex.getClass.getSimpleName}) from exception block"
        }
      }
      exceptionHandlingFn shouldBe "Caught exception(LinkageError) from exception block"
    }

    "not be able to catch LinkageError using Exception catch block" in {
      def explodingFn: String = throw new LinkageError("Could not link the native library")
      def exceptionHandlingFn = {
        try {
          explodingFn
        } catch {
          case ex: Exception =>
            s"Caught exception(${ex.getClass.getSimpleName}) from exception block"
        }
      }
      val intercepted = intercept[LinkageError](exceptionHandlingFn)
      intercepted.getMessage shouldBe "Could not link the native library"
    }
  }

  "Not be able to catch errors using Try block" in {
    def explodingFn: String = throw new LinkageError("Could not link the native library")

    def fnWithTry = {
      Try {
        explodingFn
      }
    }

    def tryToHandle = fnWithTry match {
      case Success(v) => "success"
      case Failure(ex) => "Handled exception: "+ex.getClass.getSimpleName
    }

    //since it could not be handled, it should throw this linkage error.
    val intercepted = intercept[LinkageError](tryToHandle)
    //notice that it is not returning the message "Handled exception: LinkageError"
    intercepted.getMessage shouldBe "Could not link the native library"

  }

  "be able to catch NON-errors using Try block" in {
    def explodingFn: String = throw new Exception("Could not link the native library")

    def fnWithTry = {
      Try {
        explodingFn
      }
    }

    val handled = fnWithTry match {
      case Success(v) => "success"
      case Failure(ex) => "Handled exception: " + ex.getClass.getSimpleName
    }

    handled shouldBe "Handled exception: Exception"
  }
  
  "handle non-errors using NonFatal block" in {
    def explodingFn: String = throw new Exception("Could not link the native library")
    def fnWithTry = {
      Try {
        explodingFn
      }
    }

    val handled = fnWithTry.failed.get match {
      case NonFatal(nf) => "Handled only non-fatal exception here"
      case _ => "Handled fatal exception: "
    }
    handled shouldBe "Handled only non-fatal exception here"
  }

  "NOT be able to handle errors using NonFatal block" in {
    def explodingFn: String = throw new LinkageError("Could not link the native library")

    def fnWithTry = {
      Try {
        explodingFn
      }.failed.get match {
        case NonFatal(nf) => "Handled only non-fatal exception here"
        case _ => "Handled fatal exception: "
      }
    }

    val intercepted = intercept[LinkageError](fnWithTry)
    intercepted.getMessage shouldBe "Could not link the native library"
  }
}
