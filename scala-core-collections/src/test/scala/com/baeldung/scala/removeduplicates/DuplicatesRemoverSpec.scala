package com.baeldung.scala.removeduplicates

import org.scalatest.wordspec.AnyWordSpec

class DuplicatesRemoverSpec extends AnyWordSpec {

  "DuplicatesRemover" should {
    "return a shorter integers list without duplicates" in {
      val withDuplicates = List(3, 7, 2, 7, 1, 3, 4)
      val withoutDuplicates = List(3, 7, 2, 1, 4)
      val deDuplicated =
        DuplicatesRemover.removeDuplicates(withDuplicates)
      assertResult(withoutDuplicates)(deDuplicated)
    }
  }
}
