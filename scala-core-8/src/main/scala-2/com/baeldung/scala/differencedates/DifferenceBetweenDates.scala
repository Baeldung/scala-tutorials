package com.baeldung.scala.differencedates

import java.time.temporal.ChronoUnit
import java.time.{LocalDate, Period}

object DifferenceBetweenDates {

  def usingEpochDay(fromDate: LocalDate, toDate: LocalDate) = {
    toDate.toEpochDay - fromDate.toEpochDay
  }

  def gettingPeriod(fromDate: LocalDate, toDate: LocalDate) = {
    Period.between(fromDate, toDate)
  }

  def usingUnits(fromDate: LocalDate, toDate: LocalDate)(implicit
    chronoUnit: ChronoUnit
  ) = {
    chronoUnit.between(fromDate, toDate)
  }

}
