package com.baeldung.scala.conditional

import com.baeldung.scala.conditional.ScalaConditionalExpressions.max
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScalaConditionalExpressionsUnitTest extends AnyFlatSpec with Matchers {

  "max" should "return maximum of two numbers" in {
    max(10, 20) should be(30)
  }

  it should "return maximum of three numbers" in {
    max(10, 50, 20) should be(50)
  }

  "if without else" should "have return type Any" in {
    val isAny = if (false) false

    isAny shouldBe a[Any]
  }
}
