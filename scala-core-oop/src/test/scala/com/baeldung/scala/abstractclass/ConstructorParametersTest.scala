package com.baeldung.scala.abstractclass

import com.baeldung.scala.abstractclass.ConstructorParameters.Car
import org.scalatest.FunSuite

class ConstructorParametersTest extends FunSuite {

  test("Constructor Parameters Test") {
    val mercedes = new Car("Mercedes")
    assert(mercedes.brand === "Mercedes")
  }

}
