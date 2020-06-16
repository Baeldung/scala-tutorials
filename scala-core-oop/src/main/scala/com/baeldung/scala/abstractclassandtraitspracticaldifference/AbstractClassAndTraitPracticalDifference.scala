package com.baeldung.scala.abstractclassandtraitspracticaldifference

object AbstractClassAndTraitPracticalDifference {

  abstract class Car(model: String) {
    def color(): Unit = println("Red")

    def modelName(): Unit
  }

  case class Audi(model: String) extends Car(model) {
    override def color() = println("Silver")

    override def modelName() = println(s"Model is $model")
  }

}
