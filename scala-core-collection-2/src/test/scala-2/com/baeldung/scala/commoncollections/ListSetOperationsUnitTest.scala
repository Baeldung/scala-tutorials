package com.baeldung.scala.commoncollections

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.immutable.ListSet

class ListSetOperationsUnitTest extends AnyFlatSpec with Matchers {

  val listSet = ListSet(1, 2, 3, 4);

  "ListSet.empty" should "create an empty ListSet" in {
    var listSet1: ListSet[Integer] = ListSet.empty
    var listSet2 = ListSet();

    assert(listSet1.size == 0)
    assert(listSet2.size == 0)
  }

  "+ operator" should "add  an element to the ListSet" in {
    var newListSet = listSet + 5;

    assert(newListSet.size == 5)
    assert(newListSet.last == 5)
    assert(newListSet.head == 1)
  }

  "- operator" should "remove  an element from the ListSet" in {
    var newListSet = listSet - 4;

    assert(newListSet.size == 3)
    assert(newListSet.last == 3)
    assert(newListSet.head == 1)
  }

  "- operator" should "change nothing if an element is not present in the ListSet" in {
    var newListSet = listSet - 5;

    assert(newListSet.size == 4)
    assert(newListSet.last == 4)
    assert(newListSet.head == 1)
  }

  "++ operator" should "combine two ListSets" in {
    val listSet1 = ListSet(5, 6, 7, 8);

    val newListSet = listSet ++ listSet1;

    assert(newListSet.size == 8)
    assert(newListSet.last == 8)
    assert(newListSet.head == 1)
  }

  "-- operator" should "remove all the elements of one ListSet from another" in {
    val listSet1 = ListSet(3, 4, 5, 6);

    val newListSet = listSet -- listSet1;

    assert(newListSet.size == 2)
    assert(newListSet.last == 2)
    assert(newListSet.head == 1)
  }
}
