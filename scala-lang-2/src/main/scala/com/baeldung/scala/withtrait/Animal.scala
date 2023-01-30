package com.baeldung.scala.withtrait

class Animal(name: String, species: String) {
  def makeNoise(noise: String): String =
    s"I'm $name (a $species) making this noise: $noise"
}
