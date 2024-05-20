package com.baeldung.scala.zio.streams

import zio.stream.{ZSink, ZStream}
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

import java.io.File
import scala.io.Source
object FileProcessingExample extends ZIOAppDefault {
  val fileIterator =
    Source.fromFile(getClass.getResource("/textFile.txt").getFile).getLines()
  val fileInputStream: ZStream[Any, Throwable, String] =
    ZStream.fromIterator(fileIterator)

  val fileSink = ZSink.fromFile(new File("outputFile.txt"))
  val fileOutputStream = fileInputStream
    .intersperse("\n")
    .flatMap(line => ZStream(line.getBytes.toList*))
    .run(fileSink)

  override def run: ZIO[Any & ZIOAppArgs & Scope, Any, Any] =
    fileOutputStream
}
