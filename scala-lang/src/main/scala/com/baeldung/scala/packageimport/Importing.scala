package com.baeldung.scala.packageimport

import com.baeldung.scala.packageimport.vehicle.Bicycle
// import every class in a package
import com.baeldung.scala.packageimport.vehicle._
// import multiple classes from a package
import com.baeldung.scala.packageimport.vehicle.{Bicycle, Car, Vehicle}

import com.baeldung.scala.packageimport.vehicle.{Bicycle => BC, Car, Vehicle}

object Importing extends App {
  val firstBicycle: BC = new BC(2, "Abici")
  firstBicycle.run()
  val firstCar: Car = new Car(4, "Audi", true)
  firstCar.run()
}

object bmwB38 extends Motor {

  def run(): Unit =
    println(s"I am a bmwB38 ! $noDieselMessage")
}
