package com.baeldung.scala.spire.rings
import com.baeldung.scala.spire.rings.Rings._
import org.scalatest.wordspec.AnyWordSpec

class RingsUnitTest extends AnyWordSpec {

  "sum" should {
    "return the sum of a list of integers" in {
      val input = List(1, 2, 3, 4, 5)
      val expectedSum = 15
      val result = sum(input)
      assert(result == expectedSum)
    }

    "return 0 for an empty list" in {
      val input = List.empty[Int]
      val expectedSum = 0
      val result = sum(input)
      assert(result == expectedSum)
    }
  }

  "square" should {
    "return the squared values of a list of integers" in {
      val input = List(1, 2, 3, 4, 5)
      val expectedSquared = Seq(1, 4, 9, 16, 25)
      val result = square(input)
      assert(result == expectedSquared)
    }

    "return an empty sequence for an empty list" in {
      val input = List.empty[Int]
      val expectedSquared = Seq.empty[Int]
      val result = square(input)
      assert(result == expectedSquared)
    }
  }
}
