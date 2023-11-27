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
    Scholarship("Uganda", 23, 2.5) equals None
  }

  "Version3 Scholarship" should "produce a Left[String]" in {
    import Version3.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left("Invalid Age")
  }

  "Version4 Scholarship" should "produce a Left[List[String]]" in {
    import Version4.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left(
      List("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version5 Scholarship" should "produce a Left[NonEmptyList[String]]" in {
    import Version5.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left(
      NonEmptyList.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version6 Scholarship" should "produce a Left[NonEmptyChain[String]]" in {
    import Version6.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version7 Scholarship" should "produce a Invalid[NonEmptyChain[String]]" in {
    import Version7.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Invalid(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version8 Scholarship" should "produce a Invalid[NonEmptyChain[ScholarshipValidationError]]" in {
    import Version8.{Scholarship}
    import Utilities2.ScholarshipValidationError.{
      AgeValidationError,
      CgpaValdiationError
    }
    Scholarship("Uganda", 23, 2.5) equals Invalid(
      NonEmptyChain.of(AgeValidationError, CgpaValdiationError)
    )
  }

  "Version9 Scholarship" should "exclude kenya to produce a Invalid[NonEmptyChain[ScholarshipValidationError]]" in {
    import Version9.{Scholarship}
    import Utilities2.ScholarshipValidationError.{
      CountryValidationError,
      CgpaValdiationError
    }
    Scholarship("Kenya", 26, 2.5) equals Invalid(
      NonEmptyChain.of(CountryValidationError, CgpaValdiationError)
    )
  }

  "Version10 Scholarship" should "exclude kenya to produce a Invalid[NonEmptyChain[ScholarshipValidationError]] via extension" in {
    import Version10.{Scholarship}
    import Utilities2.ScholarshipValidationError.{
      CountryValidationError,
      CgpaValdiationError
    }
    Scholarship("Kenya", 26, 2.5).excludeCountry("Kenya") equals Invalid(
      NonEmptyChain.of(CountryValidationError, CgpaValdiationError)
    )
  }

  "Version11 MastersScholarship" should "produce a Invalid[NonEmptyChain[QualificationError | ScholarshipValidationError]]" in {
    import Version11.MastersScholarship
    import Version11.QualificationError.MastersQualificationError
    import Utilities2.ScholarshipValidationError.{
      CountryValidationError,
      CgpaValdiationError
    }
    MastersScholarship("diploma", "Kenya", 26, 2.5) equals Invalid(
      NonEmptyChain.of(
        MastersQualificationError,
        CountryValidationError,
        CgpaValdiationError
      )
    )
  }
