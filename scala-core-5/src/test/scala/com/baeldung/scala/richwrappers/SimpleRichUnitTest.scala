package com.baeldung.scala.richwrappers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SimpleRichUnitTest extends AnyWordSpec with Matchers {

  "SimpleRichInt" should {
    "count the digits of a number" in {
      assert(new SimpleRichInt(105).digits == 3)
    }

    "apply an implicit conversion if the appropriate function is imported" in {
      import SimpleRichInt._

      assert(105.digits == 3)
    }
  }
}
