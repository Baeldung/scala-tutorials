package com.baeldung.scala.spire.rings

import spire.algebra._
import spire.implicits._

object EuclideanRings {
  def equotient(dividend: Int, divisor: Int): Int =
    EuclideanRing[Int].equot(dividend, divisor)
  def equotientmod(dividend: Int, divisor: Int): (Int, Int) =
    EuclideanRing[Int].equotmod(dividend, divisor)
}
