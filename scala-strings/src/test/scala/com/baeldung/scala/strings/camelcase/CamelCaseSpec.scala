package com.baeldung.scala.strings.camelcase

import org.scalatest.wordspec.AnyWordSpec

class CamelCaseSpec extends AnyWordSpec {

  import StringWrapper._

  "Camel case converter" should {
    "do nothing to strings already in camelCase" in {
      val camelCase = "thisStringIsAlreadyInCamelCase"
      assertResult(camelCase)(camelCase.toCamelCase)
    }
  }

}
