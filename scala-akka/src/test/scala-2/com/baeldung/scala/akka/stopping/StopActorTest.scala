package com.baeldung.scala.akka.stopping

import akka.actor.{ActorSystem, Props}
import akka.testkit
import akka.testkit.{ImplicitSender, TestKit}
import com.baeldung.scala.akka.stopping.MessageProcessorActor._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import scala.concurrent.duration._

class StoppingActorTest
  extends TestKit(ActorSystem("test_system"))
  with AnyWordSpecLike
  with Matchers
  with ImplicitSender {

  "Stop method" should {

    "stop the actor using stop() method" in {
      val actor =
        system.actorOf(Props(classOf[MessageProcessorActor]), "StopActor")
      val probe = testkit.TestProbe()
      probe.watch(actor)

      actor ! Greet("Sheldon")
      expectMsg(Reply("Hey, Sheldon"))
      system.stop(actor)
      probe.expectTerminated(actor, 500.millis)
      expectNoMessage()
    }

  }

}
