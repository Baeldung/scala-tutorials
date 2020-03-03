package com.baeldung.scala

object Tuples {

  val (evens, odds) = List(1, 3, 4, 5, 2).partition(_ % 2 == 0)

  val tuple = ("Joe", 34)
  tuple._1 // "Joe"
  tuple._2 // 34

  val (name, age) = tuple
  val (_, myAge) = tuple

  tuple.productArity // 2
  tuple.productElement(1) // 34
  tuple.productPrefix // Tuple2
  tuple.productIterator.foreach(println)
  (1, 2).swap // (2, 1)

  val values = (5, 10)

  val sum: (Int, Int) => Int = (x, y) => x + y
  val tupledSum: ((Int, Int)) => Int = sum.tupled

//  sum(values)
  tupledSum(values)
}
