package com.baeldung.scala.scalaz

import org.scalatest._
import ScalazIntro._
class ScalazIntroUnitTest extends FlatSpec {

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

  it should "custom print different types" in {
    val studentId = StudentId(25)
    val staffId = StaffId(12)
    val score = Score(94.2)

    assertResult(expected = "StudentId: 25")(actual = show(studentId))
    assertResult(expected = "StaffId: 12")(actual = show(staffId))
    assertResult(expected = "Score: 94.2%")(actual = show(score))
  }

  "Bot" should "detect new member details" in {
    val intro = IntroText(
      "My name is Fauz my primary programming language is Scala I have 7 years of industry experience"
    )

    assertResult(expected = "Senior")(actual = intro.level)
    assertResult(expected = "Scala")(actual = intro.language)
    assertResult(expected = "Fauz")(actual = intro.name)
  }

  "Pimped Bot" should "detect new member details" in {
    val intro =
      "My name is Beng my primary programming language is Java I have 2 years of industry experience"

    assertResult(expected = "Junior")(actual = intro.level)
    assertResult(expected = "Java")(actual = intro.language)
    assertResult(expected = "Bengi")(actual = intro.name)
  }

  "Doubler" should "work on any supported container type" in {
    val intList: List[Int] = List(1, 2, 3)
    val intOpt: Option[Int] = Some(5)
    val strList: List[String] = List("a", "b", "c")

    assertResult(expected = List(2, 4, 6))(actual = doubleIt(intList))
    assertResult(expected = Some(10))(actual = doubleIt(intOpt))
    assertResult(expected = List("aa", "bb", "cc"))(actual = doubleIt(strList))
  }
}
