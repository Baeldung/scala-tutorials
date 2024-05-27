package com.baeldung.scala.utest

import utest._

object RetryTestUnitTest extends TestSuite with TestSuite.Retries {
  override def utestRetryCount: Int = 3
  override def tests: Tests = Tests {
    test("retryable test 1") {
      assert(true)
    }
    test("retryable test 2") {
      assert(true)
    }
  }
}
