package com.baeldung.scala.implicitconversions

import org.scalatest.{Matchers, WordSpec}

class LengthUnitTest extends WordSpec with Matchers {

  "Implicit conversion" should {
    import scala.language.implicitConversions
    "convert meters into centimeters" in {
      implicit def meters2centimeters(meters: Meters): Centimeters =
        Centimeters(meters.value * 100)

      val centimeters: Centimeters = Meters(2.5)

      centimeters shouldBe Centimeters(250)
    }
    "convert kilometers into meters" in {
      implicit val kilometers2meters: Kilometers => Meters =
        kilometers => Meters(kilometers.value * 1000)

      val meters: Meters = Kilometers(2.5)

      meters shouldBe Meters(2500)
    }
    "add extension method" in {
      class LengthSyntax(value: Double) {
        def centimeters = Centimeters(value)
        def meters = Meters(value)
        def kilometers = Kilometers(value)
      }

      implicit def double2richSyntax(value: Double): LengthSyntax =
        new LengthSyntax(value)

      val length: Double = 2.5

      length.centimeters shouldBe Centimeters(length)
      length.meters shouldBe Meters(length)
      length.kilometers shouldBe Kilometers(length)
    }
  }
}
