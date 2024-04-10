package com.baeldung.scala.digits

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class IndividualDigitsUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val table = Table(
    ("Number", "Digits"),
    (987654321L, List(9, 8, 7, 6, 5, 4, 3, 2, 1)),
    (123456L, List(1, 2, 3, 4, 5, 6)),
    (1L, List(1)),
    (0L, List(0))
  )

  it should "get individual digits of a number using getDigitsByTailRecursion" in {
    forAll(table) { (number, digits) =>
      val result = IndividualDigits.getDigitsByTailRecursion(number)
      result shouldBe digits
    }
  }

  it should "get individual digits of a number using getDigitsByToString" in {
    forAll(table) { (number, digits) =>
      val result = IndividualDigits.getDigitsByToString(number)
      result shouldBe digits
    }
  }

  it should "get individual digits of a number using getDigitsByRecursion" in {
    forAll(table) { (number, digits) =>
      val result = IndividualDigits.getDigitsByRecursion(number)
      result shouldBe digits
    }
  }
}
