package com.baeldung.scala.typeinference

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TypeInferenceLimitationsUnitTest extends AnyWordSpec with Matchers {
  "type inference limitations" should {
    import com.baeldung.scala.typeinference.TypeInferenceLimitations._
    "An list of integers given input to recursiveSum function should calculate sum of its elements" in {
      val inputList = List(1, 2, 3, 4, 5)
      val sum = recursiveSum(inputList)
      sum shouldBe inputList.sum
    }
  }
}
