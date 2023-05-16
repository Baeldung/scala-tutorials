package com.baeldung.scala.differencedates

import org.scalatest.wordspec.AnyWordSpec
import com.baeldung.scala.differencedates.DifferenceBetweenDates._
import org.scalatest.matchers.should.Matchers

import java.time.temporal.ChronoUnit
import java.time.{LocalDate, Period}

class DifferenceBetweenDatesTest extends AnyWordSpec with Matchers {

  val testDate1 = LocalDate.parse("2020-01-01")
  val testDate2 = LocalDate.parse("2020-01-04")
  val testDate3 = LocalDate.parse("2010-01-01")

  "usingEpochDay" should {
    "return days between 2 dates" in {
      val dateDiff = usingEpochDay(testDate1, testDate2)
      dateDiff shouldBe 3
    }
    "return negative number for date with from date in past" in {
      val dateDiff = usingEpochDay(testDate1, testDate3)
      dateDiff shouldBe -3652
    }
  }

  "gettingPeriod" should {
    "return difference between dates in days" in {
      val dateDiff = gettingPeriod(testDate1, testDate2)
      dateDiff shouldBe Period.ofDays(3)
    }

    "return difference between dates in years and days" in {
      val dateDiff = gettingPeriod(testDate3, testDate2)
      dateDiff shouldBe Period.ofYears(10).plusDays(3)
    }
  }

  "usingUnits" should {
    "return difference in days" in {
      implicit val unit = ChronoUnit.DAYS
      val dateDiff = usingUnits(testDate1, testDate2)
      dateDiff shouldBe 3
    }

    "return difference in months" in {
      implicit val unit = ChronoUnit.MONTHS
      val dateDiff = usingUnits(testDate3, testDate1)
      dateDiff shouldBe 120
    }

    "return difference in years" in {
      implicit val unit = ChronoUnit.YEARS
      val dateDiff = usingUnits(testDate3, testDate1)
      dateDiff shouldBe 10
    }

    "when given years unit and difference of 10y 3d, should only return 10" in {
      implicit val unit = ChronoUnit.YEARS
      val dateDiff = usingUnits(testDate3, testDate2)
      dateDiff shouldBe 10
    }
  }
}
