package com.baeldung.scala.oopinscala
import scala.collection.mutable.ArrayBuffer

class Bread(breadName: String = "white", breadWeight: Int = 1) {
  val name: String = breadName
  var weight: Int = breadWeight

  def getPrice(priceUnit: Int): Int = {
    priceUnit * weight
  }
}

class Sandwich(bread: Bread, filling: ArrayBuffer[String]) {
  private def getFillingsName: String = {
    filling.mkString(", ")
  }

  def getDescription: String = {
    s"This is a sandwich with ${bread.name} bread and $getFillingsName filling"
  }

  def addFilling(extraFilling: String): Unit = {
    filling.append(extraFilling)
  }
}

class Vehicle(val numWheels: Int, val color: String) {
  def accelerate(): Unit = { println("Vroom Vroom") }
}

class Bicycle(bikeColor: String, val bikeType: String) extends Vehicle(2, bikeColor) {
  def maxSpeed(): Int = {
    bikeType match {
      case "road" => 60
      case _ => 20
    }
  }

  def maxSpeed(speedLimit: Int): Int = {
    bikeType match {
      case "road" => if(speedLimit < 60) speedLimit else 60
      case _ => if(speedLimit < 20) speedLimit else 20
    }
  }

  override def accelerate(): Unit = { println("Whoooosh") }
}
