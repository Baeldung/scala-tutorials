package com.baeldung.scala.exceptionhandling

object CalculatorExceptions {
  class IntOverflowException extends RuntimeException
  class NegativeNumberException extends RuntimeException
}

object Calculator {
  import CalculatorExceptions._

  def sum(a: Int, b: Int): Int = {
    if (a < 0 || b < 0) throw new NegativeNumberException
    val result = a + b
    if (result < 0) throw new IntOverflowException
    result
  }
}
