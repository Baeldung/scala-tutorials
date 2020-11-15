package com.baeldung.scala.implicitly

object ImplicitlyUsage {

  implicit val G: Double = 9.81

  def weight(mass: Double, gravitationalConstant: Double): Double =
    mass * gravitationalConstant

  def weightUsingImplicit(mass: Double)(implicit gravitationalConstant: Double): Double =
    weight(mass, gravitationalConstant)

  def weightUsingImplicitly(mass: Double): Double = {
    val gravitationalConstant = implicitly[Double]
    weight(mass, gravitationalConstant)
  }
}
