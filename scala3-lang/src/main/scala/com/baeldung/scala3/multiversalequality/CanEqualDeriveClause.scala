package com.baeldung.scala3.multiversalequality
import scala.language.strictEquality

object CanEqualDeriveClause extends App{
  case class Circle(radius: Float) derives CanEqual
  val circle1 = Circle(5)
  val circle2 = Circle(5)
  println(circle1 == circle2) // No compilation errors & prints true.
}
