package com.baeldung.scala.spire

import com.baeldung.scala.spire.Integral.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spire.implicits._

class IntegralUnitTest extends AnyWordSpec with Matchers {

  "factorial" should {

    "calculate the factorial for an integer" in {
      val number = 5
      val expectedFactorial = 120

      factorial(number) should be(expectedFactorial)
    }

    "calculate the factorial for a BigInt" in {
      val number = BigInt(10)
      val expectedFactorial = BigInt("3628800")

      factorial(number) should be(expectedFactorial)
    }
  }
  "gcd" should {

    "compute Int GCD" in {
      val gcdInt = gcd(3, 4)

      gcdInt shouldEqual 1
    }

    "compute BigInt GCD" in {
      val gcdBigInt =
        gcd(BigInt("123459876543254321"), BigInt("34512678923459870"))

      gcdBigInt should be(BigInt("7"))
    }
    "compute Long GCD" in {
      val gcdLong = gcd(34L, 566L)

      gcdLong should be(2)
    }
  }
}
