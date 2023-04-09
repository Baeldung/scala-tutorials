package com.baeldung.scala.builderpattern

sealed trait TBoolean
sealed trait TTrue extends TBoolean
sealed trait TFalse extends TBoolean

case class SafeGuitarBuilder[Electric <: TBoolean] private (
  isElectric: Boolean = false,
  numberOfStrings: Int = 6,
  tuning: String = "standard",
  tone: String = "clean",
  reverb: Float = 0.0f,
  delay: Int = 0
) {
  def electric: SafeGuitarBuilder[TTrue] = copy[TTrue](isElectric = true)

  def withReverb(reverb: Float)(implicit
    ev: Electric =:= TTrue
  ): SafeGuitarBuilder[TTrue] = copy[TTrue](reverb = reverb)

  def build(): Guitar = Guitar(
    isElectric = isElectric,
    numberOfStrings = numberOfStrings,
    tuning = tuning,
    tone = tone,
    reverb = reverb,
    delay = delay
  )
}

object SafeGuitarBuilder {
  def apply() = new SafeGuitarBuilder[TFalse]()
}
