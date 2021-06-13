package com.baeldung.scala.datesandtimes
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object JavaTime extends App{

  def parseDate(dateStr:String, pattern:String):LocalDate={
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val localDate = LocalDate.parse(dateStr, formatter)
    localDate
  }

  val dateStr = "2021-06-13"
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//  val localDate = parseDate(dateStr, "yyyy-MM-dd")
  val localDate = LocalDate.parse(dateStr, formatter)

  println(localDate.getDayOfWeek) // prints "SUNDAY"
}
