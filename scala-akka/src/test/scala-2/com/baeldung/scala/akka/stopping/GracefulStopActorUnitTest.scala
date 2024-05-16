package com.baeldung.scala.akka.stopping

import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.pattern.{AskTimeoutException, gracefulStop}
import akka.testkit
import akka.testkit.{ImplicitSender, TestKit}
import com.baeldung.scala.akka.stopping.MessageProcessorActor._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

import scala.concurrent.Await
import scala.concurrent.duration._

class GracefulStopActorUnitTest
  extends TestKit(ActorSystem("test_system"))
  with AnyWordSpecLike
  with Matchers
  with ImplicitSender {

  "Graceful shutdown" should {

    "stop the actor successfully" in {
      val actor =
        system.actorOf(Props(classOf[MessageProcessorActor]), "GracefulActor")
      val probe = testkit.TestProbe()
      probe.watch(actor)

      actor ! Greet("Sheldon")
      actor ! Greet("Penny")
      actor ! Greet("Howard")
      actor ! Greet("Raj")

      try {
        val stopped = gracefulStop(actor, 5.seconds, PoisonPill)
        val status = Await.result(stopped, 6.seconds)
        expectMsg(Reply("Hey, Sheldon"))
        assert(status)
      } catch {
        case e: AskTimeoutException => fail()
      }

    }

  }

}
