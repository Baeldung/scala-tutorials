package com.baeldung.scala.partialfunctions

import org.scalatest.FlatSpec

class SquareRootUnitTest extends FlatSpec {

  "SquareRoot" should "return the correct value for positive double" in {
    assert(SquareRoot.squareRoot(4.0) == 2.0)
  }

  it should "return NaN for a negative double" in {
    assert(SquareRoot.squareRoot(-4.0).equals(Double.NaN))
  }

  "SquareRootImplicit" should "return the correct value for a positive double" in {
    assert(SquareRoot.squareRootImplicit(4.0) == 2.0)
  }

  it should "throw a 'scala.MatchError' exception" in {
    assertThrows[scala.MatchError] {
      SquareRoot.squareRootImplicit(-4.0)
    }
  }
}
