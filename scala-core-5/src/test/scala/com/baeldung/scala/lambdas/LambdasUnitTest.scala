package com.baeldung.scala.lambdas

import org.scalatest.{Matchers, WordSpec}

class LambdasUnitTest extends WordSpec with Matchers {

  "A lambda" should {
    "multiply a number by two" in {
      val double = (n: Int) => 2 * n
      assert(double(10) == 20)
    }

    "accept more than one argument" in {
      val sum = (x: Int, y: Int) => x + y
      assert(sum(10, 20) == 30)
    }

    "be an argument for map (longest form)" in {
      val ints = List(1, 2, 3, 4)
      val doubled = ints.map((x: Int) => x * 2)
      assert(doubled == List(2, 4, 6, 8))
    }

    "be an argument for map (normal form)" in {
      val ints = List(1, 2, 3, 4)
      val doubled = ints.map(x => x * 2)
      assert(doubled == List(2, 4, 6, 8))
    }

    "be an argument for map (shortest form)" in {
      val ints = List(1, 2, 3, 4)
      val doubled = ints.map(_ * 2)
      assert(doubled == List(2, 4, 6, 8))
    }

    "close on the local variables" in {
      val multiplier = 3
      val ints = List(1, 2, 3, 4)
      val doubled = ints.map(_ * multiplier)
      assert(doubled == List(3, 6, 9, 12))
    }

    "modify closed local variables" in {
      var count = 0
      val ints = List(1, 2, 3, 4)
      val doubled = ints.map { i =>
        count = count + 1
        i * 2
      }
      assert(doubled == List(2, 4, 6, 8))
      assert(count == 4)
    }
  }

  "IntTransformer" should {
    "compute the number of digits of the binary representation of a number" in {
      // the binary representation is 1111011
      assert(IntTransformer.transform(123)(_.toBinaryString.length) == 7)
    }

    "compute the number of digits of the binary representation of a number (no parameter)" in {
      val binaryDigits = (n: Int) => n.toBinaryString.length
      // the binary representation is 1111011
      assert(IntTransformer.transform(123)(binaryDigits) == 7)
    }
  }
}
