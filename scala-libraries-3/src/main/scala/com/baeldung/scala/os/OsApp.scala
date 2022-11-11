package com.baeldung.scala.os

object OsApp extends App {
  val ls = os.proc("ls", "-l").call()
  println(ls.exitCode)
  println(ls.out.text())
  println(ls.err.text())

  os.proc("ls", "-l", "dir").call()

  os.proc("ls", "-l", "dir").call(check = false)

  val lines = os.read(os.pwd / "LICENSE").linesIterator.size
}
