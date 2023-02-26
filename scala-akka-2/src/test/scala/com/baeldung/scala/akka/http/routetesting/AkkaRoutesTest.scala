package com.baeldung.scala.akka.http.routetesting

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.{HttpMethods, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.baeldung.scala.akka.http.routetesting.AkkaRoutes.routes
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AkkaRoutesTest extends AnyFlatSpec with Matchers with ScalatestRouteTest {
  "a" should "b" in {
    Get("/hello") ~> routes ~> check {
      status shouldEqual StatusCodes.OK
      responseAs[String] shouldBe "Hello World!"
    }
  }
  "a" should "c" in {
    Get("/hello-name?name=John") ~> routes ~> check {
      status shouldEqual StatusCodes.OK
      responseAs[String] shouldBe "Hello John"
    }
  }
  "a" should "d" in {
    Get("/hello-user") ~> RawHeader("X-User-Id", "123") ~> routes ~> check {
      status shouldEqual StatusCodes.OK
    }
    Get("/hello-user") ~> addHeader("X-User-Id", "123") ~> routes ~> check {
      status shouldEqual StatusCodes.OK
    }
  }
  "a" should "e" in {
    new RequestBuilder(HttpMethods.POST)("/hello-name", "Bob") ~> routes ~> check {
      responseAs[String] shouldBe "Hello Bob"
    }
  }
}