package com.baeldung.scala

package object equality {
  class PersonSimpleClass(val name: String, val age: Int)

  class PersonClassWithOverrides(val name: String, val age: Int) {
    override def equals(other: Any): Boolean = other match {
      case person: PersonClassWithOverrides =>
        this.name == person.name && this.age == person.age
      case _ => false
    }

    override def hashCode(): Int =
      if (name eq null) age else name.hashCode + 31 * age
  }

  case class PersonCaseClass(name: String, age: Int)
}
