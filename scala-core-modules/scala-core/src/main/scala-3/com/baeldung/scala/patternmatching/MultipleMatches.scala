package com.baeldung.scala.patternmatching

sealed trait Command
case object Start extends Command
case object Stop extends Command
case object Report extends Command
case class CustomCommand(cmd: String) extends Command

def executeCommand(command: Command): String = command match {
  case Start | CustomCommand("begin") =>
    "System Starting."
  case Stop | CustomCommand("halt") =>
    "System Stopping."
  case Report | CustomCommand("status") =>
    "Generating Report."
  case _ =>
    "Unknown Command."
}

def httpResponse(response: Int): String = response match {
  case 200 | 201 | 202 => "Success"
  case 400 | 404 | 500 => "Error"
  case _               => "Unknown status"
}

def multipleTypePatterns(obj: Any): String = obj match {
  case _: String | _: Int => "It's either a String or an Int"
  case _                  => "It's something else"
}

def unionTypePattern(obj: Any): String = obj match {
  case _: (String | Int) => "It's either a String or an Int"
  case _                 => "It's something else"
}
