package com.baeldung.scala.fibers

import cats.effect.kernel.Outcome
import cats.effect.{FiberIO, IO, IOApp, OutcomeIO}
import com.baeldung.scala.fibers.IOExtensions._

import scala.concurrent.duration._
import scala.util.Random

object Fibers extends IOApp.Simple {

  val sequentialOps =
    IO("Step1").printIO >> IO("Step2").printIO >> IO("Step3").printIO

  val io: IO[String] =
    IO("Starting a task").printIO >> IO.sleep(400.millis) >> IO(
      "Task completed"
    ).printIO

  val fibExec = for {
    fib <- io.start
    _ <- fib.join
  } yield ()

  val fibCancel: IO[Outcome[IO, Throwable, String]] = for {
    fib <- io.start
    _ <- IO.sleep(100.millis) >> fib.cancel >> IO("Fiber cancelled").printIO
    res <- fib.join
  } yield res

  val outcome: IO[String] = fibCancel.flatMap {
    case Outcome.Succeeded(fa) => IO("fiber executed successfully").printIO
    case Outcome.Errored(e) =>
      IO("error occurred during fiber execution").printIO
    case Outcome.Canceled() => IO("fiber was canceled!").printIO
  }

  val ioWithCancelationHook =
    io.onCancel(IO("Applying cancelation finalizer").printIO.void)
  val finaliserAction = for {
    fib <- ioWithCancelationHook.start
    _ <- IO.sleep(100.millis) >> fib.cancel >> IO("fiber cancelled").printIO
    _ <- fib.join
  } yield ()

  val participant1 =
    IO("Start Task1").printIO >> IO.sleep(Random.nextInt(1000).millis) >> IO(
      "Task 1 completed"
    ).printIO
  val participant2 =
    IO("Start Task2").printIO >> IO.sleep(Random.nextInt(1000).millis) >> IO(
      "Task 2 completed"
    ).printIO
  val raceResult: IO[Either[String, String]] =
    IO.race(participant1, participant2)

  val participant1WithFinaliser =
    participant1.onCancel(IO("Task 1 got canceled").printIO.void)
  val participant2WithFinaliser =
    participant2.onCancel(IO("Task 2 got canceled").printIO.void)
  val raceWithFinaliser =
    IO.race(participant1WithFinaliser, participant2WithFinaliser)

  val racePairResult: IO[Either[
    (OutcomeIO[String], FiberIO[String]),
    (FiberIO[String], OutcomeIO[String])
  ]] = IO.racePair(participant1, participant2)

  val ioWithTimeout: IO[String] = participant1.timeout(400.millis)

  val ioWithFallback = participant1.timeoutTo(
    400.millis,
    IO("Fallback IO executed after timeout").printIO
  )

  override def run: IO[Unit] = ioWithFallback.void
}
