package com.baeldung.scala.mutability

class ImmutabilityCar(color: String, val wheels: Int, var engine: String) {
  def call(): Unit = {
    println(s"This is a car with $color paint, $wheels wheels, and $engine engine")
  }
}
