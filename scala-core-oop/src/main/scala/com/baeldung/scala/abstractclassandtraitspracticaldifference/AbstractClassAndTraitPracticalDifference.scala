package com.baeldung.scala.abstractclassandtraitspracticaldifference

object AbstractClassAndTraitPracticalDifference {

  abstract class Car(model: String) {
    def color(): Unit = println("Red")

    def modelName(): String
  }

  case class Audi(model: String) extends Car(model) {
    override def color() = println("Silver")

    override def modelName() = s"Model is $model"
  }

  abstract class Bus

  trait Truck

  case class Vehicle(model: String) extends Car(model) with Truck {
    override def modelName() = s"Model is $model"

  }

}
