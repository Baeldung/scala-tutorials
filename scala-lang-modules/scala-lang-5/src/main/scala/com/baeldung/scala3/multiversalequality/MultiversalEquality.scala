package com.baeldung.scala3.multiversalequality
import scala.language.strictEquality

object MultiversalEquality extends App {
  case class Dog(name: String)
  val rover = Dog("Rover")
  val fido = Dog("Fido")

  // fido == rover  // Throws compile error : "Values of types Dog and Dog cannot be compared with == or !="
}
