package com.baeldung.scala.scalatest.runner

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.Tag

/** This test class is a just a sample test to show scalatest Runner features.
  * There is no complex logic behind these tests.
  */

object BooleanTests extends Tag("BooleanTests")

class ScalaTestRunnerTests extends AnyWordSpec with Matchers {

  "Scalatest runnner" should {
    "convert string true to boolean true" taggedAs (BooleanTests) in {
      "true".toBoolean shouldBe true
    }

    "convert string false to boolean false" taggedAs (BooleanTests) in {
      "false".toBoolean shouldBe false
    }

    "trim the spaces to the right and left" in {
      " with spaces ".trim() shouldBe "with spaces"
    }
  }
}
