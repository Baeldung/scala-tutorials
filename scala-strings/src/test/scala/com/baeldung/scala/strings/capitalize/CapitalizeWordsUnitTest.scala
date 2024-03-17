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

}
