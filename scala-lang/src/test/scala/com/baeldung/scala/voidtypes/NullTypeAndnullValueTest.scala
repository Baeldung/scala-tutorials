package com.baeldung.scala.voidtypes
import org.scalatest.funsuite.AnyFunSuite


class NullTypeAndnullValueTest extends AnyFunSuite {

  test("Instance of Null type ") {
    assert(NullTypeAndnullValue.nullValue == NullTypeAndnullValue.nullRefCar)
  }

  test("null equality check using eq ") {
    assert(NullTypeAndnullValue.nullValue eq NullTypeAndnullValue.nullRefCar)
  }

  test("null equality check using equals ") {
    val exceptionThrown = intercept[NullPointerException] {
      NullTypeAndnullValue.nullValue equals NullTypeAndnullValue.nullRefCar
    }
  }

}
