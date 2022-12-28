package com.baeldung.scala.lastelement

import org.scalatest.wordspec.AnyWordSpec

class FinderSpec extends AnyWordSpec {
  import Finder._

  "A last element finder" should {
    "fail to find last elements in an empty list" in {
      val list = List.empty[Char]
      assert(list.lastWhen(_ == 'c') < 0)
    }

    "fail find last elements if they are not there" in {
      val list = List('a', 'b', 'c', 'd', 'c', 'e')
      assert(list.lastWhen(_ == 'n') < 0)
    }

    "find last element as last if all are the same" in {
      val list = List('a', 'a', 'a', 'a', 'a')
      assertResult(4)(list.lastWhen(_ == 'a'))
    }

    "find last element (happy case)" in {
      val list = List('a', 'b', 'c', 'd', 'c', 'e')
      assertResult(4)(list.lastWhen(_ == 'c'))
    }
  }

}
