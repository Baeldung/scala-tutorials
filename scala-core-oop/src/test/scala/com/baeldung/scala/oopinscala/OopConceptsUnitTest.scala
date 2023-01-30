package com.baeldung.scala.oopinscala

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class OopConceptsUnitTest extends AnyFunSuite {
  test("access bread fields from object") {
    val whiteBread = new Bread
    assert(whiteBread.name === "white")
    assert(whiteBread.weight === 1)

    whiteBread.weight = 2
    assert(whiteBread.weight === 2)
  }

  test("using bread's constructor") {
    val grainBread = new Bread("grain")
    assert(grainBread.name === "grain")
  }

  test("get price of a bread") {
    val bread = new Bread("grain", 2)
    assert(bread.getPrice(2) === 4)
  }

  test("get description from a sandwich") {
    val sandwich = new Sandwich(new Bread("white"), ArrayBuffer("strawberry jam", "chocolate"))
    assert(sandwich.getDescription === "This is a sandwich with white bread and strawberry jam, chocolate filling")
  }

  test("add a filling to a sandwich") {
    val sandwich = new Sandwich(new Bread("sourdough"), ArrayBuffer("chicken"))
    sandwich.addFilling("lettuce")
    assert(sandwich.getDescription === "This is a sandwich with sourdough bread and chicken, lettuce filling")
  }

  test("define a bicycle") {
    val bicycle = new Bicycle("red", "road")
    bicycle.accelerate()
    assert(bicycle.numWheels === 2)
    assert(bicycle.color === "red")
    assert(bicycle.maxSpeed() === 60)
    assert(bicycle.maxSpeed(10) === 10)
  }
}
