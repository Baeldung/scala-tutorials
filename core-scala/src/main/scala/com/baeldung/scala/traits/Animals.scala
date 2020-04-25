package com.baeldung.scala.traits

trait Animals {
  def eat(): String
  def legs(): Int
  val nickname: String
}

trait Sound {
  val sound: String
  def speak(): Unit = println(sound)
}

class Ant(name: String) extends Animals {
  def eat(): String = "Plant"
  def legs(): Int = 6
  val nickname: String = name
}

class Dog(name: String) extends Animals with Sound {
  def eat(): String = "Meat"
  def legs(): Int = 4
  val nickname: String = name

  val sound: String = "Woof"
}

class Cow(name: String) extends Animals with Sound {
  def eat(): String = "Plant"
  def legs(): Int = 4
  val nickname: String = name

  val sound: String = "Moo"
  override def speak(): Unit = println(s"Cow goes $sound")
}
