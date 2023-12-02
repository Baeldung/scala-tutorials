package com.baeldung.scala.fixedsizelist

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CircularBufferFixedSizeListSpec extends AnyFlatSpec with Matchers {
  "A CircularBufferFixedSizeListSpec" should "limit its size to the specified maximum" in {
    val list = FixedSizeList[Int](3)
    list.add(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.size should be(3)
    list.get(0) should be(Some(2))
  }

  it should "be empty when created" in {
    val list = FixedSizeList[Int](3)
    list.isEmpty should be(true)
  }

  it should "be mappable" in {
    val list = FixedSizeList[Int](3)
    list.add(1)
    list.add(2)
    list.add(3)
    list.map(_ * 2) should contain theSameElementsAs Seq(2, 4, 6)
  }

  it should "be iterable" in {
    val expected = List(1, 2, 3)
    val list = FixedSizeList[Int](3)
    list.add(1)
    list.add(2)
    list.add(3)

    for ((e, i) <- list.zipWithIndex) {
      e should be(expected(i))
    }
  }
}
