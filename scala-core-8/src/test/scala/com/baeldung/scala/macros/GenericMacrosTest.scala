package com.baeldung.scala.macros

import org.scalatest.{MustMatchers, WordSpec}

class GenericMacrosTest extends WordSpec with MustMatchers {
  "generic macro" should {
    "return String for string argument" in {
      GenericMacros.getType("this is a string") mustBe "String"
    }
    "return Int for int argument" in {
      GenericMacros.getType(8) mustBe "Int"
    }
    "return class name for a custom class argument" in {
      case class CustomClassTest(body: String)
      GenericMacros.getType(CustomClassTest("body")) mustBe "CustomClassTest"
    }
  }
}
