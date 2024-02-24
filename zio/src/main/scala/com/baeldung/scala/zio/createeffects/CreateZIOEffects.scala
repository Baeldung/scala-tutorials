package com.baeldung.scala.zio.createeffects

import zio.ZIO

import scala.concurrent.Future
import scala.util.Try

object CreateZIOEffects {

  def fromValue[A](value: A): ZIO[Any, Nothing, A] = {
    ZIO.succeed(value)
  }

  def fromFailureValue[A](fail: A): ZIO[Any, A, Nothing] = {
    ZIO.fail(fail)
  }

  def fromFailureValueToException(
    fail: String
  ): ZIO[Any, Exception, Nothing] = {
    ZIO.fail(new Exception(fail))
  }

  def fromOption[A](opt: Option[A]): ZIO[Any, Option[Nothing], A] = {
    ZIO.fromOption(opt)
  }

  def fromOptionOrElse[A](opt: Option[A]): ZIO[Any, String, A] = {
    ZIO.fromOption(opt).orElseFail("No value present")
  }

  def fromEither[A, B](eit: Either[B, A]): ZIO[Any, B, A] = {
    ZIO.fromEither(eit)
  }

  def fromTry[A](tryFn: Try[A]): ZIO[Any, Throwable, A] = {
    ZIO.fromTry(tryFn)
  }

  def fromFuture[A](future: Future[A]): ZIO[Any, Throwable, A] = {
    ZIO.fromFuture { implicit ec =>
      future
    }
  }

  def usingAttempt[A, E <: Exception](fn: () => A): ZIO[Any, Throwable, A] = {
    ZIO.attempt(fn()) // .refineOrDie[E]
  }

  def usingAttemptBlocking[A, E <: Exception](
    fn: () => A
  ): ZIO[Any, Throwable, A] = {
    ZIO.attemptBlocking(fn())
  }

  def usingBlocking[A, E <: Exception](fn: () => A): ZIO[Any, Throwable, A] = {
    ZIO.blocking(usingAttempt(fn))
  }

  def usingSucceed[A](fn: () => A): ZIO[Any, Nothing, A] = {
    ZIO.succeed(fn())
  }

  def usingAsync(num1: Int, num2: Int): ZIO[Any, String, Int] = {
    def divideNumbers(
      success: Int => Unit,
      failure: String => Unit
    ) = {
      if (num2 == 0) {
        failure("Dividing by zero")
      } else {
        success(num1 / num2)
      }
    }

    ZIO.async[Any, String, Int] { callback =>
      divideNumbers(
        success => callback(ZIO.succeed(success)),
        failure => callback(ZIO.fail(failure))
      )
    }
  }
}
