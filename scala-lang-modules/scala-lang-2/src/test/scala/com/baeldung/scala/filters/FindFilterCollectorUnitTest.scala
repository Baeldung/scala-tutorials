package com.baeldung.scala.filters

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.SortedSet

class FindFilterCollectorUnitTest extends AnyWordSpec with Matchers {
  "find method" should {
    "select one element from a collection using find method" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      numbers.find(_ % 2 == 0) shouldBe Some(2)
    }

    "return None if no value matches with the predicate" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      numbers.find(_ > 9) shouldBe None
    }

    "find the first match based on the ordering of the collection type" in {
      val numbers = SortedSet(9, 8, 6, 2, 4)
      numbers.find(_ % 2 == 0) shouldBe Some(2)
    }

    "find the last match on linear collection (not available on sets)" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      numbers.findLast(_ % 2 == 0) shouldBe Some(6)
    }
  }

  "filter method" should {
    "return all elements matching the condition" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val oddNumbers = numbers.filter(_ % 2 == 1)
      oddNumbers shouldBe List(1, 3, 5)
    }

    "return empty collection if nothing matches" in {
      val numbers = List(2, 4, 6, 8)
      val oddNumbers = numbers.filter(_ % 2 == 1)
      oddNumbers shouldBe empty
    }

    "use a complex predicate" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val oddAndNotMulOf3or5 =
        numbers.filter(n => (n % 2 == 1 && n % 3 != 0 && n % 5 != 0))
      oddAndNotMulOf3or5 shouldBe List(1)
    }

    "use a function as predicate" in {
      def isOddAndNotMulOf3or5(n: Int) =
        n % 2 == 1 && n % 3 != 0 && n % 5 != 0
      val numbers = List(1, 2, 3, 4, 5, 6)
      val oddAndNotMulOf3or5 =
        numbers.filter(isOddAndNotMulOf3or5)
      oddAndNotMulOf3or5 shouldBe List(1)
    }
  }

  "filterNot method" should {
    "apply the negate of the predicate" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val nonOdd = numbers.filterNot(_ % 2 == 1)
      val even = numbers.filter(_ % 2 != 1)
      nonOdd shouldBe even
    }
  }

  "collect method" should {
    "collect the elements matching the condition" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val even = numbers.collect {
        case n if n % 2 == 0 => n
      }
      even shouldBe List(2, 4, 6)
    }

    "collect the elements matching the condition and square it" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val evenSquared = numbers.collect {
        case n if n % 2 == 0 => n * n
      }
      evenSquared shouldBe List(4, 16, 36)
    }

    "collect and filter map be equivalent" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val evenSquared = numbers.filter(_ % 2 == 0).map(n => n * n)
      evenSquared shouldBe List(4, 16, 36)
    }

    "collectFirst" should {
      "return the first element matching the partial function" in {
        val numbers = List(1, 2, 3, 4, 5, 6)
        val firstEven = numbers.collectFirst {
          case n if n % 2 == 0 => n
        }
        firstEven shouldBe Some(2)
      }

      "return the first element matching the partial function in the same order of collection" in {
        val numbers = SortedSet(6, 5, 4, 3, 2, 1)
        val firstEven = numbers.collectFirst {
          case n if n % 2 == 0 => n
        }
        firstEven shouldBe Some(2)
      }
    }
  }

}
