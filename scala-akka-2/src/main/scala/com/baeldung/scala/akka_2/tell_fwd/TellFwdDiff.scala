package com.baeldung.scala.akka_2.tell_fwd

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.baeldung.scala.akka_2.tell_fwd.TestActors._
object TellFwdDiff extends App {

  val system = ActorSystem("MySystem")
  private val actorC = system.actorOf(Props(new ActorC()), "ActorC")
  private val actorB = system.actorOf(Props(new ActorB(actorC)), "ActorB")
  private val actorA = system.actorOf(Props(new ActorA(actorB)), "ActorA")
  actorA ! TellMsg
  actorA ! ForwardMsg
  Thread.sleep(100)
}

object TestActors {
  case object TellMsg
  case object ForwardMsg
  case object TellWithExplicitSenderMsg

  class ActorA(actorB: ActorRef) extends Actor {
    override def receive: Receive = {
      case msg:Any => actorB ! msg
    }
  }

  class ActorB(actorC: ActorRef) extends Actor {
    override def receive: Receive = {
      case TellMsg => actorC ! "Tell"
      case ForwardMsg => actorC tell ("Forward", sender)
    }
  }

  class ActorC extends Actor {
    override def receive: Receive = {
      case msg: Any => println(s"Received message: $msg, sender: ${sender.path.name}")
    }
  }
}
