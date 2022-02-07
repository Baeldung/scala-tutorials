package com.baeldung.date

import scala.util.Try
import scala.util.matching.Regex

/**
  * Simple case class to represent the elements from a date.
  *
  * @param year year of the date
  * @param month month of the date (0-11)
  * @param day day of the date (0-30)
  */
case class DateElements(year: Int, month: Int, day: Int)

class DateParser {
  def simpleParse(dateString: String): Option[DateElements] =
    dateString.split("/").toList match {
      case yyyy :: mm :: dd :: Nil =>
        Try(
          DateElements(
            year = yyyy.toInt,
            month = mm.toInt - 1,
            day = dd.toInt - 1
          )
        ).toOption
      case _ => None
    }

  def regexParse(regex: Regex, dateString: String): Option[DateElements] = {
    val groupsIteratorOption = Try(
      regex.findAllIn(dateString).matchData
    ).toOption
    groupsIteratorOption
      .map(_.next())
      .flatMap(iterator =>
        if (iterator.groupCount < 3) None
        else
          Some(
            DateElements(
              year = iterator.group(1).toInt,
              month = iterator.group(2).toInt - 1,
              day = iterator.group(3).toInt - 1
            )
          )
      )
  }
}
