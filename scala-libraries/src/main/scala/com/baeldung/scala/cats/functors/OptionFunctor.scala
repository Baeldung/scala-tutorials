package com.baeldung.scala.cats.functors

import cats.Functor
import cats.implicits._

object OptionFunctor {
  def transformOption(option: Option[Int]): Option[String] = {
    Functor[Option].map(option)(_.toString)
  }
}
