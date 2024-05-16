package com.baeldung.munit

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import munit.GenericBeforeEach

class MUnitTest extends munit.FunSuite {

  test("test assert") {
    assert(10 + 2 == 12)
  }

  test("test assert with clue") {
    assert(10 + 2 == 12, "incorrect sum")
  }

  test("test assertEquals") {
    val str1 = "a"
    val str2 = "a"
    assertEquals(str1, str2)
  }

  test("test assertNoDiff") {
    val str1 = "a  "
    val str2 = " a   "
    assertNoDiff(str1, str2)
  }

  test("test assertNotEquals") {
    val str1 = "a"
    val str2 = "b"
    assertNotEquals(str1, str2)
  }

  test("verify exception is thrown".ignore) {
    intercept[RuntimeException] {
      badMethod()
    }
  }

  test("verify exception is thrown with correct error message") {
    interceptMessage[RuntimeException]("uh oh...") {
      badMethod()
    }
  }
  def badMethod() = {
    throw new RuntimeException("uh oh...")
  }

  test("a simple munit test") {
    Future {
      println("this is asyc test")
    }
  }

  test("a failing test expected".fail) {
    val expected = "1.0"
    val actual = "1"
    assertEquals(actual, expected)
  }

  override def beforeAll(): Unit = println("before all tests")

  override def beforeEach(context: BeforeEach): Unit = println(
    "before each test"
  )
}

class MacOnlyUnitTest extends munit.FunSuite {
  override def munitIgnore: Boolean = !scala.util.Properties.isWin
  test("This is a mac only test") {
    println("mac only test")
    assert("mac" == "mac")
  }
}
