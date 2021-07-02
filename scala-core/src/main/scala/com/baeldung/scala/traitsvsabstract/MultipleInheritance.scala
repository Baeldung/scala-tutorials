package com.baeldung.scala.traitsvsabstract

trait Creature

trait WithAge {
  def age: Int
}

trait WithHeight {
  def height: Int
}

abstract class Animal
abstract class Aged(age: Int)
abstract class Weighted(weight: Int)

object MultipleInheritance {
  val creature = new Creature with WithHeight with WithAge {
    override def height: Int = 175

    override def age: Int = 27
  }
}
