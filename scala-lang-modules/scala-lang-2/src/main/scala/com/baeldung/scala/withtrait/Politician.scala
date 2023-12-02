package com.baeldung.scala.withtrait

trait Politician {
  def vote(): String =
    "I'm voting"

  def saySpeech(speech: String): String =
    s"I'm saying an important speech: $speech"
}
