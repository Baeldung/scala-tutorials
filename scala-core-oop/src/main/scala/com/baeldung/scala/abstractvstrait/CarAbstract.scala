package com.baeldung.scala.abstractvstrait

/**
 * Example of extending an abstract class with constructor parameters
 * @author Arsen Aydinyan
 */

/**
 * Abstract class with abstract method and concrete implementation
 */
package object CarAbstract {

  abstract class Car(val make: String, val model: String) {

    def printCarInfo: Unit = {
      println(s"You are driving {$make} ${model}!")
    } //concrete implementation

    def drive(): Unit //abstract method
  }

  abstract class ElectricCharger {
    println("Adding electric charger.")
  }

  /**
   * Extending class with overriding implementation
   */
  class ElectricCar(make: String, model: String, batteryLevel: Int) extends Car(make, model) {

    override def printCarInfo = {
      println(s"You are driving an electric car {$make} ${model}!")
    } //override implementation

    def drive() = {
      println(s"Battery level: ${batteryLevel}. Let's start driving.")
    } //abstract method implementation
  }

  /**
   * Extending class with overriding implementation
   */
  class PetrolCar(make: String, model: String, fuelLevel: Int) extends Car(make, model) {

    override def printCarInfo() = {
      println(s"You are driving a petrol car {$make} ${model}!")
    } //override implementation

    def drive() = {
      println(s"Fuel level: ${fuelLevel}. Let's start driving.")
    } //abstract method implementation
  }
}