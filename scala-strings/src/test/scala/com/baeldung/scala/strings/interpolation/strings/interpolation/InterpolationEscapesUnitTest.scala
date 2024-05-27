package com.baeldung.scala.strings.interpolation.strings.interpolation

import org.scalatest.flatspec.AnyFlatSpec

class InterpolationEscapesUnitTest extends AnyFlatSpec {

  val DOUBLE_QUOTE: Char = '"'

  "Escape using metachar in s-interpolation" should "include double quote character" in {
    val actual: String = s"Hello, ${DOUBLE_QUOTE}world!${DOUBLE_QUOTE}"
    val expected = "Hello, " + DOUBLE_QUOTE + "world!" + DOUBLE_QUOTE
    assert(expected == actual)
  }

  "Escape using metachar in f-interpolation" should "include double quote character" in {
    val actual: String = f"Hello, ${DOUBLE_QUOTE}world!${DOUBLE_QUOTE}"
    val expected = "Hello, " + DOUBLE_QUOTE + "world!" + DOUBLE_QUOTE
    assert(expected == actual)
  }

}
