package com.baeldung.scala.spire

import spire.implicits._
import spire.math.Fractional

object Fractional {
  def avg[T](values: List[T])(implicit fractional: Fractional[T]): T = {
    val sum = values.foldLeft(fractional.zero)(fractional.plus)
    fractional.div(sum, fractional.fromInt(values.length))
  }
}
