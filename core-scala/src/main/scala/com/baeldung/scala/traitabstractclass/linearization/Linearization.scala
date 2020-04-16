package com.baeldung.scala.traitabstractclass.linearization

trait A {
  def printer: Unit = println("Hello from A")
}

trait B extends A {
  override def printer: Unit = println("Hello from B")
}

trait C extends B {
  override def printer: Unit = println("Hello from C")
}

trait D extends C {
  override def printer: Unit = println("Hello from D")
}

class E extends A with B with C with D

object Linearization extends App {
  (new E).printer
}
