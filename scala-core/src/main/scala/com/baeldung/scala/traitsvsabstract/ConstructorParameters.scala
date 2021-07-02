package com.baeldung.scala.traitsvsabstract

trait Vehicle {
  def weight: Int
}

class Motorbike(val weight: Int) extends Vehicle
