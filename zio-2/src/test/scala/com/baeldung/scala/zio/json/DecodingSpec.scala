package com.baeldung.scala.zio.json

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import zio.json.*

class DecodingSpec extends AnyWordSpec with Matchers {
  "zio-json" should {
    "decode a JSON object to a case class" in {
      """{"timeout":789}""".fromJson[Start] shouldBe Right(Start(789))
    }

    "return a decoding error if the JSON is not correct" in {
      """{"duration":789}""".fromJson[Start] shouldBe Left(".timeout(missing)")
    }

    "decode a JSON object with extra fields to a case class" in {
      """{"timeout":789, "extra": "field"}""".fromJson[Start] shouldBe Right(
        Start(789)
      )
    }

    "decode an ADT to JSON" in {
      """{"Start":{"timeout":100}}""".fromJson[Command] shouldBe Right(
        Start(100)
      )
      """{"Stop":{}}""".fromJson[Command] shouldBe Right(Stop)
      """{"Kill":{"reason":"Random reason","force":false}}"""
        .fromJson[Command] shouldBe Right(
        Kill("Random reason", false)
      )
    }

    "decode Stop to JSON" in {
      implicit val decoder: JsonDecoder[Stop.type] =
        implicitly[JsonDecoder[String]].map(_ => Stop)

      """{"Start":{"timeout":100}}""".fromJson[Command] shouldBe Right(
        Start(100)
      )
      """"Stop"""".fromJson[Stop.type] shouldBe Right(Stop)
      """{"Kill":{"reason":"Random reason","force":false}}"""
        .fromJson[Command] shouldBe Right(
        Kill("Random reason", false)
      )
    }

    "use a discriminator" in {
      """{"type":"Start2","timeout":100}""".fromJson[Command2] shouldBe Right(
        Start2(100)
      )
      """{"type":"Stop2"}""".fromJson[Command2] shouldBe Right(Stop2)
      """{"type":"Kill2","reason":"Random reason","force":false}"""
        .fromJson[Command2] shouldBe Right(
        Kill2("Random reason", false)
      )
    }

    "fail if there's no discriminator" in {
      """{"timeout":100}""".fromJson[Command2] shouldBe Left(
        "(missing hint 'type')"
      )
    }
  }
}
