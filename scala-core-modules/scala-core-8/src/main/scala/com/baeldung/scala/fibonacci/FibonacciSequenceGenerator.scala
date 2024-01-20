package com.baeldung.scala.fibonacci

import scala.annotation.tailrec

object FibonacciSequenceGenerator {

  def fibSequenceRecursion(sequenceSize: Int): Seq[Long] = {
    def getNextNum(num: Long): Long = {
      if (num <= 1) {
        num
      } else {
        getNextNum(num - 1) + getNextNum(num - 2)
      }
    }
    (0L until sequenceSize).map(getNextNum)
  }

  def fibTailRec(sequenceSize: Int): Seq[Long] = {
    @tailrec
    def fib(n: Int, a: Long, b: Long, acc: List[Long]): List[Long] = {
      if (n <= 0) acc
      else fib(n - 1, b, a + b, acc :+ a)
    }
    fib(sequenceSize, 0L, 1, Nil)
  }

  def fibLazyList(sequenceSize: Int): Seq[Long] = {
    lazy val fib: LazyList[Long] = 0L #:: 1L #:: fib.zip(fib.tail).map(_ + _)
    fib.take(sequenceSize).toList
  }

  def fibIterator(sequenceSize: Int): List[Long] = {
    Iterator
      .iterate((0L, 1L)) { case (a, b) => (b, a + b) }
      .map(_._1)
      .take(sequenceSize)
      .toList
  }
}
