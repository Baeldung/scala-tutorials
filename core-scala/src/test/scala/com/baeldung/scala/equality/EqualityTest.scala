package com.baeldung.scala.equality

import org.scalatest.FlatSpec

class EqualityTest extends FlatSpec {
  "Equality operator for AnyVal" should "work as in Java" in {
    val intAnyVal = 4
    val booleanAnyVal = false
    assert(intAnyVal == 2 * 2)
    assert((intAnyVal > 10) == booleanAnyVal)
  }

  "Equality operator for referential types" should "work like null-safe equals()" in {
    val str1 = new String("AnyRef")
    val str2 = new String("AnyRef")
    val str3 = null
    val str4 = null
    assert(str1 == str2)
    // Unlike in java, the following lines of code do not cause NullPointerExceptions
    assert(str3 != str2)
    assert(str4 == str3)
  }

  "Equals()" should "work as in Java" in {
    val str1 = new String("AnyRef")
    val str2 = new String("AnyRef")
    val str3 = null
    assert(str1.equals(str2))
    assertThrows[NullPointerException](str3.equals(str2))
  }

  "Eq and ne" should "check referential equality" in {
    val str1 = new String("AnyRef")
    val str2 = new String("AnyRef")
    val str3 = str2
    assert(str1 ne str2)
    assert(str3 eq str2)
    // Both operators are null-safe
    assert(null eq null)
    assert(null ne str1)
  }

  "Equality operator" should "not work out of the box for PersonFirst" in {
    val pf1 = new PersonFirst("Donald", 66)
    val pf2 = new PersonFirst("Donald", 66)
    assert(pf1 != pf2)
  }

  "Equality operator" should "work for PersonSecond and PersonThird" in {
    val ps1 = new PersonSecond("Donald", 66)
    val ps2 = new PersonSecond("Donald", 66)
    val pt1 = PersonThird("Donald", 66)
    val pt2 = PersonThird("Donald", 66)
    assert(ps1 == ps2)
    assert(pt1 == pt2)
    // There is no duck-typing though
    assert(ps1 != pt1)
  }
}
