package com.baeldung.scala.zipvariants

import org.scalatest.wordspec.AnyWordSpec

class ZipVariantUnitTest extends AnyWordSpec {

  "zip method" should {
    "combine two collections of equal size correctly" in {
      val numbers = List(1, 2, 3)
      val words = List("one", "two", "three")
      val zipped = numbers.zip(words)
      assert(zipped == List((1, "one"), (2, "two"), (3, "three")))
    }

    "drop elements from larger collection" in {
      val numbers = List(1, 2, 3)
      val words = List("one", "two", "three", "four")
      val zipped = numbers.zip(words)
      assert(zipped == List((1, "one"), (2, "two"), (3, "three")))
    }
  }

  "zipAll" should {
    "combine two collections of unequal size with default values" in {
      val numbers = List(1, 2, 3)
      val words = List("one", "two", "three", "four")
      val zipped = numbers.zipAll(words, 0, "unknown")
      assert(zipped == List((1, "one"), (2, "two"), (3, "three"), (0, "four")))
      val zipped2 = numbers.zipAll(words.take(2), 0, "unknown")
      assert(zipped2 == List((1, "one"), (2, "two"), (3, "unknown")))
    }
  }

  "zipWithIndex" should {
    "combine each element of a list with its corresponding index" in {
      val words = List("one", "two", "three", "four")
      assert(
        words.zipWithIndex == List(
          ("one", 0),
          ("two", 1),
          ("three", 2),
          ("four", 3)
        )
      )
    }
  }

}
