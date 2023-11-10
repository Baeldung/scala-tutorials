package com.baeldung.scala3.macros

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class OddEvenMacrosTest extends AnyWordSpec with Matchers {
  "inline macros" should {
    "return literal odd for odd number" in {
      val res: String = OddEvenMacros.oddEvenMacroInline(3)
      res mustBe "odd"
    }
    "return literal even for even number" in {
      val res: String = OddEvenMacros.oddEvenMacroInline(2)
      res mustBe "even"
    }
  }

  "conditional inline macros" should {
    "return literal odd for odd number" in {
      OddEvenMacros.oddEvenMacroInlineConditional(3) mustBe "odd"
    }
    "return literal even for even number" in {
      OddEvenMacros.oddEvenMacroInlineConditional(2) mustBe "even"
    }
  }

  "macros" should {
    "return literal odd for odd number" in {
      OddEvenMacros.oddEvenMacro(3) mustBe "odd"
    }
    "return literal even for even number" in {
      OddEvenMacros.oddEvenMacro(2) mustBe "even"
    }
  }

  "quote macros" should {
    "return literal odd for odd number" in {
      OddEvenMacros.oddEvenMacroQuote(3) mustBe "odd"
    }
    "return literal even for even number" in {
      OddEvenMacros.oddEvenMacroQuote(2) mustBe "even"
    }
  }

  "transparent macros" should {
    "return literal odd for odd number" in {
      OddEvenMacros.oddEvenMacroTransparent(3) mustBe "odd"
    }
    "return literal even for even number" in {
      OddEvenMacros.oddEvenMacroTransparent(2) mustBe "even"
    }
  }

}
