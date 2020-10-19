package com.baeldung.scala.iteratorsvsstreamsvsviews

import scala.collection.SeqView

object NonStrictDataStructures {
  val data = Seq.range(0, 3)
  val iter = data.iterator
  val stream = data.toStream
  val view: AnyRef with SeqView[Int, Seq[Int]] = data.view
}

case class Factorial() {
  def factorial(a: Int, b: Int): Stream[Int] = a #:: factorial(a*(b+1), b+1)
  val factorialsN = (n: Int) => factorial(1, 1).take(n)
}
