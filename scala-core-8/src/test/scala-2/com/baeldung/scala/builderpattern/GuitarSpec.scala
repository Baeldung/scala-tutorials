package com.baeldung.scala.builderpattern

import org.scalatest.wordspec.AnyWordSpec

class GuitarSpec extends AnyWordSpec {
  "a guitar" should {
    "resort to defaults when not initialised" in {
      val expectedGuitar = Guitar(
        isElectric = true,
        numberOfStrings = 6,
        tuning = "standard",
        tone = "clean",
        reverb = 0.0f,
        delay = 0
      )
      val actualGuitar = GuitarBuilder().build()
      assertResult(expectedGuitar)(actualGuitar)
    }
    "initialise only some values" in {
      val expectedGuitar = Guitar(
        isElectric = true,
        numberOfStrings = 6,
        tuning = "dadgad",
        tone = "brit-j800",
        reverb = 0.2f
      )
      val actualGuitar = GuitarBuilder()
        .withTuning("dadgad")
        .withTone("brit-j800")
        .withReverb(0.2f)
        .build()
      assertResult(expectedGuitar)(actualGuitar)
    }
  }
}
