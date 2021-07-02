package com.baeldung.scala3.implicits.comparison.scala2

object Extension {
  implicit class IntExtension(value: Int) {
    def toSecond() = Second(value)
  }
}

object ExtensionExample {

  import Extension._
  val second: Second = 100.toSecond()
  TimeUtil.doSomethingWithProcessingTime(second)

}
