package com.baeldung.scala.caseobject

import java.sql.Date

object ObjectExample extends App {

  //Pattern Matching
  messageVehicle(Car)

  //Throws exception
  //nonExhaustive(FlyingObject.drone)

  sealedTraitMatch(BirdCaseObject)

  def messageVehicle(vehicle: Vehicle): Unit = {
    vehicle match {
      case Car => println("send message to Car")
      case Bicycle => println("send message to Bicycle")
    }
  }

  // Throws exception in case we call FlyingObject other than airplane and bird
  def nonExhaustive(objects: FlyingObject.Value) {
    objects match {
      case FlyingObject.airplane => println("I am an airplane")
      case FlyingObject.bird => println("I am a bird")
    }
  }

  def sealedTraitMatch(flyingObject: FlyingCaseObjects): Unit = {
    flyingObject match {
      case AirplaneCaseObject => println("I am an airplane")
      case BirdCaseObject => println("I am a bird")
    }
  }

}

object Car extends Vehicle {
  val numberOfWheels = 4

  def run(): Unit = {
    val currentDateAndTime: Date = new Date(System.currentTimeMillis())
    println(s"I am a new car running on $currentDateAndTime !")
  }
}

case object Bicycle extends Vehicle {
  val numberOfWheels = 2

  def run(): Unit = {
    val currentDateAndTime: Date = new Date(System.currentTimeMillis())
    println(s"I am a new bicycle running on $currentDateAndTime !")
  }
}

abstract class Vehicle

//Create enumeration
object FlyingObject extends Enumeration {

  //Assigning values
  val airplane: Value = Value("AP")
  val bird: Value = Value("BD")
  val drone: Value = Value("DE")
}

// Enumeration changing default ID of an value
object FlyingObjectChangingID extends Enumeration {

  //Assigning values
  val airplane: Value = Value(2, "AP")
  val bird: Value = Value(3, "BD")
  val drone: Value = Value(1, "DE")
}

sealed trait FlyingCaseObjects

case object AirplaneCaseObject extends FlyingCaseObjects

case object BirdCaseObject extends FlyingCaseObjects

case object DroneCaseObject extends FlyingCaseObjects
