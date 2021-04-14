package com.baeldung.scala.singletons

object Counter {
  private var counter: Int = 0

  def increment(): Unit = {
    counter += 1
  }

  def get: Int = counter
}
