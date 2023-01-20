package com.baeldung.scala.datesandtimes
import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

class JodaTimeUnitTest extends FlatSpec with Matchers {
  "parseDate" should "be able to parse a date-string using the pattern-input and return a DateTime object" in {
    val dateStr = "2021-06-13"
    val pattern = "yyyy-MM-dd"
    val expectedDate = new DateTime(2021, 6, 13, 0, 0, 0, 0);
    JodaTime.parseDate(dateStr, pattern) shouldEqual expectedDate
  }
}
