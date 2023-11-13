package com.baeldung.scala.either

import org.scalatest.funsuite.AnyFunSuite

class EitherTest extends AnyFunSuite {
  test("Pattern matching can be used to extract the value of an Either") {
    val e: Either[String, Int] = Right(5)
    e match {
      case Right(i) => assert(i == 5)
      case Left(_)  => fail("The Either should be a Right")
    }
  }

  test("merge can be used to extract the value of an Either") {
    val e: Either[Int, Int] = Right(5)

    assert(e.merge == 5)
  }

  test("getOrElse can be used to extract the value of an Either") {
    val e: Either[String, Int] = Left("Some error")
    val defaultValue = 10
    assert(e.getOrElse(defaultValue) == 10)
  }

  test("fold can be used to extract the value of an Either") {
    val e: Either[String, Int] = Left("Some error")
    val defaultValue = 10

    assert(e.fold(_ => defaultValue, identity) == 10)
  }

  test("fold can be used to map the left projection of an Either") {
    case class MyError(msg: String)

    val e: Either[String, Int] = Left("Some error")

    assert(
      e.fold(error => Left(MyError(error)), identity) == Left(
        MyError("Some error")
      )
    )
  }

  test("map can be used to map the right projection of an Either") {
    val e: Either[String, Int] = Right(5)

    assert(e.map(_ + 10) == Right(15))
  }

  test("An Option should become an Either") {
    val opt: Option[Int] = Option(10)
    val either: Either[Unit, Int] = opt.toRight(())

    assert(either.isRight)
  }

  test("An Either should become an Option") {
    assert(Right(10).toOption.isDefined)
    assert(Left(10).toOption.isEmpty)
  }
}
