package com.baeldung.scala.catseffects

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object TraverseApp extends IOApp {
  def putStr(str: String): IO[Unit] = IO.delay(println(str))

  val tasks: List[Int] = (1 to 1000).toList
  def taskExecutor(i: Int): String = s"Executing task $i"
  val runAllTasks: IO[List[Unit]] = tasks.traverse(i => putStr(taskExecutor(i)))

  override def run(args: List[String]): IO[ExitCode] =
    runAllTasks.as(ExitCode.Success)
}
