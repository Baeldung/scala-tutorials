package com.baeldung.scala.equality

import org.scalatest.flatspec.AnyFlatSpec

class EqualityUnitTest extends AnyFlatSpec {
  "Equality operator for AnyVal" should "work as in Java" in {
    val intAnyVal = 4
    assert(intAnyVal == 2 * 2)
  }

  "Equality operator for referential types" should "work like null-safe equals()" in {
    val firstString = new String("AnyRef")
    val secondString = new String("AnyRef")
    val thirdString = null
    val fourthString = null
    assert(firstString == secondString)
    // Unlike in java, the following lines of code do not cause NullPointerExceptions
    assert(thirdString != secondString)
    assert(fourthString == thirdString)
  }

  "Equals()" should "work as in Java" in {
    val firstString = new String("AnyRef")
    val secondString = new String("AnyRef")
    assert(firstString.equals(secondString))
  }

  "Eq and ne" should "check referential equality" in {
    val firstString = new String("AnyRef")
    val secondString = new String("AnyRef")
    val thirdString = secondString
    assert(firstString ne secondString)
    assert(thirdString eq secondString)
    // Both operators are null-safe
    assert(null eq null)
    assert(null ne firstString)
  }

  "Equality operator" should "not work out of the box for PersonSimpleClass" in {
    val firstSimpleClassInstance = new PersonSimpleClass("Donald", 66)
    val secondSimpleClassInstance = new PersonSimpleClass("Donald", 66)
    assert(firstSimpleClassInstance != secondSimpleClassInstance)
  }

  "Equality operator" should "work for PersonClassWithOverrides" in {
    val firstClassWithOverridesInstance = new PersonClassWithOverrides("Donald", 66)
    val secondClassWithOverridesInstance = new PersonClassWithOverrides("Donald", 66)
    assert(firstClassWithOverridesInstance == secondClassWithOverridesInstance)
  }

  "Equality operator" should "work for PersonCaseClass" in {
    val firstCaseClassInstance = PersonCaseClass("Donald", 66)
    val secondCaseClassInstance = PersonCaseClass("Donald", 66)
    assert(firstCaseClassInstance == secondCaseClassInstance)
  }
}
