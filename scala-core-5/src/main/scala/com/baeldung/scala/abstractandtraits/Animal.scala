package com.baeldung.scala.abstractandtraits

import scala.concurrent.duration.Duration

abstract class Animal(val name: String) {
  def averageSleepTime(): Duration // This method is abstract
}
