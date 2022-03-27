package com.baeldung.scala.removeduplicates

import org.scalatest.wordspec.AnyWordSpec

import scala.language.implicitConversions

case class Person(userId: String, firstName: String, lastName: String) {
  override def hashCode(): Int = userId.hashCode

  override def equals(other: Any): Boolean = {
    if (!canEqual(other)) false
    else if (!other.isInstanceOf[Person]) false
    else userId == other.asInstanceOf[Person].userId
  }
}

class DuplicatesRemoverSpec extends AnyWordSpec {

  "DuplicatesRemover" should {
    "return a shorter integers list without duplicates" in {
      val withDuplicates = List(3, 7, 2, 7, 1, 3, 4)
      val withoutDuplicates = List(3, 7, 2, 1, 4)
      val deDuplicated =
        DuplicatesRemover.removeDuplicates(withDuplicates)
      assertResult(withoutDuplicates)(deDuplicated)
    }
    "return the same list if no duplicates" in {
      val withoutDuplicates = List(3, 7, 2, 1, 4)
      val deDuplicated =
        DuplicatesRemover.removeDuplicates(withoutDuplicates)
      assertResult(withoutDuplicates)(deDuplicated)
    }
    "handle empty lists" in {
      assertResult(List.empty[Int])(
        DuplicatesRemover.removeDuplicates(List.empty[Int])
      )
    }
    "de-duplicate lists of objects" in {
      val withDuplicates = List(
        Person(userId = "mm01", firstName = "Mickey", lastName = "Mouse"),
        Person(userId = "jw04", firstName = "John", lastName = "Wayne"),
        Person(userId = "mm01", firstName = "Marilyn", lastName = "Manson"),
        Person(userId = "sh01", firstName = "Sherlock", lastName = "Holmes"),
        Person(userId = "jw04", firstName = "John", lastName = "Watson")
      )
      val withoutDuplicates = List(
        Person(userId = "mm01", firstName = "Mickey", lastName = "Mouse"),
        Person(userId = "jw04", firstName = "John", lastName = "Wayne"),
        Person(userId = "sh01", firstName = "Sherlock", lastName = "Holmes")
      )
      val deDuplicated =
        DuplicatesRemover.removeDuplicates(withDuplicates)
      assertResult(withoutDuplicates)(deDuplicated)
    }
  }
}
