package com.baeldung.scala.cats.functors

import cats.Functor
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureFunctor {
  def transformFuture(future: Future[Int]): Future[Int] = {
    Functor[Future].map(future)(_ + 1)
  }
}
