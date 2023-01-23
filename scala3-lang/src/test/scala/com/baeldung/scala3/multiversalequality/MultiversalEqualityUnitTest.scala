package com.baeldung.scala3.multiversalequality
import com.baeldung.scala3.multiversalequality.MultiversalEquality.{fido, rover}
import com.baeldung.scala3.multiversalequality.UniversalEquality.Square
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MultiversalEqualityUnitTest extends AnyWordSpec with Matchers{
  "Multiversal equality check for different types" should {
    " throw Type error and not compile" in {
      import scala.language.strictEquality
      assertTypeError("fido == rover")
      "fido == rover" shouldNot compile
    }
  }
}
