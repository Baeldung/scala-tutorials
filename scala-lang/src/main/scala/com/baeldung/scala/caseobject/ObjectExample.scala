package com.baeldung.scala.caseobject

import java.sql.Date

object ObjectExample extends App {
  println(Car.isInstanceOf[Serializable])
  println(Bicycle.isInstanceOf[Serializable])

  messageVehicle(Car)

  def messageVehicle(vehicle: Vehicle): Unit = {
    vehicle match {
      case Car => println("send message to Car")
      case Bicycle => println("send message to Bicycle")
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
