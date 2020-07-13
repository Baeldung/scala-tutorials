package com.baeldung.scala.namedanddefaultargs

import com.baeldung.scala.namedanddefaultargs.NamedAndDefaultArgs._
import org.scalatest.{Matchers, WordSpec}

class NamedAndDefaultArgsTest extends WordSpec with Matchers {
  "Named and Default Args" should {
    "work with default args" in {
      prettyPrint(Array(1, 2, 3)) shouldBe "(1,2,3)"
      prettyPrint(Array(1, 2, 3), "[", ";", "]") shouldBe "[1;2;3]"
    }
    "work with named args" in {
      prettyPrint(Array(1, 2, 3), start = "[", separator = ";", end = "]") shouldBe "[1;2;3]"
      // this code will not compile
      // prettyPrint(start = "{", separator = ",", end = "}", Array(1, 2, 3))
    }

    "work work with class constructors" in {
      val orderWithNamedAndDefault = new DeliveryOrder(product = "Pho Bo",
        addressToDeliver = "42 Some Street, suite 24",
        promoCode = Some("SALE42")
      )
      val orderWithoutNamedAndDefault = new DeliveryOrder(
        "Pho Bo",
        "42 Some Street, suite 24",
        1,
        Some("SALE42"),
        None,
        None
      )
      orderWithNamedAndDefault shouldBe orderWithoutNamedAndDefault
    }
  }
}
