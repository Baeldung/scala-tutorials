package com.baeldung.scala

import org.junit.Test

class TraitVsAbstractClassTest {

  @Test
  def whenDifferentObjectsFromDifferentParents_thenBehaveTheSame() = {

    // Set up a an objects with abstract parent and Trait
    val eventFromAbstract = new NewPostArrivedEventA
    val eventFromTrait = new NewPostArrivedEventT

    // We expected the same behavior
    assert(eventFromAbstract.toJson == eventFromTrait.toJson)
  }
}