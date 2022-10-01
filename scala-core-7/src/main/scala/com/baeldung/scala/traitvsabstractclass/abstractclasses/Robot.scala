package com.baeldung.scala.traitvsabstractclass.abstractclasses

abstract class Vehicle(kind: String, nWheels: Int, colour: String) {
  val description: String = s"a $nWheels-wheeled, $colour $kind"
}

case class Car(colour: String) extends Vehicle("car", 4, colour)
object Cars {
  val redCar: Car = Car("red")
}
