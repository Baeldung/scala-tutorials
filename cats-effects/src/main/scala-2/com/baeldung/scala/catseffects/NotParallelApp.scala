package com.baeldung.scala.catseffects

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import com.baeldung.scala.catseffects.Utils.ShowThread

object NotParallelApp extends IOApp {
  val tasks: List[IO[Int]] = (1 to 10).map(IO.pure).map(_.showThread).toList

  val incremented: IO[List[Int]] = tasks.traverse { ioi =>
    for (i <- ioi) yield i + 1
  }

  val parallelOrNot: IO[List[Int]] = incremented.showThread

  override def run(args: List[String]): IO[ExitCode] =
    parallelOrNot.as(ExitCode.Success)

}
