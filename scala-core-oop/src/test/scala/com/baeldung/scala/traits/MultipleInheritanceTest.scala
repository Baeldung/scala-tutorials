package com.baeldung.scala.traits

import com.baeldung.scala.traits.MultipleInheritance.{Cat, Flyable, Speakable, Walkable}
import org.scalatest.FunSuite

class MultipleInheritanceTest extends FunSuite {

  test("Multiple Inheritance Test") {
    val cat = new Cat
    assert(cat.isInstanceOf[Speakable] === true)
    assert(cat.isInstanceOf[Walkable] === true)
    assert(cat.isInstanceOf[Flyable] === false)
  }

}
