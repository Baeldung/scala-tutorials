package com.baeldung.scala.datesandtimes
import java.text.SimpleDateFormat
import java.util.Date

import org.scalatest.{FlatSpec, Matchers}

class JavaUtilDateUnitTest extends FlatSpec with Matchers{
  "toDate" should "be able to parse a date-string and return a Date object" in {
    val dateStr = "2021-06-13"
    val format:ThreadLocal[SimpleDateFormat] = new ThreadLocal[SimpleDateFormat]{
      override def initialValue = {
        new SimpleDateFormat("yyyy-MM-dd")
      }
    }
    val expectedDate:Date = new Date(121, 5, 13)
    JavaUtilDate.toDate(dateStr, format) shouldEqual expectedDate
  }
}
