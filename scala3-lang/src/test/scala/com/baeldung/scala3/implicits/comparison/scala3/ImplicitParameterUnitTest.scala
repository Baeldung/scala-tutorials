package com.baeldung.scala3.implicits.comparison.scala3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ImplicitParameterUnitTest extends AnyFlatSpec with Matchers {
  it should "use the implicit parameter" in {
    given ConsolePrinter with {}
    ParamUtil.complexLogic("Live Long and Prosper givens") shouldBe "Live Long and Prosper givens"
  }

}
