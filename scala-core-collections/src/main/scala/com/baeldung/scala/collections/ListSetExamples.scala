package com.baeldung.scala.collections

import scala.collection.immutable.ListSet

object ListSetExamples extends App{

  def createListSet[T](elems:T*): ListSet[T] ={
    ListSet(elems:_*)
  }

  def createEmptyListSet[T]: ListSet[T] ={
    ListSet.empty
  }

  def addElement[T](listSet:ListSet[T], elem:T): ListSet[T] ={
    listSet + elem
  }

  def removeElement[T](listSet:ListSet[T], elem:T): ListSet[T] ={
    listSet - elem
  }

  val emptySet = createEmptyListSet[String]
  println(emptySet)

  val fruitsSet = createListSet("apple", "orange", "apple")
  println(fruitsSet)

  val addedFruitsSet = addElement(fruitsSet, "grapes")
  println(addedFruitsSet)

  val removedFruitsSet = removeElement(addedFruitsSet, "orange")
  println(removedFruitsSet)

}
