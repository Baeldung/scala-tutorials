package com.baeldung.scala.traitvsabstractclass.traits

trait Vehicle {
  def kind: String
  def nWheels: Int
  def colour: String
  val description: String = s"a $nWheels-wheeled, $colour $kind"
}

trait Car extends Vehicle {
  override val kind: String = "car"
  override val nWheels: Int = 4
}

object Cars {
  val redCar: Car = new Car {
    override val colour: String = "red"
  }

  case class CarImpl(colour: String) extends Car
  val blueCar: Car = CarImpl("blue")
}

trait Robot {
  def name: String
  def identify(): Unit = println(s"I am $name")
}

object Robots {
  val bumbleBee: Robot = new Robot with Car {
    override val name: String = "Bumblebee"
    override val colour: String = "yellow"
    override def identify(): Unit = println(s"I am $name, $description.")
  }
}
