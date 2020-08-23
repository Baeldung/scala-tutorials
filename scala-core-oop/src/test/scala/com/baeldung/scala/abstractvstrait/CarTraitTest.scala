package com.baeldung.scala.abstractvstrait.CarTrait

import org.scalatest.funsuite.AnyFunSuite

class CarTraitTest extends AnyFunSuite {
  test("Define ElectricCar with Extended Battery Range") {
    val tesla = new ElectricCar(make = "Tesla", model = "Model X", drivingRange = 250, batteryLevel = 80)
    assert(tesla.make === "Tesla")
    assert(tesla.model === "Model X")
    assert(tesla.drivingRange === 250)
    tesla.printCarInfo()
    tesla.drive()
  }

  test("Define PetrolCar") {
    val ford = new PetrolCar(make = "Ford", model = "F150", 100)
    assert(ford.make === "Ford")
    assert(ford.model === "F150")
    ford.printCarInfo()
    ford.drive()
  }

  test("Get BMW I7 with Electric Charger and Extended Battery Range") {
    val bmwI7 = BMW.bmwI7
    assert(bmwI7.make === "BMW")
    assert(bmwI7.model === "I7")
    assert(bmwI7.drivingRange === 120)
    bmwI7.printCarInfo()
    bmwI7.drive()
  }
}
