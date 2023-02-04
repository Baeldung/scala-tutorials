package com.baeldung.scala.catseffects

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object SequenceApp extends IOApp {
  def putStr(str: String): IO[Unit] = IO.delay(println(str))

  val tasks: List[IO[Int]] = (1 to 1000).map(IO.pure).toList
  val sequenceAllTasks: IO[List[Int]] = tasks.sequence
  val printTaskSequence: IO[Unit] =
    sequenceAllTasks.map(_.mkString(", ")).flatMap(putStr)

  override def run(args: List[String]): IO[ExitCode] =
    sequenceAllTasks.as(ExitCode.Success)
}
