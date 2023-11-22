package com.baeldung.scala3.implicits.comparison.scala3

object PrinterProvider {
  given Printer = new ConsolePrinter
}

class ImplicitlyMagic {
  import PrinterProvider.given
  def greet(msg: String): Unit = {
    val printer = summon[Printer]
    printer.write(msg)
  }

}
