package com.baeldung.scala.immutablearrays

import org.scalatest.wordspec.AnyWordSpec

class ImmutableArraySpec extends AnyWordSpec {
  private trait Pet(name: String)
  private case class Dog(name: String) extends Pet(name = name)
  private case class Cat(name: String) extends Pet(name = name)

  private val dogs: IArray[Dog] = IArray(Dog("champ"), Dog("barky"))
  private val cats: IArray[Cat] = IArray(Cat("overlord"), Cat("silky"))

  "dogs and cats" should {
    "get along together" in {
      val myPets = cats ++ dogs // myPets is an IArray[Pet]
      assertResult(4)(myPets.length)
      assert(myPets.contains(Dog("barky")))
      assert(myPets.contains(Cat("silky")))
      assert(myPets.contains(Dog("champ")))
      assert(myPets.contains(Cat("overlord")))
    }
  }

  "a dogs array" should {
    "be immutable" in {
      assertDoesNotCompile("dogs(0) = Dog(\"unwanted\")")
    }
  }
}
