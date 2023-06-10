package com.baeldung.scala.spire

import com.baeldung.scala.spire.Fractional.avg
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import spire.implicits._
import spire.math.Rational

class FractionalUnitTest extends AnyFlatSpec with Matchers {

  "avg" should "return the correct average for a list of doubles" in {
    val doubleList = List(1.5, 2.5, 3.5, 4.5, 5.5)
    val expectedAverage = 3.5

    avg(doubleList) should be(expectedAverage)
  }

  "avg" should "return the correct average for a list of rationals" in {
    val rationalList = List(Rational(1, 3), Rational(2, 3), Rational(3, 3), Rational(4, 3), Rational(5, 3))
    val expectedAverage = Rational(15, 15)

    avg(rationalList) should be(expectedAverage)
  }

  "avg" should "return the correct average for a list of decimals" in {
    val decimalList = List(BigDecimal("1.25"), BigDecimal("2.25"), BigDecimal("3.25"), BigDecimal("4.25"), BigDecimal("5.25"))
    val expectedAverage = BigDecimal("3.25")

    avg(decimalList) should be(expectedAverage)
  }

}
