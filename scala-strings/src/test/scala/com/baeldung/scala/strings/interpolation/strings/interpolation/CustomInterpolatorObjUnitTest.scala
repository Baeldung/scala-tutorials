package com.baeldung.scala.strings.interpolation

import org.scalatest.{FeatureSpec, GivenWhenThen}
import com.baeldung.scala.strings.interpolation.CustomInterpolatorObj._

class CustomInterpolatorObjUnitTest extends FeatureSpec with GivenWhenThen {

  scenario("The custom interpolator may work as expected") {

    info("As a programmer")
    info(
      "I want to demonstrate the expected behavior of custom string interpolator"
    )

    Given("a string to be interpolated")
    val testString = "well"

    When("when we interpolate a string with our custom interpolator")
    val result = custom"the custom interpolator works $testString"

    Then("the result will be as expected")
    val expectedResult =
      new StringContext("the custom interpolator works ", "").custom(testString)
    assert(result == expectedResult)
  }
}
