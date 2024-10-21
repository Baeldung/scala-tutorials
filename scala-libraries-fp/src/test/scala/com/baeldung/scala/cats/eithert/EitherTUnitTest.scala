package com.baeldung.scala.cats.eithert

import cats.data.EitherT
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future
import scala.util.{Failure, Try}

class EitherTUnitTest extends AsyncFlatSpec with Matchers {

  it should "map over EitherT instance with Future" in {
    val response: EitherT[Future, String, Int] =
      EitherT(Future.successful(Right(100)))
    response.map(_ * 5).value.map(_ shouldBe Right(500))
  }

  it should "map over EitherT instance with Try" in {
    val opValue: Try[Either[String, Int]] = Try(Right(100))
    val response: EitherT[Try, String, Int] = EitherT(opValue)
    val mappedValue: EitherT[Try, String, Int] = response.map(_ * 5)
    val underlying: Try[Either[String, Int]] = mappedValue.value
    underlying shouldBe Try(Right(500))
  }

  it should "map over error" in {
    val response: EitherT[Try, String, Int] =
      EitherT(Try(Left("invalid number!")))
    response.leftMap(_.toUpperCase).value shouldBe Try(Left("INVALID NUMBER!"))
  }

  it should "be able to map on both side of either using bimap" in {
    val success: EitherT[Try, String, Int] = EitherT(Try(Right(100)))
    val biMappedSuccess = success.bimap(e => e.toUpperCase, s => s * 5)
    biMappedSuccess.value shouldBe Try(Right(500))
    val error: EitherT[Try, String, Int] = EitherT(Try(Left("error")))
    val biMappedError = error.bimap(e => e.toUpperCase, s => s * 5)
    biMappedError.value shouldBe Try(Left("ERROR"))
  }

  it should "compose multiple EitherT instances together" in {
    val num1: EitherT[Try, String, Int] = EitherT.right(Try(100))
    val num2: EitherT[Try, String, Int] = EitherT.liftF(Try(2))
    val divRes: EitherT[Try, String, Int] = for {
      n1 <- num1
      n2 <- num2
      div = n1 / n2
    } yield div
    divRes.value shouldBe Try(Right(50))
  }

  it should "compose multiple EitherT instances together when there is an error" in {
    val num1: EitherT[Try, String, Int] = EitherT(Try(Right(100)))
    val num2: EitherT[Try, String, Int] = EitherT.left(Try("zero"))
    val divRes: EitherT[Try, String, Int] = for {
      n1 <- num1
      n2 <- num2
      div = n1 / n2
    } yield div
    divRes.value shouldBe Try(Left("zero"))
  }

  it should "handle Try failure" in {
    val failedOp: Try[Either[String, Int]] =
      Try(throw new Exception("Operation failed!"))
    val response: EitherT[Try, String, Int] = EitherT(failedOp)
    response.value.isFailure shouldBe true
  }

  it should "short circuit if one of the Try fails" in {
    val num1: EitherT[Try, String, Int] = EitherT(Try(Right(100)))
    val num2: EitherT[Try, String, Int] =
      EitherT(Try(throw new Exception("Operation failed!")))
    val divRes: EitherT[Try, String, Int] = for {
      n1 <- num1
      n2 <- num2
      div = n1 / n2
    } yield div
    divRes.value.isFailure shouldBe true
  }

  it should "lift an either into eitherT" in {
    val either: Either[String, Int] = Right(100)
    val eitherT: EitherT[Try, String, Int] = EitherT.fromEither(either)
    eitherT.value.get shouldBe Right(100)
  }

  it should "lift an option into eitherT" in {
    val opt: Option[Int] = Some(100)
    val eitherT: EitherT[Try, String, Int] = EitherT.fromOption(opt, "EMPTY")
    eitherT.value.get shouldBe Right(100)
  }

}
