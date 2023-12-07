package com.baeldung.scala.inlining

object Inlining extends App {
  @inline
  final def informalGreeting(name: String): String = s"Hi, $name"

  @noinline
  final def formalGreeting(name: String): String = s"Hello, $name"

  final def veryInformalGreeting(name: String): String = s"Hey, $name"

  def greetBaeldung1 = informalGreeting("Baeldung")
  def greetBaeldung2 = formalGreeting("Baeldung")
  def greetBaeldung3 = veryInformalGreeting("Baeldung")

  def greetBaeldung4 = informalGreeting("Baeldung"): @noinline
  def greetBaeldung5 = formalGreeting("Baeldung"): @inline
  def greetBaeldung6 = veryInformalGreeting("Baeldung"): @inline
  def greetBaeldung7 = veryInformalGreeting("Baeldung"): @noinline

  def greetBaeldung8 =
    informalGreeting("Baeldung") + informalGreeting("Baeldung"): @noinline
  def greetBaeldung9 =
    informalGreeting("Baeldung") + (informalGreeting("Baeldung"): @noinline)
}
