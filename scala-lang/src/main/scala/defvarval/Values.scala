package com.baeldung.scala.defvarval

object Values extends App {
  val value: Int = {
    println("value")
    1
  }

  println("After value declaration")
  println(value)
  println(value)
}
