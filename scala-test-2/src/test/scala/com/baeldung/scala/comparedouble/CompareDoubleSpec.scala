package com.baeldung.scala.comparedouble

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CompareDoubleSpec extends AnyFlatSpec with Matchers {
  val doubleToTest: Double = 100.123

  "double" should "pass test with given tolerance" in {
    assert(doubleToTest === 100.122 +- 0.01)
  }
}
