package com.baeldung.scala.braces

object DoubleEdgedSword extends App {
  def process(value: Int): Int = value * 2

  val result = process {
    1 + 2
    5 // This is the actual integer passed to process
  }

  println(result)
}
