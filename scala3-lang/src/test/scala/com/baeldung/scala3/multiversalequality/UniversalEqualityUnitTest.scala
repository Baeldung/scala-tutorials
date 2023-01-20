package com.baeldung.scala3.multiversalequality
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import UniversalEquality.{Square, Circle}

class UniversalEqualityUnitTest extends AnyWordSpec with Matchers {
  "Universal equality check for different types" should {
    "compile without any errors and return false" in {
      "Square(5) == Circle(5)" should compile
      Square(5) should not be Circle(5)
    }
  }
}
