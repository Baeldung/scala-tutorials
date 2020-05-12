package com.baeldung.scala.enumerations

import com.baeldung.scala.enumerations.Fingers._
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertEquals
import org.junit.Test

class FingersOperationUnitTest {

  @Test
  def givenAFinger_whenIsShortestCalled_thenCorrectValueReturned() = {
    val operation = new FingersOperation()

    assertTrue(operation.isShortest(Little))
    assertFalse(operation.isShortest(Index))
  }

  @Test
  def givenFingers_whenTwoLongestCalled_thenCorrectValuesReturned() = {
    val operation = new FingersOperation()

    assertEquals(List(Index, Middle), operation.twoLongest())
  }

  @Test
  def givenStringValueOfFinger_whenWithNameCalled_thenCorrectValueReturned() = {
    assertEquals(Middle, Fingers.withName("The Middle Finger"))
  }

  @Test
  def givenAFinger_whenIdAndtoStringCalled_thenCorrectValueReturned() = {
    assertEquals(6, Thumb.id)
    assertEquals("Shorty Finger", Little.toString())
  }

  @Test
  def givenFingers_whenValuesCalled_thenOrderedValuesReturned() = {
    assertEquals(List(Index, Middle, Ring, Little, Thumb), Fingers.values.toList)
  }
}
