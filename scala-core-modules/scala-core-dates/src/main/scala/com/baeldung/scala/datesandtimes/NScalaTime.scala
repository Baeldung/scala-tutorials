package com.baeldung.scala.datesandtimes

import com.github.nscala_time.time.Imports._
import org.joda.time.DateTime
import org.joda.time.format.PeriodFormat

object NScalaTime extends App {

  def measureElapsedTimePeriod(
    processStart: DateTime,
    processEnd: DateTime
  ): Period = {
    val elapsed: Interval = processStart to processEnd
    elapsed.toPeriod
  }

  val processStart: DateTime = DateTime.now()
  // process execution
  val processEnd: DateTime = processStart + (1.hours + 10.minutes + 5.seconds)
  val elapsed: Period = measureElapsedTimePeriod(processStart, processEnd)
  println(
    elapsed.toString(PeriodFormat.getDefault)
  ) // Prints "1 hour, 10 minutes and 5 seconds"
}
