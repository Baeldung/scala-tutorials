package com.baeldung.scala.sort

import com.baeldung.scala.sort.IsSortedCollection.Direction.ASC

import scala.annotation.tailrec

object IsSortedCollection {

  enum Direction:
    case ASC, DESC

  def isSortedBySorting[A](list: List[A], direction: Direction)(using
                                                                ord: Ordering[A]
  ): Boolean = {
    direction match {
      case Direction.ASC  => list.sorted == list
      case Direction.DESC => list.sorted.reverse == list
    }
  }

  def isSortedBySliding[A](list: List[A], direction: Direction)(using
                                                                ord: Ordering[A]
  ): Boolean = {
    val comparator = if (direction == ASC) ord.lteq else ord.gteq
    list match {
      case Nil | _ :: Nil => true
      case _ => list.sliding(2).forall { case List(a, b) => comparator(a, b) }
    }
  }

  def isSortedByZip[A](list: List[A], direction: Direction)(using
                                                            ord: Ordering[A]
  ): Boolean = {
    val comparator = if (direction == ASC) ord.lteq else ord.gteq
    if (list.size < 2)
      true
    else
      list.zip(list.tail).forall { case (a, b) => comparator(a, b) }
  }

  def isSortedByLazyZip[A](list: List[A], direction: Direction)(using
                                                                ord: Ordering[A]
  ): Boolean = {
    val comparator = if (direction == ASC) ord.lteq else ord.gteq
    if (list.size < 2)
      true
    else
      list.lazyZip(list.tail).forall { case (a, b) => comparator(a, b) }
  }

  @tailrec
  def isSortedRecursive[A](list: List[A], direction: Direction)(using
                                                                ord: Ordering[A]
  ): Boolean = {
    val comparator = if (direction == ASC) ord.lteq else ord.gteq
    list match {
      case Nil | _ :: Nil => true
      case a :: b :: tail => comparator(a, b) && isSortedRecursive(b :: tail, direction)
    }
  }
}
