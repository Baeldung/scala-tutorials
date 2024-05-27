package com.baeldung.scala.arrays

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class InitializeAnArrayUnitTest extends AnyFlatSpec with Matchers {

  "new keyword" should "initialize an array of specified size and type" in {
    var array = new Array[Int](4)
    array(0) should be(0)
    array.length should be(4)

    var array1 = new Array[String](4)
    array1(0) should be(null)
    array1.length should be(4)
  }

  "passing values to the array constructor" should "initialize an array of specific size and type" in {
    var array = Array("This", "is", "a", "string", "array")

    array(0) should be("This")
    array.length should be(5)
  }

  "Array.fill" should "initialize an array of specified size and type" in {
    var array = Array.fill(4) { math.random() }

    array.length should be(4)
  }

  "splat operator" should "copy an entire list to an array" in {
    var list = List(1, 2, 3, 4)
    var array = Array[Int](list*)

    array(1) should be(list(1))
    array.length should be(4)
  }

  "List.toArray" should "return an array from a list" in {
    var list = List(1, 2, 3, 4)
    var array = list.toArray

    array(1) should be(list(1))
    array.length should be(4)
  }

  "Array.range" should "initialize an array of specified range" in {
    var array = Array.range(1, 10)

    array.length should be(9)
    array(8) should be(9)
  }

  it should "initialize an array of even numbers within specified range when step is passed" in {
    var array = Array.range(0, 10, 2)

    array.length should be(5)
    array(4) should be(8)
  }

  it should "initialize an array in descending order when a negative value of step is passed and the start value greater is than the end" in {
    var array = Array.range(10, 0, -2)

    array.length should be(5)
    array(4) should be(2)
  }

  it should "initialize an array of negative numbers when the start as greater than the end and the step value as negative" in {
    var array = Array.range(0, -10, -2)

    array.length should be(5)
    array(4) should be(-8)
  }
}
