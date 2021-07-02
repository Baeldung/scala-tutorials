package com.baeldung.scala3.implicits.comparison.scala3

trait Printer {
  def write(value: String): Unit
}

def complexLogic(value: String)(using printer: Printer) = {
  printer.write(value)
}

class ConsolePrinter extends Printer {
  override def write(value: String): Unit = println(value)
}

@main def givens: Unit = {
  // given printer:ConsolePrinter = new ConsolePrinter
  given ConsolePrinter with {}
  complexLogic("Live long and prosper with givens")
}