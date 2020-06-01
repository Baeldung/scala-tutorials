package com.baeldung.scala.abstractclassesandtraits

object PracticalAbstractVsTraits {
  trait Logger {
    def info(msg: String): Unit = {
      // logger logic
    }
  }

  class Runner extends Logger {
    def run(): Unit = {
      info("Using logging from trait")
    }
  }

  abstract class Employee(title: String, level: String) {
    def showTitleAndLevel(): Unit = {
      // logic to display title and level
    }
  }

  class JuniorDeveloper(name: String)
      extends Employee(title = "Developer", level = "Junior")
  class SeniorDesigner(name: String)
      extends Employee(title = "Designer", level = "Senior")

}
