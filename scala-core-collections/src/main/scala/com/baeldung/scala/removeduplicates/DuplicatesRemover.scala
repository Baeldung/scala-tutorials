package com.baeldung.scala.removeduplicates

object DuplicatesRemover {

  def removeDuplicatesRecursively[T](list: List[T]): List[T] = {
    def assessRemoval(init: List[T], last: T): List[T] = {
      if (init.isEmpty) List(last)
      else {
        val recursiveList = assessRemoval(init.init, init.last)
        if (init.contains(last)) recursiveList
        else recursiveList :+ last
      }
    }

    if (list.isEmpty) list
    else assessElement(list.init, list.last)
}
