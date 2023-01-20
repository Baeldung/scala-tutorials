package com.baeldung.scala.assertvsrequire
import com.baeldung.scala.assertvsrequire.AssertUsage

import org.scalatest.{FlatSpec, Matchers}

class AssertUsageUnitTest extends FlatSpec with Matchers {

  "getSquareOfNumber" should "be able to calculate square of an integer without any error" in {
    noException should be thrownBy AssertUsage.getSquareOfNumber(4)
  }

  "getSquareOfNumber" should "be able to calculate square of an integer correctly" in {
    AssertUsage.getSquareOfNumber(4) shouldEqual 16
  }

  "getSquareOfNumberWithError" should "be able to throw an AssertionError, because the implementation has errors" in {
    val thrown = the[AssertionError] thrownBy AssertUsage
      .getSquareOfNumberWithError(4)
    println(thrown.getMessage)
    thrown.getMessage should equal("assertion failed")
  }

}
