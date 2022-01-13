package com.baeldung.scalacheck

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object GeneratorsUnitTest extends Properties("Generators") {

  // random numbers from -10 to 10
  private val choiceGen = Gen.choose(-10, 10)
  property("choiceGen") = forAll(choiceGen) { num => num.abs <= 10 }

  // randomly pick 2 elements from Seq(1, 2, 3, 4, 5)
  property("randomPick") = forAll(Gen.pick(2, Seq(1, 2, 3, 4, 5))) { seq => seq.sum > 0 }

  // randomly pick one element from Seq(1, 2, -10, 40)
  private val oneOfGen = Gen.oneOf(Seq(1, 2, -10, 40))
  property("oneOf") = forAll(oneOfGen) { num => num.abs >= 0 }

  // randomly generated sequences with one element from each generator
  property("sequence") = forAll(Gen.sequence(Seq(choiceGen, oneOfGen))) { foo => foo.size >= 0 }

  private val freqGen = Gen.frequency((2, "apples"), (4, "bananas"), (6, "kiwis"))
  property("frequency") = forAll(freqGen) { _ => true }


}
