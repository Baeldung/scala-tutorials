package com.baeldung.scala.removeduplicates

object DuplicatesRemover {
  private def assessElement[T: Ordering](init: List[T], last: T): List[T] = {
    if (init.isEmpty) List(last)
    else {
      val recursiveList = assessElement(init.init, init.last)
      if (init.contains(last)) recursiveList
      else recursiveList :+ last
    }
  }

  def removeDuplicates[T: Ordering](list: List[T]): List[T] =
    assessElement(list.init, list.last)
}
