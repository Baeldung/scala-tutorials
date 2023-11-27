package com.baeldung.dataValidation

import cats.syntax.all.*
import cats.effect.{IOApp, IO, ExitCode}
import cats.data.{EitherNel, EitherNec, ValidatedNec, Validated}
import cats.data.Validated.{Invalid, Valid}

object Version1:
  case class Scholarship(country: String, age: Int, cgpa: Double)
  val countries = List("uganda", "kenya", "tanzania")
  private def checkCountry(value: String): Boolean =
    if (countries.contains(value.toLowerCase)) true else false
  private def checkAge(value: Int): Boolean =
    if (value >= 25) true else false
  private def checkCgpa(value: Double): Boolean =
    if (value >= 3.0) true else false
  class ScholarshipValidationError
    extends Exception("Failed to create scholarship")
  def getScholarship(country: String, age: Int, cgpa: Double): Scholarship =
    if (checkCountry(country) && checkAge(age) && checkCgpa(cgpa))
      Scholarship(country, age, cgpa)
    else throw new ScholarshipValidationError

object Utilities:
  case class Country(value: String)
  object Country:
    private val countries = List("uganda", "kenya", "tanzania")
    def apply(value: String): Option[Country] =
      Some(value)
        .filter(v => countries.contains(v.toLowerCase))
        .map(new Country(_))

  case class Age(value: Int)
  object Age:
    def apply(value: Int): Option[Age] =
      Some(value).filter(_ >= 25).map(new Age(_))

  case class Cgpa(value: Double)
  object Cgpa:
    def apply(value: Double): Option[Cgpa] =
      Some(value).filter(_ >= 3.0).map(new Cgpa(_))

object Version2:
  import Utilities.*

  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): Option[Scholarship] =
      for
        country <- Country(value1)
        age <- Age(value2)
        cgpa <- Cgpa(value3)
      yield new Scholarship(country, age, cgpa)

object Version3:
  import Utilities.*

  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): Either[String, Scholarship] =
      for
        country <- Country(value1).toRight("Invalid Country")
        age <- Age(value2).toRight("Invalid Age")
        cgpa <- Cgpa(value3).toRight("Invalid Cgpa")
      yield new Scholarship(country, age, cgpa)

object Version4:
  import Utilities.*

  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): Either[List[String], Scholarship] =
      (
        Country(value1).toRight(List("Invalid Country")),
        Age(value2).toRight(List("Invalid Age")),
        Cgpa(value3).toRight(List("Invalid Cgpa"))
      ).parMapN(
        Scholarship.apply
      )

object Version5:
  import Utilities.*

  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): EitherNel[String, Scholarship] =
      (
        Country(value1).toRightNel("Invalid Coutry"),
        Age(value2).toRightNel("Invalid Age"),
        Cgpa(value3).toRightNel("Invalid Cgpa")
      ).parMapN(
        Scholarship.apply
      )

object Version6:
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

object Version7:
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
        new Country(value),
        CountryValidationError
      )

  case class Age(value: Int)
  object Age:
    def apply(value: Int): ValidatedNec[ScholarshipValidationError, Age] =
      Validated.condNec(
        value >= 35,
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

object Version8:
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

object Version9:
  import Utilities2.*

  case object CountryNotSupportted extends ScholarshipValidationError:
    override val errMsg: String = "Your country is not supported this year"

  case class Scholarship(country: Country, age: Age, cgpa: Cgpa)
  object Scholarship:
    def excludeCountry(
      country: Country,
      value: String
    ): ValidatedNec[ScholarshipValidationError, Country] =
      Validated
        .condNec(
          country.value != value.toLowerCase,
          country,
          CountryNotSupportted
        )

    def apply(
      value1: String,
      value2: Int,
      value3: Double
    ): ValidatedNec[ScholarshipValidationError, Scholarship] =
      (
        Country(value1)
          .andThen(excludeCountry(_, "kenya")),
        Age(value2),
        Cgpa(value3)
      ).mapN(
        Scholarship.apply
      )

object Version10:
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

  extension (s: ValidatedNec[ScholarshipValidationError, Scholarship])
    def excludeCountry(
      value: String
    ): ValidatedNec[ScholarshipValidationError, Scholarship] =
      case object CountryNotSupportted extends ScholarshipValidationError:
        override val errMsg: String = "Your country is not supported this year"
      s.andThen(scholarship =>
        Validated
          .condNec(
            scholarship.country.value != value.toLowerCase,
            scholarship,
            CountryNotSupportted
          )
      )

object Version11:
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

  sealed trait QualificationError extends ScholarshipValidationError:
    val errMsg: String
  object QualificationError:
    case object MastersQualificationError extends QualificationError:
      override val errMsg: String =
        "A minimum of a bachelors is required for this scholarship"

  import QualificationError.*

  enum Qualification:
    case Diploma, Bachelors, Masters, Phd

  case class MastersQualification(value: Qualification)
  object MastersQualification:
    def apply(
      value: String
    ): ValidatedNec[QualificationError, MastersQualification] =
      Validated.condNec(
        value.toLowerCase == "bachelors" || value.toLowerCase == "masters",
        if (value.toLowerCase == "bachelors")
          new MastersQualification(Qualification.Bachelors)
        else
          new MastersQualification(Qualification.Masters),
        MastersQualificationError
      )

  case class MastersScholarship(
    qualification: MastersQualification,
    scholarship: Scholarship
  )

  object MastersScholarship:
    def apply(
      qualification: String,
      country: String,
      age: Int,
      cgpa: Double
    ): ValidatedNec[ScholarshipValidationError, MastersScholarship] =
      Scholarship(country, age, cgpa).map2(
        MastersQualification(qualification)
      ) { (s, m) =>
        new MastersScholarship(m, s)
      }

object BaeldungDv extends IOApp.Simple:
  import Version11.*
  def run: IO[Unit] =
    MastersScholarship("diploma", "rwanda", 23, 2.0) match
      case Valid(x)   => IO.println(x)
      case Invalid(y) => IO.println(y.toChain)
