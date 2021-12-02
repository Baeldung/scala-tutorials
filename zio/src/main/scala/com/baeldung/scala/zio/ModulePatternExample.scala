package com.baeldung.scala.zio

import zio._

object ModulePatternExample extends ZIOAppDefault {
  val myApp = for {
    _ <- Logging.log("Application Started!")
    _ <- Console.printLine("Hello! What is your name?")
    n <- Console.readLine
    _ <- Logging.log(s"User name: $n")
    _ <- Console.printLine("Hello, " + n + ", good to meet you!")
    _ <- Logging.log("Application Exited!")
  } yield ()

  def run = myApp.injectCustom(LoggingLive.layer)
}


// Service Definition
trait Logging {
  def log(line: String): UIO[Unit]
}

// Companion object containing accessor methods
object Logging {
  def log(line: String): URIO[Has[Logging], Unit] =
    ZIO.serviceWith[Logging](_.log(line))
}

// Live implementation of Logging service
case class LoggingLive(console: Console, clock: Clock) extends Logging {
  override def log(line: String): UIO[Unit] =
    for {
      current <- clock.currentDateTime
      _ <- console.printLine(s"$current--$line").orDie
    } yield ()
}

// Companion object of LoggingLive containing the service implementation into the ZLayer
object LoggingLive {
  val layer: URLayer[Has[Console] with Has[Clock], Has[Logging]] =
    (LoggingLive(_, _)).toLayer
}
