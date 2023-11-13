package com.baeldung.scala3.implicits.comparison.scala2
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ImplicitParameterUnitTest extends AnyFlatSpec with Matchers {
  it should "use the implicit parameter" in {
    implicit val printer = new ConsolePrinter
    complexLogic("Live Long and Prosper") shouldBe "Live Long and Prosper"
  }

}
