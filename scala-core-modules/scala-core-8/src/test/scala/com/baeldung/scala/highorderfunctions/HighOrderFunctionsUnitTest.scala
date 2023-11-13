package com.baeldung.scala.highorderfunctions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HighOrderFunctionsUnitTest extends AnyFlatSpec with Matchers {

  "reduceLeft" should "calculate the sum correctly" in {
    val numbers = List(1, 2, 3, 4, 5)
    val actualSum = numbers.reduceLeft(_ + _)
    // op: 1 + 2 = 3
    // op: 3 + 3 = 6
    // op: 6 + 4 = 10
    // op: 10 + 5 = 15
    // res: Int = 15
    assert(15 == actualSum)
  }

  "reduceLeft" should "concatenate strings correctly" in {
    val alphabets = List("A", "B", "C", "D", "E")
    val actualResult = alphabets.reduceLeft(_ + _)
    assert("ABCDE" == actualResult)
  }

  "reduceLeft" should "find the largest number correctly" in {
    val numbers = List(10, 22, 5, 71, 43)
    val actualResult = numbers.reduceLeft(_ max _)
    assert(71 == actualResult)
  }

  "reduceRight" should "calculate the sum correctly" in {
    val numbers = List(1, 2, 3, 4, 5)
    val actualSum = numbers.reduceRight(_ + _)
    // op: 5 + 4 = 9
    // op: 9 + 3 = 12
    // op: 12 + 2 = 14
    // op: 14 + 1 = 15
    // res: Int = 15
    assert(15 == actualSum)
  }

  "foldLeft" should "calculate the sum correctly" in {
    val numbers = List(1, 2, 3, 4, 5)
    val actualSum = numbers.foldLeft(5)(_ + _)
    // op: 5 + 1 = 6
    // op: 6 + 2 = 8
    // op: 8 + 3 = 11
    // op: 11 + 4 = 15
    // op: 15 + 5 = 20
    // res: Int = 20
    assert(20 == actualSum)
  }

  "foldRight" should "concatenate the strings correctly" in {
    val alphabets = List("A", "B", "C", "D", "E")
    val actualResult = alphabets.foldRight("$")(_ + _)
    // op: E + $ = E$
    // op: D + E$ = DE$
    // op: C + DE$ = CDE$
    // op: B + CDE$ = BCDE$
    // op: A + BCDE$ = ABCDE$
    // res: String = ABCDE$
    assert("ABCDE$" == actualResult)
  }

  "scanLeft" should "have correct intermediate states" in {
    val numbers = List(1, 2, 3, 4, 5)
    val actualResult = numbers.scanLeft(1)(_ + _)
    assert(List(1, 2, 4, 7, 11, 16) == actualResult)
  }

  "scanRight" should "have correct intermediate states" in {
    val numbers = List(1, 2, 3, 4, 5)
    val actualResult = numbers.scanRight(1)(_ + _)
    assert(List(16, 15, 13, 10, 6, 1) == actualResult)
  }

  "reduceLeft" should "throw an exception" in {
    val numbers = List.empty[Int]
    assertThrows[UnsupportedOperationException] {
      numbers.reduceLeft(_ max _)
    }
  }

  "foldRight" should "return the initial element i.e $" in {
    val alphabets = List.empty[String]
    val actualResult = alphabets.foldRight("$")(_ + _)
    assert("$" == actualResult)
  }

  "scanRight" should "return the initial element i.e 5" in {
    val numbers = List.empty[Int]
    val actualResult = numbers.scanRight(5)(_ + _)
    assert(List(5) == actualResult)
  }
}
