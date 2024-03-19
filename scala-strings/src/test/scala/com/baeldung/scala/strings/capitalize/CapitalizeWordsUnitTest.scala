package com.baeldung.scala.strings.capitalize

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class CapitalizeWordsUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  val table = Table(
    ("Input", "Expected"),
    ("This is scala 3", "This Is Scala 3"),
    ("hello world", "Hello World"),
    ("baeldung   articles", "Baeldung Articles"),
    ("   ", ""),
    ("1000", "1000")
  )
  it should "capitalize every words of a sentence" in {
    forAll(table) { (input, expected) =>
      CapitalizeWords.capitalizeWords(input) shouldBe expected
    }
  }

  val tableWithExclusions = Table(
    ("Input", "Expected"),
    ("This is scala 3", "This is Scala 3"),
    ("baeldung   articles", "Baeldung Articles"),
    ("   ", ""),
    ("the quick brown fox jumps over the lazy Dog", "The Quick Brown Fox Jumps Over the Lazy Dog")
  )
  it should "capitalize every word of a sentence with exclusion" in {
    forAll(tableWithExclusions) { (input, expected) =>
      CapitalizeWords.capitalizeTitleCase(input) shouldBe expected
    }
  }

}
