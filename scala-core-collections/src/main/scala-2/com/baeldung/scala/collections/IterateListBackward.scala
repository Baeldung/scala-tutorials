package com.baeldung.scala.collections

object IterateListBackward {

  def iterateBackwardUsingToAndBy(): Unit = {
    for (i <- 10 to 0 by -1) {
      println(i)
    }
  }

  def iterateBackwardUsingReverse(): Unit = {
    for (i <- (0 to 10).reverse) {
      println(i)
    }
  }

  def iterateBackwardUsingTo(): Unit = {
    for (i <- 10 to(0, -1)) {
      println(i)
    }
  }

  def iterateTwoListsBackwardUsingReverseAndZip(): Unit = {
    val zippedLists = List('a','b','c').reverse zip List(1,2).reverse
    for (i <- zippedLists) {
      println(i)
    }
  }

  def iterateTwoListsBackwardUsingReverseIteratorAndZip(): Unit = {
    val it = List('a', 'b', 'c').reverseIterator zip List(1, 2).reverseIterator
    val result = it.toList
    for (i <- result) {
      println(i)
    }
  }
}
