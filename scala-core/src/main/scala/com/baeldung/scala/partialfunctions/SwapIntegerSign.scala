package com.baeldung.scala.partialfunctions

object SwapIntegerSign {

  private val negativeOrZeroToPositive: PartialFunction[Int, Int] = {
    case x if x <= 0 => Math.abs(x)
  }

  private val positiveToNegative: PartialFunction[Int, Int] = {
    case x if x > 0 => -1 * x
  }

  val swapSign: PartialFunction[Int, Int] = {
    positiveToNegative orElse negativeOrZeroToPositive
  }

  val printIfPositive: PartialFunction[Int, Unit] = {
    case x if x > 0 => println(s"$x is positive!")
  }
}
