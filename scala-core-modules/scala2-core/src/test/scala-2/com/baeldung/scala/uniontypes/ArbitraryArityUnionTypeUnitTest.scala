package com.baeldung.scala.uniontypes

import com.baeldung.scala.uniontypes.ArbitraryArityUnionType._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ArbitraryArityUnionTypeUnitTest extends AnyFlatSpec with Matchers {

  "isIntOrStringOrBool" should "be able to take an integer parameter" in {
    isIntOrStringOrBool(10) shouldEqual "10 is an Integer"
  }

  "isIntOrStringOrBool" should "be able to take an string parameter" in {
    isIntOrStringOrBool("hello") shouldEqual "hello is a String"
  }

  "isIntOrStringOrBool" should "be able to take an boolean parameter" in {
    isIntOrStringOrBool(true) shouldEqual "true is a Boolean"
  }

  "isIntOrString" should "be able to take an integer parameter" in {
    isIntOrString(10) shouldEqual "10 is an Integer"
  }

  "isIntOrString" should "be able to take an string parameter" in {
    isIntOrString("hello") shouldEqual "hello is a String"
  }
}
