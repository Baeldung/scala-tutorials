package com.baeldung.scala.locally

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LocallyBlockUnitTest extends AnyFlatSpec with Matchers {

  it should "create dangling code block without locally" in {
    // intentionally place a new line to create dangling code block
    object ABC

    { val num = 100 }

    "ABC.num" shouldNot compile
  }
}

object Test extends App {
  class MyClass

  lazy val myClass = new MyClass

  {
    println("This should be an independant block")
  }
}
