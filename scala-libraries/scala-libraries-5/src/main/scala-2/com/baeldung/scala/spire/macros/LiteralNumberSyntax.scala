package com.baeldung.scala.spire.macros

import spire.implicits._

object LiteralNumberSyntax extends App {

  val poly1 = poly"3x^2 - 5x + 1"
  val poly2 = poly"5/4x^6 - 7x - 2"
  val poly3 = poly1.-(poly2)
  println(poly3)

}
