package com.baeldung.scala.akka.stopping

import akka.testkit.TestKit
import akka.actor.ActorSystem
import org.scalatest.WordSpec
import org.scalatest.Matchers
import akka.testkit.ImplicitSender
import org.scalatest.WordSpecLike
import akka.actor.Props
import MessageProcessorActor._
import akka.actor.PoisonPill
import akka.testkit.TestActorRef
import akka.testkit
import akka.actor.DeadLetter
import scala.concurrent.duration._

class PoisonPillActorTest
  extends TestKit(ActorSystem("test_system"))
  with WordSpecLike
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
