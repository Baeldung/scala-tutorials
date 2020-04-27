package com.baeldung.scala.traits

sealed trait MixingAlgorithm

case object LowInstrumentalQuality extends MixingAlgorithm {
  override def toString(): String = "Low instrumental quality"
}

case object HighInstrumentalQuality extends MixingAlgorithm {
  override def toString(): String = "High instrumental quality"
}
