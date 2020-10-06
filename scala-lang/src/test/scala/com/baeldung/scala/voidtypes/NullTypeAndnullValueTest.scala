package com.baeldung.scala.voidtypes
import org.scalatest.funsuite.AnyFunSuite


class NullTypeAndnullValueTest extends AnyFunSuite {

  test("Instance of Null type ") {

    NullTypeAndnullValue.nullValue

    assert(pets == Seq("Cat", "Dog"))
    assert(myPets == Seq("Cat", "Dog", "Hamster"))
    assert(notPets == Seq("Cat", "Dog", "Giraffe", "Elephant"))
    assert(yourPets == Seq("Mice", "Dog"))
  }

}
