package com.baedung.scala.commoncollections

import org.scalatest.{FlatSpec, Matchers}

class GetListItemsUnitTest extends FlatSpec with Matchers {
  val numbers = List.range(1, 10);

  "List.apply" should "return the item at specified index" in {
    numbers.apply(5) should be(6)
  }

  it should "produce IndexOutOfBoundsException when index is beyond the range" in {
    assertThrows[IndexOutOfBoundsException] {
      numbers.apply(10)
    }
  }

  "List.lift" should "return an Option type Some containing the item at specified index" in {
    numbers.lift(1) should be(Some(2))
  }

  it should "return an Option type None when the index is beyond the range" in {
    numbers.lift(10) should be(None)
  }

  it should "produce NoSuchElementException when get is invoked on Option type None" in {
    val none = numbers.lift(10)
    assert(none.isEmpty)
    assertThrows[NoSuchElementException] {
      none.get
    }
  }

  "List(index)" should "return the item at specified index" in {
    numbers(8) should be(9)
  }

  it should "produce IndexOutOfBoundsException when index is beyond the range" in {
    assertThrows[IndexOutOfBoundsException] {
      numbers(10)
    }
  }

  "List.head" should "return first item" in {
    numbers.head should be(1)
  }

  it should "produce NoSuchElementException when invoked on empty list" in {
    assertThrows[NoSuchElementException] {
      List.empty.head
    }
  }

  "List.last" should "return last item" in {
    numbers.last should be(9)
  }

  it should "produce NoSuchElementException when invoked on empty list" in {
    assertThrows[NoSuchElementException] {
      List.empty.last
    }
  }
}
