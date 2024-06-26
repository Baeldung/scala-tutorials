package com.baeldung.scala.strings.checkempty

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import EmptyStringExtensions._

class EmptyStringUnitTest extends AnyFlatSpec with Matchers {

  "isNullOrEmptyOrWhitespace" should "return true for null strings" in {
    val str: String = null
    str.isNullOrEmptyOrWhitespace.shouldBe(true)
  }

  it should "return true for empty strings" in {
    val str: String = ""
    str.isNullOrEmptyOrWhitespace.shouldBe(true)
  }

  it should "return true for strings with only whitespace" in {
    val str: String = "   "
    str.isNullOrEmptyOrWhitespace.shouldBe(true)
  }

  it should "return false for non-empty strings" in {
    val str: String = "Hello, Scala"
    str.isNullOrEmptyOrWhitespace.shouldBe(false)
  }
}
