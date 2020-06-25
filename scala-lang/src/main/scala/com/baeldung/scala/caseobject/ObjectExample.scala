package com.baeldung.scala.caseobject

import java.sql.Date

object ObjectExample extends App {

  //println(Car.isInstanceOf[Serializable])
  println(Bicycle.isInstanceOf[Serializable])

  //Pattern Matching
  messageVehicle(Car)

  //Call toString method
  println(Car)
  println(Bicycle)

  //Enumeration: printing the list of all values
  println(s"These are the possible flying objects = ${FlyingObject.values}")
  //Enumeration: printing one element by accessing it's value
  println(s"The value of bird = ${FlyingObject.bird}")
  //Enumeration: printing one element by accessing it's ID
  println(s"The ID of bird = ${FlyingObject.bird.id}")
  //Enumeration: printing the list of all values after changing their IDs
  println(s"These are the possible flying objects = ${FlyingObjectChangingID.values}")
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
  val numberOfWheel = 4

  def run(): Unit = {
    val dtSql: Date = new Date(System.currentTimeMillis())
    println(s"I am a new car running on $dtSql !")
  }
}

case object Bicycle extends Vehicle {
  val numberOfWheel = 2

  def run(): Unit = {
    val dtSql: Date = new Date(System.currentTimeMillis())
    println(s"I am a new bicycle running on $dtSql !")
  }
}

abstract class Vehicle

sealed abstract class FlyingObject

case object Airplane extends FlyingObject

case object Bird extends FlyingObject

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
