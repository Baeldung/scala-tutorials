package com.baeldung.scala.subarray

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GetASubarrayUnitTest extends AnyFlatSpec with Matchers {

  "operating slice function on an array" should "return a subarray of the sliced elements" in {
    val arr = Array(1, 2, 3, 4, 5)
    val subArr = arr.slice(1, 4)

    subArr.length should be(3)
    subArr(0) should be(2)
  }

  "operating drop and take functions on an array" should "return a subarray of the taken elements" in {
    val arr = Array(1, 2, 3, 4, 5)
    val afterDropArr = arr.drop(1)

    afterDropArr.length should be(4)
    afterDropArr(0) should be(2)

    val subArr = afterDropArr.take(3)
    subArr.length should be(3)
    subArr(0) should be(2)
  }

}
