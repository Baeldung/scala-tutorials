package com.baeldung.scala.slidinggrouped

import org.scalatest.wordspec.AnyWordSpec

class SlidingGroupedTest extends AnyWordSpec {

  "sliding method" should {
    "creating a sliding window with 2 elements" in {
      val numbers: List[Int] = List(1, 2, 3, 4, 5, 6)
      val slidingWindow: Iterator[List[Int]] = numbers.sliding(2, 1)
      assert(
        slidingWindow.toList == List(
          List(1, 2),
          List(2, 3),
          List(3, 4),
          List(4, 5),
          List(5, 6)
        )
      )
    }
    "without providing sliding window should use 1 by default" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val window = numbers.sliding(2)
      assert(
        window.toList == List(
          List(1, 2),
          List(2, 3),
          List(3, 4),
          List(4, 5),
          List(5, 6)
        )
      )
    }

    "create a group with lesser number than the group size provided" in {
      val numbers = List(1, 2, 3, 4)
      assert(numbers.sliding(3, 2).toList == List(List(1, 2, 3), List(3, 4)))
    }

  }

  "grouped method" should {
    "group the elements into equal groupings if possible" in {
      val numbers = List(1, 2, 3, 4)
      assert(numbers.grouped(2).toList == List(List(1, 2), List(3, 4)))
    }

    "be same as sliding if the sliding window and group size is same" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val slidingList =
        numbers.sliding(2, 2).toList // List(List(1, 2), List(3, 4), List(5, 6))
      val groupedList =
        numbers.grouped(2).toList // List(List(1, 2), List(3, 4), List(5, 6))
      assert(slidingList == groupedList)
    }
  }

}
