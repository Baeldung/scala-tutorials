package com.baeldung.scala.scalatest

import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers._

class TestIfCompiles extends AnyFlatSpec {

  "val x: Int = 258" shouldNot compile
  "val x: Int = 2.0" shouldNot typeCheck
  "val x: Int = 2" should compile

}
