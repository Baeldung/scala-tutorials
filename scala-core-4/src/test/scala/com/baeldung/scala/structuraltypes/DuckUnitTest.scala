package com.baeldung.scala.structuraltypes

import org.scalatest.{FlatSpec, Matchers}

class DuckUnitTest extends FlatSpec with Matchers {
  type Flyer = { def fly(): Unit }
  def callFly(thing: Flyer): Unit = thing.fly()

  def callFly2(thing: { def fly(): Unit }): Unit = thing.fly()

  def callFly3[T <: { def fly(): Unit }](thing: T): Unit = thing.fly()

  "Ducks" should "fly together" in {
    callFly(new Duck())
    callFly2(new Duck())
    callFly3(new Duck())
  }

  "Eagles" should "soar above all" in {
    callFly(new Eagle())
    callFly2(new Eagle())
    callFly3(new Eagle())
  }

  "Walrus" should "not fly" in {
    // The following code won't compile
//    callFly(new Walrus())
//    callFly2(new Walrus())
//    callFly3(new Walrus())
  }
}
