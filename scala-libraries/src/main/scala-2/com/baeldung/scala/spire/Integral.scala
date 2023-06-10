package com.baeldung.scala.spire

import spire.implicits._

object Integral {
  def factorial[@specialized(Int) T](
    n: T
  )(implicit integral: Integral[T]): T = {
    if (integral.lt(n, integral.one)) integral.one
    else integral.times(n, factorial(integral.minus(n, integral.one)))
  }
}
