package com.baeldung.scala.traits

object MultipleInheritance extends App {

  abstract class Animal {
    val name: String
  }

  trait Flyable {
    def fly
  }

  trait Speakable {
    def speak
  }

  trait Walkable {
    def walk
  }

  class Cat extends Animal with Speakable with Walkable {
    val name: String = "Cat"

    def speak: Unit = println("meaow")

    def walk: Unit = println("walking")
  }

  val cat = new Cat

  cat.speak
  cat.walk

}
