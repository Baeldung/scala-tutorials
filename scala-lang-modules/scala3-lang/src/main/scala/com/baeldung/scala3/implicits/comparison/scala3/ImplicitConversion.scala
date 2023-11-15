package com.baeldung.scala3.implicits.comparison.scala3

case class Second(value: Int)

object TimeUtil {
  def doSomethingWithProcessingTime(sec: Second): String =
    s"${sec.value} seconds"
}

object ImplicitConversion {
  given Conversion[Int, Second] = Second(_)
}
