package com.baeldung.scala.ioapp

import cats.effect.{ExitCode, IO, IOApp}

object ArgumentIOApp extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    if (args.nonEmpty) {
      IO.println(s"Running with args: ${args.mkString(",")}")
        .as(ExitCode.Success)
    } else {
      IO.println("No args provided. Aborting").as(ExitCode(2))
    }
  }
}
