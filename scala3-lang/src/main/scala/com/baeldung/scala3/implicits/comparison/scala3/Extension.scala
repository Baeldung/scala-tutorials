package com.baeldung.scala3.implicits.comparison.scala3

object Extension {
  extension(sec: Int) def toSecond() = Second(sec)
}

object ExtensionExample {

  import Extension._

  TimeUtil.doSomethingWithSeconds(100.toSecond())

}
