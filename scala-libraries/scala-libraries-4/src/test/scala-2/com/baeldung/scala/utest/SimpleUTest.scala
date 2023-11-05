package com.baeldung.scala.utest

import utest._

object SimpleUTest extends TestSuite {
  override def tests: Tests = Tests {
    test("str") {
      val name = "Baeldung"
      assert(name.length == 8)
    }
    test("arrow assert") {
      val name = "Baeldung"
      name.length ==> 8
    }
  }
}
