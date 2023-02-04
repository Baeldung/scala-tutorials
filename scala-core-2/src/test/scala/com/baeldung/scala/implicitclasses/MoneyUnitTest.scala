package com.baeldung.scala.implicitclasses

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
class MoneyUnitTest extends AnyWordSpec with Matchers {
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
