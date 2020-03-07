package com.baeldung.scala

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class TraitsvsAbstractClassesTest extends FunSuite {

  test("trait should support multiple inheritance by right to left resolution") {
    //given
    val extender1 = new Extender1

    //when & then
    extender1.name shouldBe "bar"
  }

  test("trait should be able to add to object instance") {
    //given
    val b = new B with Logger

    //when & then
    b.log("msg") shouldBe "msg"
  }
}
