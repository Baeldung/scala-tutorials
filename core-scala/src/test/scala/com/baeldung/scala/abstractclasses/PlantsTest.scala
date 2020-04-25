package com.baeldung.scala.abstractclasses

import org.scalatest.{FunSuite, Matchers}

class PlantsTest extends FunSuite with Matchers {
  test("Sunflower has yellow leaves and doesn't do photosynthesis") {
    val sunflora = new Sunflower

    sunflora.leafColor shouldEqual "yellow"
    sunflora.photosynthesis() shouldBe false
  }
  test("Oak tree doesn't have fruit") {
    val prof = new Oak("Prof")

    prof.nickname shouldEqual "Prof"
    prof.hasFruit shouldBe false
    prof.swing() // Wheee I'm swinging on Prof :)
  }
  test("Watermelon tree has fruit") {
    val summerFruit = new Watermelon

    summerFruit.nickname shouldEqual "Watery"
    summerFruit.hasFruit shouldBe true
    summerFruit.swing() // I can't swing on Watery :(
  }
}
