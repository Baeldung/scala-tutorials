package com.baeldung.scala3.intersectiontypes

import com.baeldung.scala3.intersectiontypes.Overloading.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class OverloadingUnitTest extends AnyWordSpec with Matchers {
  "Overloading" should {
    "work as Intersection type alternative" in {
      cutPaper(new Scissors()) shouldBe ()
      cutPaper(new Knife()) shouldBe ()
    }
  }
}
