package com.baeldung.scala.listsum

import scala.annotation.tailrec

object SumList {

  def sum(list: List[Int]): Int = list.sum

  def sumByReduce(list: List[Int]): Int = list.reduceOption(_ + _).getOrElse(0)

  def sumByFold(list: List[Int]): Int = list.foldLeft(0)(_ + _)

  def sumByIteration(list: List[Int]): Int = {
    var sum = 0
    list.foreach(num => sum += num)
    sum
  }

  def sumByFor(list: List[Int]): Int = {
    var sum = 0
    for (num <- list) { sum += num }
    sum
  }

  def sumByTailRecursion(list: List[Int]): Int = {
    @tailrec
    def rec(list: List[Int], sum: Int): Int = {
      list match {
        case Nil       => sum
        case a :: tail => rec(tail, sum + a)
      }
    }
    rec(list, 0)
  }

}
