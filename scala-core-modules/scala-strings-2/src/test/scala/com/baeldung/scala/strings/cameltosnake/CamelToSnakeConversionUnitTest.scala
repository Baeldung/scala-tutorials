package com.baeldung.scala.strings.cameltosnake

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class CamelToSnakeConversionUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val table = Table(
    ("input", "expected"),
    ("thisIsCamelCase", "this_is_camel_case"),
    ("isThisCamel?", "is_this_camel?"),
    ("alllower", "alllower"),
    ("thisIsUSA", "this_is_u_s_a"),
    ("xmlHttpRequest", "xml_http_request"),
    ("convertXMLToJSON", "convert_x_m_l_to_j_s_o_n"),
    ("parseHTML", "parse_h_t_m_l"),
    ("classOfT", "class_of_t")
  )
  private val fns = Seq(
    ("usingRegex", CamelToSnakeConversion.usingRegex),
    ("usingFoldLeft", CamelToSnakeConversion.usingFoldLeft),
    ("usingFlatMap", CamelToSnakeConversion.usingFlatMap),
    ("usingPatterMatching", CamelToSnakeConversion.usingPatternMatching),
    ("usingCollect", CamelToSnakeConversion.usingCollect)
  )

  it should "convert camel to snake case" in {
    forAll(table) { (camel, expectedSnake) =>
      fns.map { (name, fn) =>
        withClue("function name: " + name) {
          fn(camel) shouldBe expectedSnake
        }
      }
    }
  }

  it should "handle camel case starting with upper case character" in {
    val output = CamelToSnakeConversion.usingRegex("HelloWorld")
    output.stripPrefix("_") shouldBe "hello_world"
  }

  it should "handle special case" in {
    Seq(
      ("HelloWorld", "hello_world"),
      ("thisIsUSA", "this_is_usa"),
      ("convertXMLToJSON", "convert_xml_to_json"),
      ("xmlToHTTPRequest", "xml_to_http_request")
    ).map { (in, out) =>
      val output = CamelToSnakeConversion.handleAcronymsWithRegex(in)
      output shouldBe out
    }

  }

}
