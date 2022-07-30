package com.baedung.scala.splitlist

import com.baeldung.scala.splitlist.ListSplitter
import org.scalatest.wordspec.AnyWordSpec

class ListSplitterSpec extends AnyWordSpec {

  "ListSplitter" should {
    "return a list of the right number of lists" in {
      val originalList = (0 until 105).toList
      val splitListTry = ListSplitter.split(originalList, 10)
      assert(splitListTry.isSuccess)
      val splitList = splitListTry.get
      assertResult(11)(splitList.size)
    }
    "return a list of lists with the right cumulative number of elements" in {
      val originalList = (0 until 105).toList
      val splitListTry = ListSplitter.split(originalList, 10)
      assert(splitListTry.isSuccess)
      val splitList = splitListTry.get
      val count = splitList.foldLeft(0)((n, bs) => n + bs.size)
      assertResult(105)(count)
    }
    "return a list of lists with the right elements and the right order" in {
      val baeldung = List('b', 'a', 'e', 'l', 'd', 'u', 'n', 'g')
      val splitBaeldungTry = ListSplitter.split(baeldung, 3)
      assert(splitBaeldungTry.isSuccess)
      val splitBaeldung = splitBaeldungTry.get
      val expected =
        List(List('b', 'a', 'e'), List('l', 'd', 'u'), List('n', 'g'))
      assertResult(expected)(splitBaeldung)
    }
    "handle empty lists" in {
      val splitListTry = ListSplitter.split(List.empty[Int], 3)
      assert(splitListTry.isSuccess)
      val splitList = splitListTry.get
      assertResult(List.empty[List[Int]])(splitList)
    }
    "fail with wrong slice size" in {
      val baeldung = List('b', 'a', 'e', 'l', 'd', 'u', 'n', 'g')
      val splitBaeldungTry = ListSplitter.split(baeldung, 0)
      assert(splitBaeldungTry.isFailure)
    }
  }
}
