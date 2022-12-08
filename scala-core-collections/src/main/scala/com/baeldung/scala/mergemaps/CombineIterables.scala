package com.baeldung.scala.mergemaps

object CombineIterables {

  def combineIterables[K, V](
    a: Map[K, Iterable[V]],
    b: Map[K, Iterable[V]]
  ): Map[K, Iterable[V]] = {
    a ++ b.map { case (k, v) => k -> (v ++ a.getOrElse(k, Iterable.empty)) }
  }

}
