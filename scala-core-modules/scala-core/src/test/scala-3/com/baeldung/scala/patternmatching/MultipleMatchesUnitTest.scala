package com.baeldung.scala.patternmatching

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MultipleMatchesUnitTest extends AnyFlatSpec with Matchers {

  "executeCommand" should "start the system when given the command" in {
    val result = executeCommand(Start)
    result shouldEqual "System Starting."
  }

  it should "stop the system when given the command" in {
    val result = executeCommand(CustomCommand("halt"))
    result shouldEqual "System Stopping."
  }

  "httpResponse" should "Error" in {
    val result = httpResponse(404)
    result shouldEqual "Error"
  }

  "multipleTypePatterns" should "Error" in {
    val result = multipleTypePatterns(4.4)
    result shouldEqual "It's something else"
  }

  "unionTypePattern" should "Error" in {
    val result = unionTypePattern(42)
    result shouldEqual "It's either a String or an Int"
  }

}