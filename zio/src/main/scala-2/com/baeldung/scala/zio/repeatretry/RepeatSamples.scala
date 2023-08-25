package com.baeldung.scala.zio.repeatretry

import zio.{Schedule, Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object RepeatSamples extends ZIOAppDefault {
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    val simpleZio: ZIO[Any, Nothing, Unit] = ZIO.succeed(println("Hello ZIO!"))
    val aFailingZio = ZIO.fail(new Exception("failed!"))

    def fallback(
      error: Exception,
      optionB: Option[Any]
    ) =
      optionB match {
        case Some(b) => ZIO.succeed(println("inside fallback")) *> ZIO.succeed(b)
        case None    => ZIO.fail(new Exception("Unhandled case: " + error))
      }
    for {
      repeat_recurs <- simpleZio.repeat(
        Schedule.recurs(3)
      ) // executes 1 + 3 times
      repeatN <- simpleZio.repeatN(2) // executes 1 + 2 times
      repeatOrElse <- aFailingZio.repeatOrElse(Schedule.recurs(3), fallback)
    } yield ()

  }
}
