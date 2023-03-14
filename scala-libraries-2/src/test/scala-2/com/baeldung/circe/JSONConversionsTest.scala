package com.baeldung.circe

import com.baeldung.circe.JSONConversions.{stringJson, invalidStringJson}
import io.circe.parser.parse
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class JSONConversionsTest extends AnyFlatSpec with should.Matchers {
  "Convert Json String" should "successfully" in {
    val parseResult = parse(stringJson)
    assert(parseResult.isRight)
  }

  "Convert Json String" should "fail return a Json" in {
    val parseResult = parse(invalidStringJson)
    assert(parseResult.isLeft)
  }
}
