package com.baeldung.scala.ioapp

import cats.effect.{IO, IOApp}

object SimpleIOApp extends IOApp.Simple {
  val run: IO[Unit] = {
    IO.println("Running with simple app")
  }
}
