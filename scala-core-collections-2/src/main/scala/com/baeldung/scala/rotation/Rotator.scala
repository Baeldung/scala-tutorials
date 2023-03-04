package com.baeldung.scala.rotation

object Rotator {
  implicit class Wrapper[T](val sequence: Seq[T]) extends AnyVal {

    def rotatedView(index: Int): Seq[T] = useSplit(sequence, index)

    private def useSplit(sequence: Seq[T], index: Int): Seq[T] = ???

  }

}
