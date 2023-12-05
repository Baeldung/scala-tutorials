package com.baeldung.scala.splitlist

import scala.util.Try

object ListSplitter {

  @throws[IllegalArgumentException]("if the slice size is less than one")
  private def recursiveSplit[A](list: List[A], n: Int): List[List[A]] =
    if (n < 1)
      throw new IllegalArgumentException("The minimum slice size is one")
    else {
      if (list.isEmpty) List.empty[List[A]]
      else list.take(n) :: recursiveSplit(list.drop(n), n)
    }

  @throws[IllegalArgumentException]("if the slice size is less than one")
  private def splitUsingSliding[A](list: List[A], n: Int): List[List[A]] =
    if (n < 1)
      throw new IllegalArgumentException("The minimum slice size is one")
    else list.sliding(n, n).toList

  /** Dispatch method, to pick on of several implementations of the splitter.
    *
    * @param list
    *   original list to be split in sub-lists
    * @param n
    *   desired size of the resulting sub-lists
    * @tparam A
    *   type of the elements of the original list and its sub-lists
    * @return
    *   a list of the sub-lists of `list`
    */
  def split[A](list: List[A], n: Int): Try[List[List[A]]] =
    Try(splitUsingSliding(list, n))
}
