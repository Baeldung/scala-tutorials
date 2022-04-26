package com.baeldung.scala.cakepattern

import com.baeldung.scala.magnetpattern.MagnetPattern
import org.scalatest.{FlatSpec, Matchers}

class MagnetPatternTest extends FlatSpec with Matchers{
  "combineElements" should "be able to combine the elements in a collection" in {
    val intList = List(1,2,3,4)
    val strList = List("a","b","c")

    MagnetPattern.combineElements(intList) shouldEqual 10
    MagnetPattern.combineElements(strList) shouldEqual "abc"
  }
}