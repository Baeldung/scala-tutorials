package com.baeldung.scala.traits

import scala.collection.mutable.ArrayBuffer

abstract class StackableQueue {
  def remove(): Int
  def add(x: Int)
}

class Stackable extends StackableQueue {
  private val buf = new ArrayBuffer[Int]
  def remove(): Int = buf.remove(0)
  def add(x: Int) { buf += x }
}

trait Square extends StackableQueue {
  abstract override def add(x: Int): Unit = super.add(x * x)
}

trait Subtract extends StackableQueue {
  abstract override def add(x: Int): Unit = { super.add(x - 2) }
}

class Test {
  //  The oder of mixins is important. The 2 instances yield different results
  val queue1 = new Stackable with Square with Subtract
  val queue2 = new Stackable with Subtract with Square
}
