package com.baeldung.scala3.implicits.comparison.scala3

import scala.concurrent.ExecutionContext

object Extension {
  extension(sec: Int) def toSecond() = Second(sec)
}

object NumericExtensions {
  extension [T:Numeric](v1: T)
    def add(v2: T): T = summon[Numeric[T]].plus(v1,v2)
}

object ExtensionExample extends App {

  import Extension._

  val sec:Second = 100.toSecond()
  TimeUtil.doSomethingWithProcessingTime(sec)

  import NumericExtensions._
  val addInts = 10.add(9)
  val addDoubles = 10.2d.add(0.2d)


}
