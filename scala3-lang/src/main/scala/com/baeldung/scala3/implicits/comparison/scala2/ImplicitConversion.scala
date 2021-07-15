package com.baeldung.scala3.implicits.comparison.scala2

case class Second(value: Int)

object TimeUtil {
  def doSomethingWithProcessingTime(sec: Second): String =
    s"${sec.value} seconds"
}

object ImplicitConversion {
  implicit def intToSecond(value: Int): Second = Second(value)
}
