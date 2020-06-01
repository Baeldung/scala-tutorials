package com.baeldung.scala.mutability

import org.scalatest.FunSuite

class ImmutabilityCarTest extends FunSuite {
  test("Mutable vs Immutable variables") {
    val pi = 3.14
    // pi = 4 // Compile error: Reassignment to val

    var myWeight = 60
    assert(myWeight == 60)
    myWeight = 65
    assert(myWeight == 65)
  }

  test("Immutable car cannot be changed") {
    val myCar = new ImmutabilityCar("blue", 4, "diesel")
    myCar.call()

    myCar.engine = "electric"
    assert(myCar.engine == "electric")
    myCar.call()
  }
}
