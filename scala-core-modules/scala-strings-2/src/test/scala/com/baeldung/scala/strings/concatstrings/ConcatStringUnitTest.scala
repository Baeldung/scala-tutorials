package com.baeldung.scala.strings.concatstrings

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ConcatStringUnitTest extends AnyFlatSpec with Matchers {

  it should "concat strings using + operator" in {
    val str1 = "Hello"
    val str2 = "Baeldung"
    val combined = str1 + str2
    combined shouldBe "HelloBaeldung"
  }

  it should "concat string with another type using + operator" in {
    val str1 = "Hello"
    val combined = str1 + 10
    combined shouldBe "Hello10"
  }

  it should "concat strings using ++ operator" in {
    val str1 = "Hello"
    val str2 = "Baeldung"
    val combined = str1 ++ str2
    combined shouldBe "HelloBaeldung"
  }

  it should "concat strings using concat" in {
    val str1 = "Hello"
    val str2 = "Baeldung"
    val combined = str1.concat(str2)
    combined shouldBe "HelloBaeldung"
  }

  it should "concat strings using string interpolation" in {
    val str1 = "Hello"
    val str2 = "Baeldung"
    val combined = s"$str1$str2"
    combined shouldBe "HelloBaeldung"
  }

  it should "concat strings using multi line string interpolation" in {
    val str1 = "Hello"
    val str2 = "Baeldung"
    val combined = s"""$str1$str2"""
    combined shouldBe "HelloBaeldung"
  }

  it should "concat strings using StringBuilder" in {
    val str1 = "Hello"
    val str2 = "Baeldung"
    val combined = new StringBuilder(str1).append(str2).toString()
    combined shouldBe "HelloBaeldung"
  }

  it should "concat strings using mkString" in {
    val strings = Seq("Hello", "Baeldung")
    val combined = strings.mkString
    combined shouldBe "HelloBaeldung"
    strings.mkString(",") shouldBe "Hello,Baeldung"
  }

  it should "concat strings using reduce" in {
    val strings = Seq("Hello", "Baeldung")
    val combined = strings.reduce(_ + _)
    combined shouldBe "HelloBaeldung"
    val combinedWithComma = strings.reduce((a, b) => (a + "," + b))
    combinedWithComma shouldBe "Hello,Baeldung"
  }

  it should "concat strings using foldLeft" in {
    val strings = Seq("Hello", "Baeldung")
    val combined = strings.foldLeft("")(_ + _)
    combined shouldBe "HelloBaeldung"
    List.empty[String].foldLeft("")(_ + _) shouldBe ""
  }

}
