package com.baeldung.scala.romannumeral

import com.baeldung.scala.romannumerals.NumberToRomanNumeral
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec

class NumberToRomanNumeralTest
  extends AnyWordSpec
  with Matchers
  with TableDrivenPropertyChecks {
  val testValuesWithResults = Table(
    ("Number", "Roman"),
    (0, ""),
    (1, "I"),
    (2, "II"),
    (4, "IV"),
    (5, "V"),
    (6, "VI"),
    (9, "IX"),
    (10, "X"),
    (11, "XI"),
    (14, "XIV"),
    (15, "XV"),
    (17, "XVII"),
    (19, "XIX"),
    (20, "XX"),
    (40, "XL"),
    (44, "XLIV"),
    (49, "XLIX"),
    (50, "L"),
    (90, "XC"),
    (100, "C"),
    (400, "CD"),
    (500, "D"),
    (900, "CM"),
    (1000, "M"),
    (1949, "MCMXLIX")
  )

  "Correction roman numeral should be returned for number" should {
    "using usingRecursion" in {
      forAll(testValuesWithResults)((num, roman) => {
        NumberToRomanNumeral.usingRecursion(num) shouldBe roman
      })
    }

    "using usingTailRecursion" in {
      forAll(testValuesWithResults)((num, roman) => {
        NumberToRomanNumeral.usingTailRecursion(num) shouldBe roman
      })
    }

    "using usingFold" in {
      forAll(testValuesWithResults)((num, roman) => {
        NumberToRomanNumeral.usingFold(num) shouldBe roman
      })
    }
  }

}
