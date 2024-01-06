package com.baeldung.scala.primecheck

import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec
import PrimeNumber.*

class PrimeCheckUnitTest
  extends AnyWordSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val primeTestTable = Table(
    ("number", "status"),
    (0L, false),
    (1L, false),
    (2L, true),
    (3L, true),
    (-5L, false),
    (2L, true),
    (13L, true),
    (10L, false),
    (4871L, true),
    (4873L, false),
    (Int.MaxValue.toLong, true),
    (10_000_099_999L, true)
  )

  "Prime number checker" should {
    "check if the provided bigint is prime" in {
      forAll(primeTestTable) { (num, status) =>
        isPrimeUsingBigInt(num) shouldBe status
      }
    }

    "check if a number is prime using custom method" in {
      forAll(primeTestTable) { (num, status) =>
        isPrime(num) shouldBe status
      }
    }

    "check if a number is prime using custom optimized method" in {
      forAll(primeTestTable) { (num, status) =>
        isPrimeOptimized(num) shouldBe status
      }
    }
  }

}
