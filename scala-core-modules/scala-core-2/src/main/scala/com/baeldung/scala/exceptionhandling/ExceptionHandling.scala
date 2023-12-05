package com.baeldung.scala.exceptionhandling

import scala.util.Try

object ExceptionHandling {

  case class DivideByZero() extends Exception

  def divide(dividend: Int, divisor: Int): Int = {
    if (divisor == 0) {
      throw new DivideByZero
    }

    dividend / divisor
  }

  /** Exception handling by try/catch/finally
    */
  def divideByZero(a: Int): Any = {
    try {
      divide(a, 0)
    } catch {
      case _: DivideByZero => null
    } finally {
      println("Finished")
    }
  }

  /** Exception handling by Option/Some/None
    */
  def divideWithOption(dividend: Int, divisor: Int): Option[Int] = {
    if (divisor == 0) {
      None
    } else {
      Some(dividend / divisor)
    }
  }

  /** Exception handling by Try/Success/Failures
    */
  def divideWithTry(dividend: Int, divisor: Int): Try[Int] = Try(
    divide(dividend, divisor)
  )

  /** Exception handling by Either/Left/Right
    */
  def divideWithEither(dividend: Int, divisor: Int): Either[String, Int] = {
    if (divisor == 0) {
      Left("Can't divide by zero")
    } else {
      Right(dividend / divisor)
    }
  }

}
