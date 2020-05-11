package com.baeldung.scala.traitsabstract

object TraitVsAbstract extends App {
  val firstBicycle: Bicycle = new Bicycle(2, "Abici")
  firstBicycle.run()
  val firstCar: Car = new Car(4, "Audi", true)
  firstCar.run()
}

abstract class Vehicle(numberOfTires: Int, brand: String) {

  def run(): Unit =
    println("I am a vehicle !")
}

class Car(numberOfTires: Int, brand: String, isDiesel: Boolean) extends Vehicle(numberOfTires, brand) with Motor {

  override def run(): Unit = {
    super.run()
    println(s"$numberOfTires tires from the brand $brand are running. It is a diesel motor: $isDiesel.")
    if (isDiesel)
      println(dieselMessage)
  }
}

class Bicycle(numberOfTires: Int, brand: String) extends Vehicle(numberOfTires, brand) {

  override def run(): Unit = {
    super.run()
    println(s"$numberOfTires from the brand $brand are running")
  }
}

trait Motor {
  val dieselMessage: String = "I am not environment friendly"
  val noDieselMessage: String = "I am environment friendly"
}

object bmwB38 extends Motor {

  def run(): Unit =
    println(s"I am a bmwB38 ! $noDieselMessage")
}

/* This is NOT possible
object audiA6 extends Vehicle {
  def run: Unit = {
    val noDiesel = noDieselMessage
    println(
      s"I am a bmwB38 ! $noDiesel"
    )
  }
}
 */
