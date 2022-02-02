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
import akka.pattern.gracefulStop
import scala.concurrent.Await
import akka.pattern.AskTimeoutException

class GracefulStopActorTest
  extends TestKit(ActorSystem("test_system"))
  with WordSpecLike
  with Matchers
  with ImplicitSender {

  "Graceful shutdown" should {

    "stop the actor successfully" in {
      import scala.concurrent.ExecutionContext.Implicits.global
      val actor = system.actorOf(Props(classOf[MessageProcessorActor]), "GracefulActor")
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
