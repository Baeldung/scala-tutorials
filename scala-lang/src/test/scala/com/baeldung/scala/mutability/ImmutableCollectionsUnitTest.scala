package com.baeldung.scala.mutability

import org.scalatest.funsuite.AnyFunSuite

class ImmutableCollectionsUnitTest extends AnyFunSuite {
  test(
    "Immutable collections will create new instance if we add or update the elements"
  ) {
    val pets = Seq("Cat", "Dog")
    val myPets = pets :+ "Hamster"
    val notPets = pets ++ List("Giraffe", "Elephant")
    val yourPets = pets.updated(0, "Mice")

    assert(pets == Seq("Cat", "Dog"))
    assert(myPets == Seq("Cat", "Dog", "Hamster"))
    assert(notPets == Seq("Cat", "Dog", "Giraffe", "Elephant"))
    assert(yourPets == Seq("Mice", "Dog"))
  }
}
