package com.baeldung.scala.nested

object Factorial {
  def factorial(num:Int): Int = {
    def recursiveFactorial(num: Int): Int = {
      num match {
        case i if i <= 0 => throw new Exception("Enter a number greater than 0")
        case 1 => 1
        case n => n * recursiveFactorial(num-1)
      }
    }
    recursiveFactorial(num)
  }
}
