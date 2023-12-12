package com.baeldung.scala.assertions

sealed trait Currency
case object USD extends Currency
case object EUR extends Currency

case class CurrencyAmount[C <: Currency](amount: Double, cur: C) {
  def +(other: CurrencyAmount[C]): CurrencyAmount[C] =
    CurrencyAmount(this.amount + other.amount, this.cur)
}

object Currency {
  implicit class RichDouble(value: Double) {
    def usd: CurrencyAmount[USD.type] = CurrencyAmount(value, USD)
    def eur: CurrencyAmount[EUR.type] = CurrencyAmount(value, EUR)
  }
}
