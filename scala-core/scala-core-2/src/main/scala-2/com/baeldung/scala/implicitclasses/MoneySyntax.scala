package com.baeldung.scala.implicitclasses

object MoneySyntax {
  implicit class RichMoney(val amount: Double) extends AnyVal {
    def euros: Money = Money(amount, Currency.EUR)
    def dollars: Money = Money(amount, Currency.USD)
    def pounds: Money = Money(amount, Currency.GBP)
  }
}
