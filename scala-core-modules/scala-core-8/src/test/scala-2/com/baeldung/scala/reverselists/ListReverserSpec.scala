package com.baeldung.scala.reverselists

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.{Assertion, BeforeAndAfterEach}

import scala.util.Random

class ListReverserSpec extends AnyWordSpec with BeforeAndAfterEach {

  import ListReverser._

  def testReverseSmallList(f: Seq[_] => Seq[_]): Assertion = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val expectedReversedList = List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    val actualReversedList = f(list)
    assertResult(expectedReversedList)(actualReversedList)
  }

  def testReverseBigList(f: Seq[_] => Seq[_]): Assertion = {
    val n = 100_000
    val vector: Vector[String] =
      (0 to n).foldLeft(Vector.empty[String])((v, _) =>
        v.appended(Random.nextString(1000))
      )
    val expectedFirstElement: String = vector.last
    val actualFirstElement: String =
      f(vector).head.asInstanceOf[String]
    assertResult(expectedFirstElement)(actualFirstElement)
  }

  "The naive list reverser" should {
    val reversingFunction: Seq[_] => Seq[_] = naiveRecursiveReverse

    "reverse small lists" in {
      testReverseSmallList(reversingFunction)
    }

    "exhaust memory with big lists" in {
      assertThrows[StackOverflowError](testReverseBigList(reversingFunction))
    }
  }

  "The tail-recursive list reverser" should {
    val reversingFunction: Seq[_] => Seq[_] = tailRecursiveReverse

    "reverse small lists" in {
      testReverseSmallList(reversingFunction)
    }

    "handle big lists" in {
      testReverseBigList(reversingFunction)
    }
  }

  "The folding list reverser" should {
    val reversingFunction: Seq[_] => Seq[_] = foldBasedReverse

    "reverse small lists" in {
      testReverseSmallList(reversingFunction)
    }

    "handle big lists" in {
      testReverseBigList(reversingFunction)
    }
  }
}
