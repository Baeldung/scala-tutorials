package com.baeldung.scala.traits

trait Comparable[T <: Comparable[T]] { self: T =>
  def <(that: T): Boolean
  def <=(that: T): Boolean = this < that || this == that
  def >(that: T): Boolean = that < this
  def >=(that: T): Boolean = that <= this
}

abstract class AComparable[T <: AComparable[T]] { self: T =>
  def <(that: T): Boolean
  def <=(that: T): Boolean = this < that || this == that
  def >(that: T): Boolean = that < this
  def >=(that: T): Boolean = that <= this
}
