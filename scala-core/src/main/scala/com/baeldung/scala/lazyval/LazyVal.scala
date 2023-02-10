package com.baeldung.scala.lazyval

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

class LazyVal {

  var age = 27

  lazy val getMemberNo = {
    age = age + 1
    8
  }
}

object FirstObj {
  lazy val initialState = 42
  lazy val start = SecondObj.initialState
}

object SecondObj {
  lazy val initialState = FirstObj.initialState
}

object Deadlock extends App {
  def run = {
    val result = Future.sequence(
      Seq(
        Future {
          FirstObj.start
        },
        Future {
          SecondObj.initialState
        }
      )
    )
    Await.result(result, 10.second)
  }

  run
}

object LazyValStore {

  lazy val squareOf5 = square(5)
  lazy val squareOf6 = square(6)

  def square(n: Int): Int = n * n
}

object SequentialLazyVals extends App {
  def run = {
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
    Await.result(futures, 5.second)
  }

  run.foreach(println)
}
