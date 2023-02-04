package com.baeldung.scala.caseobject

import org.scalatest.flatspec.AnyFlatSpec

class ObjectExampleUnitTest extends AnyFlatSpec {

  "Bicyle" should "be an instance of Serializable" in {
    assert(Bicycle.isInstanceOf[Serializable])
  }

  "Car" should "not be an instance of Serializable" in {
    assert(!Car.isInstanceOf[Serializable])
  }
}
