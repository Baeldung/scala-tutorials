package com.baeldung.scala.akka.stopping

import akka.testkit.TestKit
import akka.actor.ActorSystem
import org.scalatest.WordSpec
import org.scalatest.Matchers
import akka.testkit.ImplicitSender
import org.scalatest.WordSpecLike
import org.scalatest.Ignore
import akka.actor.Props
import MessageProcessorActor._
import akka.actor.PoisonPill
import akka.testkit.TestActorRef
import akka.testkit
import akka.actor.DeadLetter
import scala.concurrent.duration._

@Ignore // fixing in JAVA-9841
class StoppingActorTest
  extends TestKit(ActorSystem("test_system"))
  with WordSpecLike
  with Matchers
  with ImplicitSender {

  "Stop method" should {

    "stop the actor using stop() method" in {
      import scala.concurrent.ExecutionContext.Implicits.global
      val actor = system.actorOf(Props(classOf[MessageProcessorActor]), "StopActor")
      val probe = testkit.TestProbe()
      probe.watch(actor)

      actor ! Greet("Sheldon")
      actor ! Greet("Penny")
      actor ! Greet("Howard")
      actor ! Greet("Raj")

      system.stop(actor)

      expectMsg(Reply("Hey, Sheldon"))
      expectNoMessage()
      probe.expectTerminated(actor)
    }

  }

}
