package com.baeldung.scala.withtrait

trait Politician {
  val politicalParty, politicalOrientation, charge: String

  def vote(): Unit =
    println("I'm voting")

  def saySpeech(): Unit =
    println("I'm saying an important speech: blah, blah, blah...")
}
