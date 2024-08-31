package com.baeldung.scala.removeelement

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class RemoveMatchingElementUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  //format: off
  val firstMatchTable = Table(
    ("input", "elementToRemove", "expected"),
    (List("abc", "def", "xyz", "abc"), "abc" ,List("def", "xyz", "abc")),
    (List("abc", "def", "xyz", "abc"), "abcd" ,List("abc", "def", "xyz", "abc")),
    (List.empty[String], "abcd" ,List.empty[String]),
  )

  val allMatchTable = Table(
    ("input", "elementToRemove", "expected"),
    (List("abc", "def", "xyz", "abc"), "abc", List("def", "xyz")),
    (List("abc", "def", "xyz", "abc"), "abcd", List("abc", "def", "xyz", "abc")),
    (List.empty[String], "abcd", List.empty[String]),
  )
  //format on

  // on immutable collections
  it should "remove first match using span" in {
    forAll(firstMatchTable) { (input, ele, exp) =>
      val (part1, part2)= input.span(_ != ele)
      val result = part1 ++ part2.drop(1)
      result shouldBe exp
    }
  }

  it should "remove first match using pattern matching" in {
    forAll(firstMatchTable) { (input, ele, exp) =>
      val result = input match {
        case `ele` :: sub => sub
        case sub => sub
      }
      result shouldBe exp
    }
  }

  it should "remove all matches using partition" in {
    forAll(allMatchTable) { (input, ele, exp) =>
      val (part1, _) = input.partition(_ != ele)
      val result = part1
      result shouldBe exp
    }
  }

  // on mutable collections
  it should "remove first match from mutable list" in {
    val buffer = ListBuffer[String]("abc", "def", "xyz", "def")
    buffer -= "def"
    buffer shouldBe ListBuffer[String]("abc", "xyz", "def")
  }

  it should "remove all matches from mutable list using filterInPlace" in {
    val buffer = ListBuffer[String]("abc", "def", "xyz", "def")
    buffer.filterInPlace(_ != "def")
    buffer shouldBe ListBuffer[String]("abc", "xyz")
  }

  it should "remove all matches from mutable list using filter" in {
    val list = List("abc", "def", "xyz", "def")
    val filteredList = list.filter(_ != "def")
    filteredList shouldBe List("abc", "xyz")
    list.filterNot(_ == "def") shouldBe List("abc", "xyz")
    // on ListBuffer
    val buffer = ListBuffer[String]("abc", "def", "xyz", "def")
    val newBuffer = buffer.filter(_ != "def")
    newBuffer shouldBe ListBuffer[String]("abc", "xyz")
  }

  // remove multiple matches
  it should "remove all matches using collect" in {
    forAll(allMatchTable) { (input, ele, exp) =>
      val result = input.collect {
        case e if e != ele => e
      }
      result shouldBe exp
    }
  }

  it should "remove all matches using filter" in {
    forAll(allMatchTable) { (input, ele, exp) =>
      val result = input.filter(_ != ele)
      result shouldBe exp
      val result2 = input.filterNot(_ == ele)
      result2 shouldBe exp
    }
  }

  it should "remove all matches using recursion" in {
    forAll(allMatchTable) { (input, ele, exp) =>
      val result = input.foldLeft(List.empty[String])((acc,i) => if(i != ele) acc :+ i else acc)
      result shouldBe exp
    }
  }

  it should "remove all matches from mutable ListBuffer" in {
    val buffer = ListBuffer[String]("abc", "def", "xyz", "def")
    buffer.filterInPlace(_ != "def")
    buffer shouldBe ListBuffer[String]("abc", "xyz")
  }

  it should "remove all matches from mutable ArrayBuffer" in {
    val buffer = ArrayBuffer[String]("abc", "def", "xyz", "def")
    buffer.filterInPlace(_ != "def")
    buffer shouldBe ListBuffer[String]("abc", "xyz")
  }
}
