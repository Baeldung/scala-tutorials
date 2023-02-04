package com.baeldung.scala.firstclassfunctions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CurryingUnitTest extends AnyFlatSpec with Matchers {
  "Currying" should "perform multiplication using chain of calls to functions" in {
    val multiplicationResult = Currying.multiplication(3, 5)
    val curriedMultiplicationResult = Currying.curriedMultiplication(3)(5)
    assert(multiplicationResult == curriedMultiplicationResult)
  }

  "Currying" should "perform multiplication using special curried function" in {
    val multiplicationResult = Currying.multiplication(3, 5)
    val conciseCurriedMultiplicationResult =
      Currying.conciseCurriedMultiplication(3)(5)
    assert(multiplicationResult == conciseCurriedMultiplicationResult)
  }

  "Currying" should "perform addition using multiple arguments list" in {
    val additionResult = Currying.addition(8, 4)
    val conciseCurriedAdditionResult = Currying.curriedAddition(8)(4)
    assert(additionResult == conciseCurriedAdditionResult)
  }
}
