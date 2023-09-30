package com.baeldung.scala.zio.repeatretry

import zio.{Random, Schedule, Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object RetrySamples extends ZIOAppDefault {
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    val mayBeFailingZIO = for {
      num <- Random.nextIntBounded(100)
      _ <- zio.Console.printLine("Calculating with number: " + num)
      _ <- ZIO.when(num % 3 != 0)(
        ZIO.fail(new Exception(s"$num is not an multiple of 3!"))
      )
    } yield num
    // mayBeFailingZIO.retry(Schedule.exponential(zio.Duration.fromSeconds(3)))
    def canRetry = scala.util.Random.nextBoolean() // random condition
    for {
      _ <- mayBeFailingZIO.retry(
        Schedule.exponential(zio.Duration.fromSeconds(3))
      )
      _ <- mayBeFailingZIO.retryUntil(_ =>
        canRetry
      ) // fail and exit if 3 retries are not enough
      _ <- mayBeFailingZIO.retryOrElse(
        policy = Schedule.recurs(3),
        orElse = (err, value: Long) =>
          zio.Console.printLine(
            s"Terminating retries since we are not able to succeed even after 3 attempts. Returning 0 "
          ) *> ZIO.succeed(0L)
      )
    } yield ()

  }
}
