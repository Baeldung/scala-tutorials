package com.baeldung.scala.uniformcase

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import UniformCaseChecker.*

class UniformCaseCheckerUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val fns =
    Seq(convertAndCheck, isUpperLowerAndForAll, regexCheck, countAndCheck)

  private val table = Table(
    ("Input", "Expected Result"),
    ("BAELDUNG @ 2024", true),
    ("baeldung @ 2024", true),
    ("Baeldung @ 2024", false),
    ("2024 @@@ ", true),
    ("          ", true)
  )

  it should "check if all characters are upper or lower" in {
    fns foreach { fn =>
      forAll(table) { (input, expected) =>
        withClue("for string: " + input) {
          fn(input) shouldBe expected
        }
      }
    }
  }
}
