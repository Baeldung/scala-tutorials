package com.baeldung.scala3.implicits.comparison.scala2

trait Printer {
  def write(value: String): String
}

def complexLogic(value: String)(implicit printer: Printer) = {
  printer.write(value)
}

class ConsolePrinter extends Printer {
  override def write(value: String): String = {
    println(value)
    value
  }
}