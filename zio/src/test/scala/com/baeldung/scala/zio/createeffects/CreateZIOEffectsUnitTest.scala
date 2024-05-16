package com.baeldung.scala.zio.createeffects

import zio.ZIO
import zio.test.{ZIOSpecDefault, assertTrue}
import com.baeldung.scala.zio.createeffects.CreateZIOEffects._

import scala.concurrent.Future
import scala.util.Try

object CreateZIOEffectsUnitTest extends ZIOSpecDefault {
  override def spec = suite("CreateZIOEffects")(
    test("fromValue") {
      for {
        value <- fromValue(42)
      } yield assertTrue(value == 42)
    },
    test("fromFailureValue") {
      val failureValue: ZIO[Any, Nothing, String] =
        fromFailureValue("Failure").catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value == "Failure")
    },
    test("fromFailureValueToException") {
      val failureValue: ZIO[Any, Nothing, Exception] =
        fromFailureValueToException("Failure").catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value.getMessage == "Failure")
    },
    test("fromOption") {
      for {
        value <- fromOption(Some(42))
      } yield assertTrue(value == 42)
    },
    test("fromOption of None") {
      val failureValue: ZIO[Any, Nothing, Option[Nothing]] =
        fromOption(None).catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value == None)
    },
    test("fromOptionOrElse of None") {
      val failureValue: ZIO[Any, Nothing, String] =
        fromOptionOrElse(None).catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value == "No value present")
    },
    test("fromEither of Right") {
      for {
        value <- fromEither(Right("Hello world!"))
      } yield assertTrue(value == "Hello world!")
    },
    test("fromEither of Left") {
      val failureValue: ZIO[Any, Nothing, String] =
        fromEither(Left("fail")).catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value == "fail")
    },
    test("fromTry of success") {
      for {
        value <- fromTry(Try(42 / 1))
      } yield assertTrue(value == 42)
    },
    test("fromTry of Exception") {
      val failureValue: ZIO[Any, Nothing, Any] =
        fromTry(Try(42 / 0)).catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value.isInstanceOf[ArithmeticException])
    },
    test("fromFuture") {
      for {
        value <- fromFuture(Future.successful(42))
      } yield assertTrue(value == 42)
    },
    test("usingAttempt success") {
      for {
        value <- usingAttempt(() => 42 / 1)
      } yield assertTrue(value == 42)
    },
    test("usingAttempt failure") {
      val failureValue = usingAttempt(() => 42 / 0).catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value.isInstanceOf[ArithmeticException])
    },
    test("usingAttemptBlocking") {
      for {
        value <- usingAttempt(() => Thread.sleep(100))
      } yield assertTrue(value == ())
    },
    test("usingBlocking") {
      for {
        value <- usingBlocking(() => Thread.sleep(100))
      } yield assertTrue(value == ())
    },
    test("usingSucceed") {
      for {
        value <- usingAttempt(() => 42 + 1)
      } yield assertTrue(value == 43)
    },
    test("usingAsync success") {
      for {
        value <- usingAsync(42, 1)
      } yield assertTrue(value == 42)
    },
    test("usingAsync failure") {
      val failureValue = usingAsync(42, 0).catchAll(ZIO.succeed(_))
      for {
        value <- failureValue
      } yield assertTrue(value == "Dividing by zero")
    }
  )
}
