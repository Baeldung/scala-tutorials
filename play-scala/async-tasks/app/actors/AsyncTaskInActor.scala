package actors

import akka.actor.Actor

import java.time.LocalDateTime

class AsyncTaskInActor extends Actor {
  override def receive: Receive = { case msg: String =>
    Console.println(s"Message ${msg} received at ${LocalDateTime.now()}")
  }
}
