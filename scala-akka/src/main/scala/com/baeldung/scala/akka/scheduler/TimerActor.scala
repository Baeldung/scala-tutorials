package com.baeldung.scala.akka.scheduler

import akka.actor.{Actor, ActorRef, Timers}
import com.baeldung.scala.akka.scheduler.TimerActor._
import scala.concurrent.duration._

object TimerActor {
  case object PeriodicTimerKey
  case object PeriodicTick
}

class TimerActor(replyTo: ActorRef) extends Actor with Timers {

  override def preStart(): Unit = {
    timers.startPeriodicTimer(PeriodicTimerKey, PeriodicTick, 200.millis)
    super.preStart()
  }

  override def receive: Receive = {
    case PeriodicTick => replyTo ! "Periodic-Tick"
  }

}
