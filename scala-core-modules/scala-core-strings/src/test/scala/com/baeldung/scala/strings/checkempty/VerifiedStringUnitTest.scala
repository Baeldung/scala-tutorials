package com.baeldung.scala.strings.checkempty

import com.baeldung.scala.strings.checkempty.VerifiedString.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class VerifiedStringUnitTest extends AnyFlatSpec with Matchers {

  def lenght(str: VerifiedString[NonEmpty]) = str.length

  "a verified string" should "never be empty" in {
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
