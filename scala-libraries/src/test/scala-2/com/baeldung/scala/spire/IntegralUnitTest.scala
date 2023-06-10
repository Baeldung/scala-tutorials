package com.baeldung.scala.spire

import com.baeldung.scala.spire.Integral.factorial
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import spire.implicits._

class IntegralUnitTest extends AnyFlatSpec with Matchers {

  "factorial" should "return the correct factorial for an integer" in {
    val number = 5
    val expectedFactorial = 120

    factorial(number) should be(expectedFactorial)
  }

  "factorial" should "return the correct factorial for a BigInt" in {
    val number = BigInt(10)
    val expectedFactorial = BigInt("3628800")

    factorial(number) should be(expectedFactorial)
  }

}
