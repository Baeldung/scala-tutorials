package com.baeldung.scala.traitsvsabstract

trait HelloWorldPrinter {
  def printHelloWorld = println("hello world")
}

abstract class HiTherePrinter {
  def printHiThere = println("hi there")
}

class Program()

object AdditionToObjects {

  val program = new Program() with HelloWorldPrinter
  program.printHelloWorld

}
