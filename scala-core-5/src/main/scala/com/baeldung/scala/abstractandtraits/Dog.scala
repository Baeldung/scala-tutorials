package com.baeldung.scala.abstractandtraits

import scala.concurrent.duration.{Duration, DurationInt}

class Dog(name: String) extends Animal(name) with Claws {
  override def averageSleepTime(): Duration = 10.hours
}
