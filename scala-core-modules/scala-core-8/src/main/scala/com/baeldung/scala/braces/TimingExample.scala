package com.baeldung.scala.braces

object TimingExample extends App {
  def measureTime[T](block: => T): (T, Long) = {
    val startTime = System.nanoTime()
    val result = block
    val endTime = System.nanoTime()
    (result, endTime - startTime)
  }

  // Using the measureTime method with braces
  val (result, time) = measureTime {
    // Code block whose execution time is to be measured
    val numbers = (1 to 1000000).toList
    numbers.filter(_ % 2 == 0).sum
  }

  println(s"Result: $result")
  println(s"Execution Time: $time nanoseconds")
}
