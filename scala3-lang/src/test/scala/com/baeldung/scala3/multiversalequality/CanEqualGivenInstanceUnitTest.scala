package com.baeldung.scala3.multiversalequality

import com.baeldung.scala3.multiversalequality.CanEqualGivenInstance.{email, letter}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CanEqualGivenInstanceUnitTest extends AnyWordSpec with Matchers{
  "CanEqualGivenInstance equality check for different types" should {
    " compile and return true" in {
      import scala.language.strictEquality
      "email == letter" should compile
      email shouldEqual letter
    }
  }
}
