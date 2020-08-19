package com.baeldung.scala.monoid

/**
  * * An associative binary operation, circumscribed by type and the
  * * semigroup laws.
  */
trait Semigroup[A] { self =>

  def op(x: A, y: => A): A
}

/**
  * Provides an identity element (`zero`) to the binary `op`
  * * op is subject to the monoid laws
  */
trait Monoid[A] extends Semigroup[A] { self =>
  def zero: A
}
