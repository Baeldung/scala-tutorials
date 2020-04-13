package com.baeldung.scala.partialfunctions

import org.scalatest.FlatSpec

class SwapIntegerSignUnitTest extends FlatSpec {

  "swapSign" should "return a positive number when a negative is given" in {
    assert(SwapIntegerSign.swapSign(-3) == 3)
  }

  it should "return a negative number when a positive is given" in {
    assert(SwapIntegerSign.swapSign(3) == -3)
  }

  "printIfPositive" should "print a positive number" in {
    assert(SwapIntegerSign.printIfPositive(3).getClass == classOf[Unit])
  }

  it should "throw a 'scala.MatchError' exception given a negative number" in {
    assertThrows[scala.MatchError] {
      SwapIntegerSign.printIfPositive(-3)
    }
  }

  "swapSign andThen printIfPositive" should "chain and print a positive number" in {
    assert(
      (SwapIntegerSign.swapSign andThen SwapIntegerSign.printIfPositive)(-1).getClass == classOf[
        Unit
      ]
    )
  }

  it should "chain and throw a 'scala.MatchError' exception given a negative number" in {
    assertThrows[scala.MatchError] {
      (SwapIntegerSign.swapSign andThen SwapIntegerSign.printIfPositive)(1)
    }
  }
}
