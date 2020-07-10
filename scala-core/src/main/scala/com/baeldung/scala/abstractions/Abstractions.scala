package com.baeldung.scala.abstractions

abstract class Car(brand: String) {
  def drive(): Unit
  def playRadio(): Unit = println("Ta da da")
}

trait Fly {
  def goHigher(): Unit
  def land(): Unit = println("Landing...")
}