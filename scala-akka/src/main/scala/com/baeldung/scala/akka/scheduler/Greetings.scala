package com.baeldung.scala.akka.scheduler

import akka.actor.Actor

final case class Greet(to: String, by: String)
final case class Greeted(msg: String)

class Greetings extends Actor {
  override def receive: Receive = {
    case greet: Greet =>
      sender() ! Greeted(s"${greet.by}: Hello, ${greet.to}")
  }
}
