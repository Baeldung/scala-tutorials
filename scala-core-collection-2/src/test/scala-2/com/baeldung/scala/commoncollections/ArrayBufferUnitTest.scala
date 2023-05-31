package com.baeldung.scala.commoncollections

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer

class ArrayBufferUnitTest extends AnyFlatSpec with Matchers {

  "An ArrayBuffer" should "add elements correctly" in {
    val buffer = ArrayBuffer.empty[Int]
    buffer += 1
    buffer += 2
    buffer += 3
    buffer should have size 3
    buffer(0) shouldEqual 1
    buffer(1) shouldEqual 2
    buffer(2) shouldEqual 3
  }

  it should "update elements correctly" in {
    val buffer = ArrayBuffer(1, 2, 3)
    buffer(1) = 5
    buffer shouldEqual ArrayBuffer(1, 5, 3)
  }

  it should "remove elements correctly" in {
    val buffer = ArrayBuffer(1, 2, 3)
    buffer -= 2
    buffer shouldEqual ArrayBuffer(1, 3)
  }

  it should "be empty when created with empty" in {
    val buffer = ArrayBuffer.empty[Int]
    buffer should be(empty)
  }

  it should "throw an IndexOutOfBoundsException for an invalid index" in {
    val buffer = ArrayBuffer(1, 2, 3)
    assertThrows[IndexOutOfBoundsException] {
      buffer(5)
    }
  }
}
