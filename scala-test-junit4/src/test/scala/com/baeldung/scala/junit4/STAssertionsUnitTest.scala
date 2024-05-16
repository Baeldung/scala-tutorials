package com.baeldung.scala.junit4

import org.scalatestplus.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test

class STAssertionsUnitTest extends AssertionsForJUnit {
  private final val myInt = 1

  @Test
  def testAssertJUnitStyle(): Unit = {
    assertEquals(myInt, 1)
    assertTrue(myInt > 0)

    try {
      myInt / 0
      fail()
    } catch {
      case e: ArithmeticException => // Expected
    }
  }

  @Test
  def testAssertScalaTestStyle(): Unit = {
    assert(myInt == 1)
    assert(myInt.isValidInt)
    intercept[ArithmeticException] {
      myInt / 0
    }
  }
}
