package com.baeldung.scala.spire.monoids

import spire.algebra._
import spire.implicits._

object AdditiveMonoid extends App {
  def sum[T](values: List[T])(implicit ev: AdditiveMonoid[T]): T =
    values.foldLeft(ev.zero)(ev.plus)
}
