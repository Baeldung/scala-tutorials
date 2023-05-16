package com.baeldung.scala.firstmatching

object Finder {
  implicit class Wrapper[T](val sequence: Seq[T]) extends AnyVal {
    def findMatch(condition: T => Boolean): Option[T] = {
      sequence.find(condition)
    }
  }
}
