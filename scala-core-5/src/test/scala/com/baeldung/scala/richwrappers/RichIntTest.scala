package com.baeldung.scala.richwrappers

import org.scalatest.{Matchers, WordSpec}

class RichIntTest extends WordSpec with Matchers {

  import RichIntImplicits._

  "RichInt" should {
    "count the digits of a number" in {
      assert(105.digits == 3)
    }
  }
}
