package com.baeldung.scala.abstractclasstrait.demo2

class Dog extends Animal with Walking with Eating {
  override def animalName: Unit = {
    println("dog")
  }

  override def walkingSpeed: Unit = {
    println("walking fast")
  }

  override def eatingSpeed: Unit = {
    println("eating fast")
  }
}
