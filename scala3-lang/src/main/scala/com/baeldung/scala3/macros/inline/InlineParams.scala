package com.baeldung.scala3.macros.inline
import scala.util.Random
object InlineParams {
  inline def show(inline a: Int) = {
    println("Value of a = " + a)
    println("Again value of a = " + a)
  }
  @main def inlineParamsMain = show(Random.nextInt())
}
