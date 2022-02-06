package com.baeldung.date

import scala.util.Try

/**
  * Simple case class to represent the elements from a date.
  *
  * @param year year of the date
  * @param month month of the date (0-11)
  * @param day day of the date (0-30)
  */
case class DateElements(year: Int, month: Int, day: Int)

class DateParser {
  def simpleParse(dateString: String): Option[DateElements] = {
    dateString.split("/").toList match {
      case yyyy :: mm :: dd :: Nil =>
        Try(DateElements(yyyy.toInt, mm.toInt - 1, dd.toInt - 1)).toOption
      case _ => None
    }
  }
}
