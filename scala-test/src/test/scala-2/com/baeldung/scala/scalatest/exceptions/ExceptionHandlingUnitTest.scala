package com.baeldung.scala.scalatest.exceptions

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

import scala.concurrent.Future
import scala.util.Try

object ExceptionHandlingMethods {
  def explodingMethod(): Unit = {
    throw new RuntimeException("Boom boom!")
  }

  def getLastDigit(num: Int): Try[Int] = {
    Try {
      require(num > 0, "Only positive numbers supported!")
      num % 10
    }
  }

  def getDBResult(): Future[Int] = {
    Future.failed(new RuntimeException("Unexpected error occurred!"))
  }
}

class ExceptionHandlingUnitTest extends AsyncWordSpec with Matchers {

  "Scalatest exception handler" should {
    "intercept thrown exception successfully" in {
      val exception =
        intercept[RuntimeException](ExceptionHandlingMethods.explodingMethod())
      exception shouldBe a[RuntimeException]
      exception.getMessage shouldBe "Boom boom!"
    }

    "intercept thrown exception successfully using the parent type" in {
      val exception =
        intercept[Exception](ExceptionHandlingMethods.explodingMethod())
      exception shouldBe a[Exception]
      exception shouldBe a[RuntimeException]
      exception.getMessage shouldBe "Boom boom!"
    }

    "use assertThrows to handle thrown exception successfully" in {
      assertThrows[RuntimeException](ExceptionHandlingMethods.explodingMethod())
    }

    "handle Try failures correctly" in {
      val result = ExceptionHandlingMethods.getLastDigit(-100)
      result.failed.get shouldBe a[IllegalArgumentException]
      result.failed.get.getMessage should include(
        "Only positive numbers supported!"
      )
    }

    "handle future failures correctly" in {
      val futureResult = ExceptionHandlingMethods.getDBResult()
      futureResult.failed.map { ex =>
        ex shouldBe a[RuntimeException]
        ex.getMessage shouldBe "Unexpected error occurred!"
      }
    }

    "use recoverToExceptionIf method for futures" in {
      val futureResult = ExceptionHandlingMethods.getDBResult()
      recoverToExceptionIf[RuntimeException](futureResult).map { rt =>
        rt.getMessage shouldBe "Unexpected error occurred!"
      }
    }

    "use recoverToSucceededIf method for futures" in {
      val futureResult = ExceptionHandlingMethods.getDBResult()
      recoverToSucceededIf[RuntimeException](futureResult)
    }
  }

}
