package com.baeldung.scala.cats.semigroups

import cats.Semigroup

object CustomIntSemigroup {
  implicit val multiplicationSemigroup: Semigroup[Int] = (x: Int, y: Int) =>
    x * y

  def multiply(int1: Int, int2: Int): Int = {
    Semigroup[Int].combine(int1, int2)
  }
}
