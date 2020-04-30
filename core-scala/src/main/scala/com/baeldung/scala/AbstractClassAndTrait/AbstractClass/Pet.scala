package com.baeldung.scala.AbstractClassAndTrait.AbstractClass

abstract class Pet (name: String) {
  def speak: Unit = println(s"My name is $name")
}
