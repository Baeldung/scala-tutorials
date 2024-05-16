package com.baeldung.scala.utest

import utest._

object NestedUnitTest extends TestSuite {
  override def tests: Tests = Tests {
    test("outer test") - {
      val list = List(1, 2)
      println("This is an outer level of the test.")
      test("inner test 1") - {
        val list2 = List(10, 20)
        list.zip(list2) ==> List((1, 10), (2, 20))
      }
      test("inner test 2") - {
        val str = List("a", "b")
        list.zip(str) ==> List((1, "a"), (2, "b"))
      }
    }
    test("outer test 2") - {
      println("there is no nesting level here")
      assert(true)
    }
  }
}
