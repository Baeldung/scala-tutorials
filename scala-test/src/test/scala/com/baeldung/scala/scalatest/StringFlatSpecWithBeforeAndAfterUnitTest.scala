package com.baeldung.scala.scalatest

import org.scalatest.flatspec.AnyFlatSpec
import collection.mutable.ListBuffer
import org.scalatest.BeforeAndAfter

class StringFlatSpecWithBeforeAndAfterUnitTest extends AnyFlatSpec with BeforeAndAfter {

  val builder = new StringBuilder

  before {
    builder.append("Baeldung ")
  }

  after {
    builder.clear()
  }

  "Baeldung" should "be interesting" in {
    assert(builder.toString === "Baeldung ")

    builder.append("is very interesting!")
    assert(builder.toString === "Baeldung is very interesting!")
  }

  it should "have great tutorials" in {
    assert(builder.toString === "Baeldung ")

    builder.append("has great tutorials!")
    assert(builder.toString === "Baeldung has great tutorials!")
  }
}
