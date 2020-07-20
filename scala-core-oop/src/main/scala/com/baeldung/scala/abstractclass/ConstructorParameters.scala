package com.baeldung.scala.abstractclass

object ConstructorParameters extends App {

  abstract class Vehicle(val brand: String) {
    def numWheels: Int
  }

  class Car(brand: String) extends Vehicle(brand) {
    def numWheels: Int = 4
  }

  val car = new Car("BMW")

}
