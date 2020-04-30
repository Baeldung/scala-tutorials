package com.baeldung.scala.AbstractClassAndTrait.Trait

class Dog(name: String) extends Pet {
  def comeToMaster(): Unit = println("Woo-hoo, I'm coming!")
}