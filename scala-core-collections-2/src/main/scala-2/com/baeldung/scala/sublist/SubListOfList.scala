package com.baeldung.scala.sublist

object SubListOfList {

  def usingForAll(bigList: List[String], littleList: List[String]): Boolean = {
    littleList.forall(bigList.contains)
  }

  def usingFoldLeft(
    bigList: List[String],
    littleList: List[String]
  ): Boolean = {
    bigList
      .foldLeft(littleList)((lList, string) => {
        val isStringInList = lList.headOption.map(_ == string).getOrElse(false)
        if (isStringInList) lList.tail else lList
      })
      .isEmpty
  }

  def usingSliding(bigList: List[String], littleList: List[String]): Boolean = {
    bigList.sliding(littleList.size, 1).exists(_ == littleList)
  }

  def usingContainsSlice(
    bigList: List[String],
    littleList: List[String]
  ): Boolean = {
    bigList.containsSlice(littleList)
  }
}
