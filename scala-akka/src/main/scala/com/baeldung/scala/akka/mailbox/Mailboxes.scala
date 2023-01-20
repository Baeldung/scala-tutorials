package com.baeldung.scala.akka.mailbox

import akka.actor.DeadLetter
import akka.actor.typed.eventstream.EventStream
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, MailboxSelector}
import akka.actor.typed.scaladsl.Behaviors

object Mailboxes {

  sealed trait UserEvent {
    val usedId: String
  }
  case class MouseClick(override val usedId: String, x: Int, y: Int)
    extends UserEvent
  case class TextSniffing(override val usedId: String, text: String)
    extends UserEvent
  case class MouseMove(override val usedId: String, x: Int, y: Int)
    extends UserEvent

  val eventCollector: Behavior[UserEvent] = Behaviors.receive { (ctx, msg) =>
    msg match {
      case MouseClick(user, x, y) =>
        ctx.log.info(s"The user $user just clicked point ($x, $y)")
      case MouseMove(user, x, y) =>
        ctx.log.info(s"The user $user just move the mouse to point ($x, $y)")
      case TextSniffing(user, text) =>
        ctx.log.info(s"The user $user just entered the text '$text'")
    }
    Behaviors.same
  }

  case class Start(id: String)
  val siteActor: Behavior[Mailboxes.Start] = Behaviors.receive { (ctx, msg) =>
    msg match {
      case Start(id) =>
        ctx.spawn(eventCollector, id, MailboxSelector.bounded(1000))
        val props =
          MailboxSelector.fromConfig("mailboxes.event-collector-mailbox")
        ctx.spawn(eventCollector, s"{$id}_1", props)
        Behaviors.same
    }
  }

  val deadLettersListener: Behavior[DeadLetter] = Behaviors.receive {
    (ctx, msg) =>
      msg match {
        case DeadLetter(message, sender, recipient) =>
          ctx.log.debug(
            s"Dead letter received: ($message, $sender, $recipient)"
          )
          Behaviors.same
      }
  }

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Start] = ActorSystem(siteActor, "main")
    val deadLettersActor: ActorRef[DeadLetter] =
      system.systemActorOf(deadLettersListener, "deadLettersListener")
    system.eventStream.tell(EventStream.Subscribe[DeadLetter](deadLettersActor))
    system.deadLetters[DeadLetter]
  }
}
