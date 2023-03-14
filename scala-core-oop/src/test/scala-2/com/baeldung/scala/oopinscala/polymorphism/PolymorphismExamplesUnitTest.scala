package com.baeldung.scala.oopinscala.polymorphism

import org.scalatest._
import PolymorphismExamples._
import org.scalatest.flatspec.AnyFlatSpec

class PolymorphismExamplesUnitTest extends AnyFlatSpec {

  it should "pair-wise reverse an even length int list" in {
    val original = List(1, 2, 3, 4, 5, 6)
    val expected = List(2, 1, 4, 3, 6, 5)

    val actual = pairWiseReverseInt(original)

    assertResult(expected)(actual)
  }

  it should "pair-wise reverse an odd length int list" in {
    val original = List(1, 2, 3, 4, 5)
    val expected = List(2, 1, 4, 3, 5)

    val actual = pairWiseReverseInt(original)

    assertResult(expected)(actual)
  }

  it should "pair-wise reverse a string list" in {
    val original = List("a", "b", "c", "d", "e")
    val expected = List("b", "a", "d", "c", "e")

    val actual = pairWiseReverseString(original)

    assertResult(expected)(actual)
  }

  it should "pair-wise reverse a double list" in {
    val original = List(1.2, 2.7, 3.4, 4.3, 5.0)
    val expected = List(2.7, 1.2, 4.3, 3.4, 5.0)

    val actual = pairWiseReverseDouble(original)

    assertResult(expected)(actual)
  }

  it should "pair-wise reverse lists of any type" in {
    val ints = List(1, 2, 3, 4, 5)
    val strings = List("a", "b", "c", "d", "e")
    val doubles = List(1.2, 2.7, 3.4, 4.3, 5.0)

    assertResult(expected = List(2, 1, 4, 3, 5))(actual =
      pairWiseReverse[Int](ints)
    )
    assertResult(expected = List("b", "a", "d", "c", "e"))(actual =
      pairWiseReverse[String](strings)
    )
    assertResult(expected = List(2.7, 1.2, 4.3, 3.4, 5.0))(actual =
      pairWiseReverse[Double](doubles)
    )
  }

  "Shapes" should "compute correct area" in {
    val square = Square(10.0)
    val circle = Circle(12.0)

    assertResult(expected = 100.00)(actual = printArea(square))
    assertResult(expected = 452.39)(actual = printArea(circle))
  }

  it should "sort integers correctly" in {
    val intList = List(3, 5, 2, 1, 4)
    val sortedIntList = intList.sorted
    assertResult(expected = List(1, 2, 3, 4, 5))(actual = sortedIntList)
  }

  it should "sort custom types correctly with implicits" in {
    val studentIds =
      List(StudentId(5), StudentId(1), StudentId(4), StudentId(3), StudentId(2))

    val sortedStudentIds = studentIds.sorted

    assertResult(
      expected = List(
        StudentId(1),
        StudentId(2),
        StudentId(3),
        StudentId(4),
        StudentId(5)
      )
    )(actual = sortedStudentIds)
  }

  it should "add and subtract complex numbers successfully" in {
    val cmpl1 = Complex(8.0, 3.0)
    val cmpl2 = Complex(6.0, 2.0)

    val cmplSum = cmpl1.+(cmpl2)
    val cmplDiff = cmpl1 - cmpl2

    assertResult(expected = "14.0 + 5.0i")(actual = cmplSum.toString)
    assertResult(expected = "2.0 + 1.0i")(actual = cmplDiff.toString)
  }
}
