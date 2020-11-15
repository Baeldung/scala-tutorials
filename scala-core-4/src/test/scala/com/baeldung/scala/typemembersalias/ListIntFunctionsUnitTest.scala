package com.baeldung.scala.typemembersalias
import org.scalatest.{Matchers, FlatSpec}

class ListIntFunctionsUnitTest extends FlatSpec with Matchers {
  "Mean function" should "be able to get mean from list of int" in {
    val intList = List(3, 6, 2, 2)
    ListIntFunctions.mean(intList) shouldEqual 3.25
  }

  it should "be able to get mean from another type alias" in {
    type SomeInts = List[Int]
    val intList: SomeInts = List(3, 6, 2, 2)
    ListIntFunctions.mean(intList) shouldEqual 3.25
  }

  "IntItemsToString" should "apply IntToString function to a list of int" in {
    val intList = (1 to 3).toList
    def getChicken(item: Int): String = {
      s"$item chicken"
    }

    val stringList = ListIntFunctions.IntItemsToString(intList, getChicken)
    stringList shouldEqual List("1 chicken", "2 chicken", "3 chicken")
  }
}
