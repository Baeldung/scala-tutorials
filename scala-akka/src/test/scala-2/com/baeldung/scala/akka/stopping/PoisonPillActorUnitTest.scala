package com.baeldung.scala.akka.stopping

import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.testkit
import akka.testkit.{ImplicitSender, TestKit}
import com.baeldung.scala.akka.stopping.MessageProcessorActor._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class PoisonPillActorUnitTest
  extends TestKit(ActorSystem("test_system"))
  with AnyWordSpecLike
  with Matchers
  with ImplicitSender {

  "Sending PoisonPill" should {
    "stop the actor when the PoisonPill message is received" in {
      val actor =
        system.actorOf(Props(classOf[MessageProcessorActor]), "PoisonPillActor")
      val probe = testkit.TestProbe()
      probe.watch(actor)

      actor ! Greet("Sheldon")
      actor ! Greet("Penny")
      actor ! PoisonPill
      actor ! Greet("Howard")
      actor ! Greet("Raj")

      expectMsg(Reply("Hey, Sheldon"))
      expectMsg(Reply("Hey, Penny"))
      expectNoMessage()
      probe.expectTerminated(actor)
    }

  }

}
