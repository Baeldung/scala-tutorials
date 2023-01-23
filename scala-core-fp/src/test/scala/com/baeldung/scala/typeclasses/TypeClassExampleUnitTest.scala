package com.baeldung.scala.typeclasses

import com.baeldung.scala.typeclasses.TypeClassExample._
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec

class TypeClassExampleUnitTest extends AnyFlatSpec{
  it should "custom print different types" in {
    val studentId = StudentId(25)
    val staffId = StaffId(12)
    val score = Score(94.2)

    assertResult(expected = "StudentId: 25")(actual = show(studentId))
    assertResult(expected = "StaffId: 12")(actual = show(staffId))
    assertResult(expected = "Score: 94.2%")(actual = show(score))
  }
}
