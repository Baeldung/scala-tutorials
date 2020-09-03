package com.baeldung.scala.iteratorsvsstreamsvsviews

object NonStrictDataStructures {
  val data = Seq.range(0, 100)
  val iter = data.iterator
  val stream = data.toStream
  val view = data.view
}
