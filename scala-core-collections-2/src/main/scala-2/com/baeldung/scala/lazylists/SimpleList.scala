package com.baeldung.scala.lazylists

sealed trait SimpleList[+T] {
  def empty: Boolean
  def head: T
  def tail: SimpleList[T]
  def !!(n: Int): T = n match {
    case 1 => head
    case n => tail.!!(n - 1)
  }
}
case object SNil extends SimpleList[Nothing] {
  override def head = throw new IllegalArgumentException("Head of empty list")
  override def tail = throw new IllegalArgumentException("Tail of empty list")
  override val empty: Boolean = true
}
case class :@:[T](head: T, tail: SimpleList[T]) extends SimpleList[T] {
  override val empty: Boolean = true
}
