package com.baeldung.scala.defvarval

object LazyValues extends App {
  lazy val lazyValue: Int = {
    println("lazy value")
    1
  }

  println("After lazy value declaration")
  println(lazyValue)
  println(lazyValue)
}
