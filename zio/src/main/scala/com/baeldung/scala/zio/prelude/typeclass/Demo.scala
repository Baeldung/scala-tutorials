package com.baeldung.scala.zio.prelude.typeclass

import com.baeldung.scala.zio.prelude.typeclass.Printable._

object Demo extends App {
  private def printAny[A](value: A)(implicit printable: Printable[A]): Unit = {
    println(printable.format(value))
  }

  printAny("Hello") // Prints "Hello"
  printAny(10) // Prints "10"
}
