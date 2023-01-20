package com.baeldung.scala.akka.actordiscovery

import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.util.Timeout
import java.util.concurrent.TimeUnit
import scala.util.Success

object Addition {
  def apply(): Behavior[Operations.Calculate] = Behaviors.setup { context =>
    Behaviors.receiveMessage[Operations.Calculate] { message =>
      context.log.info(
        s"${message.a} + ${message.b} = ${message.a + message.b}"
      )
      Behaviors.same
    }
  }
}

object Multiplication {
  def apply(): Behavior[Operations.Calculate] = Behaviors.setup { context =>
    Behaviors.receiveMessage[Operations.Calculate] { message =>
      context.log.info(
        s"${message.a} * ${message.b} = ${message.a * message.b}"
      )
      Behaviors.same
    }
  }
}

object Operations {
  final case class Setup()
  final case class Calculate(a: Int, b: Int)

  val AdditionKey = ServiceKey[Operations.Calculate]("addition")
  val MultiplicationKey = ServiceKey[Operations.Calculate]("multiplication")

  def apply(): Behavior[Setup] = Behaviors.setup { context =>
    Behaviors.receiveMessage[Setup] { _ =>
      context.log.info("Registering operations...")

      val addition = context.spawnAnonymous(Addition())
      context.system.receptionist ! Receptionist.Register(
        Operations.AdditionKey,
        addition
      )
      context.log.info("Registered addition")

      val multiplication = context.spawnAnonymous(Multiplication())
      context.system.receptionist ! Receptionist.Register(
        Operations.MultiplicationKey,
        multiplication
      )
      context.log.info("Registered multiplication")

      Behaviors.same
    }
  }
}

object RegistrationListener {
  def apply(): Behavior[Receptionist.Listing] = Behaviors.setup { context =>
    context.system.receptionist ! Receptionist.Subscribe(
      Operations.AdditionKey,
      context.self
    )
    context.system.receptionist ! Receptionist.Subscribe(
      Operations.MultiplicationKey,
      context.self
    )

    Behaviors.receiveMessage[Receptionist.Listing] { listing =>
      val key = listing.key
      listing.getServiceInstances(key).forEach { reference =>
        context.log.info(s"Registered: ${reference.path}")
      }
      Behaviors.same
    }
  }
}

object Calculator {
  sealed trait Command
  final case class Calculate(operation: String, a: Int, b: Int) extends Command
  final case class CalculateUsingOperation(
    operation: ActorRef[Operations.Calculate],
    a: Int,
    b: Int
  ) extends Command

  private def getKey = (operationName: String) => {
    operationName match {
      case "addition"       => Operations.AdditionKey
      case "multiplication" => Operations.MultiplicationKey
    }
  }

  def apply(): Behavior[Command] =
    Behaviors.setup { context =>
      context.spawnAnonymous(RegistrationListener())
      context.spawn(Operations(), "operations") ! Operations.Setup()

      implicit val timeout: Timeout = Timeout.apply(100, TimeUnit.MILLISECONDS)

      Behaviors.receiveMessagePartial[Command] {
        case Calculate(operation, a, b) => {
          context.log.info(s"Looking for implementation of ${operation}")
          val operationKey = getKey(operation)
          context.ask(
            context.system.receptionist,
            Receptionist.Find(operationKey)
          ) {
            case Success(listing) => {
              val instances = listing.serviceInstances(operationKey)
              val firstImplementation = instances.iterator.next()
              CalculateUsingOperation(firstImplementation, a, b)
            }
          }

          Behaviors.same
        }

        case CalculateUsingOperation(operation, a, b) => {
          context.log.info("Calculating...")
          operation ! Operations.Calculate(a, b)
          Behaviors.same
        }
      }
    }
}

object MathActorDiscovery extends App {
  val system: ActorSystem[Calculator.Calculate] =
    ActorSystem(Calculator(), "calculator")

  system ! Calculator.Calculate("addition", 3, 5)
  system ! Calculator.Calculate("multiplication", 3, 5)

  system.terminate()
}
