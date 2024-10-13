package com.baeldung.scala.listtotuple


import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.should.Matchers.shouldBe

class ConvertListToTupleUnitTest extends AnyFlatSpec with Matchers {
  "twoElementsToTuple" should "convert list with 2 elements" in {
    val testList = List("Hello", "world")
    ConvertListToTuple.twoElementsToTuple(testList) shouldBe ("Hello", "world")
  }

  "twoElementsToTuple" should "convert list with 3 elements to Tuple3" in {
    val testList = List("Hello", "world", "!")
    ConvertListToTuple.twoElementsToTuple(testList) shouldBe ("Hello", "world")
  }

  "twoElementsToTupleUsingMatch" should "convert list with 2 elements" in {
    val testList = List("Hello", "world")
    ConvertListToTuple.twoElementsToTupleUsingMatch(testList) shouldBe ("Hello", "world")
  }

  "twoElementsToTupleUsingMatch" should "convert list with 3 elements to tuple2 ignoring extra elements" in {
    val testList = List("Hello", "world", "!")
    ConvertListToTuple.twoElementsToTupleUsingMatch(testList) shouldBe ("Hello", "world")
  }

  "twoElementsToTupleUsingMatch" should "return empty Strings for 1 element" in {
    val testList = List("Hello")
    ConvertListToTuple.twoElementsToTupleUsingMatch(testList) shouldBe ("", "")
  }

  "twoElementsToTupleUsingMatch" should "return empty Strings for Nil" in {
    val testList = Nil
    ConvertListToTuple.twoElementsToTupleUsingMatch(testList) shouldBe ("", "")
  }

  "unknownSizeToTuple" should "return empty Strings for Nil" in {
    val testList = Nil
    ConvertListToTuple.unknownSizeToTuple(testList) shouldBe ("", "")
  }

  "unknownSizeToTuple" should "convert list of 2 elements to tuple2" in {
    val testList = List("Hello", "world")
    ConvertListToTuple.unknownSizeToTuple(testList) shouldBe ("Hello", "world")
  }

  "unknownSizeToTuple" should "convert list of 3 elements to tuple3" in {
    val testList = List("Hello", "world", "!")
    ConvertListToTuple.unknownSizeToTuple(testList) shouldBe ("Hello", "world", "!")
  }
}
