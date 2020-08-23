package com.baeldung.scala.abstractvstrait.CarAbstract

import org.scalatest.funsuite.AnyFunSuite

class CarAbstractTest extends AnyFunSuite {
  test("Define ElectricCar") {
    val tesla = new ElectricCar(make = "Tesla", model = "Model X", 100)
    assert(tesla.make === "Tesla")
    assert(tesla.model === "Model X")
    tesla.printCarInfo
    tesla.drive()
  }

  test("Define PetrolCar") {
    val ford = new PetrolCar(make = "Ford", model = "F150", 100)
    assert(ford.make === "Ford")
    assert(ford.model === "F150")
    ford.printCarInfo()
    ford.drive()
  }
}
