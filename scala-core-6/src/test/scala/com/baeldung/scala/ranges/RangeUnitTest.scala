package com.baeldung.scala.ranges

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RangeUnitTest extends AnyWordSpec with Matchers {

  "Inclusive Range" should {
    "include both start and end of a range" in {
      val rangeIncl = Range.inclusive(1,10)
      rangeIncl.size shouldBe 10
      rangeIncl.head shouldBe 1
      rangeIncl.last shouldBe 10

    }
  }

  "Range with 'to'" should {
    "create an inclusive range" in {
      val rangeIncl = 1 to 10
      rangeIncl.size shouldBe 10
      rangeIncl.head shouldBe 1
      rangeIncl.last shouldBe 10
    }
  }

  "Range" should {
    "create an exclusive range" in {
      val rangeExcl = Range(1,10)
      rangeExcl.size shouldBe 9
      rangeExcl.head shouldBe 1
      rangeExcl.last shouldBe 9
    }
  }

  "Range with until" should {
    "create an exclusive range" in {
      val rangeExcl = 1 until 10
      rangeExcl.size shouldBe 9
      rangeExcl.head shouldBe 1
      rangeExcl.last shouldBe 9
    }
  }

  it should {
    "create a range with step value 2" in {
      val rangeStepOpt1 = 1 to 10 by 3
      rangeStepOpt1.toList should contain allElementsOf List(1,4,7,10)

      val rangeStepOpt2 = Range.inclusive(1,10,3)
      rangeStepOpt2.toList should contain allElementsOf List(1,4,7,10)
    }
  }

  it should {
    "create a range with odd numbers" in {
      val rangeOdd = 1 to 10 by 2
      rangeOdd.toList should contain allElementsOf List(1,3,5,7,9)
    }
  }

  "Negative step" should {
    "create a reverse range" in {
      val range = 10 to 1 by -2
      range.head shouldBe 10
      range.last shouldBe 2
    }
  }

  it should {
    "create a negative number range" in {
      val negativeRange = -1 to -5 by -1
      negativeRange.toList should contain allElementsOf List(-1,-2,-3,-4,-5)
    }
  }

  it should {
    "create a range of decimal numbers" in {
      val decimalRange = BigDecimal(0.1) to BigDecimal(0.5) by 0.1
      decimalRange.toList should contain allElementsOf List(0.1d, 0.2d, 0.3d, 0.4d, 0.5d)
    }
  }

  "sum" should {
    "calculate the sum of range" in {
      val range = 1 to 5
      range.sum shouldBe 15
    }
  }

  it should {
    "create empty range for wrong step" in {
      val range = 10 to 1 by 1
      range.size shouldBe 0
    }
  }

  it should {
    "convert a inclusive range" in {
      val exclRange = 1 until 5
      exclRange.last shouldBe 4
      val inclRange = exclRange.inclusive
      inclRange.last shouldBe 5
    }
  }

}
