package com.baeldung.scala.firstmatching

import org.scalatest.wordspec.AnyWordSpec

class FinderUnitTest extends AnyWordSpec {
  import Finder._

  "A finder" should {
    "find nothing in an empty collection" in {
      val collection = Vector[Int]()
      val expected = None
      val actual = collection.findMatch(_ > 0)
      assertResult(expected)(actual)
    }
    "find nothing if the element is not there" in {
      val collection = 2 :: 4 :: 6 :: 8 :: Nil
      val expected = None
      val actual = collection.findMatch(_ > 8)
      assertResult(expected)(actual)
    }
    "find the first element that matches" in {
      val collection =
        ('a' -> 0) :: ('b' -> 1) :: ('c' -> 2) :: ('d' -> 3) :: Nil
      val expected = 'b'
      val actual = collection.findMatch(pair => pair._2 > 0).get._1
      assertResult(expected)(actual)
    }
  }
}
