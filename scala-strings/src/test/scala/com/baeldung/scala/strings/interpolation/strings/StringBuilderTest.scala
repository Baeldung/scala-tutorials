package com.baeldung.scala.strings.interpolation.strings

import org.scalatest.{FeatureSpec, GivenWhenThen}

class StringBuilderTest extends FeatureSpec with GivenWhenThen {

  scenario("The string builder may create a string as expected") {

    info("As a programmer")
    info("I want to demonstrate how to instantiate a StringBuilder object and get a String")

    Given("an instantiated with \"Website: \" a StringBuilder")
    val stringBuilder = new StringBuilder("Website: ")

    When("we append a \"Baeldung.\" string")
    stringBuilder.append("Baeldung.")

    Then("the result will be as expected \"Website: Baeldung.\"")
    val result = stringBuilder.toString
    assert(result == "Website: Baeldung.")
  }

  scenario("The string builder may append many types to the buffer and create a string as expected") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior of StringBuilder append")

    Given("an initial StringBuilder")
    val helloBuilder = new StringBuilder("hello ")
    val worldBuilder = new StringBuilder("world")

    When("we append types to the buffer")
    helloBuilder
      .append(worldBuilder)
      .append(", how are you? ")
      .append(1)
      .append(' ')
      .append(true)

    Then("the result will be a string with all values concatenated as expected")
    val result = helloBuilder.toString()
    assert(result == "hello world, how are you? 1 true")
  }

  scenario("The string builder may append strings with + and ++ operators") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior of + and ++ operators with StringBuilder")

    Given("an instantiated StringBuilder with \"Title\" string")
    val titleStringBuilder = new StringBuilder("Title")

    When("we append the ':' character and the \" StringBuilder with Scala\" string")
    titleStringBuilder += ':'
    titleStringBuilder ++= " StringBuilder with Scala"

    Then("the result will be \"Title: StringBuilder with Scala\" as expected")
    val resultString = titleStringBuilder.toString()
    assert(resultString == "Title: StringBuilder with Scala")
  }

  scenario("The string builder may insert strings at specific index") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior of insert method with StringBuilder")

    Given("an instantiated StringBuilder with \"baeldung\" string")
    val baeldungStringBuilder = new StringBuilder("baeldung")

    When("we insert the \".com\" string at the end of elements in StringBuilder buffer")
    baeldungStringBuilder.insert(8, ".com")

    Then("the result will be \"baeldung.com\" as expected")
    val resultString = baeldungStringBuilder.toString()
    assert(resultString == "baeldung.com")
  }

  scenario("The string builder may clear the buffer") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior when using clear method with StringBuilder")

    Given("an instantiated StringBuilder with an initial string \"baeldung\"")
    val baeldungStrBuilder = new StringBuilder("baeldung")

    When("we clear the buffer")
    baeldungStrBuilder.clear()

    Then("the result will be an empty string as expected")
    val resultString = baeldungStrBuilder.toString()
    assert(resultString == "")
  }
  
  scenario("The string builder may delete elements from the buffer") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior when removing characters from index i (inclusive) until j (exclusive)")

    Given("an instantiated StringBuilder with \"bael123dung\" string")
    val baeldungStrBuilder = new StringBuilder("bael123dung")
    val i = 4
    val j = 7

    When("we delete some characters i.e. the numbers in the initial string")
    baeldungStrBuilder.delete(i, j)

    Then("the result will be \"baeldung\" as expected")
    val resultString = baeldungStrBuilder.toString()
    assert(resultString == "baeldung")
  }
}
