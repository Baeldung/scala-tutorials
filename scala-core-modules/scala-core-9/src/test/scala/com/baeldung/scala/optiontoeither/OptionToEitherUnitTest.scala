package com.baeldung.scala.optiontoeither

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

sealed trait Error
case object EmptyOptionValue extends Error

class OptionToEitherUnitTest extends AnyWordSpec with Matchers {

  def usingIfElse(option: Option[String]): Either[Error, String] = {
    if (option.isDefined) Right(option.get) else Left(EmptyOptionValue)
  }

  def usingPatternMatch(option: Option[String]): Either[Error, String] = {
    option match {
      case Some(value) => Right(value)
      case None        => Left(EmptyOptionValue)
    }
  }

  def usingToRight(option: Option[String]): Either[Error, String] = {
    option.toRight(EmptyOptionValue)
  }

  def usingCond(option: Option[String]): Either[Error, String] = {
    Either.cond(option.nonEmpty, option.get, EmptyOptionValue)
  }

  def usingFold(option: Option[String]): Either[Error, String] = {
    option.fold(Left(EmptyOptionValue))(Right(_))
  }

  def usingMap(option: Option[String]): Either[Error, String] = {
    option.map(Right(_)).getOrElse(Left(EmptyOptionValue))
  }

  "Option" should {
    "be converted to Either using if else" in {
      val either = usingIfElse(Option("Baeldung"))
      either shouldBe Right("Baeldung")
      val left = usingIfElse(None)
      left shouldBe Left(EmptyOptionValue)
    }

    "be converted to Either using pattern matching" in {
      val either = usingPatternMatch(Option("Baeldung"))
      either shouldBe Right("Baeldung")
      val left = usingPatternMatch(None)
      left shouldBe Left(EmptyOptionValue)
    }

    "be converted to Either using usingToRight" in {
      val either = usingToRight(Option("Baeldung"))
      either shouldBe Right("Baeldung")
      val left = usingToRight(None)
      left shouldBe Left(EmptyOptionValue)
    }

    "be converted to Either using usingCond" in {
      val either = usingCond(Option("Baeldung"))
      either shouldBe Right("Baeldung")
      val left = usingCond(None)
      left shouldBe Left(EmptyOptionValue)
    }

    "be converted to Either using fold" in {
      val either = usingFold(Option("Baeldung"))
      either shouldBe Right("Baeldung")
      val left = usingFold(None)
      left shouldBe Left(EmptyOptionValue)
    }

    "be converted to Either using map" in {
      val either = usingMap(Option("Baeldung"))
      either shouldBe Right("Baeldung")
      val left = usingMap(None)
      left shouldBe Left(EmptyOptionValue)
    }
  }

}
