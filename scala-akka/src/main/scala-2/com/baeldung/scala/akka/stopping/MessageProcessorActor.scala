package com.baeldung.scala.akka.stopping

import akka.actor.Actor

object MessageProcessorActor {
  trait Message
  case class Greet(msg: String) extends Message
  case class Reply(msg: String) extends Message
}

class MessageProcessorActor extends Actor {

  override def postStop(): Unit =
    println(s"Stopping MessageProcessorActor actor: $self")

  override def receive: Receive = { case msg: MessageProcessorActor.Greet =>
    sender() ! MessageProcessorActor.Reply("Hey, " + msg.msg)
  }

}
