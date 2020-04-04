package com.baeldung.scala

import org.junit.Test

class QueueTest {
  @Test
  def givenPuttingThreeNumber_whenRemovingTwoItem_thenLastItemShouldBeSecondsNumber(): Unit = {

    val queue         = new IntQueue
    val SECOND_NUMBER = 5
    queue.put(10)
    queue.put(SECOND_NUMBER)
    queue.put(20)
    queue.remove()

    assert(queue.remove() == SECOND_NUMBER)
  }
}
