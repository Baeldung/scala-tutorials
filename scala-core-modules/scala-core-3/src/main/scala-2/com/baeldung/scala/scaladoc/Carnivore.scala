package com.baeldung.scala.scaladoc

/** Defines a meat eater.
  *
  * movement must be specified in class using this. Used by
  * [[com.baeldung.scala.scaladoc.TasmanianDevil]]
  */
trait Carnivore {
  def food: String = "meat"
  def movement: String
}
