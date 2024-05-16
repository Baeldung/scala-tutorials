package com.baeldung.scala.scalatest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class IfCompilesUnitTest extends AnyFlatSpec with Matchers {

  "val x: Int = 2" should compile

  // "val x: Int = 248" shouldNot compile
  // "val x: Int = 2.0" shouldNot typeCheck
}
