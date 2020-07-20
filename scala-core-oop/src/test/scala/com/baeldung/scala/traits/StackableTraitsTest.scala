package com.baeldung.scala.traits

import com.baeldung.scala.traits.StackableTraits.{Fruit, Sweet, Tasty}
import org.scalatest.FunSuite

class StackableTraitsTest extends FunSuite {

  test("Stackable Traits Test") {
    val mango = new Fruit with Tasty with Sweet
    assert(mango.qualities() === List("healthy", "tasty", "sweet"))

    val pineapple = new Fruit with Sweet with Tasty
    assert(pineapple.qualities() === List("healthy", "sweet", "tasty"))
  }

}
