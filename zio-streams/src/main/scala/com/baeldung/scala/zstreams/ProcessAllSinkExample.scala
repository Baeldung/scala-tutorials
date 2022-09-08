package com.baeldung.scala.zstreams

import zio.stream.{ZSink, ZStream}
import zio.{ZIO, ZIOAppArgs, ZIOAppDefault}

object ProcessAllSinkExample extends ZIOAppDefault{
  val processAllSink: ZSink[Any, Nothing, Int, Nothing, Int] = ZSink.sum[Int]
  val processedStream: ZIO[Any, Nothing, Int] = ZStream.range(0, 1000).run(processAllSink)
  override def run: ZIO[ZIOAppArgs, Any, Any] =
    processedStream
}
