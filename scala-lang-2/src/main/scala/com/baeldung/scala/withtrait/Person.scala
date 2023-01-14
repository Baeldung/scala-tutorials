package com.baeldung.scala.withtrait

import java.time.LocalDate

case class Person(name: String, address: String, dateOfBirth: LocalDate) {
  def breathe(): Unit = println("I'm breathing")

  def eat(): Unit = println("I'm eating: chomp chomp")

  def walk(): Unit = println("I'm walking: away I go")

  def saySomething(what: String): Unit = println(s"I say: $what")
}
