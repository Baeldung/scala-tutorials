package com.baeldung.scala.assertions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.exceptions.TestFailedException

class AssertionsTests extends AnyFlatSpec with Matchers {

  def parseAge(ageString: String): Int = {
    if (ageString.isEmpty)
      throw new IllegalArgumentException(
        "Cannot convert and empty String to an age"
      )
    val age = ageString.toInt
    if (age < 0) throw new IllegalArgumentException("Age cannot be negative")
    age
  }

  "An assert" should "pass when the condition is true" in {
    val sum = 1 + 1
    assert(sum == 2)
  }

  "assertResult" should "pass when the code block produces the expected value" in {
    assertResult(7) {
      val sum = 3 + 4
      sum
    }
  }

  "assertThrows" should "pass when the correct exception type is thrown" in {
    assertThrows[IllegalArgumentException] {
      parseAge("-5")
    }
  }

  "withClue" should "provide additional information in case of test failure" in {
    withClue(
      "Parsing an empty input should throw an IllegalArgumentException: "
    ) {
      try {
        parseAge("")
      } catch {
        case e: IllegalArgumentException =>
          assert(
            e.getMessage().equals("Cannot convert and empty String to an age")
          )
      }
    }
  }
}
