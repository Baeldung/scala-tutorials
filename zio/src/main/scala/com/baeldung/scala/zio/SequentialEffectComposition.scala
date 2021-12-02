package com.baeldung.scala.zio

import zio.ZIOAppDefault
import zio.Console

object SequentialEffectComposition extends ZIOAppDefault {
  def run =
    for {
      _ <- Console.printLine("Hello! What is your name?")
      n <- Console.readLine
      _ <- Console.printLine("Hello, " + n + ", good to meet you!")
    } yield ()
}
