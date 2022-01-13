package com.baeldung.scala.scalatest

import org.scalatest._
import collection.mutable.ListBuffer

class StringFlatSpecWithBeforeAndAfterUnitTest extends FlatSpec with BeforeAndAfter {

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
