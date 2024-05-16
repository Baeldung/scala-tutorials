package com.baeldung.scala.utest

import utest._

object ExceptionHandlingUnitTest extends TestSuite {
  override def tests: Tests = Tests {
    def funnyMethod: String = throw new RuntimeException("Uh oh...")
    test("Handle an exception") {
      val ex = intercept[RuntimeException] {
        funnyMethod
      }
      assert(ex.getMessage == "Uh oh...")
    }
  }
}
