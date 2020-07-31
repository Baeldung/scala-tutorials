package com.baeldung.scala.higherkindedtypes

object HigherKindedTypes {

  trait Collection[T[_]] {
    def wrap[A](a: A): T[A]
    def first[B](b: T[B]): B
  }

  trait BatchRun[M[_]] {
    def write[A](item: A, db: M[A]): M[A] = transform(item, db)
    def transform[A](item: A, db: M[A]): M[A]
  }

}
