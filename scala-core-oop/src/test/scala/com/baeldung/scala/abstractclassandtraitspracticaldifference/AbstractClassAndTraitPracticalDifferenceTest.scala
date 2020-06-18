package com.baeldung.scala.abstractclassandtraitspracticaldifference

import com.baeldung.scala.abstractclassandtraitspracticaldifference.AbstractClassAndTraitPracticalDifference._
import org.scalatest.{FlatSpec, Matchers}

class AbstractClassAndTraitPracticalDifferenceTest extends FlatSpec with Matchers {
  "A Audi" should "have a model equal to the passed value" in {
    val audi = Audi("A6")
    audi.model should be("A6")
    audi.modelName() should be(s"Model is ${audi.model}")
  }
  "A Vehicle" should "have a model equal to the passed value" in {
    val vehicle = Vehicle("A9")
    vehicle.model should be("A9")
    vehicle.modelName() should be(s"Model is ${vehicle.model}")
  }

}
