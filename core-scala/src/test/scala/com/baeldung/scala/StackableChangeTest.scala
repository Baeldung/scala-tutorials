package com.baeldung.scala

import org.junit.Assert.assertFalse
import org.junit.Test

class StackableChangeTest {

  @Test
  def whenFirstTraitOrderDefined_thenOneResult() = {

    // Set up a an object with stackable Traits
    val sender = new SimpleEventSender with EventFirstSender with EventSecondSender with EventThirdSender

    // Let's provide some payload to envelope

    val enveloped = sender.envelope("data")

    val expected = "{ thirdSender: { secondSender: { firstSender: { payload: data } } } }"
    assert(enveloped == expected)
  }

  @Test
  def whenSecondTraitOrderDefined_thenOtherResult() = {

    // Set up a an object with stackable Traits with different order
    val sender = new SimpleEventSender with EventThirdSender with EventSecondSender with EventFirstSender

    // Let's provide some payload to envelope

    val enveloped = sender.envelope("data")

    assert(enveloped == "{ firstSender: { secondSender: { thirdSender: { payload: data } } } }")
  }
}