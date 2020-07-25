package com.baeldung.scala.abstractclass.vs.`trait`.`trait`

trait Person {
  def firstName: String

  def lastName: String

  def name: String = s"$firstName $lastName"

}
