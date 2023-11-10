package com.baeldung.scala.withtrait

import java.time.LocalDate

class Person(name: String, address: String, dateOfBirth: LocalDate) {
  def breathe(): String = s"I'm $name, alive and kicking"

  def eat(): String = "I'm eating: chomp chomp"

  def walk(): String = "I'm walking: away I go"

  def saySomething(what: String): String = s"I say: $what"
}
