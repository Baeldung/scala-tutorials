package com.baeldung.scala.cats.semigroups

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CustomIntSemigroupSpec extends AnyFlatSpec with Matchers {
  "CustomIntSemigroup" should "multiply two numbers" in {
    assert(CustomIntSemigroup.multiply(2, 3) == 6)
  }
}
