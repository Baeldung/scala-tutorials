package com.baeldung.scala.datesandtimes
import java.time.LocalDate
import org.scalatest.{FlatSpec, Matchers}

class JavaTimeUnitTest extends FlatSpec with Matchers {
  "parseDate" should "be able to parse a date-string using the pattern-input and return a LocalDate object" in {
    val dateStr = "2021-06-13"
    val pattern = "yyyy-MM-dd"
    val expectedDate = LocalDate.of(2021, 6, 13);
    JavaTime.parseDate(dateStr, pattern) shouldEqual expectedDate
  }
}
