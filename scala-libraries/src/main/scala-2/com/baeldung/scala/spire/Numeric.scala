package com.baeldung.scala.spire

import spire.implicits._
import spire.math._
object Numeric {
  def sum[T](values: List[T])(implicit numeric: Numeric[T]): T = {
    values.foldLeft(numeric.zero)(numeric.plus)
  }
}
