package com.baeldung.scala.singletons

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CounterTest extends AnyWordSpec with Matchers {

  "Counter" should {
    "be zero at the beginning" in {
      assert(Counter.get == 0)
    }

    "be 1 after one increment" in {
      Counter.increment()
      assert(Counter.get == 1)
    }

    "keep the same label after a call to increment" in {
      assert(Counter.label == "CounterLabel")
      Counter.increment()
      assert(Counter.label == "CounterLabel")
    }
  }
}
