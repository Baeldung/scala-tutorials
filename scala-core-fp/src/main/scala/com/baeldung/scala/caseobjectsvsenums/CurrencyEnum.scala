package com.baeldung.scala.caseobjectsvsenums

object CurrencyEnum extends Enumeration {
  type Currency = Value

  val GBP = Value(1, "GBP")
  val EUR = Value
}
