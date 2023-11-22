package com.baeldung.scala.iteratecollections

object IterateCollections {

  def iterateWithForEach(): Unit = {
    val numbers = List(1, 2, 3, 4, 5)
    numbers.foreach(num => println(num))
  }

  def iterateWithForComprehension(): Unit = {
    val numbers = List(1, 2, 3, 4, 5)
    val doubledNumbers = for (num <- numbers) yield num * 2
    println(doubledNumbers) // Output: List(2, 4, 6, 8, 10)
  }

}
