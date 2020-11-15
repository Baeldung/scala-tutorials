package com.baeldung.scala.typemembersalias
import org.scalatest.{Matchers, FlatSpec}

class RepeatUnitTest extends FlatSpec with Matchers {
  "Classes that extend Repeat" should "be able to do integer repetition" in {
    IntegerRepeat(3, 5) shouldEqual 33333
    IntegerRepeat(10, 3) shouldEqual 101010
  }

  it should "be able to do string repetition" in {
    StringRepeat("Hello", 3) shouldEqual "HelloHelloHello"
    StringRepeat("World", 1) shouldEqual "World"
  }

  it should "be able to do list repetition" in {
    ListRepeat(List("a", "b"), 2) shouldEqual Seq("a", "b", "a", "b")
    ListRepeat(List(1), 3) shouldEqual Seq(1, 1, 1)
  }
}
