package com.baeldung.scala.singletons

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CounterTest extends AnyWordSpec with Matchers {

  "Counter" should {
    "be zero at the beginning" in {
      Counter.get shouldBe 0
    }

    "be 1 after one increment" in {
      Counter.increment()
      Counter.get shouldBe 1
    }
  }
}
