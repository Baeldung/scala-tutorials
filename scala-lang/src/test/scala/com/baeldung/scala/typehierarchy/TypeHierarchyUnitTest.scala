package com.baeldung.scala.typehierarchy

import com.baeldung.scala.typehierarchy.TypeHierarchy._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/** @author
  *   vid2010
  */

class TypeHierarchyUnitTest extends AnyFlatSpec with Matchers {

  "A Any type" should "compatible with any type" in {
    assert(any(1) == "")
    assert(any(true) == "")
    assert(any(()) == "")
    assert(any("S") == "")
    assert(any(null) == "")
    assert(any(List()) == "")
  }

  "A AnyVal type" should "compatible with any value type" in {
    val unit: Unit = ()
    assert(anyVal(unit) == "")
    val int: Int = 12
    assert(anyVal(int) == "")
    val bool: Boolean = false
    assert(anyVal(bool) == "")
  }

  "A AnyRef type" should "compatible with any reference type" in {
    case class Person(name: String)
    assert(anyRef(new Person("Same")) == "")
  }

  "A Null type" should "compatible with any reference type" in {
    assert(anyRef(null) == "")
  }

  "A Nothing type" should "not have any value" in {
    val list: List[Nothing] = List()
    assert(list == List())
  }
}
