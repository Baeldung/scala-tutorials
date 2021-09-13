package com.baeldung.scala3.multiversalequality

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import CanEqualGivenInstance.{email, letter}

class CanEqualGivenInstanceTest extends AnyWordSpec with Matchers{
  "CanEqualGivenInstance equality check for different types" should {
    " compile and return true" in {
      import scala.language.strictEquality
      "email == letter" should compile
      email shouldEqual letter
    }
  }
}
