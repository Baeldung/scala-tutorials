package com.baeldung.scala.cats.semigroups

import cats.instances.int._
import cats.kernel.Semigroup

object IntSemigroup {
  def defaultIntSemigroup(int1: Int, int2: Int): Int = {
    Semigroup[Int].combine(int1, int2)
  }
}
