package com.baeldung.scala3.traitsvsabstractclasses

trait Runner {
  def run(): String = "I'm running"
}

trait Swimmer {
  def swim(): String = "I'm swimming"
}

class Human(name: String) extends Runner with Swimmer

trait Singer {
  def sing(): String = "I'm singing"
}

abstract class SomeAbstractClass {
  def doSomething(): Unit
}

object Example extends App {
  val john = new Human("John")
  println(john.swim())
  val elvis = new Human("Elvis") with Singer
  println(elvis.sing())
}
