package com.baeldung.scala.twoscomplement

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TwosComplementTest extends AnyFlatSpec with Matchers {

  val testExamples = List(
    ("00110100", "11001100"),
    ("00110101", "11001011"),
    ("01111101", "10000011"),
    ("00000000", "00000000"),
    ("01111111", "10000001")
  )

  "convertWithFold" should "convert binary to twos complement" in {
    val comparer = compare(TwosComplement.convertWithFold)
    comparer(testExamples)
  }

  "convertUsingRecursion" should "convert binary to twos complement" in {
    val comparer = compare(TwosComplement.convertUsingRecursion)
    comparer(testExamples)
  }

  private def compare(
    fn: String => String
  )(tests: List[(String, String)]): Unit = {
    tests.foreach((input, expected) => fn(input) shouldBe expected)
  }
}
