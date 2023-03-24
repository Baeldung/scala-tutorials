package com.baeldung.scala.cats.monoids

import cats.Monoid

object CollectionMonoid {
  def combineAll[A](collection: Seq[A])(implicit ev: Monoid[A]): A = {
    val monoid = Monoid[A]
    collection.foldLeft(monoid.empty)(monoid.combine)
  }
}
