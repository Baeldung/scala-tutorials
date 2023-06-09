package com.baeldung.scala.spire.monoids

import spire.algebra._
import spire.implicits._
import spire.math._


object AdditiveMonoid extends App {
  def sum[T](values: List[T])(implicit ev: AdditiveMonoid[T]): T =
    values.foldLeft(ev.zero)(ev.plus)

  private def gcd[T](a: T, b: T)(implicit ev: Integral[T]) = ev.gcd(a, b)

  println(s"GCD (Int): ${gcd(3, 4)}," +
    s" GCD (BigInt): ${gcd(BigInt("123459876543254321"), BigInt("34512678923459870"))}" +
    s"GCD (Long): ${gcd(34L, 566L)}")


  private val integers = List(1, 2, 3, 4, 5)
  private val decimals = List(BigDecimal(1.5), BigDecimal(2.5), BigDecimal(3.5))

  private val sumInt = sum(integers)
  private val sumBigDecimal = sum(decimals)

  println(s"SUM (Int): $sumInt, SUM (BigDecimal): $sumBigDecimal")
}
