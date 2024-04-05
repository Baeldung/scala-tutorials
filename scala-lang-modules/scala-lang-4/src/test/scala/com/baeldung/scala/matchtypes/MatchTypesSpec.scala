package com.baeldung.scala.matchtypes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatchTypesSpec extends AnyWordSpec with Matchers:
  "firstComponentOf" should {
    "return the first digit of an Int" in {
      firstComponentOf(-153) shouldEqual 1
    }

    "return the first char of a String" in {
      firstComponentOf("Baeldung") shouldEqual Some('B')
    }

    "return None if the String is empty" in {
      firstComponentOf("") shouldEqual None
    }

    "return the first element of a Seq" in {
      firstComponentOf(Seq(10, 42)) shouldEqual Some(10)
    }

    "return None if the Seq is empty" in {
      firstComponentOf(Seq.empty[Int]) shouldEqual None
    }

    "also work with other types" in {
      firstComponentOf(1.2f) shouldEqual 1.2f
    }
  }
