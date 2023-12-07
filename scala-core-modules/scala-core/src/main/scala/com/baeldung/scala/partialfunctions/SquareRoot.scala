package com.baeldung.scala.partialfunctions

object SquareRoot {
  val squareRoot = new PartialFunction[Double, Double] {
    def apply(x: Double) = Math.sqrt(x)
    def isDefinedAt(x: Double) = x >= 0
  }

  val squareRootImplicit: PartialFunction[Double, Double] = {
    case x if x >= 0 => Math.sqrt(x)
  }
}
