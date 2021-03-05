package actors

import akka.actor.Actor
import org.joda.time.DateTime

class AsyncTaskInActor extends Actor {
  override def receive: Receive = {
    case msg: String =>
      Console.println(s"Message ${msg} received at ${DateTime.now()}")
  }
}
