package com.baeldung.scala.akka.scheduler

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{Matchers, WordSpecLike}

import scala.concurrent.duration._

class TimerUnitTest
    extends TestKit(ActorSystem("test-system"))
    with ImplicitSender
    with WordSpecLike
    with Matchers {

  "Timer Actor" must {
    "reply with a msg after timer ticks periodically" in {
      val timerActor = system.actorOf(Props(classOf[TimerActor], self))
      expectMsg(350.millis, "Periodic-Tick")
      expectMsg(350.millis, "Periodic-Tick")
      expectMsg(350.millis, "Periodic-Tick")
      system.stop(timerActor)
    }
  }

}
