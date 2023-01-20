package com.baeldung.enumeratum

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class EnumeratumUnitTest extends AnyWordSpec with Matchers {
  "Enumeratum" should {

    "create and parse Country enums" in {
      val countries = Country.values
      assert(countries.size == 2)
      assert(countries.equals(Seq(Country.Germany, Country.India)))

      val india = Country.withName("India")
      assert(india == Country.India)
    }

    "allow utility methods" in {
      val usa: Option[Country] = Country.withNameOption("USA")
      assert(usa.isEmpty)
      val germanyCaps = Country.withNameInsensitive("GERMANY")
      assert(germanyCaps == Country.Germany)
      val indiaMixed = Country.withNameInsensitive("iNDia")
      assert(indiaMixed == Country.India)
      val usaInsensitive = Country.withNameInsensitiveOption("uSA")
      assert(usaInsensitive.isEmpty)
      val germanyIndex = Country.indexOf(Country.Germany)
      assert(germanyIndex == 0)
    }

    "create and access enum using custom name" in {
      val male = Gender.withName("M")
      assert(male == Gender.Male)
      val maleOpt = Gender.withNameOption("Male")
      assert(maleOpt.isEmpty)
    }

    "support UpperWords Utility trait" in {
      val continents = Continent.values
      assert(continents.size == 7)
      val asiaCap = Continent.withName("ASIA")
      assert(asiaCap == Continent.Asia)
      intercept[NoSuchElementException] {
        val asia = Continent.withName("Asia")
      }
    }

    "support mix of utility traits" in {
      val conventions = NamingConvention.values
      val java = NamingConvention.withName("javaStyle")
      assert(java == NamingConvention.JavaStyle)
      val scala = NamingConvention.withName("scalaStyle")
      assert(scala == NamingConvention.ScalaStyle)
      val python = NamingConvention.withName("python_style")
      assert(python == NamingConvention.PythonStyle)
    }

    "support non string entry type" in {
      val bad = HttpCode.withValue(400)
      assert(bad == HttpCode.BadRequest)
      assert(bad.name == "Bad Request")
    }

  }
}
