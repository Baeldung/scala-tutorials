package com.baeldung.scala3.implicits.comparison.scala2

trait Printer {
  def write(value: String): Unit
}

def complexLogic(value: String)(implicit printer: Printer) = {
  printer.write(value)
}

class ConsolePrinter extends Printer {
  override def write(value: String): Unit = println(value)
}

@main def params: Unit = {
  implicit val printer = new ConsolePrinter
  complexLogic("Live Long and Prosper")
}