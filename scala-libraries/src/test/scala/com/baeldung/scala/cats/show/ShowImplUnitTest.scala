package com.baeldung.scala.cats.show

import cats.Show
import cats.implicits._
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class ShowImplUnitTest extends FlatSpec with Matchers {
  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  "ShowImpl" should "show string of integer" in {
    assert(showInt.show(123) == "123")
  }

  it should "show string" in {
    assert(showString.show("abc") == "abc")
  }
}
