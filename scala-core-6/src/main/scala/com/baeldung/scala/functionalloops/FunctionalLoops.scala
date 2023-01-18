package com.baeldung.scala.functionalloops
import scala.language.postfixOps
object FunctionalLoops {
  def getSumOfList(list: List[Int]): Int = {
    var sum = 0
    for (i <- 0 until list.length) {
      sum += list(i)
    }
    sum
  }

  assert(getSumOfList((1 to 100).toList) == 55)

  val length = Thread.currentThread().getStackTrace.length + 1
  def getSumOfListRecursive(list: List[Int]): Int = {
    list match {
      case Nil =>
        println(Thread.currentThread().getStackTrace.length - length) // prints 100 for 100 items
        0
      case h :: t =>
        h + getSumOfListRecursive(t)
    }
  }

  assert(getSumOfListRecursive(1 to 100 toList) == 5050)

  val length2 = Thread.currentThread().getStackTrace.length + 1
  def getSumOfListTailRecursive(numbers: List[Int]): Int = {
    def innerFunction(list: List[Int], accumulator: Int): Int = {
      list match {
        case Nil =>
          println(Thread.currentThread().getStackTrace.length - length2) // prints 1 for 1000000 items
          accumulator
        case head :: tail =>
          innerFunction(tail, head + accumulator)
      }
    }

    innerFunction(numbers, 0) // give an initial accumulator
  }
  assert(getSumOfListTailRecursive((1 to 1000000).toList) == 1784293664)
}
