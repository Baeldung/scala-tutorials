package com.baeldung.scala.spire.numbers

import spire.implicits._
import spire.math._

object Complexes extends App {
  
  private def quadraticRoots(x: Complex[Double], y: Complex[Double], z: Complex[Double]): (Complex[Double], Complex[Double]) = {
    val discriminant = y * y - Complex(4.0) * x.*(z)
    val sqrtDiscriminant = discriminant.sqrt
    val r1 = (-y + sqrtDiscriminant) / (Complex(2.0) * x)
    val r2 = (-y - sqrtDiscriminant) / (Complex(2.0) * x)
    (r1, r2)
  }

  val x = Complex(2.0, 0.0)
  val y = Complex(-3.0, 4.0)
  private val z = Complex(5.0, -6.0)

  private val (r1, r2) = quadraticRoots(x, y, z)
  println(s"Root 1: $r1")
  println(s"Root 2: $r2")

}
