package com.baeldung.scala.abstractclass.vs.`trait`

import com.baeldung.scala.abstractclass.vs.`trait`.abstractclass.PersonWithConstructor

class StudentWithConstructor(val rollNo: Int, override val firstName: String, override val lastName: String) extends
  PersonWithConstructor(firstName, lastName) {
  override def age: Int = ???
}
