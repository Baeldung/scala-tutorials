package com.baeldung.scala

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, _}
import com.baeldung.scala

class LazyValUnitTest extends FunSuite {

  test("lazy val is computed only once") {
    //given
    val lazyVal = new LazyVal
    lazyVal.getMemberNo //initialize the lazy val
    lazyVal.age shouldBe 28

    //when
    lazyVal.getMemberNo

    //then
    lazyVal.age shouldBe 28
  }

  test("lazy vals should execute sequentially in an instance ") {
    //given
    val futures = Future.sequence(Seq(
      Future {
        scala.LazyValStore.squareOf5
      },
      Future {
        scala.LazyValStore.squareOf6
      }))

    //when
    val result = Await.result(futures, 5.second)

    //then
    result should contain(25)
    result should contain(36)
  }
}
