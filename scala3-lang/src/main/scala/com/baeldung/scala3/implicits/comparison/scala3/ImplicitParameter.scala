package com.baeldung.scala3.implicits.comparison.scala3

trait Printer {
  def write(value: String): String
}
object ParamUtil {
  def complexLogic(value: String)(using printer: Printer) = {
    printer.write(value)
  }
}

class ConsolePrinter extends Printer {
  override def write(value: String): String = {
    println(value)
    value
  }
}