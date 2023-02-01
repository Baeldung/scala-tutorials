package com.baeldung.scala.caseobjectsvsenums

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CurrencyEnumUnitTest extends AnyFlatSpec with Matchers {
  "Enumerations" should "have a collection with all the values" in {
    import CurrencyEnum._

    for (cur <- CurrencyEnum.values) println(cur)
    CurrencyEnum.values.size shouldBe 2
  }

  "Enumerations" should "parse correctly from a string" in {
    import CurrencyEnum._

    CurrencyEnum.withName("GBP") shouldBe GBP
    CurrencyEnum.withName("EUR") shouldBe EUR
  }

  "Enumerations" should "throw a NoSuchElementException when parsing unknown strings" in {
    import CurrencyEnum._

    assertThrows[NoSuchElementException] {
      CurrencyEnum.withName("COP")
    }
  }

  "Enumerations" should "not compile because of erasure" in {
    object InternetCodes extends Enumeration {
      type CountryCode = Value
      val EU, DE, CO = Value
    }
    import InternetCodes._
    import CurrencyEnum._

    object Methods {
      def method(arg1: Currency): Currency = arg1
      // Uncommenting the following will result on a compiler error
      // def method(arg1: CountryCode): CountryCode = arg1
    }
  }
}
