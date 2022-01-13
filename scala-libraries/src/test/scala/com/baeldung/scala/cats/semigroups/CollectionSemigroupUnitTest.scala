package com.baeldung.scala.cats.semigroups

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CollectionSemigroupUnitTest extends AnyFlatSpec with Matchers {
  "CollectionSemigroup" should "combine strings" in {
    val sequenceOfStrings: Seq[String] = Seq("Welcome", "To", "The", "World", "Of", "Programming")
    val finalString = "WelcomeToTheWorldOfProgramming"
    assert(CollectionSemigroup.combineStrings(sequenceOfStrings) == finalString)
  }
}
