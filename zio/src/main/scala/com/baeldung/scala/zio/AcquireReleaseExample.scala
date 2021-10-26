package com.baeldung.scala.zio

import zio.{ZIOAppDefault, _}

import scala.io.Source

object AcquireReleaseExample extends ZIOAppDefault {
  def run =
    for {
      content <- ZIO.acquireReleaseWith(ZIO.attemptBlocking(Source.fromResource("file.txt")))(
        file => ZIO.attempt(file.close()).orDie
      ) {
        file => ZIO.attemptBlocking(file.getLines().mkString("\n"))
      }
      _ <- Console.printLine("File content:")
      _ <- Console.print(content)
    } yield ()
 
}
