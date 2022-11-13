package com.baedung.scala.flattening

import org.scalatest.wordspec.AnyWordSpec

class FlattenerSpec extends AnyWordSpec {

  import com.baeldung.scala.flattening.Flattener.sequenceFlattener

  "A full flattener" should {
    "respect the contents of an already flat sequence" in {
      val flatList = List(3, 7, 2, 7, 1, 3, 4)
      assertResult(flatList)(flatList.fullFlat)
    }
    "flatten a nested empty list to an empty list" in {
      val list = List(List(List()))
      assertResult(List.empty)(list.fullFlat)
    }
    "flatten several lists of the same type, one level deep" in {
      val list = List(
        List(1, 2, 3),
        List(4, 5),
        List(6)
      )
      assertResult(List(1, 2, 3, 4, 5, 6))(list.fullFlat)
    }
    "flatten several lists of the same type, diverse levels deep" in {
      val list = List(
        List(1, List(2, 3)),
        List(4, List(List(5))),
        List(6)
      )
      assertResult(List(1, 2, 3, 4, 5, 6))(list.fullFlat)
    }
    "flatten several lists of the diverse types, diverse levels deep" in {
      val list = List(
        List(1, List("b", 'c')),
        List(4.4, List(List(5)))
      )
      assertResult(List(1, "b", 'c', 4.4, 5))(list.fullFlat)
    }
  }
}
