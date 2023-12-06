package com.baeldung.scala.tuples

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TuplesUnitTest extends AnyWordSpec with Matchers {
  val tuple = ("Joe", 34)

  "Tuples" should {
    "accessing values use _.1, _.2 syntax" in {
      tuple._1 shouldBe "Joe"
      tuple._2 shouldBe 34
    }
    "accessing values using pattern matching" in {
      val (name, age) = tuple
      name shouldBe "Joe"
      age shouldBe 34
    }
    // From Scala3 onwards, we can have more than 22 fields in tuple.
    "allow more then 22 elements in Scala 3" in {
      "(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23)" should compile
    }
  }
}
