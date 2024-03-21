package com.baeldung.scala.powerof2

import org.scalacheck.Gen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class PowerOfTwoUnitTest
  extends AnyFlatSpec
  with Matchers
  with ScalaCheckPropertyChecks {

  private val powerOfTwoFunctions = Seq(
    ("Division", PowerOfTwo.isPowerOfTwoByDivision),
    ("Counting Ones", PowerOfTwo.isPowerOfTwoByCountingOnes),
    ("Bitwise AND", PowerOfTwo.isPowerOfTwoByBitwiseAnd),
    ("LazyList", PowerOfTwo.isPowerOfTwoByLazyList)
  )

  powerOfTwoFunctions.foreach { (desc, fn) =>
    it should s"[$desc] return true for a number that is power of 2" in {
      val powersOfTwo: Gen[Long] =
        Gen.choose(0, 62).map(n => Math.pow(2, n).toLong)
      forAll(powersOfTwo) { num =>
        fn(num) shouldBe true
      }
    }

    it should s"[$desc] return false for a number that is NOT a power of 2" in {
      val powersOfTwo: Gen[Long] =
        Gen.choose(0, 62).map(n => Math.pow(2, n).toLong)
      val notPowerOf2 = Gen
        .choose(0L, Long.MaxValue)
        .suchThat(n => !powersOfTwo.sample.contains(n))
      forAll(notPowerOf2) { num =>
        fn(num) shouldBe false
      }
    }

    it should s"[$desc] return false for any negative numbers" in {
      val negativeNumbers = Gen.choose(Long.MinValue, 0L)
      forAll(negativeNumbers) { num =>
        fn(num) shouldBe false
      }
    }
  }

}
