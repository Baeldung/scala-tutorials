package com.baeldung.scala.akka_2.persistence

import akka.actor.typed.{ActorRef, Behavior}
import akka.pattern.StatusReply
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{
  Effect,
  EventSourcedBehavior,
  ReplyEffect,
  RetentionCriteria
}

object BankAccount {
  sealed trait Command
  final case class WithdrawCommand(
    amount: Int,
    replyTo: ActorRef[StatusReply[Response]]
  ) extends Command
  final case class DepositCommand(
    amount: Int,
    replyTo: ActorRef[StatusReply[Response]]
  ) extends Command
  final case class BalanceCheckCommand(replyTo: ActorRef[StatusReply[Response]])
    extends Command

  sealed trait Event
  final case class WithdrawalEvent(amount: Int) extends Event
  final case class DepositEvent(amount: Int) extends Event

  final case class State(amount: Int)

  sealed trait Response
  final case class ActionResponse(message: String) extends Response
  final case class BalanceCheckResponse(amount: Int) extends Response

  def apply(id: Long): Behavior[Command] =
    EventSourcedBehavior
      .withEnforcedReplies[Command, Event, State](
        persistenceId = PersistenceId(id.toString, "bank-account"),
        emptyState = State(0),
        commandHandler = commandHandler,
        eventHandler = eventHandler
      )
      .withRetention(
        RetentionCriteria.snapshotEvery(numberOfEvents = 5, keepNSnapshots = 3)
      )

  val commandHandler: (State, Command) => ReplyEffect[Event, State] = {
    (state, command) =>
      command match {
        case WithdrawCommand(amount, replyTo) =>
          if (state.amount >= amount) {
            Effect
              .persist(WithdrawalEvent(amount))
              .thenReply(replyTo)(_ =>
                StatusReply.success(
                  ActionResponse(s"Amount $amount was withdrawn")
                )
              )
          } else {
            Effect.reply(replyTo)(
              StatusReply.error(
                s"Account has insufficient funds. Available balance ${state.amount}"
              )
            )
          }
        case DepositCommand(amount, replyTo) =>
          Effect
            .persist(DepositEvent(amount))
            .thenReply(replyTo)(_ =>
              StatusReply.success(
                ActionResponse(s"Amount $amount was deposited")
              )
            )
        case BalanceCheckCommand(replyTo) =>
          Effect.reply(replyTo)(
            StatusReply.Success(BalanceCheckResponse(state.amount))
          )
      }
  }

  val eventHandler: (State, Event) => State = { (state, event) =>
    event match {
      case WithdrawalEvent(amount) => state.copy(state.amount - amount)
      case DepositEvent(amount)    => state.copy(state.amount + amount)
    }
  }
}
