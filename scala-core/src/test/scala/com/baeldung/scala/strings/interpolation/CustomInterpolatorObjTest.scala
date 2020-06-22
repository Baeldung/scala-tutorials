package com.baeldung.scala.strings.interpolation

import com.baeldung.scala.strings.CustomInterpolatorObj._
import org.scalatest.{FeatureSpec, GivenWhenThen}

class CustomInterpolatorObjTest extends FeatureSpec with GivenWhenThen {

  feature("The custom interpolator may work as expected") {

    info("As a programmer")
    info("I want to demonstrate the expected behavior of custom string interpolator")

    Given("a string to be interpolated")
    val testString = "well"

    When("when we interpolate a string with our custom interpolator")
    val result = custom"the custom interpolator works $testString as expected"

    Then("the result will be as expected")
    val expectedResult = new StringContext("the custom interpolator works ", " as expected").custom(testString)
    assert(result == expectedResult)
  }
}