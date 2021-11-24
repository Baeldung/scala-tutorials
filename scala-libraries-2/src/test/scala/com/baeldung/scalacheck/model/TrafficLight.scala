package com.baeldung.scalacheck.model

import java.util.UUID

sealed trait TrafficLightColorT {
  def color: String
}

object TrafficLightColors {
  val RED = "red"
  val YELLOW = "yellow"
  val GREEN = "green"
}

class TrafficLightColor(override val color: String) extends TrafficLightColorT

case object Red extends TrafficLightColor(TrafficLightColors.RED)

case object Yellow extends TrafficLightColor(TrafficLightColors.YELLOW)

case object Green extends TrafficLightColor(TrafficLightColors.GREEN)

case class TrafficLight(uuid: UUID, color: TrafficLightColorT)
