package com.baeldung.scala.spire.groups

import spire.algebra._
import spire.implicits._

object Semigroups extends App {
  val a: Int = 5
  val b: Int = 7
  val result: Int = Semigroup[Int].combine(a, b)
}
