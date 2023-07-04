package com.baeldung.scala.spire.rings

import spire.algebra._

object Rings {
  implicit val IntRingInstance: Ring[Int] = new Ring[Int] {
    def zero: Int = 0

    def one: Int = 1

    def plus(x: Int, y: Int): Int = x + y

    def times(x: Int, y: Int): Int = x * y

    def negate(x: Int): Int = -x
  }

  def square(ints: List[Int]): Seq[Int] = {
    ints.map(i => Ring[Int].times(i, i))
  }

  def sum(ints: List[Int]): Int = {
    ints.foldLeft(Ring[Int].zero)(Ring[Int].plus)
  }
}
