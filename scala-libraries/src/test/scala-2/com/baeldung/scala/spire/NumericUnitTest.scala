package com.baeldung.scala.spire

import com.baeldung.scala.spire.Numeric.sum
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spire.implicits._
import spire.math._

class NumericUnitTest extends AnyWordSpec with Matchers {
  "sum" should {

    "return the correct sum for a list of integers" in {
      val intList = List(1, 2, 3, 4, 5)
      val expectedSum = 15

      sum(intList) should be(expectedSum)
    }

    "return the correct sum for a list of doubles" in {
      val doubleList = List(1.5, 2.5, 3.5, 4.5, 5.5)
      val expectedSum = 17.5

      sum(doubleList) should be(expectedSum)
    }

    "return the correct sum for a list of BigDecimals" in {
      val bigDecimalList =
        List(BigDecimal(1.2), BigDecimal(2.3), BigDecimal(3.4), BigDecimal(4.5))
      val expectedSum = BigDecimal(11.4)

      sum(bigDecimalList) should be(expectedSum)
    }

    "return the correct sum for a list of Rationals" in {
      val rationalList = List(Rational(1, 3), Rational(1, 6), Rational(1, 2))
      val expectedSum = Rational(1)

      sum(rationalList) should be(expectedSum)
    }
  }
}
