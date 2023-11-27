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
    import com.baeldung.dataValidation.Version1.{
      getScholarship,
      ScholarshipValidationError
    }
    assertThrows[ScholarshipValidationError] {
      getScholarship("Uganda", 23, 2.5)
    }
  }

  "Version2 Scholarship" should "produce a None" in {
    import com.baeldung.dataValidation.Version2.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals None
  }

  "Version3 Scholarship" should "produce a Left[String]" in {
    import com.baeldung.dataValidation.Version3.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left("Invalid Age")
  }

  "Version4 Scholarship" should "produce a Left[List[String]]" in {
    import com.baeldung.dataValidation.Version4.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left(
      List("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version5 Scholarship" should "produce a Left[NonEmptyList[String]]" in {
    import com.baeldung.dataValidation.Version5.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left(
      NonEmptyList.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version6 Scholarship" should "produce a Left[NonEmptyChain[String]]" in {
    import com.baeldung.dataValidation.Version6.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Left(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version7 Scholarship" should "produce a Invalid[NonEmptyChain[String]]" in {
    import com.baeldung.dataValidation.Version7.{Scholarship}
    Scholarship("Uganda", 23, 2.5) equals Invalid(
      NonEmptyChain.of("Invalid Age", "Invalid Cgpa")
    )
  }

  "Version8 Scholarship" should "produce a Invalid[NonEmptyChain[ScholarshipValidationError]]" in {
    import com.baeldung.dataValidation.Version8.{Scholarship}
    import com.baeldung.dataValidation.Utilities2.ScholarshipValidationError.{
      AgeValidationError,
      CgpaValdiationError
    }
    Scholarship("Uganda", 23, 2.5) equals Invalid(
      NonEmptyChain.of(AgeValidationError, CgpaValdiationError)
    )
  }

  "Version9 Scholarship" should "exclude kenya to produce a Invalid[NonEmptyChain[ScholarshipValidationError]]" in {
    import com.baeldung.dataValidation.Version9.{Scholarship}
    import com.baeldung.dataValidation.Utilities2.ScholarshipValidationError.{
      CountryValidationError,
      CgpaValdiationError
    }
    Scholarship("Kenya", 26, 2.5) equals Invalid(
      NonEmptyChain.of(CountryValidationError, CgpaValdiationError)
    )
  }

  "Version10 Scholarship" should "exclude kenya to produce a Invalid[NonEmptyChain[ScholarshipValidationError]] via extension" in {
    import com.baeldung.dataValidation.Version10.{Scholarship}
    import com.baeldung.dataValidation.Utilities2.ScholarshipValidationError.{
      CountryValidationError,
      CgpaValdiationError
    }
    Scholarship("Kenya", 26, 2.5).excludeCountry("Kenya") equals Invalid(
      NonEmptyChain.of(CountryValidationError, CgpaValdiationError)
    )
  }

  "Version11 MastersScholarship" should "produce a Invalid[NonEmptyChain[QualificationError | ScholarshipValidationError]]" in {
    import com.baeldung.dataValidation.Version11.MastersScholarship
    import com.baeldung.dataValidation.Version11.QualificationError.MastersQualificationError
    import com.baeldung.dataValidation.Utilities2.ScholarshipValidationError.{
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
