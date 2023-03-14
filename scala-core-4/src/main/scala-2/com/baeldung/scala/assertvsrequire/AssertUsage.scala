package com.baeldung.scala.assertvsrequire

object AssertUsage extends App {

  private def squareNumCalculation(n: Int): Int = n * n
  def getSquareOfNumber(n: Int): Int = {
    assert(squareNumCalculation(2) == 4)
    val squaredNum = squareNumCalculation(n)
    return squaredNum
  }

  private def squareNumCalculationWithError(n: Int): Int =
    n * -1 // This is wrong calculation, assertion fails
  def getSquareOfNumberWithError(n: Int): Int = {
    assert(squareNumCalculationWithError(2) == 4)
    val squaredNumWithError = squareNumCalculationWithError(n)
    return squaredNumWithError
  }

}
