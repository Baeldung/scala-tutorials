package com.baeldung.scala.abstractclass.vs.`trait`.abstractclass

abstract class Person {
  def firstName: String

  def lastName: String

  def name: String = s"$firstName $lastName"
}
