package com.baeldung.scala.abstractandtraits

import org.scalatest.WordSpec
import org.scalatest.Matchers._

class CatTest extends WordSpec {

  "A cat" should {
    "have a name" in {
      val subject = new Cat("Tigrou")

      subject.name should be("Tigrou")
    }

    "be able to scratch" in {
      val subject = new Cat("Tigrou") with Claws

      subject.scratch() should be("Screetch")
    }
  }
}
