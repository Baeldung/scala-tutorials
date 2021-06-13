package com.baeldung.scala.datesandtimes

import java.util.Locale

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object JodaTime extends App{


  def parseDate(dateStr:String, pattern:String):DateTime={
    val formatter = DateTimeFormat.forPattern(pattern)
    val dateTime = formatter.parseDateTime(dateStr)
    dateTime
  }
  println(parseDate("2021-06-13","yyyy-MM-dd").dayOfWeek().getAsText(Locale.getDefault()))  // Prints "Sunday"

  val dateStr = "2021-06-13"
  val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
  val dateTime:DateTime = formatter.parseDateTime(dateStr)
  println(dateTime.dayOfWeek().getAsText(Locale.getDefault()))  // Prints "Sunday"

}
