package com.baeldung.scala.takewhiledropwhile

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TakeWhileDropWhileUnitTest extends AnyWordSpec with Matchers {

  private val numbersAscending = List(1, 2, 3, 4, 5, 6, 7, 8)
  private val numbersDescending = List(8, 7, 6, 5, 4, 3, 2, 1)
  private val numbersMixed = List(1, 2, 3, 4, 3, 2, 1)

  "takeWhile" should {
    "take element from list till condition satisfies" in {
      val lessThanFive = numbersAscending.takeWhile(_ < 5)
      lessThanFive shouldBe List(1, 2, 3, 4)
    }

    "return different elements when the element order changes" in {
      val lessThanFive = numbersDescending.takeWhile(_ < 5)
      lessThanFive shouldBe Nil
    }

    "stop taking as soon as the first failure in predicate" in {
      val lessThanThree = numbersMixed.takeWhile(_ < 3)
      lessThanThree shouldBe List(1, 2)
    }

    "take all elements if the predicate is satisfied for all" in {
      val positive = numbersAscending.takeWhile(_ > 0)
      positive shouldBe numbersAscending
    }

  }

  "dropWhile" should {
    "drop the elements from the list until the predicate is true" in {
      val dropLessThan5 = numbersAscending.dropWhile(_ < 5)
      dropLessThan5 shouldBe List(5, 6, 7, 8)
    }

    "dropWhile behavior changes with the order" in {
      val dropLessThan5 = numbersDescending.dropWhile(_ < 5)
      dropLessThan5 shouldBe numbersDescending
    }
  }

}
