package com.baeldung.scala.fibers

import cats.effect.IO

object IOExtensions {
  implicit class Xtensions[A](io: IO[A]) {
    def printIO: IO[A] =
      for {
        a <- io
        _ = println(s"[${Thread.currentThread().getName}] " + a)
      } yield a
  }
}
