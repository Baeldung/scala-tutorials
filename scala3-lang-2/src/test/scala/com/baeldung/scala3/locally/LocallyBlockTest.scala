package com.baeldung.scala3.locally

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LocallyBlockTest extends AnyFlatSpec with Matchers {

  it should "create dangling code block without locally" in {
    // intentionally place a new line to create dangling code block
    object ABC

    { val num = 100 }

    "ABC.num" shouldNot compile
  }

  it should "accidentally make a code block without newline as part of class body" in {
    class MyClass

    lazy val myClass = new MyClass {
      println("This should be an independant block")
    }

    // it should compile since print method is accidentally considered as part of MyClass
    // "myClass.print" should compile
  }
}

object Test extends App {
  class MyClass

  lazy val myClass = new MyClass

  {
    println("This should be an independant block")
  }
}
