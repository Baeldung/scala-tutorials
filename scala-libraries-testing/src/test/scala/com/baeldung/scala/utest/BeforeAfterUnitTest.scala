package com.baeldung.scala.utest

import utest._

object BeforeAfterUnitTest extends TestSuite {
  println("This is executed before the tests")
  override def utestAfterAll() = {
    println("This method will be executed after all the tests")
  }
  override def tests: Tests = Tests {
    test("simple test") {
      assert(1 == 1)
    }
    test("simple test 2") {
      assert(2 == 2)
    }
  }
}
