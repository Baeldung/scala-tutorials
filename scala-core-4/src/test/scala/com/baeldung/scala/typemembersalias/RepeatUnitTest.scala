package com.baeldung.scala.typemembersalias
import org.scalatest.FunSuite

class RepeatUnitTest extends FunSuite {
  test("Integer Repetition") {
    assert(IntegerRepeat(3, 5) == 33333)
    assert(IntegerRepeat(10, 3) == 101010)
  }

  test("String Repetition") {
    assert(StringRepeat("Hello", 3) == "HelloHelloHello")
    assert(StringRepeat("World", 1) == "World")
  }

  test("List Repetition") {
    assert(ListRepeat(List("a", "b"), 2) == Seq("a", "b", "a", "b"))
    assert(ListRepeat(List(1), 3) == Seq(1, 1, 1))
  }
}
