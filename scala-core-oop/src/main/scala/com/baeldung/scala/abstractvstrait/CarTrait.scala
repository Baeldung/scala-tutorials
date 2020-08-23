package com.baeldung.scala.abstractvstrait

/**
 * Example of extending a trait with abstract members
 * @author Arsen Aydinyan
 */

/**
 * Trait with abstract method and concrete implementation
 */
package object CarTrait {

  trait Car {
    //abstract members
    val make: String
    val model: String

    def printCarInfo(): Unit = {
      println(s"You are driving {$make} ${model}!")
    } //concrete implementation

    def drive(): Unit //abstract method
  }

  trait ElectricCharger {
    println("Adding electric charger.")
  }

  trait ExtendedBatteryRange {
    //abstract member
    val drivingRange: Int
  }

  /**
   * Extending trait with overriding implementation and decorating it with another trait (stack up)
   */
  class ElectricCar(val make: String, val model: String, val drivingRange: Int, val batteryLevel: Int) extends Car with ExtendedBatteryRange {

    override def printCarInfo() = {
      println(s"You are driving an electric car ${make} ${model}!")
    } //override implementation

    def drive() = {
      println(s"Battery level: ${batteryLevel}. Let's start driving.")
    } //abstract method implementation
  }

  /**
   * Extending trait with overriding implementation
   */
  class PetrolCar(val make: String, val model: String, fuelLevel: Int) extends Car {

    override def printCarInfo() = {
      println(s"You are driving a petrol car ${make} ${model}!")
    } //override implementation

    def drive() = {
      println(s"Fuel level: ${fuelLevel}. Let's start driving.")
    } //abstract method implementation
  }

  object BMW { //adding trait to object instance
    val bmwI7 = new ElectricCar(make = "BMW", model = "I7", drivingRange = 120, batteryLevel = 100) with ElectricCharger
  }
}