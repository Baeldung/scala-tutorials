package com.baeldung.scala.akka.http.routetesting

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.{ContentTypes, HttpMethods, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.baeldung.scala.akka.http.routetesting.AkkaRoutes.routes
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AkkaRoutesTest extends AnyFlatSpec with Matchers with ScalatestRouteTest {
  "/hello" should "return Hello World!" in {
    Get("/hello") ~> routes ~> check {
      status shouldEqual StatusCodes.OK
      responseAs[String] shouldBe "Hello World!"
    }
  }
  "/hello-name" should "return hello to the name provided" in {
    val testName = "John"
    Get(s"/hello-name?name=$testName") ~> routes ~> check {
      status shouldEqual StatusCodes.OK
      responseAs[String] shouldBe s"Hello $testName"
      headers.length shouldBe 0
      contentType shouldBe ContentTypes.`text/plain(UTF-8)`
    }
  }
  "/hello-user" should "return 200 for X-User-Id of 123" in {
    Get("/hello-user") ~> RawHeader("X-User-Id", "123") ~> routes ~> check {
      status shouldEqual StatusCodes.OK
    }
  }

  "/hello-user" should "return 401 for X-User-Id of 456" in {
    Get("/hello-user") ~> addHeader("X-User-Id", "456") ~> routes ~> check {
      status shouldEqual StatusCodes.Unauthorized
      responseAs[String] shouldBe "Incorrect user"
    }
  }

  "hello-name" should "return hello to name provided in request body" in {
    val testName = "Sarah"
    new RequestBuilder(HttpMethods.POST)(
      "/hello-name",
      testName
    ) ~> routes ~> check {
      status shouldEqual StatusCodes.OK
      responseAs[String] shouldBe s"Hello $testName"
    }
  }
}
