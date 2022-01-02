package com.baeldung.scala.cats.show

import java.util.Date

import cats.Show
import cats.implicits._
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class CustomInstanceUnitTest extends FlatSpec with Matchers {
  implicit val customShow: Show[Date] =
    (date: Date) => s"This year is: ${date.getYear}"

  val actualDate: String = new Date().show
  val expectedDate: String = s"This year is: ${new Date().getYear}"

  "CustomInstance" should "give custom implementation of show" in {
    assert(actualDate == expectedDate)
  }
}
