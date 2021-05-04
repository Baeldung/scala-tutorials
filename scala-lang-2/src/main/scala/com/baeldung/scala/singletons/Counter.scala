package com.baeldung.scala.singletons

object Counter {
  private var counter: Int = 0
  val label: String = "CounterLabel"

  def increment(): Unit = {
    counter += 1
  }

  def get: Int = counter
}
