package com.baeldung.scala.firstclassfunctions

object Currying {
  val multiplication: (Int, Int) => Int = (x, y) => x * y
  val curriedMultiplication: Int => Int => Int = x => y => x * y
  val conciseCurriedMultiplication: Int => Int => Int = multiplication.curried

  def addition(x: Int, y: Int): Int = x + y

  def curriedAddition(x: Int)(y: Int): Int = x + y
}
