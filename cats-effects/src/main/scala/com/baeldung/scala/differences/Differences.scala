package com.baeldung.scala.differences
import cats.effect.IO
import scala.concurrent.duration._
import cats.effect.kernel.Deferred
import cats.effect.IOApp

object Differences extends IOApp.Simple {

  val delayedIO: IO[Unit] = IO.delay(println("Creating a delayed effect"))
  val io = IO(println("Hello World"))

  val delayedDurationIO = io.delayBy(3.seconds)

  // This will fail with Stackoverflow Exception
  def neverEnding(io: IO[Int]): IO[Unit] = { io *> neverEnding(io) }

  // This will run forever without any StackOverflowException
  def neverEndingV2(io: IO[Int]): IO[Unit] = {
    io *> IO.defer(neverEndingV2(io))
  }

  val aDeferredInstance: IO[Deferred[IO, Int]] = IO.deferred[Int]

  def deferredOperation(deferred: Deferred[IO, Int]) =
    for {
      _ <- IO.println("deferred instance manipulation")
      _ = println("Waiting for the value to be available")
      _ <- deferred.get // blocks the thread until value becomes available
      _ = println("deferred instance is complete")
    } yield ()

  def deferredCompletion(deferred: Deferred[IO, Int]) = {
    IO.sleep(3.second) *> deferred.complete(100)
  }

  val pgm = for {
    inst <- aDeferredInstance
    fib1 <- deferredOperation(inst).start
    fib2 <- deferredCompletion(inst).start
    _ <- fib1.join
    _ <- fib2.join
  } yield ()

  def run: IO[Unit] = pgm

}
