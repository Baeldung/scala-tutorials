package com.baeldung.scala.akka.scheduler

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
<<<<<<< HEAD:scala-akka/src/test/scala/com/baeldung/scala/akka/scheduler/TimerUnitTest.scala
import org.scalatest.Ignore
=======
import org.scalatest.{Ignore, Retries}
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-akka/src/test/scala-2/com/baeldung/scala/akka/scheduler/TimerUnitTest.scala
import org.scalatest.matchers.should.Matchers
import org.scalatest.tags.Retryable
import org.scalatest.wordspec.AnyWordSpecLike

import scala.concurrent.duration._

@Retryable
class TimerUnitTest
  extends TestKit(ActorSystem("test-system"))
  with ImplicitSender
  with AnyWordSpecLike
<<<<<<< HEAD:scala-akka/src/test/scala/com/baeldung/scala/akka/scheduler/TimerUnitTest.scala
  with Matchers {
=======
  with Matchers
  with Retries {

  override def withFixture(test: NoArgTest) = {
    if (isRetryable(test))
      withRetry {
        super.withFixture(test)
      }
    else
      super.withFixture(test)
  }
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-akka/src/test/scala-2/com/baeldung/scala/akka/scheduler/TimerUnitTest.scala

  "Timer Actor" must {
    "reply with a msg after timer ticks periodically" in {
      val timerActor = system.actorOf(Props(classOf[TimerActor], self))
      val msgs = receiveN(3, 700.millis)
      system.stop(timerActor)
      assert(
        msgs.size >= 3
      ) // just in case the 4th msg is produced by the timer before stopping
      assert(msgs.toSet == Set("Periodic-Tick"))
    }
  }

}
