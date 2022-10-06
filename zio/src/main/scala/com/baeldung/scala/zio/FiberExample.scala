package com.baeldung.scala.zio

import zio._

import java.io.IOException

object FiberExample extends zio.ZIOAppDefault {
  def longRunningJob: ZIO[Any, IOException, Unit] =
    Console.printLine("long running job started!") *>
      ZIO.sleep(5.seconds) *>
      Console.printLine("long-running job finished!")

  def anotherLongRunningJob: ZIO[Any, IOException, Unit] =
    Console.printLine("another long-running job started!") *>
      ZIO.sleep(3.seconds) *>
      Console.printLine("another long-running job finished!")

  def myApp: ZIO[Any, IOException, Unit] =
    for {
      fiber1 <- longRunningJob.fork
      fiber2 <- anotherLongRunningJob.fork
      result <- (fiber1 <*> fiber2).join
    } yield result

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = myApp
}
