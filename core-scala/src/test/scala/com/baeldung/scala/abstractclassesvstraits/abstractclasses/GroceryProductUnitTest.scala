package com.baeldung.scala.abstractclassesvstraits.abstractclasses

import org.scalatest.{Matchers, WordSpec}

class GroceryProductUnitTest extends WordSpec with Matchers {
  val orange = new Orange("someSku", 100.0)

  "GroceryProduct" should {
    "calculate tax fee" in {
      orange.calculateTaxFee() shouldBe 23.0
    }
  }
  "Class" should {
    "not extends from multiple abstract classes" in {
      """class Orange(val sku: String, val price: Double, val tax: Double)
        |      extends TaxCalculator
        |      with Product""".stripMargin shouldNot compile
    }
  }
}
