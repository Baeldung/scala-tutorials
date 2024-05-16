package com.baeldung.scala.zio.errorhandling

import zio.{Scope, ZIO}
import zio.test.{Spec, TestEnvironment, ZIOSpecDefault}
import zio.test._

object ErrorHandlingUnitTest extends ZIOSpecDefault {
  val successResult = "success"
  override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("ErrorHandlingUnitTest")(
      test("usingEither returns Either of String") {
        for {
          result <- ErrorHandling.usingEither
        } yield assertTrue(result == Right(successResult))
      },
      test("usingOrElse returns success") {
        for {
          result <- ErrorHandling.usingOrElse
        } yield assertTrue(result == successResult)
      },
      test("usingCatchAll returns success") {
        for {
          result <- ErrorHandling.usingCatchAll
        } yield assertTrue(result == successResult)
      },
      test("usingCatchSome returns success") {
        for {
          result <- ErrorHandling.usingCatchSome
        } yield assertTrue(result == successResult)
      },
      test("usingFold returns default") {
        for {
          result <- ErrorHandling.usingFold
        } yield assertTrue(result == "some default")
      },
      test("usingFoldZIO returns success") {
        for {
          result <- ErrorHandling.usingFoldZIO
        } yield assertTrue(result == successResult)
      },
      test("usingRetry returns an IOException") {
        val failureResult =
          ErrorHandling.usingRetryOrElse.catchAll(ZIO.succeed(_))
        for {
          error <- failureResult
        } yield assertTrue(error == error)
      },
      test("usingRetryOrElse returns success") {
        for {
          result <- ErrorHandling.usingRetryOrElse
        } yield assertTrue(result == successResult)
      }
    )
}
