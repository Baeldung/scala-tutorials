package com.baeldung.scala.magicnumber

import com.baeldung.scala.magicnumber.MagicNumber.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec

class MagicNumberUnitTest
  extends AnyWordSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val sumFunctions = List(
    sumOfDigitsUsingRecursion,
    sumOfDigitsUsingAsDigit,
    sumOfDigitsUsingFold,
    sumOfDigitsUsingIterator
  )
  private val table = Table(
    ("number", "sum", "functions", "isMagicNumber"),
    (100, 1, sumFunctions, true),
    (99, 9, sumFunctions, false),
    (0, 0, sumFunctions, false),
    (1, 1, sumFunctions, true),
    (1234, 1, sumFunctions, true),
    (98765, 8, sumFunctions, false),
    (Integer.MAX_VALUE, 1, sumFunctions, true) // 2147483647
  )

  forAll(table) { (number, sum, sumFns, isMagic) =>
    "Sum of digit calculation method" should {
      s"[No: $number] calculate the sum of digits till single digit correctly" in {
        sumFns.map { fn =>
          fn(number) shouldBe sum
        }
      }
    }
    "Magic number checker" should {
      s"[No: $number] return true/false if the input is a magic number" in {
        sumFns.map { fn =>
          isMagicNumber(fn)(number) shouldBe isMagic
        }
      }
    }
  }
}
