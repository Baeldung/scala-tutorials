package com.baeldung.scala.zio.json

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import zio.json.*

class EncodingSpec extends AnyWordSpec with Matchers {
  "zio-json" should {
    "encode a case class to JSON" in {
      Start(100).toJson shouldBe """{"timeout":100}"""
    }

    "encode an ADT to JSON" in {
      (Start(100): Command).toJson shouldBe """{"Start":{"timeout":100}}"""
      (Stop: Command).toJson shouldBe """{"Stop":{}}"""
    }

    "encode Stop to JSON" in {
      implicit val encoder: JsonEncoder[Stop.type] =
        implicitly[JsonEncoder[String]].contramap(_.toString())

      (Start(100): Command).toJson shouldBe """{"Start":{"timeout":100}}"""
      Stop.toJson shouldBe """"Stop""""
    }

    "use a discriminator" in {
      (Start2(
        100
      ): Command2).toJson shouldBe """{"type":"Start2","timeout":100}"""
      (Stop2: Command2).toJson shouldBe """{"type":"Stop2"}"""
    }
  }
}
