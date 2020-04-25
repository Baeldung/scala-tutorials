package com.baeldung.scala.abstract_class_vs_trait

import scala.reflect.ClassTag

object AbstractClassWithTraits {

  trait Actor {
    type Receive = Any => Unit
    def receive: Receive
    def !(message: Any): Unit = receive(message)
  }

  trait Output {
    def print(s: String) = Console.println(s)
  }

  abstract class CommandHandler[Command: ClassTag] extends Actor with Output {
    def receive: Receive = {
      case command: Command =>
        print(s"Received command | $command")
      case other =>
        print(s"Received unexpected message | ${other}")
    }
  }

  object Person {
    sealed trait PersonCommands
    case class Hello() extends PersonCommands
  }

}
