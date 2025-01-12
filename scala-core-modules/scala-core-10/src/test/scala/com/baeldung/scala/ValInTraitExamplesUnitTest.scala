package com.baeldung.scala

import com.baeldung.scala.valsintraits.ValInTraitExamples
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ValInTraitExamplesUnitTest extends AnyFlatSpec with Matchers {
  "TimePrinterImpl" should "return same time every call" in {
    val printer = new ValInTraitExamples.TimePrinterImpl()
    val first = printer.currentTime
    Thread.sleep(10)
    val second = printer.currentTime

    first.shouldBe(second)
  }

  "TimePrinterWithDefImpl" should "return different time every call" in {
    val printer = new ValInTraitExamples.TimePrinterWithDefImpl()
    val first = printer.currentTime
    Thread.sleep(10)
    val second = printer.currentTime

    first should not be second
  }

  "ClassAB" should "return result as 0" in {
    val classAb = ValInTraitExamples.AB
    classAb.result shouldBe 0
  }

  "ClassBA" should "return result as 70" in {
    val classBa = ValInTraitExamples.BA
    classBa.result shouldBe 70
  }

  "ClassABWithDef" should "return result as 70" in {
    val classAbWithDef = ValInTraitExamples.ABWithDef
    classAbWithDef.result shouldBe 70
  }
}
