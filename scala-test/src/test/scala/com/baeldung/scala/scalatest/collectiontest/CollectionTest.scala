package com.baeldung.scala.scalatest.collectiontest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CollectionTest extends AnyFlatSpec with Matchers {

  it should "compare equality for a list of numbers in same order" in {
    val intList = List(1,2,3,4)
    intList shouldBe List(1,2,3,4)
    assert(intList ==)
  }

  it should "return false when comparing 2 lists of same elements in different order" in {
    val list = List("a","b","c")
    list shouldNot be(List("a", "c", "b"))
  }

}
