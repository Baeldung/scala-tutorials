package com.baeldung.scala.matchexpressions

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatchExpressionImprovementstUnitTest extends AnyWordSpec with Matchers {
  val testNone = None
  val testSome = Some("Hello World!")

  "wordFromOptionNoBraces" should {
    "return Option is None for None" in {
      MatchExpressionImprovements.wordFromOptionNoBraces(
        testNone
      ) mustBe "Option is None"
    }
    "return String in sentence for Some" in {
      MatchExpressionImprovements.wordFromOptionNoBraces(
        testSome
      ) mustBe "Option contains Hello World!"
    }
  }

  "wordFromOption" should {
    "return Option is None for None" in {
      MatchExpressionImprovements.wordFromOption(
        testNone
      ) mustBe "Option is None"
    }
    "return String in sentence for Some" in {
      MatchExpressionImprovements.wordFromOption(
        testSome
      ) mustBe "Option contains Hello World!"
    }
  }

  "isOptionEmpty" should {
    "return true for None" in {
      MatchExpressionImprovements.isOptionEmpty(testNone) mustBe true
    }
    "return false for Some" in {
      MatchExpressionImprovements.isOptionEmpty(testSome) mustBe false
    }
  }

  "optionContains" should {
    "return 'nothing' for None" in {
      MatchExpressionImprovements.optionContains(testNone) mustBe "nothing"
    }
    "return 'A string' for Some" in {
      MatchExpressionImprovements.optionContains(testSome) mustBe "A string"
    }
  }

  "isOptionEmptyWithType" should {
    "return true for None" in {
      MatchExpressionImprovements.isOptionEmptyWithType(testNone) mustBe true
    }
    "return false for Some" in {
      MatchExpressionImprovements.isOptionEmptyWithType(testSome) mustBe false
    }
  }
}
