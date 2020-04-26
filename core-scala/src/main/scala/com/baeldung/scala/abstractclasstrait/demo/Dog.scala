package com.baeldung.scala.abstractclasstrait.demo

class Dog(name : String, total : Int) extends Animal(name) with Walking {
  override var animalNumber: Int = total
  override val groupNumber: Int = 3

  override def makeNoise: Unit = {
    println("Bark")
  }

  override def walkAtHighSpeed: Unit = {
    print("walk at high speed")
  }
}
