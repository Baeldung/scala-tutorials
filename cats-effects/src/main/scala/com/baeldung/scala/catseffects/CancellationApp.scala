package com.baeldung.scala.catseffects

import cats.effect.IOApp
import cats.effect.IO
import scala.concurrent.duration.DurationInt
import java.util.concurrent.atomic.AtomicBoolean
import cats.effect.ExitCode

object CancellationApp extends IOApp {

  def example() = {
    val flag = new AtomicBoolean(false)
    var counter = 0
    val ioa = IO.blocking {
      while (!flag.get()) {
        Thread.sleep(500)
        println(s"counter = $counter")
        counter += 1
      }
    }

    ioa.cancelable(
      IO.sleep(3.seconds) >> IO.println("executing the finalizer...") >> IO
        .delay(flag.set(true))
    )
  }

  override def run(args: List[String]) =
    example().as(ExitCode.Success)

}
