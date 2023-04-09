package com.baeldung.scala.builderpattern

case class GuitarBuilder(
  isElectric: Boolean = true,
  numberOfStrings: Int = 6,
  tuning: String = "standard",
  tone: String = "clean",
  reverb: Float = 0.0f,
  delay: Int = 0
) {
  def withElectric(isElectric: Boolean): GuitarBuilder =
    copy(isElectric = isElectric)

  def withNStrings(nStrings: Int): GuitarBuilder =
    copy(numberOfStrings = nStrings)

  def withTuning(tuning: String): GuitarBuilder = copy(tuning = tuning)

  def withTone(tone: String): GuitarBuilder = copy(tone = tone)

  def withReverb(reverb: Float): GuitarBuilder = copy(reverb = reverb)

  def withDelay(delay: Int): GuitarBuilder = copy(delay = delay)

  def build(): Guitar = Guitar(
    isElectric = isElectric,
    numberOfStrings = numberOfStrings,
    tuning = tuning,
    tone = tone,
    reverb = reverb,
    delay = delay
  )
}