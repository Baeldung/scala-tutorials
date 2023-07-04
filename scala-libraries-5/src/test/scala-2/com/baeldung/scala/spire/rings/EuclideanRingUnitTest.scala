package com.baeldung.scala.spire.rings

import com.baeldung.scala.spire.rings.EuclideanRings._
import org.scalatest.wordspec.AnyWordSpec
import spire.implicits._

class EuclideanRingUnitTest extends AnyWordSpec {

  "equotient" should {

    "calculate the Euclidean quotient" in {
      assert(equotient(10, 3) == 3)
      assert(equotient(15, 4) == 3)
      assert(equotient(7, 2) == 3)
    }

  }

  "equotientmod" should {

    "calculate the Euclidean quotient and remainder" in {
      assert(equotientmod(10, 3) == (3, 1))
      assert(equotientmod(15, 4) == (3, 3))
      assert(equotientmod(7, 2) == (3, 1))
    }
  }
}
