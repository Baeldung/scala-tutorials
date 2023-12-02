package com.baeldung.scala.catseffects

import cats.effect.IO

object Utils {
  implicit class ShowThread[T](io: IO[T]) {
    def showThread: IO[T] = for {
      thunk <- io
      thread = Thread.currentThread.getName
      _ = println(s"[$thread] $thunk")
    } yield thunk
  }
}
