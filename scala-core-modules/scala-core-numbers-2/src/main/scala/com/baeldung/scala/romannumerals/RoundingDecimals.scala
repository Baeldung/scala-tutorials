package com.baeldung.scala.romannumerals

import scala.annotation.tailrec
import scala.math.BigDecimal.int2bigDecimal
import scala.util.FromDigits.Decimal

object RoundingDecimals {
  def roundUp(decimal: Double): Int =
    math.ceil(decimal).toInt

  def roundDown(decimal: Double): Int =
    math.floor(decimal).toInt

  def roundToNearestWhole(decimal: Double): Int =
    math.round(decimal).toInt
}
