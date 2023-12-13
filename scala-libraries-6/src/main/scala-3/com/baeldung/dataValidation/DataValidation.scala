package com.baeldung.dataValidation

import cats.effect.{IOApp, IO, ExitCode}
import cats.data.{EitherNec, ValidatedNec, Validated}
import cats.data.Validated.{Valid, Invalid}
import cats.syntax.all.*

object Utilities:
  case class Country(value: String)
  object Country:
    private val countries = List("uganda", "kenya", "tanzania")
    def apply(value: String): Option[Country] =
      Some(value)
        .filter(v => countries.contains(v.toLowerCase))
        .map(c => new Country(c.toLowerCase))

  case class Age(value: Int)
  object Age:
    def apply(value: Int): Option[Age] =
      Some(value).filter(_ >= 25).map(new Age(_))

  case class Cgpa(value: Double)
  object Cgpa:
    def apply(value: Double): Option[Cgpa] =
      Some(value).filter(_ >= 3.0).map(new Cgpa(_))

object Version1:
  import Utilities.*

  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): EitherNec[String, Scholarship] =
      (
        Country(value1).toRightNec("Invalid Coutry"),
        Age(value2).toRightNec("Invalid Age"),
        Cgpa(value3).toRightNec("Invalid Cgpa")
      ).parMapN(
        Scholarship.apply
      )

object Version2:
  import Utilities.*

  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): ValidatedNec[String, Scholarship] =
      (
        Country(value1).toValidNec("Invalid Coutry"),
        Age(value2).toValidNec("Invalid Age"),
        Cgpa(value3).toValidNec("Invalid Cgpa")
      ).mapN(
        Scholarship.apply
      )

object Utilities2:
  sealed trait ScholarshipValidationError:
    val errMsg: String
  object ScholarshipValidationError:
    case object CountryValidationError extends ScholarshipValidationError:
      override val errMsg: String = "Must come from Uganda, Kenya or Tanzania."
    case object AgeValidationError extends ScholarshipValidationError:
      override val errMsg: String = "Must be 25 years or more."
    case object CgpaValdiationError extends ScholarshipValidationError:
      override val errMsg: String = "CGPA must be 3.0 or more"

  import ScholarshipValidationError.*

  case class Country(value: String)
  object Country:
    private val countries = List("uganda", "kenya", "tanzania")
    def apply(
      value: String
    ): ValidatedNec[ScholarshipValidationError, Country] =
      Validated.condNec(
        countries.contains(value.toLowerCase),
        new Country(value.toLowerCase),
        CountryValidationError
      )

  case class Age(value: Int)
  object Age:
    def apply(value: Int): ValidatedNec[ScholarshipValidationError, Age] =
      Validated.condNec(
        value >= 25,
        new Age(value),
        AgeValidationError
      )

  case class Cgpa(value: Double)
  object Cgpa:
    def apply(value: Double): ValidatedNec[ScholarshipValidationError, Cgpa] =
      Validated.condNec(
        value >= 3.0,
        new Cgpa(value),
        CgpaValdiationError
      )

object Version3:
  import Utilities2.*
  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): ValidatedNec[ScholarshipValidationError, Scholarship] =
      (
        Country(value1),
        Age(value2),
        Cgpa(value3)
      ).mapN(
        Scholarship.apply
      )

object BaeldungDv extends IOApp.Simple:
  import Version1.*
  def run: IO[Unit] =
    Scholarship("Uganda", 23, 2.5) match
      case Right(x) => IO.println(x)
      case Left(y)  => IO.println(y.toChain)
// Chain(Invalid Age, Invalid Cgpa)
