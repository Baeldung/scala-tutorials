package com.baeldung.date

import java.util.GregorianCalendar
import scala.util.Try
import scala.util.matching.Regex

class DateParser {
  def simpleParse(dateString: String): Option[GregorianCalendar] = {
    dateString.split("/").toList match {
      case yyyy :: mm :: dd :: Nil =>
        Try(
          new GregorianCalendar(
            yyyy.toInt,
            mm.toInt - 1,
            dd.toInt - 1
          )
        ).toOption
      case _ => None
    }
  }

  def regexParse(
    regex: Regex,
    dateString: String
  ): Option[GregorianCalendar] = {
    val groupsIteratorOption = Try(
      regex.findAllIn(dateString).matchData
    ).toOption
    groupsIteratorOption
      .map(_.next())
      .flatMap(iterator =>
        if (iterator.groupCount < 3) None
        else
          Some(
            new GregorianCalendar(
              iterator.group(1).toInt,
              iterator.group(2).toInt - 1,
              iterator.group(3).toInt - 1
            )
          )
      )
  }
}
