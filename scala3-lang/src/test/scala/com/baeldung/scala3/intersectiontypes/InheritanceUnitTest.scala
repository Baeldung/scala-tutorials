package com.baeldung.scala3.intersectiontypes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import com.baeldung.scala3.intersectiontypes.Inheritance._

class InheritanceUnitTest extends AnyWordSpec with Matchers {

  "Inheritance" should {
    "work as Intersection type alternative" in {
      fixDress(DressFixer) shouldBe ()
    }
  }
}
