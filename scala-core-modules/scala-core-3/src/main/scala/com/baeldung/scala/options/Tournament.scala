package com.baeldung.scala.options

trait Tournament {
  def getTopScore(team: String): Option[Int]
}
