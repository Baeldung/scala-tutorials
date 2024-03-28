package com.baeldung.scala.spire.numbers

import spire.implicits._
import spire.math._

import java.math.RoundingMode

object Rationals extends App {
  private val rational = Rational(1) / Rational(3)
  private val approximation = rational.toBigDecimal(5, RoundingMode.UP)
  println(s"""Rational = $rational""")
  println(s"""Rational approximation = $approximation}""")
}
