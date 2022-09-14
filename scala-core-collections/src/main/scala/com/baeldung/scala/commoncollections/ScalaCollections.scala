package com.baeldung.scala.commoncollections

import scala.collection.mutable

object ScalaCollections {

  // Scala List
  val numbersList: List[Int] = List(1, 2, 3, 4)
  val emptyList: List[Int] = List()

  val numbersListWithOperator: List[Int] = 1 :: 2 :: 3 :: 4 :: Nil
  val emptyListWithNil: List[Int] = Nil
  val x :: xs = numbersList

  // Scala Set
  val emptySet: Set[Int] = Set()
  val numbersSet: Set[Int] = Set(1, 2, 3, 4)

  //Scala Map
  val immutableMap: Map[Int, String] = Map(1 -> "a", 2 -> "b")
  val mutableMap: mutable.Map[Int, String] = collection.mutable.Map(1 -> "a", 2 -> "b")

}
