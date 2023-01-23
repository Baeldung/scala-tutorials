package com.baeldung.scala3.implicits

import com.baeldung.scala3.implicits.ExtensionMethod.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate
import scala.language.implicitConversions

class ExtensionMethodUnitTest extends AnyWordSpec with Matchers {
  "Unsing extension method for Int type" should {
    "provide that function as a method for Int values" in {
      5.square shouldBe 25
    }
  }
}
