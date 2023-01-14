package com.baeldung.scala.withtrait

case class Animal(name: String, species: String) {
  def makeNoise(noise: String): Unit =
    println(s"I'm $name (a $species) making this noise: $noise")
}
