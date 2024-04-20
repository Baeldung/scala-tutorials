package com.baeldung.scala.strings.removespecialchars

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class RemoveSpecialCharactersUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  def removeAllSpecialCharUsingRegex(text: String): String = {
    text.replaceAll("[^a-zA-Z0-9]", "")
  }

  def removeSomeSpecialCharUsingRegex(text: String): String = {
    text.replaceAll("[^a-zA-Z0-9_]", "")
  }

  def removeSpecialCharUsingWordRegex(text: String): String = {
    text.replaceAll("\\W", "")
  }

  def removeSpecialCharUsingAnotherWordRegex(text: String): String = {
    text.replaceAll("[^\\w]", "")
  }

  def removeAllSpecialCharsUsingFilter(text: String): String = {
    text.filter(_.isLetterOrDigit)
  }

  def removeSpecialCharsUsingCollect(text: String): String = {
    text.collect {
      case c if c.isLetterOrDigit || Set(' ', '_').contains(c) => c
    }
  }

  private val removeAllSpecialTable = Table(
    ("text", "expected"),
    ("Special_Chars**Text $#^{} Here 2", "SpecialCharsTextHere2"),
    ("    ", ""),
    ("!@#$....     ", ""),
    ("baeldung", "baeldung")
  )

  it should "remove all the special characters using regex" in {
    forAll(removeAllSpecialTable) { (text, expected) =>
      removeAllSpecialCharUsingRegex(text) shouldBe expected
    }
  }

  it should "remove all the special characters using filter" in {
    forAll(removeAllSpecialTable) { (text, expected) =>
      removeAllSpecialCharsUsingFilter(text) shouldBe expected
    }
  }

  it should "remove all special characters using removeAllSpecialCharUsingRegex" in {
    assert(
      removeAllSpecialCharUsingRegex("Hello Baeldung_!") == "HelloBaeldung"
    )
  }

  it should "remove all special characters using removeAllSpecialCharsUsingFilter" in {
    assert(
      removeAllSpecialCharsUsingFilter("Hello Baeldung_!") == "HelloBaeldung"
    )
  }

  it should "remove some special characters using removeSomeSpecialCharUsingRegex" in {
    assert(
      removeSomeSpecialCharUsingRegex("Hello Baeldung_!") == "HelloBaeldung_"
    )
  }

  it should "remove special characters using removeSpecialCharUsingWordRegex" in {
    assert(
      removeSpecialCharUsingWordRegex(
        "Hello Baeldung_*()!"
      ) == "HelloBaeldung_"
    )
  }

  it should "remove special characters using removeSpecialCharsUsingCollect" in {
    assert(
      removeSpecialCharsUsingCollect("Hello Baeldung_*()!") == "Hello Baeldung_"
    )
  }

  it should "remove special characters using removeSpecialCharUsingAnotherWordRegex" in {
    assert(
      removeSpecialCharUsingAnotherWordRegex(
        "Hello Baeldung_*()!"
      ) == "HelloBaeldung_"
    )
  }

  it should "filter only required character from string" in {
    val text = "Hello Baeldung_!*&$"
    val sanitized =
      text.filter(c => c.isLetterOrDigit || Set(' ', '_').contains(c))
    assert(sanitized == "Hello Baeldung_")
  }

}
