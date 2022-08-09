package com.baeldung.circe

import io.circe.Decoder.Result
import io.circe._
import io.circe.parser._
import io.circe.syntax._

object JSONConversions {
  val stringJson: String =
    """ {
      |"name": "Baeldung",
      |"language": "Scala"
      |}
      |""".stripMargin
  val invalidStringJson = "Baeldung"

  val invalidParse = parse(invalidStringJson)
  val parseResult = parse(stringJson)

  parseResult match {
    case Left(failure) => println("Inputted JSON failed")
    case Right(json)   => println("JSON conversion succeeded")
  }

  val json: Json = parseResult.getOrElse(null)

  val otherJson: Json = List(1, 2, 3).asJson

  val jsonAsList: Result[List[Int]] = otherJson.as[List[Int]]

  val jsonAsMap = json.as[Map[String, String]]
}
