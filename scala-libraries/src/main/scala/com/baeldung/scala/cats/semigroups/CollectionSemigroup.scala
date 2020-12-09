package com.baeldung.scala.cats.semigroups

import cats.Semigroup
import cats.implicits._

object CollectionSemigroup {
  def combineStrings(collection: Seq[String]): String = {
    collection.foldLeft("")(Semigroup[String].combine)
  }
}
