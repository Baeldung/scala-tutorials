package com.baeldung.scala.junit4

import org.junit.Test
import org.junit.Assert._

class IntJunitUnitTest {

  @Test
  def testOneIsPositive = {
    assertTrue(1 > 0)
  }

  @Test
  def testMinusOneIsNegative = {
    assertTrue(-1 < 0)
  }
}
