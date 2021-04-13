package com.baeldung.scala.caseobjectsvsenums

import org.scalatest.{FlatSpec, Matchers}

class CurrencyAdtTest extends FlatSpec with Matchers {
  "The Currency ADT" should "be parsed from a string" in {
    import CurrencyADT._

    CurrencyADT.fromIso("EUR") shouldBe Some(EUR)
    CurrencyADT.fromIso("USD") shouldBe Some(USD)
  }

  "The Currency ADT" should "return None for unknown string" in {
    import CurrencyADT._

    CurrencyADT.fromIso("COP") shouldBe None
  }

  "The Currency ADT" should "are type safe and do not clash with other enumerations" in {
    object InternetCodes extends Enumeration {
          type CountryCode = Value
          val EU, DE, CO = Value
       }
       import InternetCodes._
       import CurrencyADT._
       object Methods {
         def method(arg1: CurrencyADT): CurrencyADT = arg1
         def method(arg1: CountryCode): CountryCode = arg1
       }
    Methods.method(EU) shouldBe EU
    Methods.method(EUR) shouldBe EUR
  }
}
