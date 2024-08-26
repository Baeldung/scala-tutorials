package com.baeldung.nscalatime

import com.github.nscala_time.time.Imports.*
import java.util.Date
import java.util.Calendar
import java.util.Locale
import org.joda.time.chrono.EthiopicChronology
import org.joda.time.DateTime.Property
import com.github.nscala_time.time.DurationBuilder
import org.joda.time.format.DateTimeFormatter

object NScalaTime:
  // working with DateTime
  val st: DateTime = DateTime.now()
  val parsedDate: DateTime = DateTime.parse("2024-08-07")
  val parsedDate2: DateTime = DateTime.parse("2024")

  // JDK interoperability
  val jdkDate: Date = new Date()
  val convertTo: DateTime = new DateTime(jdkDate)

  val jdkCal: Calendar = Calendar.getInstance()
  val st2: DateTime = new DateTime(jdkCal)

  val st3: DateTime = new DateTime
  val cal: Calendar = st3.gregorianCalendar

  // querrying Datetime
  val dayOfWeek: Property = new DateTime().dayOfWeek()
  val shortText: String = dayOfWeek.asShortText
  val asText: String = dayOfWeek.asText
  val monthOfYearString: String = DateTime.now().monthOfYear().getAsText()
  val monthOfYearStringFrench: String =
    DateTime.now().monthOfYear().getAsShortText(Locale.FRENCH)
  val isLeapYear: Boolean = DateTime.now().year().isLeap()
  val nowToTomorrow: Interval = DateTime.now().to(DateTime.tomorrow())

  // Accessing fields
  // Date fields
  val st4: DateTime = new DateTime()
  val era: String = st4.era.asText
  val year2: String = st4.year.asText
  val centOfEra: String = st4.centuryOfEra.asText
  val yrOfEra: String = st4.yearOfEra.asText
  val yrOfCent: String = st4.yearOfCentury.asText
  val monthOfYr: String = st4.monthOfYear.asText
  val dayOfYr: String = st4.dayOfYear.asText
  val dayOfMonth: String = st4.dayOfMonth.asText
  val dayOfWeek2: String = st4.dayOfWeek.asText
  // Time fields
  val hrOfDay: String = st4.hourOfDay.asText
  val minOfHr: String = st4.minuteOfHour.asText
  val secOfMin: String = st4.secondOfMinute.asText

  // Manipulating datetimes
  val compare: Boolean = DateTime.now() < DateTime.lastMonth()
  val addVarious: Period = 2.month + 5.hours + 6.millis
  val less5: DateTime = DateTime.now() - 5.days
  val sampleDuration: DurationBuilder = 4.hours + 30.minutes + 10.seconds
  val datetimebeforeDuration: DateTime = sampleDuration.before(DateTime.now())
  val changeTZ: DateTime = less5.withZone(DateTimeZone.forID("Africa/Kampala"))
  val chrono: DateTime = less5.withChronology(EthiopicChronology.getInstance())
  val ethioYear: Int = chrono.getYear()

  // formatters
  val fmt: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
  val st5: DateTime = fmt.parseDateTime("31-07-2024")

import NScalaTime.*
@main def sTime(): Unit =
  println(st5)
