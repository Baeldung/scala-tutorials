package com.baeldung.scala.builderpattern

case class Guitar(
  isElectric: Boolean = true,
  numberOfStrings: Int = 6,
  tuning: String = "standard",
  tone: String = "clean",
  reverb: Float = 0.0f,
  delay: Int = 0
)

object Guitar {
  def builder(): GuitarBuilder = GuitarBuilder()
}
