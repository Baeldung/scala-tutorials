package com.baeldung.scala3.implicits.comparison.scala3

import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

import ImplicitConversion._

class ImplicitConversionUnitTest extends AnyFlatSpec with Matchers {
  it should "use the implicit conversion" in {
    import ImplicitConversion.given
    val processingTime = 100
    //auto conversion from Int to Second using intToSecond()
    val result = TimeUtil.doSomethingWithProcessingTime(processingTime)
    result shouldBe "100 seconds"
  }
}
