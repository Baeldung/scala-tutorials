package com.baeldung.scala.macros

import org.scalatest.{MustMatchers, WordSpec}

class OddEvenMacrosTest extends WordSpec with MustMatchers {

  "def macros" should {
    "return literal odd for odd number" in {
      val res: String = OddEvenMacros.defOddEvenMacro(3)
      res mustBe "odd"
    }
    "return literal even for even number" in {
      val res: String = OddEvenMacros.defOddEvenMacro(2)
      res mustBe "even"
    }
  }

  "def macros reify" should {
    "return literal odd for odd number" in {
      val res: String = OddEvenMacros.defOddEvenMacroReify(3)
      res mustBe "odd"
    }
    "return literal even for even number" in {
      val res: String = OddEvenMacros.defOddEvenMacroReify(2)
      res mustBe "even"
    }
  }

  "macro bundle" should {
    "return literal odd for odd number" in {
      val res: String = OddEvenMacros.oddEvenMacroBundle(3)
      res mustBe "odd"
    }
    "return literal even for even number" in {
      val res: String = OddEvenMacros.oddEvenMacroBundle(2)
      res mustBe "even"
    }
  }
}
