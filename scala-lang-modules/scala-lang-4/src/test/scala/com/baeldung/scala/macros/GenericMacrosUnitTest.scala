package com.baeldung.scala.macros

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GenericMacrosUnitTest extends AnyWordSpec with Matchers {
  "generic macro" should {
    "return String for string argument" in {
      GenericMacros.getTypeMacro("this is a string") mustBe "String"
    }

    "return Int for int argument" in {
      GenericMacros.getTypeMacro(8) mustBe "int"
    }

    "return class name for a custom class argument" in {
      case class CustomClassTest(body: String)
      GenericMacros.getTypeMacro(
        CustomClassTest("body")
      ) must include("CustomClassTest")
    }
  }
}
