package com.baeldung.scala.firstclassfunctions

object FunctionAsArgumentInHOF {
  def calcAnything(number: Int, calcFunction: Int => Int): Int = calcFunction(number)

  def calcSquare(num: Int): Int = num * num

  def calcCube(num: Int): Int = num * num * num
}
