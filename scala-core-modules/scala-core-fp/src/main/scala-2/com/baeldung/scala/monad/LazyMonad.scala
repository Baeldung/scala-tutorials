package com.baeldung.scala.monad

object LazyMonad {
  class Lazy[+A](value: => A) {
    private lazy val internal: A = value

    def get: A = internal

    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(internal)

    def map[B](f: A => B): Lazy[B] = flatMap(x => Lazy(f(x)))
  }

  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
    def flatten[A](m: Lazy[Lazy[A]]): Lazy[A] = m.flatMap(x => x)
  }
}
