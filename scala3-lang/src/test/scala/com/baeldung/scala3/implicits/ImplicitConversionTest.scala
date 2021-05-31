package com.baeldung.scala3.implicits

import com.baeldung.scala3.implicits.ImplicitConversion._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate
import scala.language.implicitConversions

class ImplicitConversionTest extends AnyWordSpec with Matchers {

  "Given implicit conversion from String to Int, square" should {
    "square input type of string" in {
      given Conversion[String, Int] = Integer.parseInt(_)

      square("4") shouldBe 16
    }

  }
}
