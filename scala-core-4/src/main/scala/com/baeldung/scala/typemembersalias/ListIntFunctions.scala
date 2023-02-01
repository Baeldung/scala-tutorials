package com.baeldung.scala.typemembersalias

object ListIntFunctions {
  type IntItems = List[Int]
  type IntToString = Int => String

  def mean(items: IntItems): Double = {
    items.sum.toDouble / items.length
  }

  def IntItemsToString(
    items: IntItems,
    intToString: IntToString
  ): List[String] = {
    items.map(intToString)
  }
}
