package com.baeldung.scala.abstractclassesvstraits

import org.scalatest.FlatSpec

class ComputationsUnitTest extends FlatSpec {

  "MeaningOfLifeComputation" should "always return a different identifer for each run" in {

    val identifiers = (1 to 10000) map (_ => new MeaningOfLifeComputation().identifier)

    assertResult(10000)(identifiers.toSet.size)

  }

  "TextMeanLengthComputation" should "compute correctly word mean length" in {

    val mean3Words = List("to","the","bone")
    val mean8Words = List("bare","witchcraft","programmin")

    assertResult(3)(new TextMeanLengthComputation("run1",mean3Words).compute)
    assertResult(8)(new TextMeanLengthComputation("run2",mean8Words).compute)

  }
}