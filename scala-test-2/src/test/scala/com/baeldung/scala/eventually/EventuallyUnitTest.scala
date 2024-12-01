package com.baeldung.scala.eventually

import org.scalatest.concurrent.Eventually
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.concurrent.PatienceConfiguration.{Interval, Timeout}
import org.scalatest.exceptions.TestFailedDueToTimeoutException
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}

class AsyncOperation(waitTime: Int) {
  private var database: Map[String, String] = Map.empty

  def writeToDatabase(key: String, value: String): Unit = {
    new Thread(() => {
      Thread.sleep(waitTime) // Simulate a delay in writing to the database
      database += (key -> value)
    }).start()
  }
  def readFromDatabase(key: String): Option[String] = database.get(key)
}
class EventuallyUnitTest extends AnyFlatSpec with Matchers with Eventually {
  it should "retry the save to database multiple times in eventually with default timeout" in {
    val op = AsyncOperation(100)
    op.writeToDatabase("key", "value")
    eventually {
      op.readFromDatabase("key") shouldBe Some("value")
    }
  }

  it should "throw exception if the result is not available within the expected timeout in eventually" in {
    val op = AsyncOperation(300)
    op.writeToDatabase("key", "value")
    assertThrows[TestFailedDueToTimeoutException] {
      eventually {
        op.readFromDatabase("key") shouldBe Some("value")
      }
    }
  }

  it should "retry with newly configured timeout value" in {
    given patienceConfig: PatienceConfig =
      PatienceConfig(
        timeout = scaled(Span(2, Seconds)),
        interval = scaled(Span(5, Millis))
      )
    val op = AsyncOperation(400)
    op.writeToDatabase("new-key", "value")
    eventually {
      op.readFromDatabase("new-key") shouldBe Some("value")
    }
  }

  it should "retry with explicit timeout value" in {
    val op = AsyncOperation(400)
    op.writeToDatabase("new-key", "value")
    eventually(
      timeout = Timeout(Span(2, Seconds)),
      interval = Interval(Span(50, Millis))
    ) {
      op.readFromDatabase("new-key") shouldBe Some("value")
    }
  }
}
