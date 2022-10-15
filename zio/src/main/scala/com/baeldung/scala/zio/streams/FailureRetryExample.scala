package com.baeldung.scala.zio.streams

import zio.stream.ZStream
import zio.{Schedule, Scope, Task, ZIO, ZIOAppArgs, ZIOAppDefault}

object FailureRetryExample extends ZIOAppDefault {
object Generator{
  var counter = 0
  def getNext(): Task[Int] = {
    counter += 1
    if(counter == 2) ZIO.fail(new RuntimeException("FAILED!")) else ZIO.succeed(counter)
  }
}
val failingStream: ZStream[Any, Throwable, Int] =
  ZStream.range(0, 5).flatMap(_ => ZStream.fromZIO(Generator.getNext()))

override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = failingStream.retry(Schedule.once)
    .runSum
}
