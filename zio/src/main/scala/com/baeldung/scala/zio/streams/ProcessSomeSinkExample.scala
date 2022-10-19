package com.baeldung.scala.zio.streams

import zio.stream.{ZSink, ZStream}
import zio.{Chunk, ZIO, ZIOAppArgs, ZIOAppDefault}

object ProcessSomeSinkExample extends ZIOAppDefault{
  val processSomeSink: ZSink[Any, Nothing, Int, Int, Chunk[Int]] = ZSink.take[Int](100)
  val processed:ZIO[Any, Nothing, (Chunk[Int], Chunk[Int])] = ZStream.range(0, 1000)
    .run(processSomeSink.collectLeftover)

  override def run: ZIO[ZIOAppArgs, Any, Any] =
    processed
}
