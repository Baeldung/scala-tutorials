package com.baeldung.scala.abstractclassestrait

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class IntQueueTest extends AnyWordSpec with Matchers {

  "Program.put" should {
    "filter numbers before putting them in the queue" in {
      val queue = new IntQueue with Doubling with Filtering {
        override val mustBeGreaterThan: Int = 0
      }

      queue.put(-5)
      assert(queue.get().isEmpty)
    }

    "double numbers before putting them in the queue" in {
      val queue = new IntQueue with Doubling with Filtering {
        override val mustBeGreaterThan: Int = 0
      }

      queue.put(10)
      assert(queue.get().contains(20))
    }
  }
}
