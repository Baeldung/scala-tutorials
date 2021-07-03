package com.baeldung.scala3.implicits.comparison.scala3

case class Second(value: Int)

object TimeUtil {
  def doSomethingWithProcessingTime(sec: Second): String = s"${sec.value} seconds"
}

object ImplicitConversion {
  given Conversion[Int, Second] = Second(_)
}

object Usage {
  import ImplicitConversion.given
  val processingTime = 100
  //auto conversion from Int to Second using given
  TimeUtil.doSomethingWithProcessingTime(processingTime)
}