package com.baeldung.scala.comparedouble

import org.scalactic.TolerantNumerics
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CompareDoubleSpec extends AnyFlatSpec with Matchers {
  val doubleToTest: Double = 100.123

  "double" should "pass test with implicit tolerance" in {
    implicit val doubleEquality = TolerantNumerics.tolerantDoubleEquality(0.001)
    assert(doubleToTest === 100.122)
  }

  "double" should "pass test with tolerance" in {
    assert(doubleToTest === 100.122 +- 0.001)
  }
}
