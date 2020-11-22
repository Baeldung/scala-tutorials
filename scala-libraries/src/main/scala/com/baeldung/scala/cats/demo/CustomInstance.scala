package com.baeldung.scala.cats.demo

import java.util.Date

import cats.Show
import cats.implicits.toShow

object CustomInstance extends App {
  implicit val customShow: Show[Date] =
    new Show[Date] {
      def show(date: Date): String =
        s"${date.getTime}ms since the epoch."
    }

  val shownDate: String = new Date().show
  println(shownDate)
}