package com.baeldung.scala.durationsugar

import org.scalatest._

import scala.concurrent.duration.{FiniteDuration, SECONDS}

class DurationSugarUnitTests extends FlatSpec with Matchers {

  "20.seconds" should "equal the object created with the native scala sugar" in {
    20.seconds shouldBe new FiniteDuration(20, SECONDS)
  }

  "20.seconds ++ 30.seconds" should "be equal to 50.seconds" in {
    20.seconds ++ 30.seconds shouldBe 50.seconds
  }

  "20.seconds ++ 1.minutes" should "be equal to 80.seconds" in {
    20.seconds ++ 1.minutes shouldBe 80.seconds
  }

  // This will not compile because of the missing dot
  /*
  "20.seconds + 1.minutes" should "be equal to 80.seconds" in {
    20 seconds + 20 minutes shouldBe 80.seconds
  }
  */
}
