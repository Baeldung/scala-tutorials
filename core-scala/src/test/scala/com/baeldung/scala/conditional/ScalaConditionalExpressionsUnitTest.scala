package com.baeldung.scala.conditional

import com.baeldung.scala.conditional.ScalaConditionalExpressions.max
import org.scalatest._

class ScalaConditionalExpressionsUnitTest extends FlatSpec with Matchers {

    "max" should "return maximum of two numbers" in {
        max(10, 20) should be(20)
    }

    it should "return maximum of three numbers" in {
        max(10, 50, 20) should be(50)
    }

    "if without else" should "have return type Any" in {
        val isAny = if (false) false

        isAny shouldBe a[Any]
    }
}
