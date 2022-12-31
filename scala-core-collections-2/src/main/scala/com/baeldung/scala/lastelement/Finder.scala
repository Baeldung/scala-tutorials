package com.baeldung.scala.lastelement

import scala.annotation.tailrec

object Finder {
  implicit class Wrapper[T](val list: List[T]) extends AnyVal {

    /** Extension method that gives List instances the possibility to find their
      * last element that satisfies a given predicate.
      *
      * @param predicate
      *   the condition for which the last satisfying element will be searched
      *   for
      * @return
      *   index (zero-based) of the last element that satisfies the condition,
      *   or a negative number if it's not found
      */
    def lastWhen(predicate: T => Boolean): Int =
      useReverse(list, predicate)

    @tailrec
    private def useRecursiveScan(list: List[T], predicate: T => Boolean): Int =
      if (list.isEmpty) -1
      else if (predicate(list.last)) list.size - 1
      else useRecursiveScan(list.take(list.size - 1), predicate)

    private def useReverse(list: List[T], predicate: T => Boolean): Int =
      list.size - list.reverse.takeWhile(predicate(_) == false).size - 1

    private def useNativeLibrary(list: List[T], predicate: T => Boolean): Int =
      list.lastIndexWhere(predicate)

  }

}
