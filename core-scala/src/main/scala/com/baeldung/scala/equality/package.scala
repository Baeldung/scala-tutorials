package com.baeldung.scala

package object equality {

  // A class with default equals/hashcode implementations
  class PersonFirst(val name: String, val age: Int)

  // A class with overridden equals/hashcode implementations
  class PersonSecond(val name: String, val age: Int) {
    override def equals(other: Any): Boolean = other match {
      case person: PersonSecond =>
        this.name == person.name && this.age == person.age
      case _ => false
    }

    override def hashCode(): Int = if (name eq null) age else name.hashCode + 31 * age
  }

  // A case class with the compiler-generated equals/hashcode implementations
  case class PersonThird(name: String, age: Int)

}
