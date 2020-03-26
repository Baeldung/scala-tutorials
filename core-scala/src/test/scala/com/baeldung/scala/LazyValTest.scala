package com.baeldung.scala

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, _}
import com.baeldung.scala

class LazyValTest extends FunSuite {

  test("should enter deadlock when lazy val have cyclic dependency") {
    //given
    val result = Future.sequence(Seq(
      Future {
        FirstObj.start
      },
      Future {
        SecondObj.initialState
      }))

    //when
    val thrown = intercept[Exception] {
      Await.result(result, 5.second)
    }

    //then
    thrown.getMessage shouldBe "Futures timed out after [5 seconds]"
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
    result shouldBe List(25,36)
  }
}
