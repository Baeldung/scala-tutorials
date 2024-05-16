package com.baeldung.scala.zio.prelude.averages

import com.baeldung.scala.zio.prelude.averages.CombinedAverage.*
import org.scalatest.wordspec.AnyWordSpec
import zio.prelude.AssociativeOps

class CombinedAverageUnitTest extends AnyWordSpec {
  val avg1: Double = (2.0 + 3.0 + 4.0) / 3 // avg1 is 3.0
  val avg2: Double = (10.0 + 11.0) / 2 // avg2 is 10.5

  "a simple average" should {
    "sum its elements and divide them by their count" in {
      assertResult(3.0)(avg1)
      assertResult(10.5)(avg2)
    }
    "not be associative" in {
      val avgAvg = (avg1 + avg2) / 2

      // if "average" were associative, the average of the two averages
      // would be 6, but it's 6.75 instead!
      assert(avgAvg != 6.0)
    }
  }

  "a smarter average" should {
    "properly combine partial averages" in {
      val avg1 = CombinedAverage(3.0, 3)
      val avg2 = CombinedAverage(10.5, 2)
      val avgAvg = avg1 <> avg2
      assertResult(CombinedAverage(6.0, 5))(avgAvg)
    }
  }
}
