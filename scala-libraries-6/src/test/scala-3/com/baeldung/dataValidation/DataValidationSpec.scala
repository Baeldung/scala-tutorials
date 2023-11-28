package com.baeldung.dataValidation

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import cats.data.{NonEmptyList, NonEmptyChain}
import cats.data.Validated.Invalid

class BaeldungDvSuite
    extends AnyFlatSpec
    with Matchers
    with TableDrivenPropertyChecks:
  "Version1 getScholarship" should "produce a ScholarshipValidationError" in {
    import Version1.{getScholarship, ScholarshipValidationError}
    assertThrows[ScholarshipValidationError] {
      getScholarship("Uganda", 23, 2.5)
    }
  }

  "Version2 Scholarship" should "produce a None" in {
    import Version2.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe None
  }

  "Version3 Scholarship" should "produce a Left[String]" in {
    import Version3.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe Left("Invalid Age")
  }

  "Version4 Scholarship" should "produce a Left[List[String]]" in {
    import Version4.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe Left(
      List("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version5 Scholarship" should "produce a Left[NonEmptyList[String]]" in {
    import Version5.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe Left(
      NonEmptyList.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version6 Scholarship" should "produce a Left[NonEmptyChain[String]]" in {
    import Version6.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe Left(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version7 Scholarship" should "produce a Invalid[NonEmptyChain[String]]" in {
    import Version7.{Scholarship}
    Scholarship("Uganda", 23, 2.5) shouldBe Invalid(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version8 Scholarship" should "produce a Invalid[NonEmptyChain[ScholarshipValidationError]]" in {
    import Version8.{Scholarship}
    import Utilities2.ScholarshipValidationError.{
      AgeValidationError,
      CgpaValdiationError
    }
    Scholarship("Uganda", 23, 2.5) shouldBe Invalid(
      NonEmptyChain.of(AgeValidationError, CgpaValdiationError)
    )
  }

  "Version9 Scholarship" should "exclude kenya to produce a Invalid[NonEmptyChain[ScholarshipValidationError]]" in {
    import Version9.{Scholarship, CountryNotSupportted}
    import Utilities2.ScholarshipValidationError.CgpaValdiationError
    Scholarship("Kenya", 26, 2.5) shouldBe Invalid(
      NonEmptyChain.of(CountryNotSupportted, CgpaValdiationError)
    )
  }

  "Version10 MastersScholarship" should "produce a Invalid[NonEmptyChain[QualificationError | ScholarshipValidationError]]" in {
    import Version10.MastersScholarship
    import Version10.QualificationError.MastersQualificationError
    import Utilities2.ScholarshipValidationError.CgpaValdiationError

    MastersScholarship("diploma", "Kenya", 26, 2.5) shouldBe Invalid(
      NonEmptyChain.of(
        CgpaValdiationError,
        MastersQualificationError
      )
    )
  }
