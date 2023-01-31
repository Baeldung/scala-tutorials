package com.baeldung.scala.lifting

import org.scalatest.flatspec.AnyFlatSpec
import Examples._

class ExamplesUnitTest extends AnyFlatSpec {

  "getSqrtRootMessagePartialFunction" should "calculate square root" in {
    assert(Examples.getSqrtRootMessagePartialFunction(4.0) == "Square root of 4.0 is 2.0")
  }

  it should "not calculate square root" in {
    assert(Examples.getSqrtRootMessagePartialFunction(-3.0) == "Cannot calculate square root for -3.0")
  }

  "getSqrtRootMessageTotalFunction" should "calculate square root" in {
    assert(Examples.getSqrtRootMessageTotalFunction(4.0) == "Square root of 4.0 is 2.0")
  }

  it should "not calculate square root" in {
    assert(Examples.getSqrtRootMessageTotalFunction(-3.0) == "Cannot calculate square root for -3.0")
  }

  "Methods" should "combine correctly" in {
    assert(Examples.isEven(Examples.add5(3)) == true)
  }

  "Functions" should "combine correctly" in {
    assert((funcAdd5 andThen funcIsEven)(3)  == true)
  }

  "getGreetingsBasic" should "return the correct sentence" in {
    assert(Examples.getGreetingsBasic() == "Say hello to Fabio")
  }

  "getGreetingsMonadTranformer" should "return the correct sentence" in {
    assert(Examples.getGreetingsMonadTranformer() == Some("Say hello to Fabio"))
  }

  "optionLength" should "return the correct length with Some" in {
    assert(Examples.optionLength(Some("abc")) == Some(3))
  }

  "optionLength" should "return the correct length with None" in {
    assert(Examples.optionLength(None) == None)
  }

}
