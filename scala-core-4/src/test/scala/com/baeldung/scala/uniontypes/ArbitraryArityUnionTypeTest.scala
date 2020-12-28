package com.baeldung.scala.uniontypes

class ArbitraryArityUnionTypeTest extends FlatSpec with Matchers {

  "isIntOrStringOrBool" should "be able to take an integer parameter" in {
    isIntOrStringOrBool(10) shouldEqual "10 is an Integer"
  }

  "isIntOrStringOrBool" should "be able to take an string parameter" in {
    isIntOrStringOrBool("hello") shouldEqual "hello is a String"
  }

  "isIntOrStringOrBool" should "be able to take an boolean parameter" in {
    isIntOrStringOrBool(true) shouldEqual "true is a Boolean"
  }

}
