package com.baeldung.scala.firstclassfunctions

object Closure {
  val rate = 0.10
  val time = 2
  def calcSimpleInterest(principal: Double): Double = {
    (principal * rate * time) / 100
  }
}
