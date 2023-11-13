package com.baeldung.scala.array

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AppendAnElementToAnArrayUnitTest extends AnyFlatSpec with Matchers {

  "adding an element to the end of an array" should "yield a new array with the new element at the end" in {
    val array = Array(1, 2, 3)
    val array2 = array :+ 4

    assert(array2 sameElements Array(1, 2, 3, 4))
  }

  "adding an element at the beginning of an array" should "yield a new array with the new element at the start" in {
    val array = Array(1, 2, 3)
    val array2 = 4 +: array

    assert(array2 sameElements Array(4, 1, 2, 3))
  }

  "using the ':+=' operator on a mutable array" should "append the new element to the end in-place" in {
    var array = Array(1, 2, 3)
    array :+= 4

    assert(array sameElements Array(1, 2, 3, 4))
  }

  "using the '+:=' operator on a mutable array" should "prepend the new element to the start in-place" in {
    var array = Array(1, 2, 3)
    array +:= 4

    assert(array sameElements Array(4, 1, 2, 3))
  }
}
