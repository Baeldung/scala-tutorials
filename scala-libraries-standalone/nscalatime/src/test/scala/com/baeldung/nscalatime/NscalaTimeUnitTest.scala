package com.baeldung.nscalatime

import com.github.nscala_time.time.Imports.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.Tables.*
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.prop.TableFor1
import java.util.Date
import java.util.Calendar
import com.github.nscala_time.time.DurationBuilder
import org.joda.time.format.DateTimeFormatter
import NScalaTime.*

class NScalaTimeUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks:

  "DateTime checks" should "pass as DateTime objects" in:
    val dateTimeObjects: TableFor1[DateTime] =
      Table(
        "datetimeobjects",
        parsedDate,
        parsedDate2,
        convertTo,
        st2,
        st3,
        st4,
        less5,
        changeTZ,
        chrono,
        st5
      )

    forAll(dateTimeObjects) { n =>
      n shouldBe an[DateTime]
    }

  "jdkDate" should "pass as a Date objects" in:
    jdkDate shouldBe an[Date]

  "Calendar checks" should "pass as Calendar objects" in:
    val calendarObjects: TableFor1[Calendar] =
      Table(
        "calendarobjects",
        jdkCal,
        cal
      )

    forAll(calendarObjects) { n =>
      n shouldBe an[Calendar]
    }

  "strings checks" should "pass as String objects" in:
    val stringObjects: TableFor1[String] =
      Table(
        "stringsobjects",
        shortText,
        asText,
        monthOfYearString,
        monthOfYearStringFrench,
        era,
        year2,
        centOfEra,
        yrOfEra,
        yrOfCent,
        monthOfYr,
        dayOfYr,
        dayOfMonth,
        dayOfWeek2,
        hrOfDay,
        minOfHr,
        secOfMin
      )

    forAll(stringObjects) { n =>
      n shouldBe an[String]
    }

  "boolean checks" should "pass as Boolean objects" in:
    val booleanObjects: TableFor1[Boolean] =
      Table(
        "booleanobjects",
        isLeapYear,
        compare
      )

    forAll(booleanObjects) { n =>
      n shouldBe an[Boolean]
    }

  "nowToTomorrow" should "pass as an Interval object" in:
    nowToTomorrow shouldBe an[Interval]

  "addVarious" should "pass as a Period object" in:
    addVarious shouldBe an[Period]

  "sampleDuration" should "pass as a DurationBuilder object" in:
    sampleDuration shouldBe an[DurationBuilder]

  "ethioYear" should "pass as an Int object" in:
    ethioYear shouldBe an[Int]

  "fmt" should "pass as a DateTimeFormatter object" in:
    fmt shouldBe an[DateTimeFormatter]
