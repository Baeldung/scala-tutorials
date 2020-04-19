package com.baeldung.scala.abstractclassesvstraits

class TextMeanLengthComputation(val identifer: String, content: List[String]) extends ManagedComputation[Double](identifier = identifer) {

  val compute: Double = content.map(_.length).sum.toDouble / content.length

}
