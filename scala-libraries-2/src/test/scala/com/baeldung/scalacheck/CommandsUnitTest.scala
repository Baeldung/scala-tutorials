package com.baeldung.scalacheck

import com.baeldung.scalacheck.model.{SystemUnderTest, TrafficLight}
import org.scalacheck.{Gen, Prop, Properties}
import org.scalacheck.commands.Commands

import java.time.Clock
import java.util.UUID
import scala.util.{Success, Try}

object TrafficLightCommandsTest extends Properties("TrafficLightCommands") {
  property("TrafficLight") = CommandsUnitTest.property()
}

object CommandsUnitTest extends Commands {
  override type State = TrafficLight
  override type Sut = SystemUnderTest

  override def canCreateNewSut(newState: TrafficLight, initSuts: Traversable[TrafficLight], runningSuts: Traversable[SystemUnderTest]): Boolean = {
    !initSuts.exists(_.uuid == newState.uuid)
  }

  override def newSut(state: TrafficLight): SystemUnderTest = SystemUnderTest(UUID.randomUUID(), Clock.systemUTC().millis(), state)

  override def destroySut(sut: SystemUnderTest): Unit = {
    println(s"Destroying: $sut")
  }

  override def initialPreCondition(state: TrafficLight): Boolean = true

  override def genInitialState: Gen[TrafficLight] = {
    for (
      trafficLightColor <- Gen.oneOf(model.Red, model.Orange, model.Green)
    ) yield TrafficLight(UUID.randomUUID(), trafficLightColor)
  }

  override def genCommand(state: TrafficLight): Gen[Command] = {
    state.color match {
      case model.Green => TransitionToOrange(state)
      case model.Orange => TransitionToRed(state)
      case model.Red => TransitionToGreen(state)
      case _ => throw new RuntimeException("Traffic lights have only green, orange and red color.")
    }
  }

  case class TransitionToGreen(trafficLight: TrafficLight) extends Command {
    override type Result = Boolean

    override def run(sut: SystemUnderTest): Boolean = {
      println("going green")
      true
    }

    override def nextState(state: TrafficLight): TrafficLight = state.copy(color = model.Green)

    override def preCondition(state: TrafficLight): Boolean = state.color == model.Red

    override def postCondition(state: TrafficLight, result: Try[Boolean]): Prop = result == Success(true)
  }

  case class TransitionToRed(trafficLight: TrafficLight) extends Command {
    override type Result = Boolean

    override def run(sut: SystemUnderTest): Boolean = {
      println("going red")
      true
    }

    override def nextState(state: TrafficLight): TrafficLight = state.copy(color = model.Red)

    override def preCondition(state: TrafficLight): Boolean = state.color == model.Orange

    override def postCondition(state: TrafficLight, result: Try[Boolean]): Prop = result == Success(true)
  }

  case class TransitionToOrange(trafficLight: TrafficLight) extends Command {
    override type Result = Boolean

    override def run(sut: SystemUnderTest): Boolean = {
      println("going orange")
      true
    }

    override def nextState(state: TrafficLight): TrafficLight = state.copy(color = model.Orange)

    override def preCondition(state: TrafficLight): Boolean = state.color == model.Green

    override def postCondition(state: TrafficLight, result: Try[Boolean]): Prop = result == Success(true)
  }
}
