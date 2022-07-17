package com.baeldung.scala.retry

import com.baeldung.scala.retry.PrimeNumberRetry.success
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpec

import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.Future

class PrimeNumberRetryTest extends AsyncWordSpec with Matchers {
  "PrimeNumberRetry" should {

    "FailFast when an NumberFormatException is thrown" in {
      val counter = new AtomicInteger(0)
      val result = PrimeNumberRetry.outerPolicy.apply(Future {
        counter.incrementAndGet()
        throw new NumberFormatException
      })
      result
        .map(_ => assert(false))
        .recover {
          case _: NumberFormatException =>
            assert(counter.get() == 1)
        }
    }

    "retry in any exception" in {
      val counter = new AtomicInteger(0)
      val result = PrimeNumberRetry.outerPolicy.apply(Future {
        counter.incrementAndGet()
        throw new IllegalArgumentException
      })
      result
        .map(_ => assert(false))
        .recover {
          case _: IllegalArgumentException =>
            assert(counter.get() == 6)
        }
    }

    "succeed when a prime is returned" in {
      val counter = new AtomicInteger(0)
      val result = PrimeNumberRetry.outerPolicy.apply(Future {
        counter.incrementAndGet()
        7
      })
      result
        .map(_ => {
          assert(counter.get() == 1)
        })
    }

    "fail when a non prime number is returned" in {
      val counter = new AtomicInteger(0)
      val result = PrimeNumberRetry.outerPolicy.apply(Future {
        counter.incrementAndGet()
        10
      })
      result
        .flatMap(number => {
          assert(counter.get() == 6)
          assert(number == 10)
        })
    }
  }
}
