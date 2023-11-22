package com.baeldung.scala.scalatest.stringtest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CaseInsensitiveStringComparisonUnitTest
  extends AnyFlatSpec
  with Matchers {
  "calling equals ignore case method on a string" should "return true if strings are the same when ignoring case" in {
    val str = "Hello World"
    val result = str.equalsIgnoreCase("hello world")

    result should be(true)
  }

  "calling method toLowerCase.contains on a string" should "return if strings are the same when ignoring case" in {
    val str = "Hello World"
    val result = str.toLowerCase.contains("hello".toLowerCase)

    result should be(true)
  }
}
