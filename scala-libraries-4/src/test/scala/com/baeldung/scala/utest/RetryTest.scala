package com.baeldung.scala.utest

import utest._

import scala.util.Random

object RetryTest extends TestSuite {
  override def tests: Tests = Tests {
    def flakyMethod: Int =
      Random.nextInt(2) // change this value to test failure
    test("retry flaky test") - retry(3) {
      val value = flakyMethod
      assert(value < 2)
    }
  }
}
