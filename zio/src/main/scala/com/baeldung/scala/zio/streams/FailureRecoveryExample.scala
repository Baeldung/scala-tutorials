package com.baeldung.scala.zio.streams

import zio.stream.ZStream
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object FailureRecoveryExample extends ZIOAppDefault {
  val failingStream: ZStream[Any, Throwable, Int] = ZStream.range(0, 5) ++
    ZStream.fail(new RuntimeException("Failing!")) ++
    ZStream.range(6, 10)
  val recoveryStream: ZStream[Any, Throwable, Int] = ZStream.range(10, 15)

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    ZStream.range(0, 5)
      .runSum
  }

}
