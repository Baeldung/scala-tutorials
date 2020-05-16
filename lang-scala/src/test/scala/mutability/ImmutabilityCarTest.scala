package com.baeldung.scala.mutability

import org.scalatest.FunSuite

class ImmutabilityCarTest extends FunSuite {
  test("Mutable vs Immutable variables") {
    val pi = 3.14
    // pi = 4 // Compile error: Reassignment to val
    println(pi)

    var myWeight = 60
    println(myWeight)
    myWeight = 65
    println(myWeight)
  }

  test("Immutable car cannot be changed") {
    val myCar = new ImmutabilityCar("blue", 4, "diesel")
    myCar.call()

    // myCar.color = "green" // cannot be accessed from outside the class
    // myCar.wheels = 5 // reassignment to val
    myCar.engine = "electric"
    myCar.call()
  }
}
