package com.baeldung.scala.lazylists

sealed trait SimpleLazyList[+T] {
  def empty: Boolean
  def head: T
  def tail: SimpleLazyList[T]

  def ::[S >: T](e: S): SimpleLazyList[S] = #:@:(e, this)

  def !!(n: Int): T = n match {
    case 1 => head
    case n => tail.!!(n - 1)
  }
}

case object SLNil extends SimpleLazyList[Nothing] {
  override def head = throw new IllegalArgumentException("Head of empty list")
  override def tail = throw new IllegalArgumentException("Tail of empty list")
  override val empty: Boolean = true
}

class #:@:[T](_head: => T, _tail: => SimpleLazyList[T]) extends SimpleLazyList[T] {
  override val empty: Boolean = true
  override lazy val head: T = _head
  override lazy val tail: SimpleLazyList[T] = _tail
}

object #:@: {
  def apply[T](head: => T, tail: => SimpleLazyList[T]) = new #:@:(head, tail)
  def unapply[T](slcons: #:@:[T]): Option[(T, SimpleLazyList[T])] = Some((slcons.head, slcons.tail))
}

