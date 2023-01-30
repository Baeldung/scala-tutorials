package com.baeldung.scala.typecasts

object TypeErasure {
  // Some sample classes whose types we'll test
  class T1
  class T2 extends T1
  class T3

  // Typical usage of generic types in Scala
  val counts: List[Int] = List.empty[Int]
  val teamScores: Map[String, Int] =
    Map[String, Int]("Team A" -> 10, "Team B" -> 5, "Team C" -> 13)

  // Simply function that converts a variable number of values to a List of that type
  def convertValuesToList[T](values: T*): List[T] = {
    List[T](values: _*)
  }
}
