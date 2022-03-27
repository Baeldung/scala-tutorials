package com.baeldung.scala.removeduplicates

import org.scalatest.wordspec.AnyWordSpec

import scala.language.implicitConversions

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
      case class FullIdentityPerson(
        userId: String,
        firstName: String,
        lastName: String
      )

      case class PartialIdentityPerson(
        userId: String,
        firstName: String,
        lastName: String
      ) {
        override def hashCode(): Int = userId.hashCode

        override def equals(other: Any): Boolean = {
          if (!canEqual(other)) false
          else if (!other.isInstanceOf[PartialIdentityPerson]) false
          else userId == other.asInstanceOf[PartialIdentityPerson].userId
        }
      }

      // First, let's test full equivalence
      val withFullDuplicates = List(
        FullIdentityPerson(userId = "mm01", firstName = "Mickey", lastName = "Mouse"),
        FullIdentityPerson(userId = "jw04", firstName = "John", lastName = "Wayne"),
        FullIdentityPerson(userId = "mm01", firstName = "Marilyn", lastName = "Manson"),
        FullIdentityPerson(userId = "sh01", firstName = "Sherlock", lastName = "Holmes"),
        FullIdentityPerson(userId = "mm01", firstName = "Mickey", lastName = "Mouse"),
        FullIdentityPerson(userId = "jw04", firstName = "John", lastName = "Watson")
      )
      val withoutFullDuplicates = List(
        FullIdentityPerson(userId = "mm01", firstName = "Mickey", lastName = "Mouse"),
        FullIdentityPerson(userId = "jw04", firstName = "John", lastName = "Wayne"),
        FullIdentityPerson(userId = "mm01", firstName = "Marilyn", lastName = "Manson"),
        FullIdentityPerson(userId = "sh01", firstName = "Sherlock", lastName = "Holmes"),
        FullIdentityPerson(userId = "jw04", firstName = "John", lastName = "Watson")
      )
      val deDuplicatedFull =
        DuplicatesRemover.removeDuplicates(withFullDuplicates)
      assertResult(withoutFullDuplicates)(deDuplicatedFull)

      // Now, let's test partial equivalence
      val withPartialDuplicates = List(
        PartialIdentityPerson(userId = "mm01", firstName = "Mickey", lastName = "Mouse"),
        PartialIdentityPerson(userId = "jw04", firstName = "John", lastName = "Wayne"),
        PartialIdentityPerson(userId = "mm01", firstName = "Marilyn", lastName = "Manson"),
        PartialIdentityPerson(userId = "sh01", firstName = "Sherlock", lastName = "Holmes"),
        PartialIdentityPerson(userId = "jw04", firstName = "John", lastName = "Watson")
      )
      val withoutPartialDuplicates = List(
        PartialIdentityPerson(userId = "mm01", firstName = "Mickey", lastName = "Mouse"),
        PartialIdentityPerson(userId = "jw04", firstName = "John", lastName = "Wayne"),
        PartialIdentityPerson(userId = "sh01", firstName = "Sherlock", lastName = "Holmes")
      )
      val deDuplicatedPartial =
        DuplicatesRemover.removeDuplicates(withPartialDuplicates)
      assertResult(withoutPartialDuplicates)(deDuplicatedPartial)
    }
  }
}
