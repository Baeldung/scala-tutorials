package com.baeldung.scala.options

object OptionBuilder {
  implicit class OptionWhenBuilder[A](value: A) {
    def when(condition: A => Boolean): Option[A] = {
      Some(value).filter(condition)
    }
  }

  implicit class OptionUnlessBuilder[A](value: A) {
    def unless(condition: A => Boolean): Option[A] = {
      Some(value).filterNot(condition)
    }
  }
}
