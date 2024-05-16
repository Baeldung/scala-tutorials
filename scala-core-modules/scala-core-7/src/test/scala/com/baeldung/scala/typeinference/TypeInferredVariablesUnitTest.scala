package com.baeldung.scala.typeinference

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TypeInferredVariablesUnitTest extends AnyWordSpec with Matchers {
  "type inference for variables " should {
    import com.baeldung.scala.typeinference.TypeInferredVariables._
    "An integer number given as initial value should infer a Integer type" in {
      integerObj.getClass shouldBe java.lang.Integer.TYPE
    }
    "An decimal number given as initial value should infer a Double type" in {
      doubleObj.getClass shouldBe java.lang.Double.TYPE
    }
    "A character literal given as initial value should infer a Char type" in {
      charObj.getClass shouldBe java.lang.Character.TYPE
    }
    "A boolean given as initial value should infer a Boolean type" in {
      booleanObj.getClass shouldBe java.lang.Boolean.TYPE
    }
  }
}
