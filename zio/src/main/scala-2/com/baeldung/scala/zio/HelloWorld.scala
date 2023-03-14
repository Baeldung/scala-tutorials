package com.baeldung.scala.zio

import zio._

import java.io.IOException

object HelloWorld extends ZIOAppDefault {
  val myApp: ZIO[Any, IOException, Unit] =
    Console.printLine("Hello, World!")

  def run = myApp
}
