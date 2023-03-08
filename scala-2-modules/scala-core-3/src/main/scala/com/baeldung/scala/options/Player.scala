package com.baeldung.scala.options

trait Player {
  def name: String
  def getFavoriteTeam: Option[String]
}
