package com.baeldung.scala3.implicits.comparison.scala2

object Extension {
  implicit class IntExtension(value: Int) {
    def toSecond() = Second(value)
  }

}

object ExtensionExample {

  import Extension._

  TimeUtil.doSomethingWithSeconds(100.toSecond())

}
