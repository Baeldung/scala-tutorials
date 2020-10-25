package com.baeldung.scala.voidtypes

import org.scalatest.funsuite.AnyFunSuite

class UnitReturnTypeTest extends AnyFunSuite{

  test("test return value of unit function"){
    assert(UnitReturnType.functionReturnUnit == ())
  }

  test("test return value of implicit unit function"){
    assert(UnitReturnType.functionReturnImplicitUnit == ())
  }

}
