package com.baeldung.scalawithmaven

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HelloWorldSpec extends AnyFlatSpec with Matchers {
  
  "The HelloWorld object" should "say hello" in {
    val greeting = HelloWorld.message
    greeting shouldEqual "Hello, World!"
  }
}