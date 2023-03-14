package com.baeldung.scala.junit4

import org.junit.Test
import org.junit.Assert._

class StringJunitTests {

  @Test
  def testEmptyStringLengthIsZero {
    assertEquals("".length, 0)
  }
}
