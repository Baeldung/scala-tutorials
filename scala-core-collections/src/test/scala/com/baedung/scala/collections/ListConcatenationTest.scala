package com.baedung.scala.collections

import org.scalatest.wordspec.AnyWordSpec

class ListConcatenationTest extends AnyWordSpec {

  private val list1 = 1 :: 2 :: 3 :: Nil
  private val list2 = 4 :: 5 :: Nil
  private val list3 = 6 :: 7 :: Nil

  "Lists" should {
    "get concatenated using the ::: operator" in {
      assert(list1 ::: list2 == 1 :: 2 :: 3 :: 4 :: 5 :: Nil)
    }

    "get concatenated using the ::: operator in a row" in {
      assert(
        list1 ::: list2 ::: list3 == 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: Nil
      )
    }

    "get concatenated using the ++ operator" in {
      assert(list1 ++ list2 == 1 :: 2 :: 3 :: 4 :: 5 :: Nil)
    }

    "get concatenated using the ++ operator in a row" in {
      assert(list1 ++ list2 ++ list3 == 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: Nil)
    }
  }

  "++" should {
    "allow for the concatenation of two collections" in {
      assert(List(1, 2) ++ Map(3 -> 4) == List(1, 2, 3 -> 4))
    }

    "allow for the concatenation of a List and a String" in {
      assert(List(1, 2, 3) ++ "ab" == List(1, 2, 3, 'a', 'b'))
    }
  }
}
