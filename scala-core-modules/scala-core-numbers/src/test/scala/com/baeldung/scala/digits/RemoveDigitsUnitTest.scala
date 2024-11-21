package com.baeldung.scala.digits

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RemoveDigitsUnitTest extends AnyFlatSpec with Matchers {

  val number = 123456
  val negativeNumber = -number

  "naive" should "return remove first N digits" in {
    RemoveDigits.removeFirstNDigits(number, 2) shouldBe 3456
    RemoveDigits.removeFirstNDigits(number, 5) shouldBe 6
  }

  "limit" should "return remove first N digits" in {
    RemoveDigits.removeFirstNDigitsLimit(number, 2) shouldBe 3456
    RemoveDigits.removeFirstNDigitsLimit(number, 5) shouldBe 6
    RemoveDigits.removeFirstNDigitsLimit(number, 6) shouldBe 0
  }

  "patternMatching" should "return remove first N digits" in {
    RemoveDigits.removeFirstNDigitsPatternMatching(number, 2) shouldBe 3456
    RemoveDigits.removeFirstNDigitsPatternMatching(number, 5) shouldBe 6
    RemoveDigits.removeFirstNDigitsPatternMatching(number, 6) shouldBe 0
  }

  "negative" should "return remove first N digits" in {
    RemoveDigits.removeFirstNDigitsNegative(number, 2) shouldBe 3456
    RemoveDigits.removeFirstNDigitsNegative(number, 5) shouldBe 6
    RemoveDigits.removeFirstNDigitsNegative(number, 6) shouldBe 0
    RemoveDigits.removeFirstNDigitsNegative(negativeNumber, 2) shouldBe -3456
  }

}
