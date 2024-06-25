package com.baeldung.scala.takewhiledropwhile

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TakeWhileDropWhileUnitTest extends AnyWordSpec with Matchers {

  "takeWhile" should {
    "take element from list till condition satisfies" in {
      val numbers = List(1, 2, 3, 4, 5, 6, 7, 8)
      val lessThanFive = numbers.takeWhile(_ < 5)
      lessThanFive shouldBe List(1, 2, 3, 4)
    }

    "return different elements when the element order changes" in {
      val numbers = List(8, 7, 6, 5, 4, 3, 2, 1)
      val lessThanFive = numbers.takeWhile(_ < 5)
      lessThanFive shouldBe Nil
    }

    "stop taking as soon as the first failure in predicate" in {
      val numbers = List(1, 2, 3, 4, 3, 2, 1)
      val lessThanThree = numbers.takeWhile(_ < 3)
      lessThanThree shouldBe List(1, 2)
    }

  }

  "dropWhile" should {
    "drop the elements from the list until the predicate is true" in {
      val numbers = List(1, 2, 3, 4, 5, 6, 7, 8)
      val dropLessThan5 = numbers.dropWhile(_ < 5)
      dropLessThan5 shouldBe List(5, 6, 7, 8)
    }

    "dropWhile behavior changes with the order" in {
      val numbers = List(8, 7, 6, 5, 4, 3, 2, 1)
      val dropLessThan5 = numbers.dropWhile(_ < 5)
      dropLessThan5 shouldBe numbers
    }
  }

}
