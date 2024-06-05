package com.baeldung.scala.enumerations.scala3

import org.junit.Test
import org.junit.Assert.assertTrue

class FingersUnitTest {

  @Test
  def givenAFinger_getTheHeight(): Unit = {
    val Thumb = Fingers.Thumb
    assertTrue(Thumb.height == 1d)
  }

  @Test
  def givenAFinger_getTheHeightInCm(): Unit = {
    val Thumb = Fingers.Thumb
    assertTrue(Thumb.heightInCms() == 2.54d)
  }

}
