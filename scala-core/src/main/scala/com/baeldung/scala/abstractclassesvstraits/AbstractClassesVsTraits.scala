package com.baeldung.scala.abstractclassesvstraits

object AbstractClassesVsTraits_Errors {
  import AbstractClassesVsTraits._

  // val bob = new Person // error - not all members implemented
  // trait Person(name: String, age: Int) // error - traits don't have constructor args
}

object AbstractClassesVsTraits {
  abstract class Person {
    // the abstract keyword is not necessary in the field/method definition
    val canDrive: Boolean
    def discussWith(another: Person): String
  }

  class Adult(val name: String, hasDrivingLicence: Boolean) extends Person {
    override def toString: String = name
    override val canDrive: Boolean = hasDrivingLicence
    override def discussWith(other: Person): String = s"Indeed, $other, Kant was indeed revolutionary for his time..."
  }

}
