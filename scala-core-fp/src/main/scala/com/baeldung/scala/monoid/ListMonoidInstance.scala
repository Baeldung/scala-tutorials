package com.baeldung.scala.monoid

object ListMonoidInstance {
  implicit def listMonoid[A]: Monoid[List[A]] = {
    new Monoid[List[A]] {
      override def zero: List[A] = Nil
      override def op(l: List[A], r: => List[A]) = l ++ r
    }
  }
}
