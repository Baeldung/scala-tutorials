package com.baeldung.scala.caseobjectsvsenums

sealed abstract class CurrencyADT(val name: String, val iso: String)

object CurrencyADT {
  case object EUR extends CurrencyADT("Euro", "EUR")
  case object USD extends CurrencyADT("United States Dollar", "USD")

  val values: Seq[CurrencyADT] = Seq(EUR, USD)

  val isoToCurrency: Map[String, CurrencyADT] =
    values.map(c => c.iso -> c).toMap

  def fromIso(iso: String): Option[CurrencyADT] =
    isoToCurrency.get(iso.toUpperCase)

}
