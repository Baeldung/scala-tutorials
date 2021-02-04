package com.baeldung.scala.assertvsrequire

object AssertUsage extends App {

  private def squareNum(n: Int):Int = n * n

  def getSquareOfNumber(n: Int):Int = {
    assert(squareNum(2) == 4)
    val squaredNum = n * n
    return squaredNum
  }

  private def squareNumWithError(n: Int):Int = n * -1 // This is wrong calculation, fails in assertion

  def getSquareOfNumberWithError(n: Int):Int = {
    assert(squareNumWithError(2) == 4)
    val squaredNumWithError = squareNumWithError(n)
    return squaredNumWithError
  }

}
