package com.baeldung.scala.arrays

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CopyAnArrayToAnotherUnitTest extends AnyFlatSpec with Matchers {
  val array1 = Array(1, 2, 3, 4)

  "splat operator" should "copy an entire array to another" in {
    var array2 = Array(array1: _*)
    array1(1) should be(array2(1))
    array1 should not be (array2)

    array2(1) = 5
    array1(1) should not be (array2(1))
  }

  "copyToArray" should "copy an entire array to another" in {
    var array2 = new Array[Int](4)
    array1.copyToArray(array2)

    array1(1) should be(array2(1))
    array1(3) should be(array2(3))
    array1 should not be (array2)

    array2(1) = 5
    array1(1) should not be (array2(1))
  }

  "overloaded copyToArray" should "copy specified array elements to another array starting at given index" in {
    var array2 = new Array[Int](4)
    array1.copyToArray(array2, 1, 3)

    array1(0) should not be (array2(0))
    array2(0) should be(0)
    array2(1) should be(1)
    array2(2) should be(2)
    array2(3) should be(3)
    array1 should not be (array2)
  }

  "map" should "copy an entire array to another" in {
    var array2 = array1.map(identity)

    array1(1) should be(array2(1))
    array1(3) should be(array2(3))
    array1 should not be (array2)

    array2(1) = 5
    array1(1) should not be (array2(1))
  }

  "clone" should "clone an array to another" in {
    var array2 = array1.clone()

    array1(1) should be(array2(1))
    array1(3) should be(array2(3))
    array1 should not be (array2)

    array2(1) = 5
    array1(1) should not be (array2(1))
  }

  "assignment operator" should "simply assign an array to another" in {
    var array2 = array1
    array1(1) should be(array2(1))
    array1 should be(array2)

    array2(1) = 5
    array1(1) should be(array2(1))
  }
}
