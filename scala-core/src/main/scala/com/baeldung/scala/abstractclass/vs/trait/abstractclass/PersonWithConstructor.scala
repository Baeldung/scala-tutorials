package com.baeldung.scala.abstractclass.vs.`trait`.abstractclass

abstract class PersonWithConstructor(val firstName: String, val lastName: String) {
  def age: Int

  def name: String = s"$firstName $lastName"
}
