package com.baeldung.scala3.implicits.comparison.scala3

import scala.concurrent.ExecutionContext

object Extension {
  extension(sec: Int) def toSecond() = Second(sec)
}

object NumericExtensions {
  extension [T:Numeric](v1: T)
    def add(v2: T): T = summon[Numeric[T]].plus(v1,v2)
}
