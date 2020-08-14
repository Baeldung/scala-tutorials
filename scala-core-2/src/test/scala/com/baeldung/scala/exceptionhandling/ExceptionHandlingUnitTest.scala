package com.baeldung.scala.exceptionhandling

import com.baeldung.scala.exceptionhandling.ExceptionHandling.{DivideByZero, divideWithEither, divideWithOption, divideWithTry}
import org.scalatest.{FlatSpec, Matchers}

import scala.util.{Failure, Success}

class ExceptionHandlingTest extends FlatSpec with Matchers {

  "divideWithOption" should "return Some when divisor not zero" in {
    divideWithOption(10, 2) should be(Some(5))
  }

  it should "return None when divisor is zero" in {
    divideWithOption(10, 0) should be(None)
  }

  "divideWithTry" should "return Success when divisor not zero" in {
    divideWithTry(10, 2) should be(Success(5))
  }

  it should "return Failure when divisor is zero" in {
    divideWithTry(10, 0) should be(Failure(DivideByZero()))
  }

  "divideWithEither" should "return Right when divisor not zero" in {
    divideWithEither(10, 2) should be(Right(5))
  }

  it should "return Left when divisor is zero" in {
    divideWithEither(10, 0) should be(Left("Can't divide by zero"))
  }

}
