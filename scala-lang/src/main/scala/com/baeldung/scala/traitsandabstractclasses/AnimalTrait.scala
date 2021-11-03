package com.baeldung.scala.operators

abstract class AbsClsAnimal(name: String) {
  def selftPresentation(): Unit
}

trait TraitAnimal {
  def selfPresentation(): Unit
}

trait Fly {
  def location: Unit = println("I'm at the Sky!")
}

class Bird extends Animal {
  def selfPresentation: Unit = println("Chirp, I'm a bird!")
}

class FlyingBird extends Animal with Fly