package com.baeldung.scala.caseclasses

import org.junit.Assert.assertEquals
import org.junit.Test

case class CovidCountryStats(
  countryCode: String,
  deaths: Int,
  confirmedCases: Int
)

class CaseClassesUnitTest {
  @Test
  def givenCaseClass_whenPatternMatched_thenReturnsProperValue() = {
    val covidPL = CovidCountryStats("PL", 776, 15366)

    val text = covidPL match {
      case CovidCountryStats("PL", x, y) =>
        "Death rate for Poland is " + x.toFloat / y.toFloat
      case _ => "Unknown country"
    }

    assertEquals("Death rate for Poland is 0.05050111", text)
  }

  @Test
  def givenTwoEqualsCaseClasses_whenCheckingEquality_thenReturnsTrue(): Unit = {
    assert(
      CovidCountryStats("PL", 776, 15366) == CovidCountryStats("PL", 776, 15366)
    )
  }

  @Test
  def givenCaseClass_whenCallingCopy_thenParametersAreCopied(): Unit = {
    val covidPL = CovidCountryStats("PL", 776, 15366)
    val covidUA = covidPL.copy(countryCode = "UA")

    assertEquals("UA", covidUA.countryCode)
    assertEquals(776, covidUA.deaths)
    assertEquals(15366, covidUA.confirmedCases)
  }

  @Test
  def givenTuple_whenCallingApply_thenCreatesNewInstance() = {
    val tuple = ("PL", 776, 15366)
    val covidPL = (CovidCountryStats.apply).tupled(tuple)

    assertEquals(CovidCountryStats("PL", 776, 15366), covidPL)
  }
}
