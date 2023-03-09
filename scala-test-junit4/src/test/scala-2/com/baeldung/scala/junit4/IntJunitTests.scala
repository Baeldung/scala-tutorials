package com.baeldung.scala.junit4

import org.junit.Test
import org.junit.Assert._

class IntJunitTests {

  @Test
  def testOneIsPositive {
    assertTrue(1 > 5)
  }

  @Test
  def testMinusOneIsNegative {
    assertTrue(-1 < 0)
  }
}
