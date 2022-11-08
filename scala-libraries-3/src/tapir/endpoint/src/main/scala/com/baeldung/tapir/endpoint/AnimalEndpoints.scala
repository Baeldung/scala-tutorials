package com.baeldung.tapir.endpoint

import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import io.circe.generic.auto._
import sttp.model.StatusCode
import sttp.tapir._

case class ErrorResponse(message: String)

case class Kitten(id: Long, name: String, gender: String, ageInDays: Int)

object AnimalEndpoints {

  val kittens: Endpoint[Unit, Unit, String, List[Kitten], Any] = endpoint
    .get
    .in("kitten")
    .errorOut(stringBody)
    .out(jsonBody[List[Kitten]])

  val kittensWithQueryParam: Endpoint[Unit, Int, (StatusCode, ErrorResponse), (StatusCode, List[Kitten]), Any] = endpoint
    .get
    .in("kitten")
    .in(query[Int]("ageInDays"))
    .errorOut(statusCode)
    .errorOut(jsonBody[ErrorResponse])
    .out(statusCode)
    .out(jsonBody[List[Kitten]])

  val kittensWithPathParam: Endpoint[Unit, String, String, Option[Kitten], Any] = endpoint
    .get
    .in("kitten")
    .in(path[String]("name"))
    .errorOut(stringBody)
    .out(jsonBody[Option[Kitten]])

  val kittensPost: Endpoint[Unit, Kitten, (StatusCode, ErrorResponse), (StatusCode, Kitten), Any] = endpoint
    .post
    .in("kitten")
    .in(jsonBody[Kitten])
    .errorOut(statusCode)
    .errorOut(jsonBody[ErrorResponse])
    .out(statusCode)
    .out(jsonBody[Kitten])

  val kittensPut: Endpoint[Unit, Kitten, (StatusCode, ErrorResponse), (StatusCode, Kitten), Any] = endpoint
    .put
    .in("kitten")
    .in(jsonBody[Kitten])
    .errorOut(statusCode)
    .errorOut(jsonBody[ErrorResponse])
    .out(statusCode)
    .out(jsonBody[Kitten])


  val kittensDelete: Endpoint[Unit, Long, (StatusCode, ErrorResponse), (StatusCode, Kitten), Any] = endpoint
    .delete
    .in("kitten")
    .in(path[Long]("id"))
    .errorOut(statusCode)
    .errorOut(jsonBody[ErrorResponse])
    .out(statusCode)
    .out(jsonBody[Kitten])
}
