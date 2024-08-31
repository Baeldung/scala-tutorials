package com.baeldung.scala.roundingdecimals

object RoundingDecimals {
  def roundUp(decimal: Double): Int =
    math.ceil(decimal).toInt

  def roundDown(decimal: Double): Int =
    math.floor(decimal).toInt

  def roundToNearestWhole(decimal: Double): Int =
    math.round(decimal).toInt
}
