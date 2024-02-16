package com.baeldung.scala.zip

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ZipUnitTest extends AnyWordSpec with Matchers {

  "Zip" should {
    "zip two lists" in {
      val list1 = List(1, 2, 3)
      val list2 = List("a", "b", "c")
      val zipped = list1.zip(list2)
      zipped should be(List((1, "a"), (2, "b"), (3, "c")))
    }

  }

  "Lazy Zip" should {
    "zip two lists" in {
      val list1 = List(1, 2, 3)
      val list2 = List("a", "b", "c")
      val zipped = list1.lazyZip(list2)
      zipped.toList should be(List((1, "a"), (2, "b"), (3, "c")))
    }
  }

}
