package com.baeldung.scala.zip

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.LazyZip2

class ZipUnitTest extends AnyWordSpec with Matchers {

  "Zip" should {
    "zip two lists" in {
      val list1 = List(1, 2, 3)
      val list2 = List("a", "b", "c")
      val zipped = list1.zip(list2)
      zipped shouldBe List((1, "a"), (2, "b"), (3, "c"))
      Map(1 -> 2) zip Map(3 -> 4)
      Set(1, 2) zip Set(3, 4)
    }

  }

  "Lazy Zip" should {
    "zip two lists" in {
      val list1 = List(1, 2, 3)
      val list2 = List("a", "b", "c")
      val zipped = list1.lazyZip(list2)
      zipped.toList shouldBe List((1, "a"), (2, "b"), (3, "c"))
    }

    "zip two potentially infinite sequences" in {
      val infiniteNumbers: LazyList[Int] = LazyList.from(1)
      val infiniteStrings: LazyList[String] = LazyList.iterate("a")(_ + "a")
      val result = infiniteNumbers.lazyZip(infiniteStrings)
      result.take(3).toList shouldBe List((1, "a"), (2, "aa"), (3, "aaa"))
    }

    "automatically flattens upto 4 levels" in {
      val list = List(1, 2, 3)
      val level4Res = list.lazyZip(list).lazyZip(list).lazyZip(list).toList
      level4Res shouldBe List((1, 1, 1, 1), (2, 2, 2, 2), (3, 3, 3, 3))
      val level5Res =
        list.lazyZip(list).lazyZip(list).lazyZip(list).lazyZip(list).toList
      level5Res shouldBe List(
        ((1, 1, 1, 1), 1),
        ((2, 2, 2, 2), 2),
        ((3, 3, 3, 3), 3)
      )
    }
  }

}
