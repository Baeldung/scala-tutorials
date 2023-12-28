package com.baeldung.datavalidation

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import cats.data.NonEmptyChain
import cats.data.Validated.Invalid

class DataValidationSuite
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks:

  "Version1 Scholarship" should "produce a Left[NonEmptyChain[String]]" in {
    import Version1.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe Left(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version2 Scholarship" should "produce a Invalid[NonEmptyChain[String]]" in {
    import Version2.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe Invalid(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version3 Scholarship" should "produce a Invalid[NonEmptyChain[ScholarshipValidationError]]" in {
    import Version3.{Scholarship}
    import Utilities2.ScholarshipValidationError.{
      AgeValidationError,
      CgpaValdiationError
    }
    Scholarship("Uganda", 23, 2.5) shouldBe Invalid(
      NonEmptyChain.of(AgeValidationError, CgpaValdiationError)
    )
  }
