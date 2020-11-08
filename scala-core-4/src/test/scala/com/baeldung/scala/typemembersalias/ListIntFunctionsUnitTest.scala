package com.baeldung.scala.typemembersalias
import org.scalatest.FunSuite

class ListIntFunctionsUnitTest extends FunSuite {
  test("Get mean from list of int") {
    val intList = List(3, 6, 2, 2)
    assert(ListIntFunctions.mean(intList) === 3.25)
  }

  test("Get mean from other type alias") {
    type SomeInts = List[Int]
    val intList: SomeInts = List(3, 6, 2, 2)
    assert(ListIntFunctions.mean(intList) === 3.25)
  }

  test("Apply IntToString to a list of int") {
    val intList = (1 to 3).toList
    def getChicken(item: Int): String = {
      s"$item chicken"
    }

    val stringList = ListIntFunctions.IntItemsToString(intList, getChicken)
    assert(stringList === List("1 chicken", "2 chicken", "3 chicken"))
  }
}
