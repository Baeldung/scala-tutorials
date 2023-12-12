package com.baeldung.scala.scalatest

import org.scalatest.funsuite.AnyFunSuite

class ListFunSuite extends AnyFunSuite {

  test("An empty List should have size 0") {
    assert(List.empty.size == 0)
  }

  test("Accessing invalid index should throw IndexOutOfBoundsException") {
    val fruit = List("Banana", "Pineapple", "Apple")
    assert(fruit.head == "Banana")
    assertThrows[IndexOutOfBoundsException] {
      fruit(5)
    }
  }

}
