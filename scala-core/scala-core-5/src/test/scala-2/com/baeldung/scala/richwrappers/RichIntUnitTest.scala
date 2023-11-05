package com.baeldung.scala.richwrappers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RichIntUnitTest extends AnyWordSpec with Matchers {

  import RichIntImplicits._

  "RichInt" should {
    "count the digits of a number" in {
      assert(105.digits == 3)
    }
  }
}
