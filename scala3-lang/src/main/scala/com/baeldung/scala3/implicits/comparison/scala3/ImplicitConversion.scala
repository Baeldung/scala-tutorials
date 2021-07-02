package com.baeldung.scala3.implicits.comparison.scala3

case class Second(value: Int)

object TimeUtil {
  def doSomethingWithSeconds(sec: Second): String = "Value is:"+sec.value.toString
}
object ImplicitConversion {
  
  given Conversion[Int, Second] = Second(_)

  val processingTime = 100

  //auto conversion from Int to Second using intToSecond()
  val result = TimeUtil.doSomethingWithSeconds(processingTime)
  println(result)

}
