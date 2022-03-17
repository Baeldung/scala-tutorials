package com.baeldung.scala.scalatest.collectiontest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CollectionTest extends AnyFlatSpec with Matchers {

  it should "compare equality for a list of numbers in same order" in {
    val intList = List(1, 2, 3, 4)
    val expected = List(1, 2, 3, 4)
    intList shouldBe expected
    assert(intList == expected)
  }

  it should "compare 2 lists of case classes correctly" in {
    val countries = List(
      Country("Germany", "DE"),
      Country("India", "IN"),
      Country("France", "FR")
    )

    val expected = List(
      Country("Germany", "DE"),
      Country("India", "IN"),
      Country("France", "FR")
    )

    countries shouldBe expected
  }

  it should "return false when comparing 2 lists of same elements in different order" in {
    val list = List("a", "b", "c")
    list shouldNot be(List("a", "c", "b"))
  }

  it should "pass only if exactly 1 element overlaps" in {
    val asianCountries = List("india", "china", "russia")
    asianCountries should contain oneOf ("germany", "italy", "russia") //exactly one element shoud match
    asianCountries should contain atMostOneElementOf (Seq(
      "germany",
      "italy",
      "russia"
    )) // can use Seq()
  }

  it should "pass if atleast one element overlaps" in {
    val capitalCities = List("Berlin", "Paris", "Brussels", "Bern")
    val cities = List("Zurich", "Geneva", "Munich", "Paris")
    capitalCities should contain atLeastOneElementOf (cities)
  }

  it should "check for no overlap" in {
    val cities = List("Barcelona", "Hamburg", "Amsterdam")
    val scandinavian = List("Oslo", "Stockholm", "Copenhagen")
    cities should contain noneOf ("Oslo", "Stockholm", "Copenhagen")
    cities should contain noElementsOf (scandinavian)
  }

  it should "verify if all required elements are present" in {
    val cities = List("Barcelona", "Hamburg", "Amsterdam", "Oslo")
    val cities2 = List("Hamburg", "Oslo")
    cities should contain allElementsOf cities2
  }

  it should "pass if both collections contains exactly same elements" in {
    val cities = List("Barcelona", "Hamburg")
    val cities2 = List("Barcelona", "Hamburg")
    cities should contain theSameElementsAs (cities2)
    cities shouldBe cities2
    cities should contain only (cities2: _*) // but duplicates are allowed
    cities ++ cities should contain only (cities2: _*)
  }

  it should "pass if both collections contains exactly same elements in same order" in {
    val cities = List("Barcelona", "Hamburg")
    val cities2 = List("Barcelona", "Hamburg")
    cities should contain theSameElementsInOrderAs (cities2)
  }

}