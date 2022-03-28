package com.baeldung.scala.removeduplicates

object DuplicatesRemover {

  def removeDuplicatesRecursively[T](list: List[T]): List[T] = {
    def assessRemoval(init: List[T], last: T): List[T] =
      if (init.isEmpty) List(last)
      else {
        val recursiveList = assessRemoval(init.init, init.last)
        if (init.contains(last)) recursiveList
        else recursiveList :+ last
      }

    if (list.isEmpty) list
    else assessRemoval(list.init, list.last)
  }

  def removeDuplicatesIteratively[T](list: List[T]): List[T] =
    list.foldLeft(List.empty[T]) { (partialResult, element) =>
      if (partialResult.contains(element)) partialResult
      else partialResult :+ element
    }
}
