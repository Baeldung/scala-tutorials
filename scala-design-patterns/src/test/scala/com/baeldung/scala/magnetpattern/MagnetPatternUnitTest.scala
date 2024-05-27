package com.baeldung.scala.magnetpattern

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MagnetPatternUnitTest extends AnyFlatSpec with Matchers {
  "combineElements" should "be able to combine the elements in a collection" in {
    val intList = List(1, 2, 3, 4)
    val strList = List("a", "b", "c")

    MagnetPattern.combineElements(intList) shouldEqual 10
    MagnetPattern.combineElements(strList) shouldEqual "abc"
  }
}
