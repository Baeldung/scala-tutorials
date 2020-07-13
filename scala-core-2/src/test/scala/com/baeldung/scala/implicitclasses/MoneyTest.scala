package com.baeldung.scala.implicitclasses

import org.scalatest.{Matchers, WordSpec}

class MoneyTest extends WordSpec with Matchers {
  val amount: Double = 30.5

  "MoneySyntax" should {
    import MoneySyntax._
    "create dollars" in {
      amount.dollars shouldBe Money(amount, Currency.USD)
    }
    "create euros" in {
      amount.euros shouldBe Money(amount, Currency.EUR)
    }
    "create pounds" in {
      amount.pounds shouldBe Money(amount, Currency.GBP)
    }
  }
}
