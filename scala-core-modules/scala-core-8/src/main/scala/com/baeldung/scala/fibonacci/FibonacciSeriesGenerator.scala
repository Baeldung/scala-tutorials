package com.baeldung.scala.fibonacci

import scala.annotation.tailrec

object FibonacciSeriesGenerator {

  def fibSeriesRecursion(seriesSize: Int): Seq[Long] = {
    def getNextNum(num: Long): Long = {
      if (num <= 1) {
        num
      } else {
        getNextNum(num - 1) + getNextNum(num - 2)
      }
    }
    (0L until seriesSize).map(getNextNum)
  }

  def fibTailRec(seriesSize: Int): Seq[Long] = {
    @tailrec
    def fib(n: Int, a: Long, b: Long, acc: List[Long]): List[Long] = {
      if (n <= 0) acc
      else fib(n - 1, b, a + b, acc :+ a)
    }
    fib(seriesSize, 0L, 1, Nil)
  }

  def fibLazyList(seriesSize: Int): Seq[Long] = {
    lazy val fib: LazyList[Long] = 0L #:: 1L #:: fib.zip(fib.tail).map(_ + _)
    fib.take(seriesSize).toList
  }

  def fibIterator(seriesSize: Int): List[Long] = {
    Iterator
      .iterate((0L, 1L)) { case (a, b) => (b, a + b) }
      .map(_._1)
      .take(seriesSize)
      .toList
  }
}
