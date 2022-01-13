package com.baeldung.scala.typeinference

import org.scalatest.{Matchers, WordSpec}

class TypeInferredFunctionsTest extends WordSpec with Matchers {
  "type inference for functions" should {
    import com.baeldung.scala.typeinference.TypeInferredFunctions._
    "An integer number given as input to the function squareInt should calculate its square value, and its return type is inferred as Integer type" in {
      val square = squareInt(2)
      square.getClass shouldBe java.lang.Integer.TYPE
    }
  }
}
