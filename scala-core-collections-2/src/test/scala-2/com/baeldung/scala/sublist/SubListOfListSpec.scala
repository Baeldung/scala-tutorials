package com.baeldung.scala.sublist

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SubListOfListSpec extends AnyWordSpec with Matchers {
  val fullList = "a" :: "b" :: "c" :: "d" :: Nil
  val subList = "a" :: "b" :: "c" :: Nil
  val notSubList = "e" :: "f" :: Nil
  val subListWithGap = "a" :: "c" :: Nil
  val subListUnordered = "b" :: "a" :: Nil

  "usingForAll" should {
    "return true if list in list" in {
      SubListOfList.usingForAll(fullList, subList) shouldBe true
    }
    "return true if list in list but not together" in {
      SubListOfList.usingForAll(fullList, subListWithGap) shouldBe true
    }
    "return true if list in list but not in order" in {
      SubListOfList.usingForAll(fullList, subListUnordered) shouldBe true
    }
    "return false if list not in list" in {
      SubListOfList.usingForAll(fullList, notSubList) shouldBe false
    }
  }

  "usingFoldLeftAllowGaps" should {
    "return true if list in list" in {
      SubListOfList.usingFoldLeftAllowGaps(fullList, subList) shouldBe true
    }
    "return true if list in list but not together" in {
      SubListOfList.usingFoldLeftAllowGaps(
        fullList,
        subListWithGap
      ) shouldBe true
    }
    "return false if list in list but not in order" in {
      SubListOfList.usingFoldLeftAllowGaps(
        fullList,
        subListUnordered
      ) shouldBe false
    }
    "return false if list not in list" in {
      SubListOfList.usingFoldLeftAllowGaps(fullList, notSubList) shouldBe false
    }
  }

  "usingFoldLeftNoGaps" should {
    "return true if list in list" in {
      SubListOfList.usingFoldLeftNoGaps(fullList, subList) shouldBe true
    }
    "return true if list in list but not together" in {
      SubListOfList.usingFoldLeftNoGaps(fullList, subListWithGap) shouldBe true
    }
    "return false if list in list but not in order" in {
      SubListOfList.usingFoldLeftNoGaps(
        fullList,
        subListUnordered
      ) shouldBe false
    }
    "return false if list not in list" in {
      SubListOfList.usingFoldLeftNoGaps(fullList, notSubList) shouldBe false
    }
  }

  "usingSliding" should {
    "return true if list in list" in {
      SubListOfList.usingSliding(fullList, subList) shouldBe true
    }
    "return false if list in list but not together" in {
      SubListOfList.usingSliding(fullList, subListWithGap) shouldBe false
    }
    "return false if list in list but not in order" in {
      SubListOfList.usingSliding(fullList, subListUnordered) shouldBe false
    }
    "return false if list not in list" in {
      SubListOfList.usingSliding(fullList, notSubList) shouldBe false
    }
  }

  "usingContainsSlice" should {
    "return true if list in list" in {
      SubListOfList.usingContainsSlice(fullList, subList) shouldBe true
    }
    "return false if list in list but not together" in {
      SubListOfList.usingContainsSlice(fullList, subListWithGap) shouldBe false
    }
    "return false if list in list but not in order" in {
      SubListOfList.usingContainsSlice(
        fullList,
        subListUnordered
      ) shouldBe false
    }
    "return false if list not in list" in {
      SubListOfList.usingContainsSlice(fullList, notSubList) shouldBe false
    }
  }
}
