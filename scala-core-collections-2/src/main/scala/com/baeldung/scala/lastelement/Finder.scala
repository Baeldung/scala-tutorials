package com.baeldung.scala.lastelement

object Finder {
  implicit class Wrapper[T](val list: List[T]) extends AnyVal {
    def lastWhen(predicate: T => Boolean): Int = 0
  }
}
