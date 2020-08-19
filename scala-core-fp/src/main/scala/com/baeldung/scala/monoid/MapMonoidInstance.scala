package com.baeldung.scala.monoid

/**
  * This Monoid type class instance supports combining maps that have a numeric value.
  * the value of maps with common keys, are added using the underlying Numeric.plus
  */
object MapMonoidInstance {
  implicit def mapMonoid[K, V: Numeric]: Monoid[Map[K, V]] = {
    new Monoid[Map[K, V]] {
      override def zero: Map[K, V] = Map()
      override def op(l: Map[K, V], r: => Map[K, V]): Map[K, V] = {
        l.keySet
          .union(r.keySet)
          .map(k =>
            (k -> implicitly[Numeric[V]]
              .plus(
                l.getOrElse(k, implicitly[Numeric[V]].zero),
                r.getOrElse(k, implicitly[Numeric[V]].zero)
              ))
          )
          .toMap
      }
    }
  }
}
