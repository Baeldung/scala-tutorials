package com.baeldung.scala.traitsandabstractclasses

import com.baeldung.scala.traitsandabstractclasses.BirdClassExtendsTrait.Bird
import org.scalatest.FunSuite

class BirdTest extends FunSuite {
  test("Bird class self presentation definition returns expected output") {
    val bird = new Bird()
    val result = bird.selfPresentation()

    assert(result == "Chirp, I'm a bird!")
  }
}