package com.baeldung.scala.strings.introduction

import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.util.matching.Regex

class UsingRegexTest extends FeatureSpec with GivenWhenThen {

  scenario("Match with regular expression if a string contains letters and numbers") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior of string regular expression")

    Given("a string with letters and numbers")
    val testString = "this is a string with numbers 123456"

    Given("regular expression for matching if a string contains letters and numbers")
    val regularExpression: Regex = "^(?=.*[a-zA-Z])(?=.*[0-9])".r

    When("when we apply the regex")
    val result = regularExpression.findFirstMatchIn(testString).isDefined

    Then("the result will be true because testString contains both letters and numbers")
    assert(result == true)
  }

  scenario("Match groups of regular expression to a multistring") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior of mathing string with group of regular expressions")

    Given("a multi-string with a list of key values")
    val testString =
      """property1: value1
        |property2: value2
        |property3: value3
        |property
        |"""

    Given("regular expression for matching if a string contains letters and numbers")
    val groupOfRegularExpressions: Regex = "([0-9a-zA-Z- ]+): ([0-9a-zA-Z-#()/. ]+)".r

    When("when we apply the regex")
    val result: Iterator[Regex.Match] = groupOfRegularExpressions.findAllMatchIn(testString)

    val matchResult = groupOfRegularExpressions
      .findAllMatchIn(testString)

    Then("the result will be true because testString contains both letters and numbers")
    val expected = """key: property1 value: value1
      |key: property2 value: value2
      |key: property3 value: value3"""
      .stripMargin

    val matchedStrings = matchResult
      .map(regularExpressionMatch => s"key: ${regularExpressionMatch.group(1)} value: ${regularExpressionMatch.group(2)}")
      .mkString(System.lineSeparator)

    assert(matchedStrings.equals(expected))
  }
}
