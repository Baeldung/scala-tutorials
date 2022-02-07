package com.baeldung.date

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DateParserSpec extends AnyWordSpec with Matchers {
  val parser = new DateParser

  "a simple parser" should {
    "retrieve elements of a string when it matches the predefined format" in {
      val maybeDate = parser.simpleParse("2022/02/14")
      // the format is "yyyy/mm/dd"
      assert(maybeDate.isDefined)
      assert(maybeDate.get.year == 2022)
      // in the case class, elements are 0-based:
      assert(maybeDate.get.month == 2 - 1)
      assert(maybeDate.get.day == 14 - 1)
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
      assert(maybeDate.get.year == 2022)
      // in the case class, elements are 0-based:
      assert(maybeDate.get.month == 2 - 1)
      assert(maybeDate.get.day == 14 - 1)
    }
    "retrieve date elements even if it includes unexpected elements (time)" in {
      val maybeDate = parser.regexParse(naiveDateRegExp, "2022/02/14T20:30:00")
      assert(maybeDate.isDefined)
      assert(maybeDate.get.year == 2022)
      // in the case class, elements are 0-based:
      assert(maybeDate.get.month == 2 - 1)
      assert(maybeDate.get.day == 14 - 1)
    }
  }

  "a library-based parser" should {
    "retrieve date elements when a complex date/time string is passed" in {}
    "fail to retrieve date elements when an invalide date/time is passed" in {}
  }
}
