package com.baeldung.scala.ioapp

import cats.effect.{ExitCode, IO, IOApp, Resource}

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object ResourceIOApp extends IOApp {

  val customExecutionContext: Resource[IO, ExecutionContext] =
    Resource.make(
      IO(ExecutionContext.fromExecutorService(Executors.newWorkStealingPool(1)))
    ) { ec =>
      IO.println("Shutting down execution contest").as(ec.shutdown())
    }

  def run(args: List[String]): IO[ExitCode] = customExecutionContext
    .use { ec =>
      IO.println(s"Running on thread: ${Thread.currentThread().getName}")
    }
    .as(ExitCode.Success)
}
