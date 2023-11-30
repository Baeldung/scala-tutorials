package com.baeldung.scala.dates

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

import java.time.LocalDate

class DateListGeneratorUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val start = LocalDate.parse("2023-10-28")
  private val end = LocalDate.parse("2023-11-03")

  private val expectedResult = List(
    "2023-10-28",
    "2023-10-29",
    "2023-10-30",
    "2023-10-31",
    "2023-11-01",
    "2023-11-02",
    "2023-11-03"
  ).map(LocalDate.parse)

  private val inOutTable = Table(
    ("startDate", "endDate", "expectedOutput", "message"),
    (start, end, expectedResult, "1 week"),
    (start, start, List(start), "start and end as start"),
    (end, end, List(end), "start and end as end"),
    (start, start.minusDays(1), List.empty, "end before start")
  )

  forAll(inOutTable) { (startDate, endDate, expectedDates, prefix) =>

    it should s"[${prefix}] generate a list of dates between a range using recursion" in {
      val recDateList = DateListGenerator.recursiveDateList(startDate, endDate)
      assert(recDateList == expectedDates)
    }

    it should s"[${prefix}] generate a list of dates between a range using foldLeft" in {
      val foldedDateList =
        DateListGenerator.foldLeftDateList(startDate, endDate)
      assert(foldedDateList == expectedDates)
    }

    it should s"[${prefix}] generate a list of dates between a range using iterator" in {
      val iteratorDates = DateListGenerator.iteratorDateList(startDate, endDate)
      assert(iteratorDates == expectedDates)
    }

    it should s"[${prefix}] generate a list of dates between a range using tabulator" in {
      val tabulateDateList =
        DateListGenerator.tabulateDateList(startDate, endDate)
      assert(tabulateDateList == expectedDates)
    }

    it should s"[${prefix}] generate a list of dates between a range using epoch days" in {
      val datesEpoch = DateListGenerator.dateListEpochDays(startDate, endDate)
      assert(datesEpoch == expectedDates)
    }

    it should s"[${prefix}] generate a list of dates between a range using daysBetween" in {
      val dates = DateListGenerator.dateListDaysBetween(startDate, endDate)
      assert(dates == expectedDates)
    }

  }

}
