package com.baeldung.scala.firstmatching

import scala.annotation.tailrec

object Finder {
  implicit class Wrapper[T](val sequence: Seq[T]) extends AnyVal {
    @tailrec
    final def findMatch(condition: T => Boolean): Option[T] = {
      if (sequence.isEmpty) None
      else if (condition(sequence.head)) Some(sequence.head)
      else sequence.tail.findMatch(condition)
    }
  }
}
