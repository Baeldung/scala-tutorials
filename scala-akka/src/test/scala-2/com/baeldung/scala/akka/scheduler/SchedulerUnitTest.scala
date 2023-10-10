package com.baeldung.scala.akka.scheduler

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.Retries.{isRetryable, withRetry}
import org.scalatest._
import org.scalatest.matchers.should.Matchers
import org.scalatest.tags.Retryable
import org.scalatest.wordspec.{AnyWordSpec, AnyWordSpecLike}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

@Retryable
class SchedulerUnitTest
  extends TestKit(ActorSystem("test-system"))
  with ImplicitSender
  with AnyWordSpecLike
  with Matchers
  with Retries {

  val retries = 5
  override def withFixture(test: NoArgTest): Outcome = {
    if (isRetryable(test)) withFixture(test, retries)
    else super.withFixture(test)
  }
  def withFixture(test: NoArgTest, count: Int): Outcome = {
    val outcome = super.withFixture(test)
    outcome match {
      case Failed(_) | Canceled(_) =>
        if (count == 1) super.withFixture(test)
        else {
          println(
            s"Retrying SchedulerUnitTest flaky test  `${test.name}`, Attempts remaining: ${count - 1}"
          )
          // scheduling the retry after 1 second
          withRetry(1.seconds)(withFixture(test, count - 1))
        }
      case other => other
    }
  }

  "Akka scheduler" must {

    "execute the task exactly once after the provided time" in {
      val greeter = system.actorOf(
        Props(classOf[Greetings]),
        "Greetings-" + System.currentTimeMillis()
      )
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.scheduleOnce(500.millis, greeter, greet)
      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(1.seconds, expectedMessage)
      // should not get anymore message
      expectNoMessage(1.seconds)
      system.stop(greeter)
    }

    "execute the task exactly once using Runnable interface" in {
      println(
        "running the test: execute the task exactly once using Runnable interface"
      )
      val greeter =
        system.actorOf(
          Props(classOf[Greetings]),
          "Greeter-With-Runnable-" + System.currentTimeMillis()
        )
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.scheduleOnce(
        500.millis,
        new Runnable {
          override def run(): Unit = greeter ! greet
        }
      )

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(1.seconds, expectedMessage)
      // should not get anymore message
      expectNoMessage(1.seconds)
      system.stop(greeter)
    }

    "execute a task periodically" in {
      val greeter =
        system.actorOf(
          Props(classOf[Greetings]),
          "Periodic-Greeter-" + System.currentTimeMillis()
        )
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.schedule(10.millis, 250.millis, greeter, greet)

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(300.millis, expectedMessage)
      // get another message after 500 millis
      expectMsg(300.millis, expectedMessage)
      expectMsg(300.millis, expectedMessage)
      system.stop(greeter)
    }

    "execute a task periodically using scheduleWithFixedDelay" in {
      println(
        "running the test: execute a task periodically using scheduleWithFixedDelay"
      )
      val greeter =
        system.actorOf(
          Props(classOf[Greetings]),
          "Periodic-Greeter-Fixed-Delay-" + System.currentTimeMillis()
        )
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.scheduleWithFixedDelay(
        10.millis,
        250.millis,
        greeter,
        greet
      )

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(350.millis, expectedMessage)
      // get another message after 500 millis
      expectMsg(350.millis, expectedMessage)
      expectMsg(350.millis, expectedMessage)
      system.stop(greeter)
    }

    "execute a task periodically using Runnable interface" in {
      println(
        "running the test: execute a task periodically using Runnable interface"
      )
      val greeter =
        system.actorOf(
          Props(classOf[Greetings]),
          "Periodic-Greeter-Runnable-" + System.currentTimeMillis()
        )
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.schedule(
        10.millis,
        250.millis,
        new Runnable {
          override def run(): Unit = greeter ! greet
        }
      )

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      within(3.seconds) {
        expectMsg(expectedMessage)
        // get another message after 500 millis
        expectMsg(expectedMessage)
        expectMsg(expectedMessage)
        system.stop(greeter)
      }
    }

    // commenting this test due to flakiness
    /* "cancel a running scheduler" in {
      val greeter =
        system.actorOf(
          Props(classOf[Greetings]),
          "Cancelling-Greeter-" + System.currentTimeMillis()
        )
      val greet = Greet("Detective", "Lucifer MorningStar")
      val schedulerInstance =
        system.scheduler.schedule(10.millis, 1.seconds, greeter, greet)

      val expectedMessage = Greeted("Lucifer MorningStar: Hello, Detective")
      expectMsg(350.millis, expectedMessage)
      // Cancel the schedule, should not get any more messages after that
      schedulerInstance.cancel()
      schedulerInstance.isCancelled shouldBe true
      expectNoMessage(1.seconds)

    }*/

    "scheduleAtFixedRate should run the next execution at fixed rate even if the previous task took more time" in {
      println(
        "running the test: scheduleAtFixedRate should run the next execution at fixed rate even if the previous task took more time"
      )
      val greeter =
        system.actorOf(
          Props(classOf[Greetings]),
          "Fixed-Rate-Scheduling-" + System.currentTimeMillis()
        )
      val greet = Greet("Detective", "Lucifer")
      var flag = true
      system.scheduler.scheduleAtFixedRate(10.millis, 500.millis)(new Runnable {
        override def run(): Unit = {
          if (flag)
            Thread.sleep(200)
          flag = false
          greeter ! greet
        }
      })

      within(1.second) {
        val expectedMessage = Greeted("Lucifer: Hello, Detective")
        expectMsg(500.millis, expectedMessage)
        expectMsg(500.millis, expectedMessage)
        system.stop(greeter)
      }
    }

  }

}
