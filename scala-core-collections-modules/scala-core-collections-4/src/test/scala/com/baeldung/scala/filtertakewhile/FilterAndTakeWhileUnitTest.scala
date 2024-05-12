package com.baeldung.scala.filtertakewhile

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{LocalDate, LocalDateTime}
import scala.concurrent.{Await, Future, TimeoutException}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

class FilterAndTakeWhileUnitTest extends AnyWordSpec with Matchers {

  "filter" should {
    "filter all elements matching the condition" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val oddNumbers = numbers.filter(_ % 2 != 0)
      oddNumbers shouldBe List(1, 3, 5)
    }

    "not complete the execution due to infinite collection" ignore {
      val infiniteNumbers = LazyList.from(1)
      val lessThan100 =
        infiniteNumbers.filter(_ < 100).toList // never completes
    }
  }

  "takeWhile" should {
    "select elements until the predicate fails" in {
      val numbers = List(1, 2, 3, 4, 5, 6)
      val numbersBeforeFirstEven = numbers.takeWhile(_ % 2 != 0)
      numbersBeforeFirstEven shouldBe List(1)
    }

    "successfully get result from an infinite collection" in {
      val infiniteNumbers = LazyList.from(1)
      val first100 = infiniteNumbers.takeWhile(_ < 100).toList
      first100 shouldBe (1 to 99).toList
    }
  }
}
