package com.baeldung.scala.assertvsrequire

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RequireUsageUnitTest extends AnyFlatSpec with Matchers {

  "issueDrivingLicense" should "be able to execute if age is greater than or equal to 18" in {
    noException should be thrownBy RequireUsage.issueDrivingLicense(
      "Martha",
      32
    )
  }

  "issueDrivingLicense" should "be able to throw an IllegalArgumentException if age is less than 18" in {
    val thrown = the[IllegalArgumentException] thrownBy RequireUsage
      .issueDrivingLicense("Bebe", 12)
    println(thrown.getMessage)
    thrown.getMessage should equal("requirement failed")
  }

}
