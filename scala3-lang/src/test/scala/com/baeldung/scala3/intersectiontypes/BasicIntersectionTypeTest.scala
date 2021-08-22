package com.baeldung.scala3.intersectiontypes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import com.baeldung.scala3.intersectiontypes.BasicIntersectionType._

class BasicIntersectionTypeTest extends AnyWordSpec with Matchers {

  "Intersection Types" should {
    "check & is commutative" in {
      fixDressOne(DressFixer) shouldBe ()
      fixDressTwo(DressFixer) shouldBe ()
    }

    "use linearization to decide method override" in {
      generateNumbers(NumberGenerator21) shouldBe (2)
      generateNumbers(NumberGenerator12) shouldBe (1)
    }

    "make paper cutter work" in {
       cutPaper(PaperCutter) shouldBe ()
    }
  }
}
