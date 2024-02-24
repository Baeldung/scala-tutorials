package com.baeldung.scala.zio.streams

import zio.stream.ZStream
import zio.{ZIO, ZIOAppArgs, ZIOAppDefault}

object SimpleStreamExample extends ZIOAppDefault {
  val simpleStream = ZStream.range(0, 1000)
  override def run: ZIO[ZIOAppArgs, Any, Any] = simpleStream.runSum
}
