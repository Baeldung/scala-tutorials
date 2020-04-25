package com.baeldung.scala.traits

import org.scalatest.{FunSuite, Matchers}

class AnimalsTest extends FunSuite with Matchers {
  test("an Ant will have 6 legs and eats Plant") {
    val annie = new Ant("Annie")

    annie.eat() shouldEqual "Plant"
    annie.legs() shouldEqual 6
    annie.nickname shouldEqual "Annie"
  }
  test("a Dog will have 4 legs, eats Meat, and speak Woof") {
    val doug = new Dog("Doug")

    doug.eat() shouldEqual "Meat"
    doug.legs() shouldEqual 4
    doug.nickname shouldEqual "Doug"

    doug.sound shouldEqual "Woof"
    doug.speak() // Woof
  }
  test("a Cow will have 4 legs, eats Plant, and speak Moo") {
    val corrie = new Cow("Corrie")

    corrie.eat() shouldEqual "Plant"
    corrie.legs() shouldEqual 4
    corrie.nickname shouldEqual "Corrie"

    corrie.sound shouldEqual "Moo"
    corrie.speak() // Cow gous Moo
  }
}
