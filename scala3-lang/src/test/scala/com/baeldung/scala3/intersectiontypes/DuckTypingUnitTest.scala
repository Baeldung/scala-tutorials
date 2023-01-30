package com.baeldung.scala3.intersectiontypes

import com.baeldung.scala3.intersectiontypes.DuckTyping.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.reflect.Selectable.reflectiveSelectable

class DuckTypingUnitTest extends AnyWordSpec with Matchers {

  "DuckTyping" should {
    "work as Intersection type alternative" in {
      cutPaper(new Scissors()) shouldBe ()
      cutPaper(new Knife()) shouldBe ()
    }
  }
}
