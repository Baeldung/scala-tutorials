package com.baeldung.scala.strings.comparison

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

class StringComparisonUnitTest extends AnyFeatureSpec with GivenWhenThen {

  scenario("String Comparison with ==, eq and equals") {

    info("Demonstrate various scenarios on String comparison.")

    Given("two same Strings")
    val stringOne = "baeldung"
    val stringTwo = "baeldung"

    When("compared with == method")
    val result1 = stringOne == stringTwo

    And("compared with eq method")
    val result2 = stringOne eq stringTwo

    And("compared with equals method")
    val result3 = stringOne.equals(stringTwo)

    Then("the results will be true")
    assert(result1 == true)
    assert(result2 == true)
    assert(result3 == true)
  }

  scenario("Handling of null String by == method") {

    Given("two Strings out of which one String is null")
    val stringOne = "baeldung"
    val stringTwo = null

    When("compared with == method")
    val result1 = stringOne == stringTwo

    Then("the result will be false")
    assert(result1 == false)
  }

  scenario("Case-insensitive comparison") {

    Given("two similar Strings which are case-insensitive")
    val stringOne = "baeldung"
    val stringTwo = "Baeldung"

    When("compared with == method")
    val result1 = stringOne == stringTwo

    Then("the result will be false")
    assert(result1 == false)

    When("compared after converting toUpperCase")
    val result2 = stringOne.toUpperCase() == stringTwo.toUpperCase()

    Then("the result will be true")
    assert(result2 == true)

    When("compared with equalsIgnoreCase method")
    val result3 = stringOne.equalsIgnoreCase(stringTwo)

    Then("the result will be true")
    assert(result3 == true)
  }
}
