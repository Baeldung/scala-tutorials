package com.baeldung.scala.cats.functors

import cats.Functor

object FunctorSyntax {
  def withFunctor[A, B, F[_] : Functor](item: F[A], op: A => B): F[_] = Functor[F].map(item)(op)
}
