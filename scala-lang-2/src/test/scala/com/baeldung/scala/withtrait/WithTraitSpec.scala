package com.baeldung.scala.withtrait

import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class WithTraitSpec extends AnyWordSpec {
  "A person should" should {
    "know how to breathe and say their name" in {
      val pat = Person("Pat", "123 Main St.", LocalDate.of(1933, 10, 11))
      assertResult("I'm Pat, alive and kicking")(pat.breathe())
    }
  }
}
