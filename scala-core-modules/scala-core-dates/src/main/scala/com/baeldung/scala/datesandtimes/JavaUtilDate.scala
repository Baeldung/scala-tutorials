package com.baeldung.scala.datesandtimes

import java.text.SimpleDateFormat
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object JavaUtilDate extends App {

  implicit val ec: ExecutionContext =
    scala.concurrent.ExecutionContext.Implicits.global

  val format: ThreadLocal[SimpleDateFormat] =
    new ThreadLocal[SimpleDateFormat] {
      override def initialValue = {
        new SimpleDateFormat("yyyy-MM-dd")
      }
    }
  def toDate(
    dateStr: String,
    format: ThreadLocal[SimpleDateFormat]
  ): java.util.Date = format.get().parse(dateStr)

  val r1 = Future {
    val dateStr = "2020-07-01"
    toDate(dateStr, format)
  }

  val r2 = Future {
    val dateStr = "2020-08-21"
    toDate(dateStr, format)
  }

  val date1 = Await.result(r1, Duration.Inf)
  val date2 = Await.result(r2, Duration.Inf)

  println(s"$date1, $date2") // Prints "Wed Jul 01 2020, Fri Aug 21 2020"
}
