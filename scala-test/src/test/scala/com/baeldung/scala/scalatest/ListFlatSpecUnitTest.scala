package com.baeldung.scala.scalatest

import org.scalatest.flatspec.AnyFlatSpec

class ListFlatSpecUnitTest extends AnyFlatSpec {

  "An empty List" should "have size 0" in {
    assert(List.empty.size == 0)
  }

  it should "throw an IndexOutOfBoundsException when trying to access any element" in {
    val emptyList = List()
    assertThrows[IndexOutOfBoundsException] {
      emptyList(1)
    }
  }
}
