package com.baeldung.scala.date

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.temporal.ChronoField
import java.time.{ZoneId, ZonedDateTime}
import java.util.Calendar
import scala.util.Try

class DateParserUnitTest extends AnyWordSpec with Matchers {
  val parser = new DateParser

  "a simple parser" should {
    "retrieve elements of a string when it matches the predefined format" in {
      val maybeDate = parser.simpleParse("2022/02/14")
      // the format is "yyyy/mm/dd"
      assert(maybeDate.isDefined)
      assert(maybeDate.get.get(Calendar.YEAR) == 2022)
      // in the case class, elements are 0-based:
      assert(maybeDate.get.get(Calendar.MONTH) == 2 - 1)
      assert(maybeDate.get.get(Calendar.DAY_OF_MONTH) == 14 - 1)
    }
    "fail to retrieve elements if date includes unexpected time" in {
      val maybeDateTime = parser.simpleParse("2022/02/14T20:30:00")
      assert(maybeDateTime.isEmpty)
    }
  }

  "a regular expression parser" should {
    // Note that this is a very naive regular expression,
    // just to show a point. Don't use it for real.
    val naiveDateRegExp = "^([0-9]{4})/([0-1]?[0-9])/([1-3]?[0-9]).*".r

    "retrieve date elements when it matches the regular expression" in {
      val maybeDate = parser.regexParse(naiveDateRegExp, "2022/02/14")
      assert(maybeDate.isDefined)
      assert(maybeDate.get.get(Calendar.YEAR) == 2022)
      // in the case class, elements are 0-based:
      assert(maybeDate.get.get(Calendar.MONTH) == 2 - 1)
      assert(maybeDate.get.get(Calendar.DAY_OF_MONTH) == 14 - 1)
    }
    "retrieve date elements even if it includes unexpected elements (time)" in {
      val maybeDate = parser.regexParse(naiveDateRegExp, "2022/02/14T20:30:00")
      assert(maybeDate.isDefined)
      assert(maybeDate.get.get(Calendar.YEAR) == 2022)
      // in the case class, elements are 0-based:
      assert(maybeDate.get.get(Calendar.MONTH) == 2 - 1)
      assert(maybeDate.get.get(Calendar.DAY_OF_MONTH) == 14 - 1)
    }
  }

  "a library-based parser" should {
    "retrieve date elements when a complex date/time string is passed" in {
      val attemptedParse =
        Try(ZonedDateTime.parse("2022-02-14T20:30:00.00Z[Europe/Paris]"))
      assert(attemptedParse.isSuccess)
      // This parsing is different in JDK 8 and 11 due to a bug in JDK 8
      // https://bugs.openjdk.org/browse/JDK-8066982
      val zdt = attemptedParse.get
      assert(zdt.get(ChronoField.YEAR) == 2022)
      assert(zdt.get(ChronoField.MONTH_OF_YEAR) == 2)
      assert(zdt.get(ChronoField.DAY_OF_MONTH) == 14)
      assert(zdt.get(ChronoField.HOUR_OF_DAY) == 21) // 21 due to timezone DST
      assert(zdt.get(ChronoField.MINUTE_OF_HOUR) == 30)
      assert(zdt.getZone == ZoneId.of("Europe/Paris"))
    }
    "fail to retrieve date elements when an invalid date/time is passed" in {
      val attemptedParse =
        Try(ZonedDateTime.parse("2022-02-14"))
      assert(attemptedParse.isFailure)
      assert(
        attemptedParse.failed.get.getMessage.contains("could not be parsed")
      )
    }
  }
}
