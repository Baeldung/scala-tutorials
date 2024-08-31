package com.baeldung.scala.romannumeral

import com.baeldung.scala.romannumerals.RoundingDecimals
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec

class RoundingDecimalsUnitTest extends AnyWordSpec with Matchers {

  "RoundingDecimals" should {
    "Always round literals down" in {
      5 / 3 shouldBe 1
    }
    "Always round up in roundUp" in {
      RoundingDecimals.roundUp(1.1) shouldBe 2
      RoundingDecimals.roundUp(1.5) shouldBe 2
      RoundingDecimals.roundUp(1.9) shouldBe 2
    }
    "Always round down in roundDown" in {
      RoundingDecimals.roundDown(1.1) shouldBe 1
      RoundingDecimals.roundDown(1.5) shouldBe 1
      RoundingDecimals.roundDown(1.9) shouldBe 1
    }
    "Always round to nearest in roundDown" in {
      RoundingDecimals.roundToNearestWhole(1.1) shouldBe 1
      RoundingDecimals.roundToNearestWhole(1.5) shouldBe 2
      RoundingDecimals.roundToNearestWhole(1.9) shouldBe 2
    }
  }
}
