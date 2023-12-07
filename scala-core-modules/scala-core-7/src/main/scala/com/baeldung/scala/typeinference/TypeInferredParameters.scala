package com.baeldung.scala.typeinference

object TypeInferredParameters extends App {
  val list = List(1, 2, 3, 4)
  val sum1 = list.reduce((a: Int, b: Int) => a + b)
  val sum2 = list.reduce((a, b) => a + b)
}
