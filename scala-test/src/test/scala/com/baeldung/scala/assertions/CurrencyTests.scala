package com.baeldung.scala.assertions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CurrencyTests extends AnyFlatSpec with Matchers {
  import Currency.RichDouble

  "Currency DSL" should "allow adding amounts of the same currency" in {
    val a = 80.0.usd
    val b = 70.0.usd
    (a + b) should be(150.0.usd)
  }

  it should "not compile when trying to add different currencies" in {
    assertDoesNotCompile("50.0.usd + 100.0.eur")
  }

  it should "not compile due to a type error when adding different currencies" in {
    assertTypeError("50.usd + 100.eur")
  }
}
