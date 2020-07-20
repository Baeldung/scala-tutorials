package com.baeldung.scala.traits

object ObjectInstanceTrait extends App {

  trait Logger {
    def logMessage(msg: String) = println(msg)
  }

  class Process {
    def doProcess() = ???
  }

  val loggedProcess = new Process with Logger

  loggedProcess.logMessage("hello, I am logged")
  // output : hello, I am logged

}
