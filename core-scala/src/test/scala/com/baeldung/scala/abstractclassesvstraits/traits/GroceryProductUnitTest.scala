package com.baeldung.scala.abstractclassesvstraits.traits

import org.scalatest.{Matchers, WordSpec}

class GroceryProductUnitTest extends WordSpec with Matchers {
  val orange = new GroceryProduct.Orange("someSku", 100.0, 0.23)

  "GroceryProduct" should {
    "calculate tax fee" in {
      orange.calculateTaxFee() shouldBe 23.0
    }
  }
  "Trait" should {
    "not have constructor" in {
      """trait Orange(sku: String) {}""" shouldNot compile
    }
  }
}
