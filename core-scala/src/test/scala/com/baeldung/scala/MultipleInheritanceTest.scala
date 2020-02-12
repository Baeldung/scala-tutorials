package com.baeldung.scala

import org.junit.Test

class MultipleInheritanceTest {

  val expected: String = """{"type": "SimpleType"}"""

  @Test
  def whenMultipleTraitsInherited_thenFunctionalityExtended() = {

    // Set up a an objects with abstract parent and Trait
    val ev = new SimpleEvent() with Debug

    // We expected object have extended functionality
    ev.debug
  }
}