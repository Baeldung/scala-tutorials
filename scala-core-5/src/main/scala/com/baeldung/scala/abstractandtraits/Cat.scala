package com.baeldung.scala.abstractandtraits

import scala.concurrent.duration.{Duration, DurationInt}

class Cat(name: String) extends Animal(name) {
  override def averageSleepTime(): Duration = 15.hours
}
