package com.baeldung.scala.abstractclassandtraitspracticaldifference

import com.baeldung.scala.abstractclassandtraitspracticaldifference.AbstractClassAndTraitPracticalDifference._
import org.scalatest.{FlatSpec, Matchers}

class AbstractClassAndTraitPracticalDifferenceTest extends FlatSpec with Matchers {

  "A Audi" should "have a model equal to the passed value" in {
    val audi = new Audi("A6")
    audi.model should be("A6")
  }

}
