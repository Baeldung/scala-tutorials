package com.baeldung.scala.akka_2.tell_fwd

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import scala.concurrent.duration._

case object TellMsg
case object ForwardMsg
case object TellWithExplicitSenderMsg

class ActorA(actorBRef: ActorRef) extends Actor {
  override def receive: Receive = {
    case TellMsg => actorBRef ! "Tell"
    case ForwardMsg => actorBRef forward "Forward"
    case TellWithExplicitSenderMsg => actorBRef tell ("TellWithExplicitSender", self)
  }
}

class ActorB(replyTo: ActorRef) extends Actor {
  override def receive: Receive = {
    case msg: Any =>
      val senderName = sender.path.name
      println(s"Sender of this message($msg) is : $senderName")
      replyTo ! s"[$msg] Sender: ${sender.path.name}"
  }
}

class TellForwardDiffUnitTest
  extends TestKit(ActorSystem("tell_fwd_system"))
  with ImplicitSender
  with Matchers
  with AnyWordSpecLike {

  val actorB = system.actorOf(Props(new ActorB(self)), "ActorB")
  val actorA = system.actorOf(Props(new ActorA(actorB)), "ActorA")

  actorA ! TellMsg
  expectMsg(1.second, "[Tell] Sender: ActorA")

  actorA ! ForwardMsg
  expectMsgPF(1.second) {
    case msg:String => msg contains "[Forward] Sender: testActor"
  }

  actorA ! TellWithExplicitSenderMsg
  expectMsg(1.second, "[TellWithExplicitSender] Sender: ActorA")

}
