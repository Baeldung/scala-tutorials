package com.baeldung.scala.spire.numbers

import spire.implicits._
import spire.math._

object Reals extends App {
  private val PI = Real(16) * atan(Real(Rational(1, 5))) - Real(4) * atan(Real(Rational(1, 239)))
  println(s"""Machin-like Approximation of PI = $PI""")

}
