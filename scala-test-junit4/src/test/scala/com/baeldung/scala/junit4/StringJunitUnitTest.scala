package com.baeldung.scala.junit4

import org.junit.Test
import org.junit.Assert._

class StringJunitUnitTest {

  @Test
  def testEmptyStringLengthIsZero = {
    assertEquals("".length, 0)
  }
}
