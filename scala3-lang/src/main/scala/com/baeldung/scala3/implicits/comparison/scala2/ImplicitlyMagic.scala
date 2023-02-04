package com.baeldung.scala3.implicits.comparison.scala2

object PrinterProvider {
  implicit val console: Printer = new ConsolePrinter
}

class ImplicitlyMagic {
  import PrinterProvider.*
  def greet(msg: String): Unit = {
    val printer = implicitly[Printer]
    printer.write(msg)
  }

}
