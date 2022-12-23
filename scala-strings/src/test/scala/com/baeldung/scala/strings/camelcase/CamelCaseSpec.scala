package com.baeldung.scala.strings.camelcase

import org.scalatest.WordSpec

class CamelCaseSpec extends WordSpec {

  import StringWrapper._

  "Camel case converter" should {
    "do nothing to strings already in camelCase" in {
      val camelCase = "thisStringIsAlreadyInCamelCase"
      assertResult(camelCase)(camelCase.toCamelCase)
    }
    "handle the empty string gracefully" in {
      assertResult("")("".toCamelCase)
    }
    "remove spaces and change case" in {
      val notCamelCase = "A string With DivErSe CASEs"
      val camelCase = "aStringWithDiverseCases"
      assertResult(camelCase)(notCamelCase.toCamelCase)
    }
    "remove underscores and spaces" in {
      val notCamelCase = "I don't like_snakes_becauseThey_BITE"
      val camelCase = "iDon'tLikeSnakesBecauseTheyBite"
      assertResult(camelCase)(notCamelCase.toCamelCase)
    }
  }

}
