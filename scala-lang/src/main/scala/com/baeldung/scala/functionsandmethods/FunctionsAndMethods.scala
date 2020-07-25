package com.baeldung.scala.functionsandmethods

import scala.annotation.tailrec

object FunctionsAndMethods {
  // Anonymous function execution
  def anonymousFunctionUsage(anonymous: (Int) => Int): Int = {
      anonymous(10)
  }

  // Anonymous function execution
  def anonymousFunctionUsageWithApply(anonymous: (Int) => Int): Int = {
    anonymous.apply(10)
  }

  // Method with one parameter
  def inc(number: Int): Int = number + 1

  // Method with parameter by-value
  def byValue(num: Int): (Int, Int) = (num, num)

  // Method with parameter by-name, take a look on => sign before Type of parameter
  // That is how we define a by-name parameters
  def byName(num: => Int): (Int, Int) = (num, num)

  // Here we defined a Value Class with extension method
  implicit class IntExtension(val value: Int) extends AnyVal { def isOdd: Boolean = value % 2 == 0 }


  def plot(f: Double => Double): List[Double] = {
    val xs: Range = -10 to 10
    xs.map(x =>  f(x)).toList
  }

  val lines: (Double, Double, Double) => Double = (a,b,x) => a * x + b

  val line: (Double, Double) => Double => Double = (a,b) => x => lines(a,b,x)

  def factorial(num: Int): Int = {
    @tailrec
    def fact(num: Int, acc: Int): Int = {
      if (num == 0) acc else fact(num - 1, acc * num)
    }

    fact(num, 1)
  }

  def pop[T](seq: Seq[T]): T = seq.head
}


