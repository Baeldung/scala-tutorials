package com.baeldung.scala.strings.removemultispace

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class RemoveMultipleSpacesUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val table = Table(
    ("input string", "expected"),
    ("  too many    spaces    ", "too many spaces"),
    ("     ", ""),
    ("a", "a"),
    ("a ", "a"),
    ("", "")
  )

  private val functions = Seq(
    ("usingReplaceAll", RemoveMultipleSpaces.usingReplaceAll),
    ("usingSplit", RemoveMultipleSpaces.usingSplit),
    ("usingZip", RemoveMultipleSpaces.usingZip),
    ("usingStringBuilder", RemoveMultipleSpaces.usingStringBuilder)
  )
  it should "remove multiple spaces with a single space in the string" in {
    forAll(table) { (input, expected) =>
      functions.map { (name, fn) =>
        withClue(
          s"Failed for the input string ${input} in the function ${name}"
        ) {
          fn(input) shouldBe expected
        }
      }
    }
  }

}
