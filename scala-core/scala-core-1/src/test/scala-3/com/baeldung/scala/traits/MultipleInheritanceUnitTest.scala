package com.baeldung.scala.traits

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MultipleInheritanceUnitTest extends AnyFlatSpec with Matchers {

  "A class extended from multiple traits" should "have access to all the methods" in {
    val instance = new MultipleInheritance()

    instance.method1() shouldEqual "Trait1 method"
    instance.method2() shouldEqual "Trait2 method"
  }
}
