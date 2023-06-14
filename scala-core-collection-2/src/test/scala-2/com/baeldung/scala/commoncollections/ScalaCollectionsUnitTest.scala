package com.baeldung.scala.commoncollections

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScalaCollectionsUnitTest extends AnyFlatSpec with Matchers {

  "Scala list" should "concatenate using ::: operator" in {
    // given
    val list1 = List(1, 2)
    val list2 = List(3, 4)

    // when
    val res = list1 ::: list2

    // then
    res shouldBe List(1, 2, 3, 4)
  }

  it should "create a list will all identical elements using fill method" in {
    // when and then
    List.fill(3)(100) shouldBe List(100, 100, 100)
  }

  it should "reverse should create a reverse all elements of the list" in {
    // given
    val list = List(1, 2, 3, 4)

    // when and then
    list.reverse shouldBe List(4, 3, 2, 1)
  }

  "Scala set" should "return head of the set when .head method invoked" in {
    // given
    val set = Set(1, 2, 3, 4)

    // when and then
    set.head shouldBe 1
  }

  it should "return all element of set except head when .tail method invoked" in {
    // given
    val set = Set(1, 2, 3, 4)

    // when and then
    set.tail shouldBe Set(2, 3, 4)
  }

  "Maps" should "return all keys from the map when .keys method invoked" in {
    // given
    val map = Map(1 -> "a", 2 -> "b")

    // when and then
    map.keys shouldBe Set(1, 2)
  }

  it should "return all values from the map when .values invoked" in {
    // given
    val map = Map(1 -> "a", 2 -> "b")

    // when and then
    map.values.toList shouldBe List("a", "b")
  }

  it should "return None if the key is not found in map when .get method invoked" in {
    // given
    val map = Map(1 -> "a", 2 -> "b")

    // when
    val res = map.get(-1)

    // then
    res.isEmpty shouldBe true
  }

  it should "return value if the key is found in map using .get method" in {
    // given
    val map = Map(1 -> "a", 2 -> "b")

    // when
    val res = map.get(1)

    // then
    res shouldBe Some("a")
  }
}
