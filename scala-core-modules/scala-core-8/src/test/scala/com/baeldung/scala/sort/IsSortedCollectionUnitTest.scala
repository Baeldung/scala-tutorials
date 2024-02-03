package com.baeldung.scala.sort

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import IsSortedCollection.*

class IsSortedCollectionUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  val fnsToTest = List(
    (("isSortedBySorting", isSortedBySorting[Int])),
    (("isSortedBySliding", isSortedBySliding[Int])),
    (("isSortedByZip", isSortedByZip[Int])),
    (("isSortedByLazyZip", isSortedByLazyZip[Int])),
    (("isSortedRecursive", isSortedRecursive[Int]))
  )

  // format: off - to read the table more easily
  private val intTable = Table(
    ("Functions", "Input", "Direction", "IsSorted"),
    (fnsToTest, List(1, 2, 3, 4), Direction.ASC, true),
    (fnsToTest, List(1, 2, 3, 4), Direction.DESC, false),
    (fnsToTest, List(4, 3, 2, 1), Direction.DESC, true),
    (fnsToTest, List(1), Direction.DESC, true),
    (fnsToTest, List(1), Direction.ASC, true),
    (fnsToTest, Nil, Direction.ASC, true),
    (fnsToTest, Nil, Direction.DESC, true)
  )

  private val stringTable = Table(
    ("Function Name", "Function", "Input", "Direction", "IsSorted"),
    (
      "isSortedBySorting",
      isSortedBySorting[String],
      List("a", "b", "c"),
      Direction.ASC,
      true
    ),
    (
      "isSortedBySorting",
      isSortedBySorting[String],
      List("a", "b", "c"),
      Direction.DESC,
      false
    ),
    (
      "isSortedBySorting",
      isSortedBySorting[String],
      List("a", "aa", "aab", "bcd"),
      Direction.ASC,
      true
    ),
    (
      "isSortedBySorting",
      isSortedBySorting[String],
      List("ccc", "bbb", "a"),
      Direction.DESC,
      true
    )
  )

  // format: on

  it should "check if a list is ordered for numbers" in {
    forAll(intTable) { (fnsToTest, input, dir, res) =>
      fnsToTest.foreach { (name, fn) =>
        withClue(s"For function with name: ${name}") {
          fn(input, dir) shouldBe res
        }
      }

    }
  }

  it should "check if a list of strings are ordered" in {
    forAll(stringTable) { (name, fn, input, dir, res) =>
      withClue(s"For function with name: ${name}") {
        fn(input, dir) shouldBe res
      }
    }
  }

}
