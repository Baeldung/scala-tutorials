package com.baeldung.scala

import scala.collection.mutable.ArrayBuffer

/**
 * Simple ADT for Queue written by abstract class
 * remember that abstract classes are for writing natures (here ADT)
 *
 * @tparam T type of each element in queue
 */
sealed abstract class Queue[T] {
  def remove(): T

  def put(x: T): Unit
}

/**
 * Concrete Implementation of Queue with ArrayBuffer
 *
 * @tparam T type of each element in queue
 */
class BufferedQueue[T] extends Queue[T] {
  private val buf = new ArrayBuffer[T]

  override def remove(): T = buf.remove(0)

  override def put(item: T): Unit = buf += item
}

/**
 * IntQueue is Buffered Int Queue with logging behaviour
 */
class IntQueue extends BufferedQueue[Int] with LoggableQueue[Int]

/**
 * Logging behaviour is written with trait
 * remember that traits are for behaviours
 *
 * @tparam T type of each element in queue
 */
trait LoggableQueue[T] extends BufferedQueue[T] {
  override def put(item: T): Unit = {
    super.put(item)
    println(s"Queue: adding $item to the queue")
  }

  override def remove(): T = {
    val item = super.remove()
    println(s"Queue: $item removed from queue")
    item
  }
}
