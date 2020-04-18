package com.baeldung.scala.exceptionhandling

import scala.util.{Try, Success, Failure}
import scala.util.control.Exception._

object Examples {
  import CalculatorExceptions._
  def tryCatch(a: Int, b: Int): Int = {
    try {
      return Calculator.sum(a, b)
      // println(s"${a} + ${b} = ${result}")
    } catch {
      case e: IntOverflowException    => -1
      case e: NegativeNumberException => -2
    } finally {
      // This block will always be invoked
      println("Calculation done!")
    }
  }

  def trySuccessFailure(a: Int, b: Int): Try[Int] = Try {
    Calculator.sum(a, b)
  }

  def catchObjects(a: Int, b: Int): Try[Int] = allCatch.withTry {
    Calculator.sum(a, b)
  }

  val myCustomCatcher = catching(classOf[NegativeNumberException])

  def customCatchObjects(a: Int, b: Int): Try[Int] = myCustomCatcher.withTry {
    Calculator.sum(a, b)
  }
}
