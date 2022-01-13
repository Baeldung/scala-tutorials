package com.baedung.scala.collections

import com.baeldung.scala.collections.FoldingLists.Person
import org.scalatest.FlatSpec

class FoldingListsUnitTest extends FlatSpec {

  val persons = List(Person("Thomas", "male"), Person("Sowell", "male"), Person("Liz", "female"))

  "Fold" should "sum numbers in an Int list" in {
    val list = List(1, 2, 3, 4, 5, 6)
    val sum = list.fold(0)((x, y) => x + y)
    assert(sum == 21)
  }

  "FoldRight and FoldLeft" should "sum numbers in a stringified Int list" in {
    val stringifiedInts = List("1", "2", "3", "4", "5")
    val foldLeftSum = stringifiedInts.foldLeft(0)((acc, currNum) => acc + currNum.toInt)
    val foldRightSum = stringifiedInts.foldRight(0)((currNum, acc) => currNum.toInt + acc)
    assert(foldLeftSum == 15)
    assert(foldRightSum == 15)
  }

  "FoldRight" should "process lists from right to left" in {
    val foldedList = persons.foldRight(List[String]()) { (person, accumulator) =>
      val title = person.sex match {
        case "male" => "Mr."
        case "female" => "Ms."
      }
      accumulator :+ s"$title ${person.name}"
    }
    assert(foldedList == List("Ms. Liz", "Mr. Sowell", "Mr. Thomas"))
  }

  "FoldLeft" should "process lists from left to right" in {
    val foldedList = persons.foldLeft(List[String]()) { (accumulator, person) =>
      val title = person.sex match {
        case "male" => "Mr."
        case "female" => "Ms."
      }
      accumulator :+ s"$title ${person.name}"
    }
    assert(foldedList == List("Mr. Thomas", "Mr. Sowell", "Ms. Liz"))
  }
}
