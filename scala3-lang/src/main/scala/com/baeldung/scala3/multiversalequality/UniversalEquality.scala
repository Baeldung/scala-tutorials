package com.baeldung.scala3.multiversalequality
import com.baeldung.scala3.multiversalequality.MultiversalEquality.Dog

object UniversalEquality extends App{

  case class Square(length: Float)
  case class Circle(radius: Float)
  val square = Square(5)
  val circle = Circle(5)

  println(square == circle) // prints false. No compilation errors
}

