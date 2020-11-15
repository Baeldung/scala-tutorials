package com.baeldung.scala.implicitly

import com.baeldung.scala.implicitly.ImplicitlyUsage.{weight, weightUsingImplicit}
import org.scalatest.FlatSpec

class ImplicitlyUsageUnitTest extends FlatSpec {

  "The weight method" should "return the weight of a mass" in {
    val actualWeight: Double = weight(50.0, 9.81)
    assert(actualWeight == 490.5)
  }

  "The weightUsingImplicit method" should "return the weight of a mass" in {
    import ImplicitlyUsage.G
    val actualWeight: Double = weightUsingImplicit(50.0)
    assert(actualWeight == 490.5)
  }

  "The weightUsingImplicitly method" should "return the weight of a mass" in {
    import ImplicitlyUsage.G
    val actualWeight: Double = weightUsingImplicit(50.0)
    assert(actualWeight == 490.5)
  }
}
