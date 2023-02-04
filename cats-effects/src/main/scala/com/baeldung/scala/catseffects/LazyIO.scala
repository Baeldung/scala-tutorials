package com.baeldung.scala.catseffects

case class LazyIO[A](runEffect: () => A) {
  def map[B](fn: A => B): LazyIO[B] = LazyIO.io(fn(runEffect()))

  def flatMap[B](fn: A => LazyIO[B]): LazyIO[B] =
    LazyIO.io(fn(runEffect()).runEffect())
}

object LazyIO {
  def io[A](effect: => A): LazyIO[A] = new LazyIO[A](() => effect)
}
