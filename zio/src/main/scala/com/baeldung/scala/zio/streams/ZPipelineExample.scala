package com.baeldung.scala.zio.streams

import zio.stream.{ZPipeline, ZStream}
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}
object ZPipelineExample extends ZIOAppDefault {
  val mappingPipeline: ZPipeline[Any, Nothing, String, Int] =
    ZPipeline.map[String, Int](string => string.toInt + 1)
  val filterOdd: ZPipeline[Any, Nothing, Int, Int] =
    ZPipeline.filter(_ % 2 == 0)
  val oddSream =
    ZStream("1", "2", "3", "4", "5", "6").via(mappingPipeline >>> filterOdd)

  val firstStreamMapped = ZStream("1", "2", "3", "4", "5").via(mappingPipeline)
  val secondStreamMapped =
    ZStream("6", "7", "8", "9", "10").via(mappingPipeline)
  val totalSum: ZIO[Any, Nothing, Int] =
    firstStreamMapped.concat(secondStreamMapped).runSum

  override def run: ZIO[Any & ZIOAppArgs & Scope, Any, Any] = totalSum
}
