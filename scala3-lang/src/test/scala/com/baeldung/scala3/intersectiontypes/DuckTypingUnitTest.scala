package com.baeldung.scala3.intersectiontypes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import com.baeldung.scala3.intersectiontypes.DuckTyping._
import reflect.Selectable.reflectiveSelectable

class DuckTypingUnitTest extends AnyWordSpec with Matchers {

  "DuckTyping" should {
    "work as Intersection type alternative" in {
        cutPaper(new Scissors()) shouldBe ()
        cutPaper(new Knife()) shouldBe ()
    }
  }
}
