package com.baeldung.scala.lazyval

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent._

class LazyValUnitTest extends AnyFunSuite {

  test("lazy val is computed only once") {
    // given
    val lazyVal = new LazyVal
    lazyVal.getMemberNo // initialize the lazy val
    lazyVal.age shouldBe 28

    // when
    lazyVal.getMemberNo

    // then
    lazyVal.age shouldBe 28
  }

  test("lazy vals should execute sequentially in an instance ") {
    // given
    val futures = Future.sequence(
      Seq(
        Future {
          LazyValStore.squareOf5
        },
        Future {
          LazyValStore.squareOf6
        }
      )
    )

    // when
    val result = Await.result(futures, 5.second)

    // then
    result should contain(25)
    result should contain(36)
  }
}
