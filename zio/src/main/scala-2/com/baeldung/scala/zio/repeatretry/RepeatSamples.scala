package com.baeldung.scala.zio.repeatretry

import zio.{Schedule, Scope, Task, ZIO, ZIOAppArgs, ZIOAppDefault}

import scala.util.Random

object RepeatSamples extends ZIOAppDefault {
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    val simpleZio: ZIO[Any, Nothing, Unit] = ZIO.succeed(println("Hello ZIO!"))
    val aFailingZio = ZIO.fail(new Exception("failed!"))

    def fallback(
      error: Exception,
      optionB: Option[Any]
    ) =
      optionB match {
        case Some(b) =>
          ZIO.succeed(println("inside fallback")) *> ZIO.succeed(b)
        case None => ZIO.fail(new Exception("Unhandled case: " + error))
      }
    for {
      repeat_recurs <- simpleZio.repeat(
        Schedule.recurs(3)
      ) // executes 1 + 3 times
      repeatN <- simpleZio.repeatN(2) // executes 1 + 2 times
      repeatUntil <- simpleZio.repeatUntil(_ => isEven)
      repeatOrElse <- aFailingZio.repeatOrElse(Schedule.recurs(3), fallback)
      repeatUntilZIO <- simpleZio.repeatUntilZIO(_ =>
        isOdd
      ) // repeat until odd number
      repeatWhileZIO <- simpleZio.repeatWhileZIO(_ =>
        isOdd
      ) // repeat while odd number
    } yield ()

  }
  def isOdd: ZIO[Any, Nothing, Boolean] =
    ZIO.succeed(Random.nextInt(100) % 2 == 1)
  def isEven = scala.util.Random.nextInt(100) % 2 == 0
}

object FailingRepeatSamples extends ZIOAppDefault {
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    val aFailingZIO = ZIO.attempt {
      println("A failing action here")
      throw new Exception("Failure block")
    }
    aFailingZIO.repeat(Schedule.recurs(3))
  }
}
