package com.baeldung.scala.os

import scala.io.Source
import scala.sys.process._

object ScalaSysApp extends App {
  val output = "ls -l".!!
  println(output)

  // This will print the standard output of the "ls -l command", even though we didn't ask for it!
  val result = Seq("ls", "-l").!

  val outputErr = "ls -l dir".!!
  val resultErr = "ls -l dir".!

  val source = Source.fromFile("./LICENSE")
  val lines = source.getLines().size
  source.close()
}
