package com.baeldung.scala.withfilter

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.{Matchers, WordSpec}

import scala.collection.generic.FilterMonadic

class WithFilterVsFilterUnitTest extends WordSpec with Matchers {

  "Filter" should {
    "evaluated collection eagerly" in {
      val counter = new AtomicInteger(0)
      val isEven: Int => Boolean = n => {
        counter.getAndIncrement()
        n % 2 == 0
      }
      val numbers: List[Int] = List(1, 2, 3, 4)

      val evenNumbers: List[Int] = numbers.filter(isEven)

      evenNumbers shouldBe List(2, 4)
      counter.get() shouldBe 4
    }
  }

  "WithFilter" should {
    "evaluated collection lazily" in {
      val counter = new AtomicInteger(0)
      val isEven: Int => Boolean = n => {
        counter.getAndIncrement()
        n % 2 == 0
      }
      val numbers: List[Int] = List(1, 2, 3, 4)

      val evenNumbersFilter: FilterMonadic[Int, List[Int]] =
        numbers.withFilter(isEven)
      counter.get() shouldBe 0

      val evenNumbers: List[Int] = evenNumbersFilter.map(n => n)
      evenNumbers shouldBe List(2, 4)
      counter.get() shouldBe 4
    }
  }

}
