package com.baeldung.scala.nonfatal

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.*
import scala.util.control.{ControlThrowable, NonFatal}

class NonFatalExceptionsTest extends AnyWordSpec with Matchers {

  "NonFatalExceptionsTest" should {

    "handle different exceptions in catch block" in {
      def failingFn(num: Int): String = {
        num match {
          case 0 => throw new RuntimeException("Number 0 is not allowed")
          case n if n < 0 =>
            throw new IllegalStateException("Number can't be negative")
          case _ => "Success"
        }
      }
      val handledResult =
        try {
          failingFn(-3)
        } catch {
          case is: IllegalStateException => "IllegalStateException"
          case rt: RuntimeException      => "RuntimeException"
          case _                         => "Unknown"
        }
      handledResult shouldBe "IllegalStateException"
    }

    "handle different exceptions in Try block" in {
      def failingFn(num: Int): String = {
        num match {
          case 0 => throw new RuntimeException("Number 0 is not allowed")
          case n if n < 0 =>
            throw new IllegalStateException("Number can't be negative")
          case _ => "Success"
        }
      }

      val triedResult = Try(failingFn(-3))
      val handledResult = triedResult match {
        case Success(value) => value
        case Failure(exception) =>
          exception match {
            case is: IllegalStateException => "IllegalStateException"
            case rt: RuntimeException      => "RuntimeException"
            case _                         => "Unknown"
          }
      }
      handledResult shouldBe "IllegalStateException"
    }

    "catch all exceptions and errors using Throwable case block" in {
      def explodingFn: String =
        throw new LinkageError("Could not link the native library")
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

    "catch ControlThrowable using Throwable case block" in {
      def explodingFn: String =
        throw new ControlThrowable("This is not good!") {}

      def exceptionHandlingFn = {
        try {
          explodingFn
        } catch {
          case _: ControlThrowable =>
            "It is a bad practice to catch ControlThrowable"
        }
      }

      exceptionHandlingFn shouldBe "It is a bad practice to catch ControlThrowable"
    }

    "not be able to catch LinkageError using Exception catch block" in {
      def explodingFn: String =
        throw new LinkageError("Could not link the native library")
      // Same for VirtualMachineError, ThreadDeath, InterruptedException, ControlThrowable and their subclasses
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
    def explodingFn: String =
      throw new LinkageError("Could not link the native library")
    def fnWithTry = {
      Try {
        explodingFn
      }
    }
    def tryToHandle = fnWithTry match {
      case Success(v)  => "success"
      case Failure(ex) => "Handled exception: " + ex.getClass.getSimpleName
    }
    // since it could not be handled, it should throw this linkage error.
    val intercepted = intercept[LinkageError](tryToHandle)
    // notice that it is not returning the message "Handled exception: LinkageError"
    intercepted.getMessage shouldBe "Could not link the native library"
  }

  "be able to catch NON-errors using Try block" in {
    def explodingFn: String =
      throw new Exception("Could not link the native library")

    def fnWithTry = {
      Try {
        explodingFn
      }
    }

    val handled = fnWithTry match {
      case Success(v)  => "success"
      case Failure(ex) => "Handled exception: " + ex.getClass.getSimpleName
    }

    handled shouldBe "Handled exception: Exception"
  }

  "handle non-errors using NonFatal block" in {
    val fnWithTry: Try[String] =
      Failure(new Exception("Something didn't work!"))
    val handled = fnWithTry.failed.get match {
      case NonFatal(nf) => "Handled only non-fatal exception here"
    }
    handled shouldBe "Handled only non-fatal exception here"
  }

  "not able to handle errors using NonFatal block" in {
    def result = try {
      throw new OutOfMemoryError("No Memory available")
    } catch {
      case NonFatal(_) => "Handled NonFatal Exceptions"
    }
    val errorMsg = intercept[OutOfMemoryError](result).getMessage
    errorMsg shouldBe "No Memory available"
  }

  "handle exceptions using NonFatal block" in {
    def explodingFn: String =
      throw new LinkageError("Could not link the native library")

    def fnWithTry = {
      Try {
        explodingFn
      }.failed.get match {
        case NonFatal(nf) => "Handled only non-fatal exception here"
        case _            => "Handled fatal exception: "
      }
    }

    val intercepted = intercept[LinkageError](fnWithTry)
    intercepted.getMessage shouldBe "Could not link the native library"
  }

  "ignore fatal error from Try responsibility" in {
    def handleWithTry = Try {
      throw new VirtualMachineError(
        "You are not strong enough to handle me!"
      ) {}
    }
    val errorMsg = intercept[VirtualMachineError](handleWithTry).getMessage
    errorMsg shouldBe "You are not strong enough to handle me!"
  }
}
