package com.baeldung.scala.cats.semigroups

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class IntSemigroupUnitTest extends AnyFlatSpec with Matchers {
  "IntSemigroup" should "add two numbers" in {
    assert(IntSemigroup.defaultIntSemigroup(1, 2) == 3)
  }
}
