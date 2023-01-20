package com.baeldung.scala3.implicits.comparison.scala2
import com.baeldung.scala3.implicits.comparison.scala2.Extension.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ExtensionUnitTest extends AnyFlatSpec with Matchers {

  it should "extend Int to create Second" in {
    val second: Second = 100.toSecond()
    val result = TimeUtil.doSomethingWithProcessingTime(second)
    result shouldBe "100 seconds"
  }

}
