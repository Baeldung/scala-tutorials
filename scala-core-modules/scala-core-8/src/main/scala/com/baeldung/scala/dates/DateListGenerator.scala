package com.baeldung.scala.dates

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import scala.annotation.tailrec

object DateListGenerator {

  def recursiveDateList(startDate: LocalDate, endDate: LocalDate) = {
    @tailrec
    def findNextDate(
      currentDate: LocalDate,
      accDates: List[LocalDate]
    ): List[LocalDate] = {
      if (currentDate.isAfter(endDate)) {
        accDates
      } else {
        findNextDate(currentDate.plusDays(1), accDates :+ currentDate)
      }
    }
    findNextDate(startDate, Nil)
  }

  def foldLeftDateList(startDate: LocalDate, endDate: LocalDate) = {
    val noOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1
    (0 until noOfDays.toInt).foldLeft(List.empty[LocalDate]) { (acc, incr) =>
      acc :+ startDate.plusDays(incr)
    }
  }

  def iteratorDateList(startDate: LocalDate, endDate: LocalDate) = {
    Iterator
      .iterate(startDate)(_.plusDays(1))
      .takeWhile(!_.isAfter(endDate))
      .toList
  }
}
