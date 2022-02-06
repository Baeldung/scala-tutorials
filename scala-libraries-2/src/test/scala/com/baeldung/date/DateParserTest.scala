package com.baeldung.date

import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec

class DateParserTest extends AnyFunSpec with GivenWhenThen {
  val parser = new DateParser
  describe("simple parsing") {
    Given("a very simple fixed format, known in advance")
    // the format is "yyyy/mm/dd"
    When("the date matches the format")
    val maybeDate = parser.simpleParse("2022/02/14")
    Then("the elements of the date can be retrieved")
    assert(maybeDate.isDefined)
    assert(maybeDate.get.year == 2022)
    // in the case class, elements are 0-based:
    assert(maybeDate.get.month == 2 - 1)
    assert(maybeDate.get.month == 14 - 1)
    When("the date includes unexpected elements (time)")
    val maybeDateTime = parser.simpleParse("2022/02/14T20:30:00")
    Then("the elements of the date are not retrievable")
    assert(maybeDate.isEmpty)
  }

  describe("regular expression parsing") {
    Given("a regular expression representing a date")
    When("the date matches the regular expression")
    Then("the elements of the date can be retrieved")
    When("the date includes unexpected elements (time)")
    Then("the elements of the date are still retrievable")
  }

  describe("library-supported date parsing") {
    Given("the standard java library for date and time")
    When("a rather complex date/time representation is passed")
    Then("the elements of the date/time can be retrieved")
    When("an invalid date/time representation is passed")
    Then("the elements of the date are not retrievable")
  }
}
