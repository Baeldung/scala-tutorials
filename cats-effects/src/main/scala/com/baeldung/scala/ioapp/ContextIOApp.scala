package com.baeldung.scala.ioapp

import cats.effect._
import cats.effect.unsafe.IORuntimeConfig

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object ContextIOApp extends IOApp {

  private val customExecutionContext: ExecutionContext =
    ExecutionContext.fromExecutor(Executors.newWorkStealingPool(1))

  override def run(args: List[String]): IO[ExitCode] = {
    val computation = IO {
      println(s"Running on thread: ${Thread.currentThread().getName}")
    }

    computation.evalOn(customExecutionContext).as(ExitCode.Success)
  }
}
