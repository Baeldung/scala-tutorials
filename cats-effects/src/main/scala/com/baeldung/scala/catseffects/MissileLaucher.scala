package com.baeldung.scala.catseffects

import cats.effect.{ExitCode, IO, IOApp}

object MissileLaucher extends IOApp {
  def putStr(str: String): IO[Unit] = IO.delay(println(str))

  val launch: IO[Unit] = for {
    _ <- putStr("Lauch missiles")
    _ <- putStr("Lauch missiles")
  } yield ()

  override def run(args: List[String]): IO[ExitCode] =
    launch.as(ExitCode.Success)
}
