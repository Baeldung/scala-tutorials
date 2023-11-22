package com.baeldung.scala.inlining

object MegamorphicCallsite extends App {

  def execute(fun: Int => Int)(n: Int): Int = {
    fun(n) // callsite
  }

  val f1 = { n: Int => n * n }
  val f2 = { n: Int => n + n }
  val f3 = { n: Int => n }

  execute(f1)(5)
  execute(f2)(5)
  execute(f3)(5)
}
