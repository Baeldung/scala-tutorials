package com.baeldung.scala.withtrait

trait Politician {
  val politicalParty, politicalOrientation, charge: String

  def vote(): String =
    "I'm voting"

  def saySpeech(): String =
    "I'm saying an important speech: blah, blah, blah..."
}
