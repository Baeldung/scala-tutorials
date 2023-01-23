package com.baeldung.scala.datesandtimes
import com.github.nscala_time.time.Imports._
import org.joda.time.DateTime
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NScalaTimeUnitTest extends AnyFlatSpec with Matchers{
  "measureElapsedTimePeriod" should "be able to calculate the time period between starting and ending DateTime objects and  return a Period object" in {

    val processStart:DateTime = DateTime.now()
    // process execution
    val processEnd:DateTime = processStart + (1.hours + 10.minutes + 5.seconds)

    val expectedElapsedTimePeriod:Period = Period.parse("PT1H10M5S")
    NScalaTime.measureElapsedTimePeriod(processStart, processEnd) shouldEqual expectedElapsedTimePeriod
  }
}
