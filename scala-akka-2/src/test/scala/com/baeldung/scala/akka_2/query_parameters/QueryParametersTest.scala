package com.baeldung.scala.akka_2.query_parameters

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class QueryParametersTest
  extends AnyWordSpec
  with ScalatestRouteTest
  with Matchers {

  val route: Route =
    pathPrefix("params") {
      concat(
        path("with-parameters") {
          get {
            parameters(Symbol("page").as[String], Symbol("size").as[String]) {
              (page, size) =>
                complete(
                  HttpEntity(
                    ContentTypes.`text/plain(UTF-8)`,
                    s"parameters passed: page=$page and size=$size"
                  )
                )
            }
          }
        },
        path("with-extract") {
          get {
            extract(_.request.uri.query()) { params =>
              complete(
                HttpEntity(
                  ContentTypes.`text/plain(UTF-8)`,
                  s"parameters passed: page=${params.get("page").get} and size=${params.get("size").get}"
                )
              )
            }
          }
        }
      )
    }

  "The get with params" should {
    "return text with right query params values" in {
      Get("/params/with-parameters?page=0&size=23") ~> Route.seal(
        route
      ) ~> check {
        responseAs[String] shouldEqual "parameters passed: page=0 and size=23"
      }
    }
  }

  "The get with extract" should {
    "return text with right query params values" in {
      Get("/params/with-extract?page=0&size=23") ~> Route.seal(route) ~> check {
        responseAs[String] shouldEqual "parameters passed: page=0 and size=23"
      }
    }
  }
}
