package com.baeldung.scala.abstractclasstrait.demo

class DogFromTrait(name : String,total : Int) extends Animal(name) with AnimalTrait with Walking {
  override var animalNumber: Int = 10
  override val groupNumber: Int = 3

  override def setAnimalNumber(x: Int): Unit = {
    animalNumber = 10
  }
  override def makeNoise {
    println("bark")
  }

  override def walkAtHighSpeed: Unit = {
    print("trait walking")
  }
}
