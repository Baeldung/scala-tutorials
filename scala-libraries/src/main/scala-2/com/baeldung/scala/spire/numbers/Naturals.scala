package com.baeldung.scala.spire.numbers

import spire.implicits._
import spire.math._

object Naturals extends App {

  // Naturals

  val a: Natural = Natural(42)
  val b: Natural = Natural(10)
  private val comparison: Int = a.compare(b)
  private val isALessThanB: Boolean = a.<=(b)
  private val isAGreaterThanB: Boolean = a.>=(b)
  println(s"""Natural Comparison = $comparison""")
  println(s"""Natural isALessThanB = $isALessThanB""")
  println(s"""Natural isAGreaterThanB = $isAGreaterThanB""")
}
