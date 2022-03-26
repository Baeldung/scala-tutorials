package com.baeldung.scala.removeduplicates

object DuplicatesRemover {
  def removeDuplicatesNaively[T](list: List[Ordering[T]]): List[Ordering[T]] =
    list.filterNot(_ => list.count(list.contains(_)) > 1)
}
