package com.baeldung.scala.akka

import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.{AskPattern, Behaviors}
import akka.util.Timeout
import ActorTest.TrafficLight.SignalCommand._
import ActorTest.TrafficLight.CurrentSignal
import ActorTest.TrafficLight.Signal.RED
import ActorTest.{Greeter, TrafficLight}

import scala.concurrent.Await

object ActorTest {

  object Greeter {
    case class GreetingRequest(
      greeting: String,
      self: ActorRef[GreetingResponse]
    )
    case class GreetingResponse(greeting: String)

    def apply(): Behavior[GreetingRequest] = Behaviors.receiveMessage {
      case GreetingRequest(greeting, recipient) =>
        recipient ! GreetingResponse(greeting)
        Behaviors.same
    }
  }

  object TrafficLight {
    sealed trait Signal
    object Signal {
      case object RED extends Signal
      case object YELLOW extends Signal
      case object GREEN extends Signal
    }

    sealed trait SignalCommand
    object SignalCommand {
      case class ChangeSignal(recipient: ActorRef[CurrentSignal])
        extends SignalCommand
      case class GetSignal(recipient: ActorRef[CurrentSignal])
        extends SignalCommand
    }
    case class CurrentSignal(signal: Signal)

    import Signal._
    def apply(): Behavior[SignalCommand] = Behaviors.setup { _ =>
      var state: Signal = RED
      Behaviors.receiveMessage {
        case ChangeSignal(recipient) =>
          val nextState = state match {
            case RED    => YELLOW
            case YELLOW => GREEN
            case GREEN  => RED

          }
          state = nextState
          recipient ! CurrentSignal(nextState)
          Behaviors.same

        case GetSignal(recipient) =>
          recipient ! CurrentSignal(state)
          Behaviors.same

      }
    }
  }
}

class GreeterUnitTest extends TestServiceUnitTest {
  import scala.concurrent.duration._
  val greeting = "Hello there"
  val sender = testKit.spawn(Greeter(), "greeter")
  // create a test probe of type Greeter.GreetingResponse
  val probe = testKit.createTestProbe[Greeter.GreetingResponse]()
  sender ! Greeter.GreetingRequest(greeting, probe.ref)
  probe.expectMessage(Greeter.GreetingResponse(greeting))
  // no other message should be received by the greeter actor
  probe.expectNoMessage(50.millis)
}

class TrafficLightUnitTest extends TestServiceUnitTest {
  import scala.concurrent.duration._
  val sender = testKit.spawn(TrafficLight(), "traffic")
  val probe = testKit.createTestProbe[TrafficLight.CurrentSignal]()

  // ensure that initial state is RED
  sender ! TrafficLight.SignalCommand.GetSignal(probe.ref)
  probe.expectMessage(TrafficLight.CurrentSignal(TrafficLight.Signal.RED))
  probe.expectNoMessage(50.millis)

  // now try to change signal
  sender ! TrafficLight.SignalCommand.ChangeSignal(probe.ref)
  probe.expectMessage(TrafficLight.CurrentSignal(TrafficLight.Signal.YELLOW))
  // ensure no other message is received
  probe.expectNoMessage(50.millis)

  // ensure that the state is preserved
  sender ! TrafficLight.SignalCommand.ChangeSignal(probe.ref)
  probe.expectMessage(TrafficLight.CurrentSignal(TrafficLight.Signal.GREEN))
  probe.expectNoMessage(50.millis)
  // ensure that the state is preserved
  sender ! TrafficLight.SignalCommand.ChangeSignal(probe.ref)
  probe.expectMessage(TrafficLight.CurrentSignal(TrafficLight.Signal.RED))
  probe.expectNoMessage(50.millis)
}

class TrafficLightTestFutUnitTest extends TestServiceUnitTest {
  import scala.concurrent.duration._
  val sender = testKit.spawn(TrafficLight(), "traffic")
  val duration = 300.millis
  implicit val timeout = Timeout(duration)

  val signalFut =
    sender.ask(replyTo => TrafficLight.SignalCommand.GetSignal(replyTo))
  val signal = Await.result(signalFut, duration)
  assert(signal == CurrentSignal(RED))
}
