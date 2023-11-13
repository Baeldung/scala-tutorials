package com.baeldung.scala3.intersectiontypes

object Overloading {
  class Knife()
  class Scissors()

  def cutPaper(cutter: Knife) = println("Cutting with Knife")
  def cutPaper(cutter: Scissors) = println("Cutting with Scissors")
}
