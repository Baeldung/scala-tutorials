package com.baeldung.scala.richwrappers

import org.scalatest.{Matchers, WordSpec}

class RichWrappersUnitTest extends WordSpec with Matchers {

  "RichInt" should {
    "format a number in base 2" in {
      assert(3.toBinaryString == "11")
    }

    "format a number in base 16" in {
      assert(10.toHexString == "a")
    }
  }

  "StringOps" should {
    "revert a string" in {
      assert("test".reverse == "tset")
    }

    "select a char as if it was an array" in {
      assert("test" (1) == 'e')
    }
  }

  "ArrayOps" should {
    "let us add elements to an array" in {
      assert(Array(1, 2, 3).:+(4).:+(5).sameElements(Array(1, 2, 3, 4, 5)))
    }

    "slice an array" in {
      assert(Array(1, 2, 3).slice(1, 2).sameElements(Array(2)))
    }
  }

}
