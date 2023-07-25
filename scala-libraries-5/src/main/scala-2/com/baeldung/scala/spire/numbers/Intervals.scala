package com.baeldung.scala.spire.numbers

import spire.implicits._
import spire.math._

object Intervals extends App {
  private val interval1 = Interval.closed(1, 4)
  private val interval2 = Interval.open(2, 7)

  private val union = interval1.union(interval2)
  private val intersection = interval1.intersect(interval2)
  val contains = intersection.contains(3)
  private val isSubset = intersection.isSubsetOf(interval2)

  println(s"""Union Interval = ${union.toString}""")
  println(s"""Intersection Interval = ${intersection.toString}""")
  println(s"""isSubset Interval = $isSubset""")

}
