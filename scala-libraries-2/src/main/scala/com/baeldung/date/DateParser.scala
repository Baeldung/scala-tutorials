package com.baeldung.date

/**
  * Simple case class to represent the elements from a date.
  *
  * @param year year of the date
  * @param month month of the date (0-11)
  * @param day day of the date (0-30)
  */
case class DateElements(year: Int, month: Int, day: Int)

class DateParser {
  def simpleParse(dateString: String): Option[DateElements] = ???
}
