package com.baeldung.scala.builderpattern

import org.scalatest.wordspec.AnyWordSpec

class GuitarSpec extends AnyWordSpec {

  "an unsafe guitar builder" should {
    "resort to defaults when not initialised" in {
      val expectedGuitar = Guitar(
        isElectric = false,
        numberOfStrings = 6,
        tuning = "standard",
        tone = "clean",
        reverb = 0.0f,
        delay = 0
      )
      val actualGuitar = GuitarBuilder().build()
      assertResult(expectedGuitar)(actualGuitar)
    }
    "initialise from just some values" in {
      val expectedGuitar = Guitar(
        isElectric = true,
        numberOfStrings = 6,
        tuning = "dadgad",
        tone = "brit-j800",
        reverb = 0.2f
      )
      val actualGuitar = GuitarBuilder()
        .withElectric(true)
        .withTuning("dadgad")
        .withTone("brit-j800")
        .withReverb(0.2f)
        .build()
      assertResult(expectedGuitar)(actualGuitar)
    }
  }

  "a safe guitar builder" should {
    "allow reverb only in electric guitars" in {
      assertTypeError("""
          |val acousticGuitar = SafeGuitarBuilder()
          |          .withReverb(0.2f)
          |          .build()
          |""".stripMargin)
      val electricGuitar = SafeGuitarBuilder().electric
        .withReverb(0.2f)
        .build()
    }
  }
}
