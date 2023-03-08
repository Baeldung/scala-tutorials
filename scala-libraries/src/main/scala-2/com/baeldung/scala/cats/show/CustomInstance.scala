package com.baeldung.scala.cats.show

import java.util.Date

import cats.Show
import cats.implicits.toShow

object CustomInstance extends App {
  implicit val customShow: Show[Date] =
    new Show[Date] {
      def show(date: Date): String =
        s"This year is: ${date.getYear}"
    }

  val shownDate: String = new Date().show
  println(shownDate)
}
