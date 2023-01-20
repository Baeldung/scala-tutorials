package com.baeldung.scala.akka.tell

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object LoggingApplication {
  object LogKeeperActor {
    sealed trait Log
    final case class Trace(msg: String) extends Log
    final case class Info(msg: String) extends Log
    final case class Error(msg: String) extends Log

    def apply(): Behavior[Log] = {
      Behaviors.receive { (context, message) =>
        message match {
          case Trace(msg) => context.log.trace(msg)
          case Info(msg)  => context.log.info(msg)
          case Error(msg) => context.log.error(msg)
        }
        Behaviors.same
      }
    }
  }

  object MicroserviceActor {
    final case class DoSomeStuff[T](stuff: T)
    def apply(
      logger: ActorRef[LogKeeperActor.Log]
    ): Behavior[DoSomeStuff[_]] = {
      Behaviors.receiveMessage { case DoSomeStuff(stuff) =>
        logger ! LogKeeperActor.Info(stuff.toString)
        Behaviors.same
      }
    }
  }
}
