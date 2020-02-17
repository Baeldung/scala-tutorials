package com.baeldung.scala.traitsvsabstractclasses.traitwithimplementation

trait Logger {
  def log(s: String): Unit = { println(s) }
}

class LoggerWrapper extends Logger
