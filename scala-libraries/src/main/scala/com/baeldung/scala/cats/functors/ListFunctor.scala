package com.baeldung.scala.cats.functors

import cats.Functor
import cats.implicits._

object ListFunctor {
  def transformList(list: List[Int]): List[Int] = {
    Functor[List].map(list)(_ * 2)
  }
}
