package com.baeldung.scala.zip

object ZipPerformanceApp {

  def timed[T](f: => T): T = {
    val startTime = System.nanoTime()
    val res = f
    val endTime = System.nanoTime()
    println(
      s"Time taken for operation: ${(endTime - startTime) / 1000000} milliseconds"
    )
    res
  }

  @main
  def main(): Unit = {
    val largeList = (1 to 10000000).toList
    println("--- zip ---")
    timed(largeList.zip(largeList)) // force evaluation of lazyZip
    println("--- lazyZip without eval ---")
    timed(largeList.lazyZip(largeList)) // lazy evaluation of lazyZip
    println("--- lazyZip with partial eval ---")
    timed(
      largeList.lazyZip(largeList).take(100).toList
    ) // force evaluation of lazyZip
    println("--- lazyZip with full eval ---")
    timed(largeList.lazyZip(largeList).toList) // force evaluation of lazyZip

  }
}
