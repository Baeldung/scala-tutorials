package com.baeldung.scala

import org.junit.Test
import org.scalatest.Matchers

class CurryingUnitTest extends Matchers {

  @Test
  def givenMultipleArgumentsFunction_whenCurried_thenReturnCurriedFunction() {
    val sum: (Int, Int) => Int = (x, y) => x + y
    val curriedSum: Int => Int => Int = sum.curried

    sum(1, 2) shouldBe 3
    curriedSum(1)(2) shouldBe 3
  }

  @Test
  def givenMultipleArgumentsMethod_whenCurried_thenReturnCurriedFunction() {
    def sum(x: Int, y: Int): Int = x + y

    val curriedSum: Int => Int => Int = (sum _).curried

    sum(1, 2) shouldBe 3
    curriedSum(1)(2) shouldBe 3
  }

  @Test
  def givenMultipleArgumentListsMethod_whenCurried_thenReturnCurriedFunction() {
    def sum(x: Int)(y: Int): Int = x + y

    val curriedSum: Int => Int => Int = sum

    sum(1)(2) shouldBe 3
    curriedSum(1)(2) shouldBe 3
  }

  @Test
  def givenCurriedFunction_whenPartialApplied_thenReturnLowerArityFunction() {
    val sum: Int => Int => Int = x => y => x + y
    val increment: Int => Int = sum(1)

    increment(1) shouldBe 2
  }

  @Test
  def givenMultipleArgumentListsMethod_whenPartialApplied_thenReturnLowerArityMethod() {
    def sum(x: Int)(y: Int): Int = x + y

    val increment: Int => Int = sum(1)

    increment(1) shouldBe 2
  }

  @Test
  def givenMultipleArgumentsFindMethod_whenCalled_thenPredicateFunctionNeedsExplicitType() {
    def find[A](xs: List[A], predicate: A => Boolean): Option[A] = {
      xs match {
        case Nil => None
        case head :: tail =>
          if (predicate(head)) Some(head) else find(tail, predicate)
      }
    }

    find(List(1, 2, 3), (x: Int) => x % 2 == 0) shouldBe Some(2)
  }

  @Test
  def givenMultipleArgumentListFindMethod_whenCalled_thenPredicateFunctionDoesNotNeedExplicitType() {
    def find[A](xs: List[A])(predicate: A => Boolean): Option[A] = {
      xs match {
        case Nil => None
        case head :: tail =>
          if (predicate(head)) Some(head) else find(tail)(predicate)
      }
    }

    find(List(1, 2, 3))(x => x % 2 == 0) shouldBe Some(2)
  }

  @Test
  def givenGenericMultipleArgumentListSumMethod_whenPartialApplied_thenReturnSimpleMethods() {
    def sumF(f: Int => Int)(x: Int, y: Int): Int = f(x) + f(y)
    val sum: (Int, Int) => Int = sumF(identity)
    val sumSquare: (Int, Int) => Int = sumF(x => x * x)
    val increment: Int => Int = sum.curried(1)
    val decrement: Int => Int = sum.curried(-1)

    sum(1, 2) shouldBe 3
    sumSquare(1, 2) shouldBe 5
    increment(2) shouldBe 3
    decrement(2) shouldBe 1
  }

  @Test
  def givenMultipleArgumentsFunction_whenUseItAsOneArgumentFunction_thenNeedsExplicitArgumentPassing() {
    val sum: (Int, Int) => Int = (x, y) => x + y
    val numbers: List[Int] = List(1, 2, 3)

    numbers.map(n => sum(1, n)) shouldBe List(2, 3, 4)
  }

  @Test
  def givenCurriedFunction_whenUseItAsOneArgumentFunction_thenDoesNotNeedExplicitArgumentPassing() {
    val curriedSum: Int => Int => Int = x => y => x + y
    val numbers: List[Int] = List(1, 2, 3)

    numbers.map(curriedSum(1)) shouldBe List(2, 3, 4)
  }

}
