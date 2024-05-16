package com.baeldung.scala.immutablearrays

import org.scalatest.wordspec.AnyWordSpec

class ImmutableArrayUnitTest extends AnyWordSpec {
  trait Pet(val name: String, val age: Int)
  case class Dog(override val name: String, override val age: Int)
    extends Pet(name = name, age = age)
  case class Cat(override val name: String, override val age: Int)
    extends Pet(name = name, age = age)

  val dogs = IArray(Dog("champ", 2), Dog("barky", 3))
  val cats = IArray(Cat("overlord", 3), Cat("silky", 5))

  "dogs and cats" should {
    "get along together" in {
      val myPets = cats ++ dogs // myPets is an IArray[Pet]
      // is the size of the array correct?
      assertResult(4)(myPets.length)

      // check contents by full comparison
      assert(myPets.indexOf(Dog("barky", 3)) == 3)
      assert(myPets.indexOf(Cat("silky", 5)) == 1)
      assert(myPets.indexOf(Dog("champ", 2)) == 2)
      assert(myPets.indexOf(Cat("overlord", 3)) == 0)

      // check contents by predicate
      assert(myPets.exists(_.name == "barky"))
      assert(myPets.filter(_.age == 3).size == 2)

      // check type is respected by closure
      assert(myPets(1).isInstanceOf[Cat])
      assert(myPets(2).isInstanceOf[Dog])
    }
  }

  "a dogs array" should {
    "be immutable" in {
      assertDoesNotCompile("dogs(0) = Dog(\"unwanted\", 8)")
    }
  }
}
