package com.baeldung.scala.cats.show

import cats.implicits._
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class InterfaceSyntaxSpec extends FlatSpec with Matchers {
  "InterfaceSyntax" should "show string of integer" in {
    assert(123.show == "123")
  }

  it should "show string" in {
    assert("abc".show == "abc")
  }
}
