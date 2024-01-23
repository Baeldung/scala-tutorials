package com.baeldung.futurelist

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import scala.concurrent.{Future, Await}

class FutureFailuresListSpec extends AnyFlatSpec with Matchers {
  val testList = List(
    Future.successful("success 1"),
    Future.failed(Exception("failure 1")),
    Future.successful("success 2"),
    Future.failed(Exception("failure 2")),
    Future.successful("success 3")
  )

  "getSuccessful usingTransform" should "return all successful futures" in {
    val result = Await.result(
      FutureFailuresList.getSuccessful(testList)(
        FutureFailuresList.usingTransform
      ),
      2.seconds
    )
    result shouldBe List(
      "success 1",
      "success 2",
      "success 3"
    )
  }

  "getFailures usingTransform" should "return all failed futures" in {
    val result = Await.result(
      FutureFailuresList.getFailures(testList)(
        FutureFailuresList.usingTransform
      ),
      2.seconds
    )
    result.map(_.getMessage) shouldBe List(
      "failure 1",
      "failure 2"
    )
  }

  "getSuccessful usingRecover" should "return all successful futures" in {
    val result = Await.result(
      FutureFailuresList.getSuccessful(testList)(
        FutureFailuresList.usingRecover
      ),
      2.seconds
    )
    result shouldBe List(
      "success 1",
      "success 2",
      "success 3"
    )
  }

  "getFailures usingRecover" should "return all failed futures" in {
    val result = Await.result(
      FutureFailuresList.getFailures(testList)(FutureFailuresList.usingRecover),
      2.seconds
    )
    result.map(_.getMessage) shouldBe List(
      "failure 1",
      "failure 2"
    )
  }
}
