package com.baeldung.scala3.implicits.comparison.scala3
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ExtensionUnitTest extends AnyFlatSpec with Matchers {

  it should "extend Int to create Second" in {
    import Extension.*
    val sec: Second = 100.toSecond()
    val result = TimeUtil.doSomethingWithProcessingTime(sec)
    result shouldBe "100 seconds"
  }

  it should "extend a numeric using generic extension" in {
    import NumericExtensions.*
    val addInts = 10.add(9)
    addInts shouldBe 19
    val addDoubles = 10d.add(0.2d)
    addDoubles shouldBe 10.2 +- 0.1
  }

}
